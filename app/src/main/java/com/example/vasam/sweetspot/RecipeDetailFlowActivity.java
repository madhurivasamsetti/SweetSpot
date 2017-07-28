package com.example.vasam.sweetspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.vasam.sweetspot.fragments.DetailFlowFragment;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

public class RecipeDetailFlowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail_flow);
        if(savedInstanceState==null)
        {
            Bundle bundle = getIntent().getBundleExtra(Intent.EXTRA_TEXT);
            ArrayList<RecipeSteps> steps = bundle.getParcelableArrayList(getString(R.string.steps_key));
            int position = bundle.getInt(getString(R.string.step_position_key));
            String name = bundle.getString(getString(R.string.recipeName_key));

            ActionBar actionBar = getSupportActionBar();
            if(actionBar!=null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(name);
            }

            Bundle content = new Bundle();
            content.putParcelableArrayList(getString(R.string.steps_key), steps);
            content.putInt(getString(R.string.step_position_key), position);

            DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
            detailFlowFragment.setArguments(content);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().add(R.id.detail_flow_fragment, detailFlowFragment).commit();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
