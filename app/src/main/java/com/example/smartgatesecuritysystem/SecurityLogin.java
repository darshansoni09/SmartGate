package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SecurityLogin extends AppCompatActivity {

    TextInputLayout securityName, securityPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_login);

        securityName = findViewById(R.id.securityNameLogin);
        securityPassword = findViewById(R.id.securityNamePassword);

    }

    public void forgotPass(View view) {
    }

    public void doLogin(View view) {

        String name, password;
        name = securityName.getEditText().getText().toString();
        password = securityPassword.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/securityLogin.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("loginresponse", response);

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("0");

                            String loginString = jsonObject.getString("is_login");

//                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                            editor.putString("id", jsonObject1.getString("id"));
//                            editor.putString("society_name", jsonObject1.getString("society_name"));
//                            editor.putString("password", jsonObject1.getString("password"));
//                            editor.putString("secretary_name", jsonObject1.getString("secretary_name"));
//                            editor.putString("address", jsonObject1.getString("address"));
//                            editor.putString("phone_number", jsonObject1.getString("phone_number"));
//                            editor.apply();

                            if (loginString.equals("yes")) {
                                Intent intent = new Intent(SecurityLogin.this, SecurityRequests.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SecurityLogin.this, "Not Registered!", Toast.LENGTH_SHORT).show();
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
                map.put("securityName", name);
                map.put("securityPassword", password);
                return map;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}