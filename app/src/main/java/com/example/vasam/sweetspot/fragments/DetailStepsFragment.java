package com.example.vasam.sweetspot.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailStepsFragment extends Fragment {
    @BindView(R.id.steps_list_view)ListView listView;
    public DetailStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_detail_steps,container,false);
        ButterKnife.bind(this,rootView);

        ArrayList<RecipeSteps> stepsList = getArguments().getParcelableArrayList(getString(R.string.steps_key));

        DetailStepsAdapter detailStepsAdapter = new DetailStepsAdapter(getActivity().getApplicationContext(),stepsList);
        listView.setAdapter(detailStepsAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
