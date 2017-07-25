package com.example.vasam.sweetspot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.vasam.sweetspot.model.BakingRecipes;
import com.example.vasam.sweetspot.utils.JsonUtils;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeCardsAdapter.RecipeCardClickListener {
    private RecyclerView mRecyclerView;
    private RecipeCardsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        List<BakingRecipes> recipes = null;
        try {
            recipes = JsonUtils.readJsonStream(this);
            Log.d("MainActivity.class", "values int recipe list are" + recipes);
        } catch (IOException e) {
            Log.d("MainActivity.class", "error while reading json file");
        }

        mAdapter = new RecipeCardsAdapter(this, recipes, this);
        mRecyclerView.setAdapter(mAdapter);
        for (BakingRecipes recipe : recipes) {
            Log.d("MainActivity.class", "recipe contents:" + recipe.getmRecipeImage());
        }
    }

    @Override
    public void onItemClick(BakingRecipes recipe) {
        Toast.makeText(this, "item got clicked" + recipe.getmRecipeId(), Toast.LENGTH_SHORT).show();
    }
}
