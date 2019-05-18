package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
         String mainName;
         List<String> alsoKnownAs = new ArrayList<String>();
         String placeOfOrigin;
         String description;
         String image;
         List<String> ingredients = new ArrayList<String>();

        JSONObject data=new JSONObject(json);
        JSONObject name=data.getJSONObject("name");
        mainName=name.getString("mainName");
        JSONArray alsoknow=name.getJSONArray("alsoKnownAs");
        for(int i=0;i<alsoknow.length();i++){
            alsoKnownAs.add(alsoknow.getString(i));

        }
        placeOfOrigin=data.getString("placeOfOrigin");
        description=data.getString("description");
        image=data.getString("image");
        JSONArray ingredint=data.getJSONArray("ingredients");
        for(int i=0;i<ingredint.length();i++){
            ingredients.add(ingredint.getString(i));

        }

        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
