package com.example.vasam.sweetspot.retrofit;

import com.example.vasam.sweetspot.model.BakingRecipes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vasam on 8/1/2017.
 */

public interface ApiInterface {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<BakingRecipes>> getBakingRecipes();
}
