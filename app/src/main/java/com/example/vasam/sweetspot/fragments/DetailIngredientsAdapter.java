package com.example.vasam.sweetspot.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DetailIngredientsAdapter extends RecyclerView.Adapter<DetailIngredientsAdapter.IngredientsViewHolder> {
    private Context mContext;
    private ArrayList<RecipeIngredients> mDataSource;

    public DetailIngredientsAdapter(Context mContext, ArrayList<RecipeIngredients> mDataSource) {
        this.mContext = mContext;
        this.mDataSource = mDataSource;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.ingredients_list_item_view, parent, false);
        return new IngredientsViewHolder(root);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.ingredientName.setText(mDataSource.get(position).getmIngredient());
        holder.ingredientQuantity.setText(String.format(Locale.getDefault(),"%.1f",
                mDataSource.get(position).getmIngredientQuantity()));
        holder.ingredientMeasure.setText(mDataSource.get(position).getmIngredientMeasure());
    }

    @Override
    public int getItemCount() {
        if (mDataSource == null) {
            return 0;
        } else
            return mDataSource.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredientName_text_view)
        TextView ingredientName;
        @BindView(R.id.ingredientQuantity_text_view)
        TextView ingredientQuantity;
        @BindView(R.id.ingredientMeasure_text_view)
        TextView ingredientMeasure;

        public IngredientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
