package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailStepsAdapter extends RecyclerView.Adapter<DetailStepsAdapter.StepsViewHolder> {
    private Context mContext;
    private ArrayList<RecipeSteps> mDataSource;
    StepsListClickListener mStepsListClickListener;

    public DetailStepsAdapter(Context mContext, ArrayList<RecipeSteps> mDataSource, StepsListClickListener mStepsListClickListener) {
        this.mContext = mContext;
        this.mDataSource = mDataSource;
        this.mStepsListClickListener = mStepsListClickListener;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.step_list_item_view, parent, false);
        return new StepsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.mShortDescription.setText(mDataSource.get(position).getmShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mDataSource != null) {
            return mDataSource.size();
        } else return 0;
    }

    public interface StepsListClickListener {
        void onItemClick(int position);
    }


    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.step_list_text_view)
        TextView mShortDescription;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mStepsListClickListener.onItemClick(position);
        }
    }
}
