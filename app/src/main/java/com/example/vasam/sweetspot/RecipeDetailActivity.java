package com.example.vasam.sweetspot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vasam.sweetspot.fragments.DetailFlowFragment;
import com.example.vasam.sweetspot.fragments.DetailStepsFragment;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements DetailStepsFragment.OnStepClickListener {
    @Nullable
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @Nullable
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    ArrayList<RecipeSteps> stepsList;
    ArrayList<RecipeIngredients> ingredientsList;
    String recipeName;
    private boolean mTwoPane;


//    // Track whether to display a two-pane or single-pane UI
//    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_detail);
        stepsList = getIntent().getParcelableArrayListExtra(getString(R.string.steps_key));
        ingredientsList = getIntent().getParcelableArrayListExtra(getString(R.string.ingredients_key));
        recipeName = getIntent().getStringExtra(getString(R.string.recipeName_key));

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(recipeName);
        }

        RecipeDetailAdapter adapter = new RecipeDetailAdapter(getSupportFragmentManager(),
                this, ingredientsList, stepsList);
        if (viewPager != null && tabLayout != null) {
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }

        if (findViewById(R.id.video_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                FragmentManager manager = getSupportFragmentManager();
                DetailFlowFragment videoFragment = new DetailFlowFragment();
                manager.beginTransaction().replace(R.id.video_container, videoFragment).commit();
                Bundle bundle = new Bundle();
                bundle.putInt(getString(R.string.step_position_key), -1);
                bundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);
                videoFragment.setArguments(bundle);
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pref_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.user_pref) {
            saveToPreferenceFile();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveToPreferenceFile() {
        Toast.makeText(this, getString(R.string.pref_icon_click_msg), Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(ingredientsList);
        editor.putString(getString(R.string.pref_data_key),jsonString);
        editor.putString(getString(R.string.pref_recipeName_key),recipeName);
        editor.apply();

       //updating widget after user click preference icon
        SweetSpotWidget.sendRefreshBroadcast(this);

    }


    @Override
    public void onStepSelected(int position) {
        if (mTwoPane) {
            FragmentManager manager = getSupportFragmentManager();
            DetailFlowFragment videoFragment = new DetailFlowFragment();
            manager.beginTransaction().replace(R.id.video_container, videoFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.step_position_key), position);
            bundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);
            videoFragment.setArguments(bundle);
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(getString(R.string.steps_key), stepsList);
            bundle.putInt(getString(R.string.step_position_key), position);
            bundle.putString(getString(R.string.recipeName_key), recipeName);

            Intent intent = new Intent(RecipeDetailActivity.this, RecipeDetailFlowActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, bundle);
            startActivity(intent);
        }
    }
}
