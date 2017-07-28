package com.example.vasam.sweetspot.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by vasam on 7/28/2017.
 */

public class ApplicationUtils {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpwidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 180;
        return (int) (dpwidth / scalingFactor);
    }
}
