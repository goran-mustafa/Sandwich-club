package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent

            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
         sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable

            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();

    }

    private void populateUI() {

        TextView origin_tv=findViewById(R.id.origin_tv);

          origin_tv.setText(sandwich.getPlaceOfOrigin());
        TextView also_known_tv=findViewById(R.id.also_known_tv);
        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++){
            if(i+1==sandwich.getAlsoKnownAs().size()){
                also_known_tv.append(sandwich.getAlsoKnownAs().get(i));
            }
            else{
                also_known_tv.append(sandwich.getAlsoKnownAs().get(i)+",");
            }

        }
        TextView ingredients_tv=findViewById(R.id.ingredients_tv);
        for(int i=0;i<sandwich.getIngredients().size();i++){
            if(i+1==sandwich.getIngredients().size()){
                ingredients_tv.append(sandwich.getIngredients().get(i));
            }
            else{
                ingredients_tv.append(sandwich.getIngredients().get(i)+",");
            }

        }

        TextView description_tv=findViewById(R.id.description_tv);
        description_tv.setText(sandwich.getDescription());

    }
}
