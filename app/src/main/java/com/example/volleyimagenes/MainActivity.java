package com.example.volleyimagenes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView lista = findViewById(R.id.Recylist);
        lista.setLayoutManager(new LinearLayoutManager(this));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                (Request.Method.GET, url, null,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Heroes> superheroe;
                        try {
                            JSONArray jsonArray = response.getJSONArray("heroes");
                            if(jsonArray.length()>0){
                                superheroe = Arrays.asList(gson.fromJson(jsonArray.toString(),Heroes[].class));
                               lista.setAdapter(new Adapter(MainActivity.this,superheroe));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } }},
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }});
        Volley.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}