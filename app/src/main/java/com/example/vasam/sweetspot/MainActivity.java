package com.example.vasam.sweetspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vasam.sweetspot.model.BakingRecipes;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;
import com.example.vasam.sweetspot.utils.ApplicationUtils;
import com.example.vasam.sweetspot.utils.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeCardsAdapter.RecipeCardClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, ApplicationUtils.calculateNoOfColumns(this));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        ArrayList<BakingRecipes> recipes = null;
        try {
            recipes = JsonUtils.readJsonStream(this);
            Log.d("MainActivity.class", "values int recipe list are" + recipes);
        } catch (IOException e) {
            Log.d("MainActivity.class", "error while reading json file");
        }

        RecipeCardsAdapter mAdapter = new RecipeCardsAdapter(this, recipes, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(BakingRecipes recipe) {
        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);

        ArrayList<RecipeSteps> steps = recipe.getmSteps();
        ArrayList<RecipeIngredients> ingredients = recipe.getmIngredients();

        intent.putParcelableArrayListExtra(getString(R.string.steps_key), steps);
        intent.putParcelableArrayListExtra(getString(R.string.ingredients_key), ingredients);
        intent.putExtra(getString(R.string.recipeName_key), recipe.getmRecipeName());

        startActivity(intent);
    }
}
