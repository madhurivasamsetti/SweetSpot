package com.example.vasam.sweetspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vasam.sweetspot.fragments.DetailFlowSwipeAdapter;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;


public class RecipeDetailFlowActivity extends AppCompatActivity {

    DetailFlowSwipeAdapter swipeAdapter;
    ArrayList<RecipeSteps> steps;


    String recipeName;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail_flow);

        if (savedInstanceState != null) {
            steps = savedInstanceState.getParcelableArrayList(getString(R.string.steps_key));
            recipeName = savedInstanceState.getString(getString(R.string.recipeName_key));
            int currentPosition = savedInstanceState.getInt("viewPosition");
            initializeViewPager(currentPosition);
        }

        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getBundleExtra(Intent.EXTRA_TEXT);
            steps = bundle.getParcelableArrayList(getString(R.string.steps_key));
            int position = bundle.getInt(getString(R.string.step_position_key));
            recipeName = bundle.getString(getString(R.string.recipeName_key));
            initializeViewPager(position);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(recipeName);
            }
        }

    }

    public void initializeViewPager(int currentPosition) {
        viewPager = (ViewPager) findViewById(R.id.pager);
        swipeAdapter = new DetailFlowSwipeAdapter(getSupportFragmentManager(), this, steps);

        viewPager.setAdapter(swipeAdapter);
        viewPager.setOffscreenPageLimit(1);
        swipeAdapter.notifyDataSetChanged();

        viewPager.setCurrentItem(currentPosition);
        Toast.makeText(this, "swipe left or right to navigate", Toast.LENGTH_SHORT).show();
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
    protected void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(getString(R.string.steps_key), steps);
        currentState.putString(getString(R.string.recipeName_key), recipeName);
        currentState.putInt("viewPosition", viewPager.getCurrentItem());
    }
}
