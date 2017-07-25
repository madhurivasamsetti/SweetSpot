package com.example.vasam.sweetspot.model;

/**
 * Created by vasam on 7/25/2017.
 */

public class RecipeIngredients {
    private double mIngredientQuantity;
    private String mIngredientMeasure;
    private String mIngredient;

    public RecipeIngredients(double mIngredientQuantity, String mIngredientMeasure, String mIngredient) {
        this.mIngredientQuantity = mIngredientQuantity;
        this.mIngredientMeasure = mIngredientMeasure;
        this.mIngredient = mIngredient;
    }

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
}

