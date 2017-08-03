package com.example.vasam.sweetspot.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeIngredients;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailIngredientsFragment extends Fragment {
    @BindView(R.id.ingredients_recycler_view)
    RecyclerView recyclerView;
    ArrayList<RecipeIngredients> ingredientsList;

    public DetailIngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ingredientsList = getArguments().
                getParcelableArrayList(getString(R.string.ingredients_key));

        View rootView = inflater.inflate(R.layout.fragment_detail_ingredients, container, false);

        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        DetailIngredientsAdapter mAdapter = new DetailIngredientsAdapter(getContext(), ingredientsList);
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
