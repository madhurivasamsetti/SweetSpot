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
import com.example.vasam.sweetspot.fragments.DetailIngredientsFragment;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

public class RecipeDetailFlowActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
private ViewPager viewPager;
    DetailFlowSwipeAdapter swipeAdapter;
    ArrayList<RecipeSteps> steps;
    int position;
//    @Override
//    public void onBackPressed() {
//        if(viewPager.getCurrentItem()==0) {
//            super.onBackPressed();
//        }
//        else {
//            // Otherwise, select the previous step.
//            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
//        }
//    }

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

//            Bundle content = new Bundle();
//            content.putParcelableArrayList(getString(R.string.steps_key), steps);
//            content.putInt(getString(R.string.step_position_key), position);

//            DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
//            detailFlowFragment.setArguments(content);

//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.detail_flow_fragment, detailFlowFragment).commit();
            //,this,position,steps
            viewPager = (ViewPager) findViewById(R.id.pager);
            swipeAdapter = new DetailFlowSwipeAdapter(getSupportFragmentManager(),this,position,steps);
            viewPager.setCurrentItem(position);
            viewPager.setAdapter(swipeAdapter);
            viewPager.addOnPageChangeListener(this);

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
//        Log.d("MainActivity.class", "page scrolled:" + position);

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("RecipeDetailFlow.class", "pageSelected:" + position);
       //FragmentManager manager =  getSupportFragmentManager();

        DetailIngredientsFragment fragment  = new DetailIngredientsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("steps", steps);
        bundle.putInt("index", position);
        fragment.setArguments(bundle);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
