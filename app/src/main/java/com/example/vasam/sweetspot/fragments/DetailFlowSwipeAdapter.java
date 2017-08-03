package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

/**
 * Created by vasam on 7/29/2017.
 */

public class DetailFlowSwipeAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private ArrayList<RecipeSteps> steps;

    public DetailFlowSwipeAdapter(FragmentManager fm, Context mContext, ArrayList<RecipeSteps> steps) {
        super(fm);
        this.mContext = mContext;
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int position) {

        DetailFlowFragment detailFlowFragment = new DetailFlowFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(mContext.getString(R.string.step_position_key), position);
        bundle.putParcelableArrayList(mContext.getString(R.string.steps_key), steps);
        detailFlowFragment.setArguments(bundle);

        return detailFlowFragment;
    }


    @Override
    public int getCount() {
        return steps.size();
    }


}
