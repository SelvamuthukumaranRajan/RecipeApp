package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageSourceAdapter extends RecyclerView.Adapter<ImageSourceAdapter.viewHolder>{

    private Context context;
    private List<Integer> image;
    private List<String> type;
    private List<String> disc;
    private List<String> other;

    public ImageSourceAdapter(Context context, List<Integer> image, List<String> type, List<String> disc, List<String> other) {
        this.context = context;
        this.image = image;
        this.type = type;
        this.disc = disc;
        this.other = other;
    }

    @Override
    public ImageSourceAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_imagesource, parent, false);
        return new ImageSourceAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.types.setText(type.get(position));
        holder.disc.setText(disc.get(position));
        holder.others.setText(other.get(position));
        holder.layoutDish.setBackgroundResource(image.get(position));
    }

    @Override
    public int getItemCount() {
        return type.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView types;
        private TextView disc;
        private TextView others;
        private LinearLayout layoutDish;

        public viewHolder(View itemView) {
            super(itemView);
            layoutDish = (LinearLayout) itemView.findViewById(R.id.layoutDish);
            types = (TextView) itemView.findViewById(R.id.type);
            disc = (TextView) itemView.findViewById(R.id.discription);
            others = (TextView) itemView.findViewById(R.id.others);
        }
    }
}
