package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.viewHolder> {

    private Context context;
    private ArrayList<HashMap<String, String>> recipe;
    private Map<String,Integer> recipeImage;

    public CategoryListAdapter(Context context, ArrayList<HashMap<String, String>> recipe,Map<String,Integer> recipeImage) {
        this.context = context;
        this.recipe = recipe;
        this.recipeImage = recipeImage;
    }

    @Override
    public CategoryListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.viewHolder viewHolder, int position) {
        viewHolder.icon.setImageResource(recipeImage.get(recipe.get(position).get("image")));
        viewHolder.disc.setText(String.format("%s %s", recipe.get(position).get("line1"), recipe.get(position).get("line2")));
        viewHolder.others.setText(String.format("%s Mins | %s Serving", recipe.get(position).get("time"), recipe.get(position).get("serving")));
    }

    @Override
    public int getItemCount() {
        return recipe.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView disc;
        private TextView others;

        public viewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            disc = (TextView) itemView.findViewById(R.id.discription);
            others = (TextView) itemView.findViewById(R.id.others);
        }
    }

}
