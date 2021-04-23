package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout society_name, secretary_name, address, phone_number, society_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        toolbar = findViewById(R.id.registrationToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Register);

        society_name = findViewById(R.id.Society_Name);
        secretary_name = findViewById(R.id.Secretary_Name);
        address = findViewById(R.id.Address);
        phone_number = findViewById(R.id.Phone_Number);
        society_password = findViewById(R.id.Society_Password);

    }


    public void registrationButtton(View view) {

        String name, society, addressString, phone, password;

        name = secretary_name.getEditText().getText().toString();
        society = society_name.getEditText().getText().toString();
        addressString = address.getEditText().getText().toString();
        phone = phone_number.getEditText().getText().toString();
        password = society_password.getEditText().getText().toString();

        Log.d("usernameadvavaefaefv",name+society+addressString+phone+password );

        name = name.replaceAll("\\s", "%20");
        society = society.replaceAll("\\s", "%20");
        addressString = addressString.replaceAll("\\s", "%20");
        password = password.replaceAll("\\s", "%20");


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://unremedied-injectio.000webhostapp.com/Smart_Gate/registerSociety.php?society_name="
                + society + "&password=" + password + "&secretary_name=" +name + "&address=" + addressString + "&phone_number=" + phone;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("urlafvbalfbvef",url);

                        if (!response.equals("Not")) {

                            Toast.makeText(RegistrationActivity.this, "You have Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Please Login with different Username,email,Password!!", Toast.LENGTH_SHORT).show();
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


