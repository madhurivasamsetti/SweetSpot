package com.example.vasam.sweetspot;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.vasam.sweetspot.fragments.DetailIngredientsFragment;
import com.example.vasam.sweetspot.fragments.DetailStepsFragment;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {
    private ArrayList<RecipeSteps> stepsList;
    private ArrayList<RecipeIngredients> ingredientsList;
    private String recipeName;
    static boolean stepsClicked = false;
    static boolean ingredientsClicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        stepsList = getIntent().getParcelableArrayListExtra("recipeSteps");
        ingredientsList = getIntent().getParcelableArrayListExtra("ingredients");
        recipeName = getIntent().getStringExtra("recipeName");
        final ImageView steps_icon = (ImageView) findViewById(R.id.steps_image_view);
        final ImageView ingredient_icon = (ImageView) findViewById(R.id.ingredients_image_view);

        LinearLayout ingredients = (LinearLayout) findViewById(R.id.ingredients_layout);

        final FrameLayout ingredientsFrame = (FrameLayout) findViewById(R.id.detailIngredient_fragment);
        LinearLayout steps = (LinearLayout) findViewById(R.id.steps_layout);

        final FrameLayout stepsFrame = (FrameLayout) findViewById(R.id.detailSteps_fragment);

        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientsFrame.setVisibility((ingredientsFrame.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
                stepsFrame.setVisibility((stepsFrame.getVisibility() == View.GONE)
                        ? View.VISIBLE : View.GONE);
               if(!ingredientsClicked)
               {
                   ingredientsClicked = true;
                   ingredient_icon.setImageResource(R.drawable.down_arrow);
               }
               else {
                   ingredientsClicked = false;
                   ingredient_icon.setImageResource(R.drawable.up_arrow);
               }
                if(stepsClicked)
                {
                    stepsClicked = false;
                    steps_icon.setImageResource(R.drawable.up_arrow);
                }
                else {
                    stepsClicked = true;
                    steps_icon.setImageResource(R.drawable.down_arrow);
                }
            }
        });

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsFrame.setVisibility((stepsFrame.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
                ingredientsFrame.setVisibility((ingredientsFrame.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

                if(stepsClicked)
                {
                    stepsClicked = false;
                    steps_icon.setImageResource(R.drawable.up_arrow);
                }
                else {
                    stepsClicked = true;
                    steps_icon.setImageResource(R.drawable.down_arrow);
                }
                if(!ingredientsClicked)
                {
                    ingredientsClicked = true;
                    ingredient_icon.setImageResource(R.drawable.down_arrow);
                }
                else {
                    ingredientsClicked = false;
                    ingredient_icon.setImageResource(R.drawable.up_arrow);
                }

            }
        });
        Bundle stepsBundle = new Bundle();
        stepsBundle.putParcelableArrayList("stepsList", stepsList);

        Bundle ingredientsBundle = new Bundle();
        ingredientsBundle.putParcelableArrayList("ingredientsList", ingredientsList);
        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailIngredientsFragment detailIngredientsFragment = new DetailIngredientsFragment();
        DetailStepsFragment detailStepsFragment = new DetailStepsFragment();

        fragmentManager.beginTransaction()
                .add(R.id.detailIngredient_fragment, detailIngredientsFragment)
                .commit();

        fragmentManager.beginTransaction()
                .add(R.id.detailSteps_fragment, detailStepsFragment)
                .commit();

        detailStepsFragment.setArguments(stepsBundle);
        detailIngredientsFragment.setArguments(ingredientsBundle);
    }

}
