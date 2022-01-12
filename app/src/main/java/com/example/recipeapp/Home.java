package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ArrayList<itemModel> arrayList;
    RecyclerView recyclerView;
    String type[] = {"Starter", "Dessert", "Pasta", "Local", "Gravy"};
    int icons[] = {R.drawable.grill, R.drawable.jamun, R.drawable.pasta, R.drawable.meat, R.drawable.drumstick};
    String dis[] = {"Full Grill With Eggless Mayonnaise", "Paneer Jamun With Milk Rabdi", "Spaghetti With Shrimp Sauce",
            "Meat Satay With Brown Sauce", "Drumstick Sambar With Onion Rings"};
    String others[] = {"45 min | 4 Serving", "60 min | 6 Serving", "30 min | 1 Serving", "40 min | 2 Serving", "25 min | 4 Serving"};

    private List<Integer> image;
    private List<String> types;
    private List<String> disc;
    private List<String> other;

    private ArrayList<HashMap<String, String>> recipeDetails = new ArrayList<>();
    private Map<String, Integer> recipeImage = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        arrayList = new ArrayList<>();
        /*recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < icons.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setImage(icons[i]);
            arrayList.add(itemModel);
        }
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),arrayList,type,dis,others);

        image = new ArrayList<>();
        image.add(R.drawable.grill);
        image.add(R.drawable.jamun);
        image.add(R.drawable.pasta);
        image.add(R.drawable.meat);
        image.add(R.drawable.drumstick);

        types = new ArrayList<>();
        types = Arrays.asList(type);
        disc = new ArrayList<>();
        disc = Arrays.asList(dis);
        other = new ArrayList<>();
        other = Arrays.asList(others);*/

        //ArrayList Hashmap
        getRecipeData();

        recipeImage.put("R.drawable.grill", R.drawable.grill);
        recipeImage.put("R.drawable.jamun", R.drawable.jamun);
        recipeImage.put("R.drawable.pasta", R.drawable.pasta);
        recipeImage.put("R.drawable.meat", R.drawable.meat);
        recipeImage.put("R.drawable.drumstick", R.drawable.drumstick);

        //Recipe View
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        /*ImageSourceAdapter adapter = new ImageSourceAdapter(Home.this,image,types,disc,other);*/
        RecipeAdapter adapter = new RecipeAdapter(Home.this, recipeDetails, recipeImage);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(Home.this,RecipePage.class);
                intent.putExtra("Recipe", recipeDetails.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //Category wise Recipe View
        RecyclerView categoryList = (RecyclerView) findViewById(R.id.categories_List);
        categoryList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        categoryList.setItemAnimator(new DefaultItemAnimator());
        ArrayList<HashMap<String, String>> categoryOne = new ArrayList<>();
        categoryOne.add(recipeDetails.get(0));
        CategoryListAdapter cla = new CategoryListAdapter(getApplicationContext(), categoryOne, recipeImage);
        categoryList.setAdapter(cla);


        //Category View
        recyclerView = (RecyclerView) findViewById(R.id.categories_Label);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /*CategoryAdapter ca = new CategoryAdapter(getApplicationContext(),type);
        recyclerView.setAdapter(ca);*/
        CategoryAdapter ca = new CategoryAdapter(getApplicationContext(), getData("type"));
        recyclerView.setAdapter(ca);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String category = recipeDetails.get(position).get("type");
                ArrayList<HashMap<String, String>> categoryDetails = new ArrayList<>();

                for (HashMap<String, String> recipeDetail : recipeDetails) {
                    if (category.contentEquals(recipeDetail.get("type"))) {
                        categoryDetails.add(recipeDetail);
                    }
                }
                RecyclerView categoryList = (RecyclerView) findViewById(R.id.categories_List);
                categoryList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                categoryList.setItemAnimator(new DefaultItemAnimator());
                CategoryListAdapter cla = new CategoryListAdapter(getApplicationContext(), categoryDetails, recipeImage);
                categoryList.setAdapter(cla);
                cla.notifyDataSetChanged();
                categoryList.addOnItemTouchListener(new RecyclerTouchListener(Home.this, recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(Home.this,RecipePage.class);
                        intent.putExtra("Recipe", recipeDetails.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        /*recyclerView = (RecyclerView) findViewById(R.id.categories_List);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false)
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        *//*CategoryListAdapter cla = new CategoryListAdapter(getApplicationContext(),arrayList,dis,others);
        recyclerView.setAdapter(cla);*//*
        CategoryListAdapter cla = new CategoryListAdapter(getApplicationContext(),arrayList,dis,others);
        recyclerView.setAdapter(cla);*/


    }

    public ArrayList<String> getData(String key) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("recipe");
            ArrayList<String> formList = new ArrayList<String>();

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                formList.add(jo_inside.getString(key));
            }
            return formList;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void getRecipeData() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("recipe");

            HashMap<String, String> map;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                map = new HashMap<String, String>();
                map.put("type", jo_inside.getString("type"));
                map.put("image", jo_inside.getString("image"));
                map.put("line1", jo_inside.getString("line1"));
                map.put("line2", jo_inside.getString("line2"));
                map.put("time", jo_inside.getString("time"));
                map.put("serving", jo_inside.getString("serving"));

                recipeDetails.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("sampledata");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;

            case R.id.navigation_favorites:
                fragment = new BookmarkFragment();
                break;

            case R.id.navigation_settings:
                fragment = new SettingsFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
