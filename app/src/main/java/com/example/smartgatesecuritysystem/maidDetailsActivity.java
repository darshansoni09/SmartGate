package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class maidDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recycler;

    ArrayList<String> maidName, availableTime, phoneNumber, maidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_details);

        toolbar = findViewById(R.id.maidDetailsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Maid Details");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        maidName = new ArrayList<>();
        availableTime = new ArrayList<>();
        phoneNumber = new ArrayList<>();
        maidId = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/maidData.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("loginresponse", response);

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                maidId.add(jsonObject.getString("maidId"));
                                maidName.add(jsonObject.getString("maidName"));
                                availableTime.add(jsonObject.getString("availableTime"));
                                phoneNumber.add(jsonObject.getString("phoneNumber"));
                            }

                            maidAdapter latestAdapter1 = new maidAdapter(maidDetailsActivity.this, maidId, maidName, availableTime, phoneNumber);

                            recycler.setAdapter(latestAdapter1);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}