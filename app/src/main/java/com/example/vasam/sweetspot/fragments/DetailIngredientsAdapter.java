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
        TextView mIngredientName;
        TextView mIngredientQuantity;
        TextView mIngredientMeasure;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ingredients_list_item_view, parent, false);
        }
        mIngredientName = (TextView) convertView.findViewById(R.id.ingredientName_text_view);
        mIngredientQuantity = (TextView) convertView.findViewById(R.id.ingredientQuantity_text_view);
        mIngredientMeasure = (TextView) convertView.findViewById(R.id.ingredientMeasure_text_view);

        mIngredientName.setText(mDataSource.get(position).getmIngredient());
        mIngredientQuantity.setText(String.format(Locale.getDefault(),"%.1f",
                mDataSource.get(position).getmIngredientQuantity()));
        mIngredientMeasure.setText(mDataSource.get(position).getmIngredientMeasure());

        return convertView;
    }
}
