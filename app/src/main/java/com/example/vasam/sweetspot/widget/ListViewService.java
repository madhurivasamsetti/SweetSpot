package com.example.vasam.sweetspot.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.vasam.sweetspot.R;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by vasam on 8/3/2017.
 */

public class ListViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    private class ListRemoteViewsFactory implements RemoteViewsFactory {

        Context mContext;
        Intent intent;
        ArrayList<RecipeIngredients> ingredientsArrayList;


        public ListRemoteViewsFactory(Context applicationContext, Intent intent) {
            mContext = applicationContext;
            this.intent = intent;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            String jsonString = sharedPreferences.getString(getString(R.string.pref_data_key), "");
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<RecipeIngredients>>() {
            }.getType();
            ingredientsArrayList = gson.fromJson(jsonString, type);
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingredientsArrayList == null) {
                return 0;
            } else
                return ingredientsArrayList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                    R.layout.list_item_view_widget);

            remoteViews.setTextViewText(R.id.ingredientName_widget,
                    ingredientsArrayList.get(position).getmIngredient());
            remoteViews.setTextViewText(R.id.ingredientQuantity_widget,
                    String.format(Locale.getDefault(), "%.1f",
                            ingredientsArrayList.get(position).getmIngredientQuantity()));
            remoteViews.setTextViewText(R.id.ingredientMeasure_widget,
                    ingredientsArrayList.get(position).getmIngredientMeasure());

            return remoteViews;

        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
