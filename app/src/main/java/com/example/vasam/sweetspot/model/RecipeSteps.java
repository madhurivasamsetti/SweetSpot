package com.example.vasam.sweetspot.model;

/**
 * Created by vasam on 7/25/2017.
 */

public class RecipeSteps {

    private int mStepId;
    private String mShortDescription;
    private String mDescription;
    private String mVideoURL;
    private String mThumbnailURL;

    public RecipeSteps(int mStepId, String mShortDescription, String mDescription, String mVideoURL, String mThumbnailURL) {
        this.mStepId = mStepId;
        this.mShortDescription = mShortDescription;
        this.mDescription = mDescription;
        this.mVideoURL = mVideoURL;
        this.mThumbnailURL = mThumbnailURL;
    }

    public int getmStepId() {
        return mStepId;
    }

    public void setmStepId(int mStepId) {
        this.mStepId = mStepId;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmVideoURL() {
        return mVideoURL;
    }

    public void setmVideoURL(String mVideoURL) {
        this.mVideoURL = mVideoURL;
    }

    public String getmThumbnailURL() {
        return mThumbnailURL;
    }

    public void setmThumbnailURL(String mThumbnailURL) {
        this.mThumbnailURL = mThumbnailURL;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
