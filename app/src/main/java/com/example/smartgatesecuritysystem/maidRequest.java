package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class maidRequest extends AppCompatActivity {

    TextInputLayout maidName, maidTime;
    
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_request);
        
        toolbar = findViewById(R.id.maidRequestToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Maid Request");

        maidName = findViewById(R.id.maidRequestName);
        maidTime = findViewById(R.id.maidTiming);


    }

    public void maidRequest(View view) {

        String name = maidName.getEditText().getText().toString();
        String timing = maidTime.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://unremedied-injectio.000webhostapp.com/Smart_Gate/maidRequest.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (!response.equals("Not")) {

                            Toast.makeText(maidRequest.this, "You have entered details Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(maidRequest.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("maidName", name);
                map.put("timing", timing);
                return map;
            }
        };

        queue.add(stringRequest);

    }
}