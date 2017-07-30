package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

/**
 * Created by vasam on 7/29/2017.
 */

public class DetailFlowSwipeAdapter extends FragmentStatePagerAdapter {
    private int mPosition;
    private Context mContext;
    private ArrayList<RecipeSteps> steps;

    //int position, ArrayList<RecipeSteps> steps
    public DetailFlowSwipeAdapter(FragmentManager fm, Context mContext, int mPosition, ArrayList<RecipeSteps> steps) {
        super(fm);
        this.mContext = mContext;
        this.mPosition = mPosition;
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("DetailAdapter.class", "position:" + position);
        DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(mContext.getString(R.string.step_position_key), mPosition);
        bundle.putParcelableArrayList(mContext.getString(R.string.steps_key), steps);
        detailFlowFragment.setArguments(bundle);

        return detailFlowFragment;
    }

    @Override
    public int getCount() {
        return steps.size();
    }
}
