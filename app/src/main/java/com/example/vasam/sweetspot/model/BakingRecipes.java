package com.example.vasam.sweetspot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by vasam on 7/25/2017.
 */

public class BakingRecipes implements Parcelable {
    private int mRecipeId;
    private String mRecipeName;
    private List<RecipeIngredients> mIngredients;
    private List<RecipeSteps> mSteps;
    private int mServings;
    private String mRecipeImage;

    public BakingRecipes(int mRecipeId, String mRecipeName, List<RecipeIngredients> mIngredients,
                         List<RecipeSteps> mSteps, int mServings, String mRecipeImage) {
        this.mRecipeId = mRecipeId;
        this.mRecipeName = mRecipeName;
        this.mIngredients = mIngredients;
        this.mSteps = mSteps;
        this.mServings = mServings;
        this.mRecipeImage = mRecipeImage;
    }

    protected BakingRecipes(Parcel in) {
        mRecipeId = in.readInt();
        mRecipeName = in.readString();
        mServings = in.readInt();
        mRecipeImage = in.readString();
    }

    public static final Creator<BakingRecipes> CREATOR = new Creator<BakingRecipes>() {
        @Override
        public BakingRecipes createFromParcel(Parcel in) {
            return new BakingRecipes(in);
        }

        @Override
        public BakingRecipes[] newArray(int size) {
            return new BakingRecipes[size];
        }
    };

    public int getmRecipeId() {
        return mRecipeId;
    }

    public void setmRecipeId(int mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public String getmRecipeName() {
        return mRecipeName;
    }

    public void setmRecipeName(String mRecipeName) {
        this.mRecipeName = mRecipeName;
    }

    public List<RecipeIngredients> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(List<RecipeIngredients> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public List<RecipeSteps> getmSteps() {
        return mSteps;
    }

    public void setmSteps(List<RecipeSteps> mSteps) {
        this.mSteps = mSteps;
    }

    public int getmServings() {
        return mServings;
    }

    public void setmServings(int mServings) {
        this.mServings = mServings;
    }

    public String getmRecipeImage() {
        return mRecipeImage;
    }

    public void setmRecipeImage(String mRecipeImage) {
        this.mRecipeImage = mRecipeImage;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRecipeId);
        dest.writeString(mRecipeName);
        dest.writeList(mIngredients);
        dest.writeList(mSteps);
        dest.writeInt(mServings);
        dest.writeString(mRecipeImage);
    }
}
