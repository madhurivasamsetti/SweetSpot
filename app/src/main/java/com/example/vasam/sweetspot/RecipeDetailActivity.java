package com.example.vasam.sweetspot;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.vasam.sweetspot.fragments.DetailFlowFragment;
import com.example.vasam.sweetspot.fragments.DetailIngredientsFragment;
import com.example.vasam.sweetspot.fragments.DetailStepsFragment;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements DetailStepsFragment.OnStepClickListener {

    @BindView(R.id.steps_image_view)
    ImageView stepsList_icon;
    @BindView(R.id.ingredients_image_view)
    ImageView ingredientList_icon;
    @BindView(R.id.ingredients_layout)
    LinearLayout ingredientsTitle_layout;
    @BindView(R.id.steps_layout)
    LinearLayout stepsTitle_layout;
    @BindView(R.id.detailIngredient_fragment)
    FrameLayout ingredientsListFrame;
    @BindView(R.id.detailSteps_fragment)
    FrameLayout stepsListFrame;
    @BindDrawable(R.drawable.up_arrow)
    Drawable up_arrow;
    @BindDrawable(R.drawable.down_arrow)
    Drawable down_arrow;


    ArrayList<RecipeSteps> stepsList;
    String recipeName;
    /**
     * below two booleans are used to toggle between arrow images present near the titles
     */
    static boolean stepsClicked = false;
    static boolean ingredientsClicked = true;

    // Track whether to display a two-pane or single-pane UI
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        stepsList = getIntent().getParcelableArrayListExtra(getString(R.string.steps_key));
        ArrayList<RecipeIngredients> ingredientsList = getIntent().getParcelableArrayListExtra(getString(R.string.ingredients_key));
        recipeName = getIntent().getStringExtra(getString(R.string.recipeName_key));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(recipeName);
        }
        ButterKnife.bind(this);

        if (findViewById(R.id.detail_flow_linear_layout) != null) {
            mTwoPane = true;

            if (savedInstanceState == null) {
                //using bundle to communicate between fragments.
                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);

                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelableArrayList(getString(R.string.ingredients_key), ingredientsList);

                FragmentManager fragmentManager = getSupportFragmentManager();

                DetailIngredientsFragment detailIngredientsFragment = new DetailIngredientsFragment();
                DetailStepsFragment detailStepsFragment = new DetailStepsFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.detailIngredient_fragment, detailIngredientsFragment)
                        .commit();
                detailIngredientsFragment.setArguments(ingredientsBundle);

                fragmentManager.beginTransaction()
                        .add(R.id.detailSteps_fragment, detailStepsFragment)
                        .commit();
                detailStepsFragment.setArguments(stepsBundle);

                Bundle content = new Bundle();
                content.putParcelableArrayList(getString(R.string.steps_key), stepsList);
                content.putInt(getString(R.string.step_position_key), 0);

                DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
                detailFlowFragment.setArguments(content);
                fragmentManager.beginTransaction().add(R.id.detail_flow_fragment, detailFlowFragment).commit();
            }
        } else {
            mTwoPane = false;
            if (savedInstanceState == null) {
                //using bundle to communicate between fragments.
                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);

                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelableArrayList(getString(R.string.ingredients_key), ingredientsList);

                FragmentManager fragmentManager = getSupportFragmentManager();

                DetailIngredientsFragment detailIngredientsFragment = new DetailIngredientsFragment();
                DetailStepsFragment detailStepsFragment = new DetailStepsFragment();

                fragmentManager.beginTransaction()
                        .add(R.id.detailIngredient_fragment, detailIngredientsFragment)
                        .commit();
                detailIngredientsFragment.setArguments(ingredientsBundle);

                fragmentManager.beginTransaction()
                        .add(R.id.detailSteps_fragment, detailStepsFragment)
                        .commit();
                detailStepsFragment.setArguments(stepsBundle);

            }

        }

        ingredientsTitle_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientsListFrame.setVisibility((ingredientsListFrame.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
                stepsListFrame.setVisibility((stepsListFrame.getVisibility() == View.GONE)
                        ? View.VISIBLE : View.GONE);
                if (!ingredientsClicked) {
                    ingredientsClicked = true;
                    ingredientList_icon.setImageDrawable(down_arrow);
                } else {
                    ingredientsClicked = false;
                    ingredientList_icon.setImageDrawable(up_arrow);
                }
                if (stepsClicked) {
                    stepsClicked = false;
                    stepsList_icon.setImageDrawable(up_arrow);
                } else {
                    stepsClicked = true;
                    stepsList_icon.setImageDrawable(down_arrow);
                }
            }
        });

        stepsTitle_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepsListFrame.setVisibility((stepsListFrame.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);
                ingredientsListFrame.setVisibility((ingredientsListFrame.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);

                if (stepsClicked) {
                    stepsClicked = false;
                    stepsList_icon.setImageDrawable(up_arrow);
                } else {
                    stepsClicked = true;
                    stepsList_icon.setImageDrawable(down_arrow);
                }
                if (!ingredientsClicked) {
                    ingredientsClicked = true;
                    ingredientList_icon.setImageDrawable(down_arrow);
                } else {
                    ingredientsClicked = false;
                    ingredientList_icon.setImageDrawable(up_arrow);
                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);
        bundle.putInt(getString(R.string.step_position_key), position);
        bundle.putString(getString(R.string.recipeName_key), recipeName);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mTwoPane) {
            DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
            detailFlowFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.detail_flow_fragment, detailFlowFragment).commit();
        } else {
            Intent intent = new Intent(RecipeDetailActivity.this, RecipeDetailFlowActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, bundle);
            startActivity(intent);
        }


    }
}
