package com.example.recipeapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    private Context context;
    private ArrayList<String> categories;
    int row_index;

    public CategoryAdapter(Context context, ArrayList<String> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories, parent, false);
        return new CategoryAdapter.viewHolder(view);
    }


    @Override
    public void onBindViewHolder(CategoryAdapter.viewHolder holder, int position) {
        holder.text.setText(categories.get(position));

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if (row_index == position) {
            holder.text.setTextColor(Color.parseColor("#FFFFFFFF"));
            holder.text.setBackgroundColor(Color.parseColor("#3ECB98"));
        } else {
            holder.text.setBackgroundColor(Color.parseColor("#4B02A3A2"));
            holder.text.setTextColor(Color.parseColor("#3ECB98"));
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public viewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
