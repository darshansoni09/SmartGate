package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class OwnerLogin extends AppCompatActivity {

    Toolbar toolbar;

    TextInputLayout ownerName, ownerPassword;

    public static final String MY_PREFS_NAME = "UserDetailsOwner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        toolbar = findViewById(R.id.ownerToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Owner Login");

        ownerName = findViewById(R.id.ownerNameLogin);
        ownerPassword = findViewById(R.id.ownerNamePassword);
    }

    public void ownerLogin(View view) {

        String name, password;

        name = ownerName.getEditText().getText().toString();
        password = ownerPassword.getEditText().getText().toString();

        Log.d("namevafvbslfvs",name+password);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/tanentLogin.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("0");

                            String loginString = jsonObject.getString("is_login");

                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString("id", jsonObject1.getString("id"));
                            editor.putString("block_number", jsonObject1.getString("block_number"));
                            editor.putString("flat_no", jsonObject1.getString("flat_no"));
                            editor.putString("name", jsonObject1.getString("name"));
                            editor.putString("type", jsonObject1.getString("type"));
                            editor.putString("password", jsonObject1.getString("password"));
                            editor.apply();

                            if (loginString.equals("yes")) {
                                Intent intent = new Intent(OwnerLogin.this, SocietyMainScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(OwnerLogin.this, "Not Registered!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("password", password);
                return map;
            }
        };

        queue.add(stringRequest);

    }
}