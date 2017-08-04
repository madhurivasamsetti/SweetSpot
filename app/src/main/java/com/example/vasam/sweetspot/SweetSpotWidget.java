package com.example.vasam.sweetspot;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RemoteViews;

import com.example.vasam.sweetspot.widget.ListViewService;

/**
 * Implementation of App Widget functionality.
 */
public class SweetSpotWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sweet_spot_widget);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String recipeName = sharedPreferences.getString(
                context.getString(R.string.pref_recipeName_key), "");
        if (recipeName.isEmpty()) {
            views.setViewVisibility(R.id.appwidget_RecipeName, View.INVISIBLE);
            views.setViewVisibility(R.id.widget_listView, View.INVISIBLE);
            views.setViewVisibility(R.id.widget_empty_TextView, View.VISIBLE);
            views.setTextViewText(R.id.widget_empty_TextView,
                    context.getString(R.string.widget_emptyView_text));
        } else {
            views.setViewVisibility(R.id.widget_empty_TextView, View.INVISIBLE);
            views.setTextViewText(R.id.appwidget_RecipeName, recipeName);
            Intent intent = new Intent(context, ListViewService.class);
            views.setRemoteAdapter(R.id.widget_listView, intent);
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public static void sendRefreshBroadcast(Context context) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, SweetSpotWidget.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final String action = intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, SweetSpotWidget.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName),
                    R.id.widget_listView);
            onUpdate(context,appWidgetManager,appWidgetManager.getAppWidgetIds(componentName));
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

