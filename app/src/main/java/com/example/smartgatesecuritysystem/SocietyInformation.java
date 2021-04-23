package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

public class SocietyInformation extends AppCompatActivity {

    Toolbar toolbar;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    SharedPreferences pref;

    TextView nameTextView;

    String id;

    TextInputLayout totalBlocks, totalFloors, flatsPerFloor, securityName, securityPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_information);

        toolbar = findViewById(R.id.societyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.information);

        nameTextView = findViewById(R.id.societyNameInfo);

        totalBlocks = findViewById(R.id.societyTotalBlocks);
        totalFloors = findViewById(R.id.societyTotalFloors);
        flatsPerFloor = findViewById(R.id.societyFlatsPerFloor);
        securityName = findViewById(R.id.societySecurityName);
        securityPassword = findViewById(R.id.societySecurityPassword);

        pref = this.getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        String name = pref.getString("society_name","nothing");
        id = pref.getString("id","nothing");

        nameTextView.setText(name + "'s Information");
        
        

    }

    public void societySubmitButton(View view) {

        String blocks, floors, flats, security_name, security_password;

        blocks = totalBlocks.getEditText().getText().toString();
        floors = totalFloors.getEditText().getText().toString();
        flats = flatsPerFloor.getEditText().getText().toString();
        security_name = securityName.getEditText().getText().toString();
        security_password = securityPassword.getEditText().getText().toString();

        security_name = security_name.replaceAll("\\s", "%20");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://unremedied-injectio.000webhostapp.com/Smart_Gate/societyInfo.php?id="
                + id + "&totalBlocks=" + blocks + "&totalFloors=" +floors + "&flatsPerFloor=" + flats + "&securityName=" + security_name + "&securityPassword=" + security_password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("urlInfo",url);

                        if (!response.equals("Not")) {

                            Toast.makeText(SocietyInformation.this, "You have Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SocietyInformation.this, MainScreen.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SocietyInformation.this, "Please Login with different Username,email,Password!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }
}