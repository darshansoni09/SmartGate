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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextInputLayout society_name, password;

    TextView securityLogin;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.loginToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Login);

        society_name = findViewById(R.id.societyNameLogin);
        password = findViewById(R.id.societyNamePassword);

        securityLogin = findViewById(R.id.textViewSecurityLogin);
    }

    public void doLogin(View view) {

        Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();

        String name, pass;
        name = society_name.getEditText().getText().toString();
        pass = password.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/loginFile.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("loginresponse", response);

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("0");

                            String loginString = jsonObject.getString("is_login");

                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString("id", jsonObject1.getString("id"));
                            editor.putString("society_name", jsonObject1.getString("society_name"));
                            editor.putString("password", jsonObject1.getString("password"));
                            editor.putString("secretary_name", jsonObject1.getString("secretary_name"));
                            editor.putString("address", jsonObject1.getString("address"));
                            editor.putString("phone_number", jsonObject1.getString("phone_number"));
                            editor.apply();

                            if (loginString.equals("yes")) {
                                Intent intent = new Intent(LoginActivity.this, MainScreen.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this, "Not Registered!", Toast.LENGTH_SHORT).show();
                            }

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
                map.put("society_name", name);
                map.put("password", pass);
                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void GoRegister(View view) {
    }

    public void forgotPass(View view) {
    }

    public void GoSecurity(View view) {

        Intent intent = new Intent(LoginActivity.this, SecurityLogin.class);
        startActivity(intent);

    }
}