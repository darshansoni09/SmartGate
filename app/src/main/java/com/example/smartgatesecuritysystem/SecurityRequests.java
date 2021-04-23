package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;

public class SecurityRequests extends AppCompatActivity {

    TextView maidRequestTextView, maidRegistration, visitorRequest;

    RecyclerView mRecyclerView;

    Toolbar mToolbar;

    ArrayList<String> visitor_name, status, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_requests);

        maidRequestTextView = findViewById(R.id.maidRequest);
        maidRegistration = findViewById(R.id.maidRegistration);
        visitorRequest = findViewById(R.id.visitorRequest);

        mToolbar = findViewById(R.id.requestToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Security Area");

        mRecyclerView = findViewById(R.id.securityRequestRecycle);

        visitor_name = new ArrayList<>();
        status = new ArrayList<>();
        id = new ArrayList<>();

        maidRequestTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecurityRequests.this, maidRequest.class);
                startActivity(intent);

            }
        });

        maidRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecurityRequests.this, maidRegistration.class);
                startActivity(intent);
            }
        });

        visitorRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecurityRequests.this, visitorRequest.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/securityStatus.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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
                                status.add(jsonObject.getString("status"));
                            }

                            statusAdapter latestAdapter1 = new statusAdapter(SecurityRequests.this, visitor_name, status, id);

                            mRecyclerView.setAdapter(latestAdapter1);


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

    public void logoutButtonSecurity(View view) {

        Intent intent = new Intent(SecurityRequests.this, MainActivity.class);
        startActivity(intent);

    }
}