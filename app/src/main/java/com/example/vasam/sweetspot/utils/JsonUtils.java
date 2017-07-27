package com.example.vasam.sweetspot.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.JsonReader;
import android.widget.Toast;

import com.example.vasam.sweetspot.model.BakingRecipes;
import com.example.vasam.sweetspot.model.RecipeIngredients;
import com.example.vasam.sweetspot.model.RecipeSteps;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by vasam on 7/25/2017.
 */

/*
  This class includes helper methods to parse the json file from asset folder.
 */
public class JsonUtils {

    /**
     * This method is used for parsing the Json file.
     * @param context application context
     * @return ArrayList<BakingRecipes> list of recipes
     * @throws IOException
     */
    public static ArrayList<BakingRecipes> readJsonStream(Context context) throws IOException {

        ArrayList<BakingRecipes> recipes = new ArrayList<>();
        JsonReader jsonReader = getJsonReader(context);
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                recipes.add(readStream(jsonReader));
            }
            jsonReader.endArray();
            return recipes;
        } finally {
            jsonReader.close();
        }
    }

    /**
     * this method is used for creating a JsonReader.
     * @param context application Context
     * @return JsonReader JsonReader object pointing to the JSON array of recipes.
     * @throws IOException throws Exception when recipes file is not found
     */
    private static JsonReader getJsonReader(Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        String uri = null;
        try {
            for (String asset : assetManager.list("")) {
                if (asset.endsWith(".json")) {
                    uri = "asset:///" + asset;
                }
            }
        } catch (IOException e) {
            Toast.makeText(context, "Error loading one or more recipe", Toast.LENGTH_LONG)
                    .show();
        }

        String userAgent = Util.getUserAgent(context, "SweetSpot");
        DataSource dataSource = new DefaultDataSource(context, null, userAgent, false);
        DataSpec dataSpec = new DataSpec(Uri.parse(uri));
        InputStream inputStream = new DataSourceInputStream(dataSource, dataSpec);

        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        } finally {
            Util.closeQuietly(dataSource);
        }

        return reader;
    }

    /**
     * returns a bakingRecipe object after fetching all the details required for that bakingRecipe object
     * @param jsonReader JsonReader object pointing to the JSON array of recipes.
     * @return BakingRecipes object
     * @throws IOException
     */
    private static BakingRecipes readStream(JsonReader jsonReader) throws IOException {
        int id = -1;
        String title = null;
        ArrayList<RecipeIngredients> ingredients = null;
        ArrayList<RecipeSteps> steps = null;
        int servings = -1;
        String image = null;

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals("id")) {
                id = jsonReader.nextInt();
            } else if (name.equals("name")) {
                title = jsonReader.nextString();

            } else if (name.equals("ingredients")) {
                ingredients = readIngredients(jsonReader);

            } else if (name.equals("steps")) {
                steps = readSteps(jsonReader);

            } else if (name.equals("servings")) {
                servings = jsonReader.nextInt();

            } else if (name.equals("image")) {
                    image = jsonReader.nextString();

            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new BakingRecipes(id, title, ingredients, steps, servings, image);
    }

    /**
     * returns list of all the steps required for single baking recipe.
     * @param jsonReader JsonReader object pointing to the JSON array of steps.
     * @return ArrayList<RecipeSteps> returns list of RecipeSteps objects
     * @throws IOException
     */
    private static ArrayList<RecipeSteps> readSteps(JsonReader jsonReader) throws IOException {

        ArrayList<RecipeSteps> steps = new ArrayList<>();
        int id = -1;
        String shortDescription = null;
        String description = null;
        String videoURL = null;
        String thumbnailURL = null;

        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if (name.equals("id")) {
                    id = jsonReader.nextInt();
                } else if (name.equals("shortDescription")) {
                    shortDescription = jsonReader.nextString();

                } else if (name.equals("description")) {
                    description = jsonReader.nextString();

                } else if (name.equals("videoURL")) {
                        videoURL = jsonReader.nextString();

                } else if (name.equals("thumbnailURL")) {
                        thumbnailURL = jsonReader.nextString();

                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            steps.add(new RecipeSteps(id, shortDescription, description, videoURL, thumbnailURL));

        }
        jsonReader.endArray();
        return steps;
    }

    /**
     * returns list of all ingredients required for single baking recipe
     * @param jsonReader JsonReader object pointing to the JSON array of ingredients.
     * @return ArrayList<RecipeIngredients> returns list of RecipeIngredients objects.
     * @throws IOException
     */
    private static ArrayList<RecipeIngredients> readIngredients(JsonReader jsonReader) throws IOException {
        ArrayList<RecipeIngredients> ingredients = new ArrayList<>();
        double quantity = -1;
        String measure = null;
        String ingredient = null;
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if (name.equals("quantity")) {
                    quantity = jsonReader.nextDouble();
                } else if (name.equals("measure")) {
                    measure = jsonReader.nextString();

                } else if (name.equals("ingredient")) {
                    ingredient = jsonReader.nextString();
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            ingredients.add(new RecipeIngredients(quantity, measure, ingredient));

        }
        jsonReader.endArray();
        return ingredients;
    }
}
