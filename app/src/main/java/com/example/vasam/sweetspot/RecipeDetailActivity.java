package com.example.vasam.sweetspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vasam.sweetspot.fragments.DetailStepsFragment;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements DetailStepsFragment.OnStepClickListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    ArrayList<RecipeSteps> stepsList;
    String recipeName;

//    // Track whether to display a two-pane or single-pane UI
//    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        stepsList = getIntent().getParcelableArrayListExtra(getString(R.string.steps_key));
        final ArrayList<RecipeIngredients> ingredientsList = getIntent().getParcelableArrayListExtra(getString(R.string.ingredients_key));
        recipeName = getIntent().getStringExtra(getString(R.string.recipeName_key));
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(recipeName);
        }

        RecipeDetailAdapter adapter = new RecipeDetailAdapter(getSupportFragmentManager(),
                this, ingredientsList, stepsList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



//        if (findViewById(R.id.detail_flow_linear_layout) != null) {
//            mTwoPane = true;
//            if (savedInstanceState == null) {
//                //using bundle to communicate between fragments.
//                Bundle stepsBundle = new Bundle();
//                stepsBundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);
//
//                Bundle ingredientsBundle = new Bundle();
//                ingredientsBundle.putParcelableArrayList(getString(R.string.ingredients_key), ingredientsList);
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//
//                DetailIngredientsFragment detailIngredientsFragment = new DetailIngredientsFragment();
//                DetailStepsFragment detailStepsFragment = new DetailStepsFragment();
//
//                fragmentManager.beginTransaction()
//                        .add(R.id.detailIngredient_fragment, detailIngredientsFragment)
//                        .commit();
//                detailIngredientsFragment.setArguments(ingredientsBundle);
//
//                fragmentManager.beginTransaction()
//                        .add(R.id.detailSteps_fragment, detailStepsFragment)
//                        .commit();
//                detailStepsFragment.setArguments(stepsBundle);
//
//                Bundle content = new Bundle();
//                content.putParcelableArrayList(getString(R.string.steps_key), stepsList);
//                content.putInt(getString(R.string.step_position_key), 0);
//                content.putBoolean("twoPane", mTwoPane);
//
//                DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
//                detailFlowFragment.setArguments(content);
//                fragmentManager.beginTransaction().add(detail_flow_fragment, detailFlowFragment).commit();
//            }
//        }

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
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);
        bundle.putInt(getString(R.string.step_position_key), position);
        bundle.putString(getString(R.string.recipeName_key), recipeName);

        Intent intent = new Intent(RecipeDetailActivity.this, RecipeDetailFlowActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, bundle);
        startActivity(intent);

    }
}
