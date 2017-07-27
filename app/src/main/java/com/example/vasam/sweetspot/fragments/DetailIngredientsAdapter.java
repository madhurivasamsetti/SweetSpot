package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeIngredients;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vasam on 7/25/2017.
 */

public class DetailIngredientsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<RecipeIngredients> mDataSource;

    public DetailIngredientsAdapter(Context context, ArrayList<RecipeIngredients> mDataSource) {
        mContext = context;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ingredients_list_item_view, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.mIngredientName.setText(mDataSource.get(position).getmIngredient());
        holder.mIngredientQuantity.setText(String.format(Locale.getDefault(),"%.1f",
                mDataSource.get(position).getmIngredientQuantity()));
        holder.mIngredientMeasure.setText(mDataSource.get(position).getmIngredientMeasure());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ingredientName_text_view) TextView mIngredientName;
        @BindView(R.id.ingredientQuantity_text_view) TextView mIngredientQuantity;
        @BindView(R.id.ingredientMeasure_text_view) TextView mIngredientMeasure;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
