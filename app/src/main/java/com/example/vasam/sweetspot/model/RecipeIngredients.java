package com.example.vasam.sweetspot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vasam on 7/25/2017.
 */

public class RecipeIngredients implements Parcelable{
    private double mIngredientQuantity;
    private String mIngredientMeasure;
    private String mIngredient;

    public RecipeIngredients(double mIngredientQuantity, String mIngredientMeasure, String mIngredient) {
        this.mIngredientQuantity = mIngredientQuantity;
        this.mIngredientMeasure = mIngredientMeasure;
        this.mIngredient = mIngredient;
    }

    public RecipeIngredients(Parcel in) {
        mIngredientQuantity = in.readDouble();
        mIngredientMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<RecipeIngredients> CREATOR = new Creator<RecipeIngredients>() {
        @Override
        public RecipeIngredients createFromParcel(Parcel in) {
            return new RecipeIngredients(in);
        }

        @Override
        public RecipeIngredients[] newArray(int size) {
            return new RecipeIngredients[size];
        }
    };

    public double getmIngredientQuantity() {
        return mIngredientQuantity;
    }

    public void setmIngredientQuantity(int mIngredientQuantity) {
        this.mIngredientQuantity = mIngredientQuantity;
    }

    public String getmIngredientMeasure() {
        return mIngredientMeasure;
    }

    public void setmIngredientMeasure(String mIngredientMeasure) {
        this.mIngredientMeasure = mIngredientMeasure;
    }

    public String getmIngredient() {
        return mIngredient;
    }

    public void setmIngredient(String mIngredient) {
        this.mIngredient = mIngredient;
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
        dest.writeDouble(mIngredientQuantity);
        dest.writeString(mIngredientMeasure);
        dest.writeString(mIngredient);
    }
}

