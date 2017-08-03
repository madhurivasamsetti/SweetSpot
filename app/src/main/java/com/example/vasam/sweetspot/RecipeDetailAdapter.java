package com.example.vasam.sweetspot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vasam.sweetspot.fragments.DetailIngredientsFragment;
import com.example.vasam.sweetspot.fragments.DetailStepsFragment;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

/**
 * Created by vasam on 7/28/2017.
 */

public class RecipeDetailAdapter extends FragmentPagerAdapter {
    private String[] tabTitles = new String[]{"Ingredients", "Steps"};
    private ArrayList<RecipeIngredients> ingredientsArrayList;
    private ArrayList<RecipeSteps> stepsArrayList;
    private Context mContext;

    public RecipeDetailAdapter(FragmentManager fm,Context context,ArrayList<RecipeIngredients>
            ingredientsArrayList,ArrayList<RecipeSteps> stepsArrayList) {
        super(fm);
        mContext = context;
        this.ingredientsArrayList = ingredientsArrayList;
        this.stepsArrayList = stepsArrayList;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailIngredientsFragment ingredientFragment = new DetailIngredientsFragment();
                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putParcelableArrayList(mContext.getString(R.string.ingredients_key),
                        ingredientsArrayList);
                ingredientFragment.setArguments(ingredientsBundle);
                return ingredientFragment;

            case 1:
                DetailStepsFragment stepsFragment =  new DetailStepsFragment();
                Bundle stepsBundle = new Bundle();
                stepsBundle.putParcelableArrayList(mContext.getString(R.string.steps_key),
                        stepsArrayList);
                stepsFragment.setArguments(stepsBundle);
                return stepsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
