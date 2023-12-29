package com.example.assigmnent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class Team extends AppCompatActivity {
    TextView text;
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String>teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        text=findViewById(R.id.team);
        listView=findViewById(R.id.teamlist);
        Intent intent=getIntent();
        String cid=intent.getExtras().getString("cid");
        getDataTeam(cid);





    }
    private void getDataTeam(String cid){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.sportmonks.com/v3/football/teams";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray TeamArray = jsonResponse.getJSONArray("data");
//                                String result="";
                            teams=new ArrayList<>();
                            for (int i = 0; i < TeamArray.length(); i++) {
                                JSONObject leagueObject = TeamArray.getJSONObject(i);
                                String TeamName = leagueObject.getString("name");
//                                result+=TeamName+"\n";
                                String land=leagueObject.getString("country_id");
                                if (land.equals(cid)){
                                    teams.add(TeamName);

                                }
                                adapter = new ArrayAdapter<>(Team.this, android.R.layout.simple_list_item_1,teams);
                                listView.setAdapter(adapter);



                            }




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
