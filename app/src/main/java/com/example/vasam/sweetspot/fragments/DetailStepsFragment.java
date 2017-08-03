package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailStepsFragment extends Fragment implements DetailStepsAdapter.StepsListClickListener {

    @BindView(R.id.steps_recycler_view)
    RecyclerView recyclerView;
    private ArrayList<RecipeSteps> stepsList;
    int clickedPosition;
    OnStepClickListener mCallback;


    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    public DetailStepsFragment() {
    }

    @Override
    public void onItemClick(int position) {
        clickedPosition = position;
        mCallback.onStepSelected(clickedPosition);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        stepsList = getArguments().getParcelableArrayList(getString(R.string.steps_key));

        View rootView = inflater.inflate(R.layout.fragment_detail_steps, container, false);
        ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        DetailStepsAdapter detailStepsAdapter = new DetailStepsAdapter(getContext(), stepsList, this);
        recyclerView.setAdapter(detailStepsAdapter);

        return rootView;
    }


}
