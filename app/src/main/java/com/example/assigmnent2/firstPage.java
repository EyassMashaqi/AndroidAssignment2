package com.example.assigmnent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class firstPage extends AppCompatActivity {
    TextView league;
    ListView listView;
    Button button;
    List<League>leaguess;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        league = findViewById(R.id.team);
        listView = findViewById(R.id.teamlist);


        getData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(firstPage.this, Team.class);
                intent.putExtra("cid",leaguess.get(position).getCountryid());
                startActivity(intent);
            }
        });

    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.sportmonks.com/v3/football/leagues";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray leaguesArray = jsonResponse.getJSONArray("data");

                            ArrayList<String>leagueNames=new ArrayList<>();
                           leaguess=new ArrayList<>();

                            for (int i = 0; i < leaguesArray.length(); i++) {
                                JSONObject leagueObject = leaguesArray.getJSONObject(i);
                                String leagueName = leagueObject.getString("name");
                                String Countryid=leagueObject.getString("country_id");
                               leaguess.add(new League(leagueName,Countryid));
                                leagueNames.add(leagueName);
                            }


                            adapter = new ArrayAdapter<>(firstPage.this,
                                    android.R.layout.simple_list_item_1, leagueNames);


                            listView.setAdapter(adapter);


                        } catch (JSONException e) {
                            Log.e("JSONException", e.getMessage());

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "yY7xI0qbnzVKnBjLkReFq9mL8Z0845kzmSEwEdShuPFxQKhwCA48C5ERs69N");
                return headers;
            }

        };

        queue.add(stringRequest);

    }

}
