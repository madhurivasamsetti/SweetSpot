package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeSteps;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailStepsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<RecipeSteps> mDataSource;

    public DetailStepsAdapter(Context mContext, ArrayList<RecipeSteps> mDataSource) {
        this.mContext = mContext;
        this.mDataSource = mDataSource;
    }

    @Override
    public int getCount() {
        if (mDataSource == null)
            return 0;
        else return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.step_list_item_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.mDescriptionTextView.setText(mDataSource.get(position).getmShortDescription());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.step_list_text_view)
        TextView mDescriptionTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
