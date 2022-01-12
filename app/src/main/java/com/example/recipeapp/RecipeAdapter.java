package com.example.recipeapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.viewHolder>{

    private Context context;
    private ArrayList<HashMap<String, String>> recipe;
    private Map<String,Integer> recipeImage;

    public RecipeAdapter(Context context, ArrayList<HashMap<String, String>> recipe,Map<String,Integer> recipeImage) {
        this.context = context;
        this.recipe = recipe;
        this.recipeImage = recipeImage;
    }

    @Override
    public RecipeAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_recipe, parent, false);
        return new RecipeAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.types.setText(recipe.get(position).get("type"));
        holder.line1.setText(recipe.get(position).get("line1"));
        holder.line2.setText(recipe.get(position).get("line2"));
        holder.others.setText(String.format("%s Mins | %s Serving", recipe.get(position).get("time"), recipe.get(position).get("serving")));
        holder.layoutDish.setBackgroundResource(recipeImage.get(recipe.get(position).get("image")));
    }

    @Override
    public int getItemCount() {
        return recipe.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView types;
        private TextView line1;
        private TextView line2;
        private TextView others;
        private LinearLayout layoutDish;

        public viewHolder(View itemView) {
            super(itemView);
            layoutDish = (LinearLayout) itemView.findViewById(R.id.layoutDish);
            types = (TextView) itemView.findViewById(R.id.type);
            line1 = (TextView) itemView.findViewById(R.id.line1);
            line2 = (TextView) itemView.findViewById(R.id.line2);
            others = (TextView) itemView.findViewById(R.id.others);
        }
    }
}
