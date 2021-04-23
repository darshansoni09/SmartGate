package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class SocietyMainScreen extends AppCompatActivity {

    Toolbar toolbar;

    String blockDetails, flatDetails;

    TextView pendingRequest, maidDetails;

    RecyclerView recyclerView;

    ArrayList<String> visitor_name, status, id;

    Button logout;

    public static final String MY_PREFS_NAME = "UserDetailsOwner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_main_screen);

        pendingRequest = findViewById(R.id.pendingRequest);
        maidDetails = findViewById(R.id.maidDetails);

        logout = findViewById(R.id.logoutSocietyMember);

        SharedPreferences sharedPreferences = this.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = sharedPreferences.edit();

        recyclerView = findViewById(R.id.lastRequestsRecycler);

        visitor_name = new ArrayList<>();
        status = new ArrayList<>();
        id = new ArrayList<>();

        String name = sharedPreferences.getString("name","");

        blockDetails = sharedPreferences.getString("block_number","");
        flatDetails = sharedPreferences.getString("flat_no","");

        toolbar = findViewById(R.id.mainSocietyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hi," + " " + name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

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

                            mainScreenAdapter latestAdapter1 = new mainScreenAdapter(SocietyMainScreen.this, visitor_name, status, id);

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
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        pendingRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SocietyMainScreen.this, PendingRequest.class);

                intent.putExtra("block_number",blockDetails);
                intent.putExtra("flat_no",flatDetails);

                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("id","nothing");
                editor.apply();
                Intent intent = new Intent(SocietyMainScreen.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

//    public void logoutMemberMainScreen(View view) {
//
//        editor.putString("id","nothing");
//        editor.apply();
//        Intent intent = new Intent(SocietyMainScreen.this, MainActivity.class);
//        startActivity(intent);
//
//    }

    public void maidDetailsButton(View view) {
        Intent intent = new Intent(SocietyMainScreen.this, maidDetailsActivity.class);
        startActivity(intent);
    }
}