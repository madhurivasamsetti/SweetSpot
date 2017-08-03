package com.example.vasam.sweetspot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vasam.sweetspot.model.BakingRecipes;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;
import com.example.vasam.sweetspot.retrofit.ApiClient;
import com.example.vasam.sweetspot.retrofit.ApiInterface;
import com.example.vasam.sweetspot.utils.ApplicationUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecipeCardsAdapter.RecipeCardClickListener {


    @BindView(R.id.error_textView)
    TextView errorView;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    RecipeCardsAdapter mAdapter;
    ApiInterface apiInterface;
    ArrayList<BakingRecipes> recipesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, ApplicationUtils.calculateNoOfColumns(this));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecipeCardsAdapter(this, this);
        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ArrayList<BakingRecipes>> call = apiInterface.getBakingRecipes();

        if (!ApplicationUtils.checkInternetConnection(this)) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText("NO INTERNET CONNECTION");
            mRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            call.enqueue(new Callback<ArrayList<BakingRecipes>>() {
                @Override
                public void onResponse(Call<ArrayList<BakingRecipes>> call, Response<ArrayList<BakingRecipes>> response) {
                    recipesArrayList = response.body();
                    mAdapter.swapData(recipesArrayList);
                    mRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<BakingRecipes>> call, Throwable t) {
                    Log.d("MainActivity.class", "failed during call" + t.toString());
                }
            });
        }

    }


    @Override
    public void onItemClick(BakingRecipes recipe) {
        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);

        ArrayList<RecipeSteps> steps = recipe.getmSteps();
        ArrayList<RecipeIngredients> ingredients = recipe.getmIngredients();

        intent.putParcelableArrayListExtra(getString(R.string.steps_key), steps);
        intent.putParcelableArrayListExtra(getString(R.string.ingredients_key), ingredients);
        intent.putExtra(getString(R.string.recipeName_key), recipe.getmRecipeName());

        startActivity(intent);
    }


}
