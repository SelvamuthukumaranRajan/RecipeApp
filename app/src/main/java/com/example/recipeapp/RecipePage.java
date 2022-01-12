package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipePage extends AppCompatActivity {

    private Map<String, Integer> recipeImage = new HashMap<>();
    private ArrayList<HashMap<String, String>> ingredientsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        getSupportActionBar().hide();

        recipeImage.put("R.drawable.grill", R.drawable.grill);
        recipeImage.put("R.drawable.jamun", R.drawable.jamun);
        recipeImage.put("R.drawable.pasta", R.drawable.pasta);
        recipeImage.put("R.drawable.meat", R.drawable.meat);
        recipeImage.put("R.drawable.drumstick", R.drawable.drumstick);

        HashMap<String,String> map = new HashMap<String, String>();
        map.put("name", "Egg" );
        map.put("weight", "2 Pcs");
        ingredientsList.add(map);
        map = new HashMap<String, String>();
        map.put("name", "Potato" );
        map.put("weight", "1/2 Kg");
        ingredientsList.add(map);
        map = new HashMap<String, String>();
        map.put("name", "Onion" );
        map.put("weight", "1/4 Kg");
        ingredientsList.add(map);
        map = new HashMap<String, String>();
        map.put("name", "Ghee" );
        map.put("weight", "4 Tbsp");
        ingredientsList.add(map);
        map = new HashMap<String, String>();
        map.put("name", "Chilli Sauce" );
        map.put("weight", "2 Tbsp");
        ingredientsList.add(map);
        map = new HashMap<String, String>();
        map.put("name", "Tomato Sauce" );
        map.put("weight", "4 Tbsp");
        ingredientsList.add(map);

        ImageView back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipePage.this,Home.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Map<String, String> recipeDetails = (HashMap<String, String>) intent.getSerializableExtra("Recipe");

        TextView line1 = findViewById(R.id.line1);
        TextView line2 = findViewById(R.id.line2);
        TextView others = findViewById(R.id.others);

        line1.setText(recipeDetails.get("line1"));
        line2.setText(recipeDetails.get("line2"));
        others.setText(String.format("%s Mins | %s Serving", recipeDetails.get("time"), recipeDetails.get("serving")));

        LinearLayout layout = findViewById(R.id.layout_recipeImg);
        layout.setBackgroundResource(recipeImage.get(recipeDetails.get("image")));

        RecyclerView ingredients = findViewById(R.id.rv_ingredients);
        ingredients.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        ingredients.setItemAnimator(new DefaultItemAnimator());
        IngredientsAdapter cla = new IngredientsAdapter(RecipePage.this, ingredientsList);
        ingredients.setAdapter(cla);
    }
}