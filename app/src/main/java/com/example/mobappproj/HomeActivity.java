package com.example.mobappproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> publishers = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();

    Button goBackButton;

    TextView welUser;

    SharedPreferences sharedPreferences;

    public static final String filename = "validation";
    public static final String uName = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        welUser = findViewById(R.id.welcomeUser);

        goBackButton = findViewById(R.id.goBackButton);

        sharedPreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(uName)){
            welUser.setText("Welcome, " + sharedPreferences.getString(uName, ""));
        }

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.gamesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        {
            try {
                JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
                JSONArray jsonArray = jsonObject.getJSONArray("games");
                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject gameData =jsonArray.getJSONObject(i);

                    titles.add(gameData.getString("Title"));
                    genres.add(gameData.getString("Genre"));
                    publishers.add(gameData.getString("Publisher"));
                    prices.add(gameData.getString("Price"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Adapter helperAdapter = new Adapter(HomeActivity.this, titles, publishers, genres, prices);
            recyclerView.setAdapter(helperAdapter);
        }
    }

    private String JsonDataFromAsset() {
        String json = "";

        try {
            InputStream inputStream = getAssets().open("GamesJson.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}