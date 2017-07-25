package com.example.vasam.sweetspot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vasam.sweetspot.model.BakingRecipes;

import java.util.List;

/**
 * Created by vasam on 7/25/2017.
 */

public class RecipeCardsAdapter extends RecyclerView.Adapter<RecipeCardsAdapter.RecipeCardHolder> {
    private Context mContext;
    private List<BakingRecipes> mDataSource;
    private final RecipeCardClickListener mClickListener;

    public interface RecipeCardClickListener {
        void onItemClick(BakingRecipes recipe);
    }

    public RecipeCardsAdapter(Context mContext, List<BakingRecipes> dataSource,RecipeCardClickListener clickListener) {
        this.mContext = mContext;
        mDataSource = dataSource;
        mClickListener = clickListener;
    }

    @Override
    public RecipeCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.main_list_item_view, parent, false);
        return new RecipeCardHolder(root);
    }

    @Override
    public void onBindViewHolder(RecipeCardHolder holder, int position) {
        int thumbnailImage = -1;
        switch (position) {
            case 0:
                thumbnailImage = R.drawable.nutella_pie;
                break;
            case 1:
                thumbnailImage = R.drawable.brownie;
                break;
            case 2:
                thumbnailImage = R.drawable.yellow_cake;
                break;
            case 3:
                thumbnailImage = R.drawable.cheese_cake;
                break;
        }
        String imagePath = mDataSource.get(position).getmRecipeImage();
        if (imagePath.isEmpty()) {
            holder.mRecipeImage.setImageResource(thumbnailImage);
        }
    }

    @Override
    public int getItemCount() {
        if (mDataSource == null) {
            return 0;
        } else
            return mDataSource.size();

    }


    public class RecipeCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mRecipeImage;

        public RecipeCardHolder(View itemView) {
            super(itemView);
            mRecipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            BakingRecipes recipe = mDataSource.get(position);
            mClickListener.onItemClick(recipe);
        }
    }
}