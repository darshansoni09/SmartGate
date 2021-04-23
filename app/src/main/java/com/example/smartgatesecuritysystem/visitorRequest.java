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

public class visitorRequest extends AppCompatActivity {

    TextInputLayout visitorName, entryTime, vehicleAvailability, vehicleNumber, visitorType, blockNumber, flatNumber;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_request);

        visitorName = findViewById(R.id.visitorRequestName);
        entryTime = findViewById(R.id.visitTiming);
        vehicleAvailability = findViewById(R.id.vehicleAvailable);
        vehicleNumber = findViewById(R.id.vehicleNumber);
        visitorType = findViewById(R.id.visitorRequestType);
        blockNumber = findViewById(R.id.blockNumberRequest);
        flatNumber = findViewById(R.id.FlatNumberRequest);

        toolbar = findViewById(R.id.visitorRequestToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Visitor Request");
    }

    public void visitorRequest(View view) {

        String name, timing, vehicleAvailable, vehicle_Number, visitor_type, block_number, flat_number;

        name = visitorName.getEditText().getText().toString();
        timing = entryTime.getEditText().getText().toString();
        vehicleAvailable = vehicleAvailability.getEditText().getText().toString();
        vehicle_Number = vehicleNumber.getEditText().getText().toString();
        visitor_type = visitorType.getEditText().getText().toString();
        block_number = blockNumber.getEditText().getText().toString();
        flat_number = flatNumber.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://unremedied-injectio.000webhostapp.com/Smart_Gate/visitorRequest.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (!response.equals("Not")) {

                            Toast.makeText(visitorRequest.this, "You have entered details Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(visitorRequest.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
                map.put("visitor_name", name);
                map.put("visitor_type", visitor_type);
                map.put("time", timing);
                map.put("vehicle_available", vehicleAvailable);
                map.put("vehicle_number", vehicle_Number);
                map.put("block_number", block_number);
                map.put("flat_number", flat_number);
                map.put("status", "Pending");

                return map;
            }
        };

        queue.add(stringRequest);

    }
}