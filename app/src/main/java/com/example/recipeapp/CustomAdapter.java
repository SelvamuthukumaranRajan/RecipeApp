package com.example.recipeapp;

import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

        private Context context;
        private ArrayList<itemModel> arrayList;
        private String[] type;
        private String[] dis;
        private String[] other;

        public CustomAdapter(Context context, ArrayList<itemModel> arrayList,String[] type,String[] dis,String[] other) {
            this.context = context;
            this.arrayList = arrayList;
            this.type = type;
            this.dis = dis;
            this.other = other;
        }

        @Override
        public  CustomAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.image_source, viewGroup, false);
            return new viewHolder(view);
        }
        @Override
        public  void onBindViewHolder(CustomAdapter.viewHolder viewHolder,int position) {
            viewHolder.icon.setImageResource(arrayList.get(position).getImage());
            viewHolder.types.setText(type[position]);
            viewHolder.disc.setText(dis[position]);
            viewHolder.others.setText(other[position]);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class viewHolder extends RecyclerView.ViewHolder {
            private ImageView icon;
            private TextView types;
            private TextView disc;
            private TextView others;

            public viewHolder(View itemView) {
                super(itemView);
                icon = (ImageView) itemView.findViewById(R.id.icon);
                types = (TextView) itemView.findViewById(R.id.type);
                disc = (TextView) itemView.findViewById(R.id.discription);
                others = (TextView) itemView.findViewById(R.id.others);
            }
        }

    }
