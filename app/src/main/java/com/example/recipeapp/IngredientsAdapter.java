package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.viewHolder> {

    private Context context;
    private ArrayList<HashMap<String, String>> ingredients;

    public IngredientsAdapter(Context context, ArrayList<HashMap<String, String>> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public IngredientsAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_ingredients, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsAdapter.viewHolder viewHolder, int position) {
        viewHolder.disc.setText(ingredients.get(position).get("name"));
        viewHolder.others.setText(ingredients.get(position).get("weight"));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView disc;
        private TextView others;

        public viewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            disc = (TextView) itemView.findViewById(R.id.name);
            others = (TextView) itemView.findViewById(R.id.weight);
        }
    }
}
