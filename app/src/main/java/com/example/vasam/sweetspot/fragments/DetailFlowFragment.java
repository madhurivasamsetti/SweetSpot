package com.example.vasam.sweetspot.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/27/2017.
 */

public class DetailFlowFragment extends Fragment {
    @BindView(R.id.next_step)
    ImageButton next_step;
    @BindView(R.id.previous_step)
    ImageButton previous_step;
    @BindView(R.id.detail_instruction)
    TextView instruction;
    @BindView(R.id.snackBar_position)
    TextView snackBarView;
    private static int position;

    private ArrayList<RecipeSteps> stepsArrayList;
    public DetailFlowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null)
        {
            stepsArrayList = savedInstanceState.getParcelableArrayList(getString(R.string.steps_key));
            position = savedInstanceState.getInt(getString(R.string.step_position_key));
        }
        else {

            stepsArrayList = getArguments().getParcelableArrayList(getString(R.string.steps_key));
            position = getArguments().getInt(getString(R.string.step_position_key));
        }
        View rootView = inflater.inflate(R.layout.fragment_detail_flow, container, false);
        ButterKnife.bind(this, rootView);

        instruction.setText(stepsArrayList.get(position).getmDescription());


        final int sizeOfList = stepsArrayList.size();
        next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < sizeOfList - 1) {
                    position++;
                    previous_step.setColorFilter(Color.BLACK);
                    next_step.setColorFilter(Color.BLACK);
                    instruction.setText(stepsArrayList.get(position).getmDescription());
                }
                if (position == sizeOfList - 1) {
                    next_step.setColorFilter(Color.GRAY);
                    Snackbar snackbar = Snackbar.make(snackBarView, "reached end of steps", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

        });
        previous_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position > 0) {
                    position--;
                    previous_step.setColorFilter(Color.BLACK);
                    next_step.setColorFilter(Color.BLACK);
                    instruction.setText(stepsArrayList.get(position).getmDescription());

                }
                if (position == 0) {
                    previous_step.setColorFilter(Color.GRAY);
                    Snackbar snackbar = Snackbar.make(snackBarView, "reached begining of steps", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putParcelableArrayList(getString(R.string.steps_key),stepsArrayList);
        currentState.putInt(getString(R.string.step_position_key),position);
    }
}
