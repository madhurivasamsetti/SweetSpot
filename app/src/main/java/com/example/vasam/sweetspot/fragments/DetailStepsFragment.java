package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    OnStepClickListener mCallback;
    @BindView(R.id.steps_list_view)
    ListView listView;
    private ArrayList<RecipeSteps> stepsList;

    public DetailStepsFragment() {
    }

    public interface OnStepClickListener {
        void onClick(int position);
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
        if (savedInstanceState != null) {
            stepsList = savedInstanceState.getParcelableArrayList(getString(R.string.steps_key));
        } else {
            stepsList = getArguments().getParcelableArrayList(getString(R.string.steps_key));
        }
        View rootView = inflater.inflate(R.layout.fragment_detail_steps, container, false);
        ButterKnife.bind(this, rootView);

        DetailStepsAdapter detailStepsAdapter = new DetailStepsAdapter(getActivity().getApplicationContext(), stepsList);
        listView.setAdapter(detailStepsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onClick(position);
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putParcelableArrayList(getString(R.string.steps_key), stepsList);
    }
}
