package com.example.vasam.sweetspot.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeIngredients;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailIngredientsFragment extends Fragment {
    @BindView(R.id.ingredients_list_view)ListView listView;

    public DetailIngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ArrayList<RecipeIngredients> ingredientsList = getArguments().
                getParcelableArrayList(getString(R.string.ingredients_key));
        View rootView = inflater.inflate(R.layout.fragment_detail_ingredients,container,false);

        ButterKnife.bind(this,rootView);

        DetailIngredientsAdapter mAdapter = new DetailIngredientsAdapter(
                getActivity().getApplicationContext(),ingredientsList);
        listView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
