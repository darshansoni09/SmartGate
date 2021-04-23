package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PendingRequest extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    String blockDetails, flatDetails;

    ArrayList<String> visitor_name, visitor_type, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request);

        toolbar = findViewById(R.id.pendingRecyclerToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pending Requests");

        recyclerView = findViewById(R.id.pendingRecycler);

        blockDetails = getIntent().getStringExtra("block_number");
        flatDetails = getIntent().getStringExtra("flat_no");

        Log.d("blockDetailsanvaf",blockDetails+flatDetails);

        visitor_name = new ArrayList<>();
        visitor_type = new ArrayList<>();
        id = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/userRequestDetails.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("loginresponse", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                id.add(jsonObject.getString("id"));
                                visitor_name.add(jsonObject.getString("visitor_name"));
                                visitor_type.add(jsonObject.getString("visitor_type"));
                            }

                            latestAdapter latestAdapter1 = new latestAdapter(PendingRequest.this, visitor_name, visitor_type, id);

                            recyclerView.setAdapter(latestAdapter1);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("block_number", blockDetails);
                map.put("flat_number", flatDetails);
                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}