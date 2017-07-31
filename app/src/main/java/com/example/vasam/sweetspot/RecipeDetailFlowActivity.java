package com.example.vasam.sweetspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.vasam.sweetspot.fragments.DetailFlowSwipeAdapter;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

//implements ViewPager.OnPageChangeListener
public class RecipeDetailFlowActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
private ViewPager viewPager;
    DetailFlowSwipeAdapter swipeAdapter;
    ArrayList<RecipeSteps> steps;

    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_detail_flow);
        if(savedInstanceState==null)
        {
            Bundle bundle = getIntent().getBundleExtra(Intent.EXTRA_TEXT);
           steps = bundle.getParcelableArrayList(getString(R.string.steps_key));
            position = bundle.getInt(getString(R.string.step_position_key));
            String name = bundle.getString(getString(R.string.recipeName_key));

            ActionBar actionBar = getSupportActionBar();
            if(actionBar!=null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(name);
            }

            viewPager = (ViewPager) findViewById(R.id.pager);
            swipeAdapter = new DetailFlowSwipeAdapter(getSupportFragmentManager(),this,position,steps);

            viewPager.setAdapter(swipeAdapter);
            viewPager.setOffscreenPageLimit(1);
            swipeAdapter.notifyDataSetChanged();
            viewPager.setCurrentItem(position);
            Log.d("RecipeDetailFlow.class","viewpagercurent: "+viewPager.getCurrentItem());

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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("RecipeDetailFlow.class", "pageSelected:" + position);
//
//        DetailIngredientsFragment fragment  = new DetailIngredientsFragment();
//        fragment.setUserVisibleHint(true);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
