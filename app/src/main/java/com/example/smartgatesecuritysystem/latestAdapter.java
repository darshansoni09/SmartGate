package com.example.smartgatesecuritysystem;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Darshan Soni on 22/4/21.
 */
public class latestAdapter extends RecyclerView.Adapter<latestAdapter.ViewHolders> {

    PendingRequest pendingRequest;
    ArrayList<String> visitor_name;
    ArrayList<String> visitor_type;
    ArrayList<String> id;

    public latestAdapter(PendingRequest pendingRequest, ArrayList<String> visitor_name, ArrayList<String> visitor_type, ArrayList<String> id) {
        this.pendingRequest = pendingRequest;
        this.visitor_name = visitor_name;
        this.visitor_type = visitor_type;
        this.id = id;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) pendingRequest.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.latestitem_file, parent, false);
        ViewHolders v = new ViewHolders(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        holder.visitorType.setText(visitor_type.get(position));
        holder.visitorName.setText(visitor_name.get(position));

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(pendingRequest);
                String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/updateStatus.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("loginresponseasvalhfvah", response);

                                if (!response.equals("Not")){
                                    Toast.makeText(pendingRequest, "Request Accepted Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(pendingRequest,SocietyMainScreen.class);
                                    pendingRequest.startActivity(intent);
                                }else{
                                    Toast.makeText(pendingRequest, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                }
//
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", String.valueOf(id.get(position)));
                        map.put("status", "Accepted");
                        return map;
                    }
                };

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

        holder.declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(pendingRequest);
                String url = "https://unremedied-injectio.000webhostapp.com/Smart_Gate/updateStatus.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("loginresponse", response);

                                if (!response.equals("Not")){
                                    Toast.makeText(pendingRequest, "Request Declined Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(pendingRequest,SocietyMainScreen.class);
                                    pendingRequest.startActivity(intent);
                                }else{
                                    Toast.makeText(pendingRequest, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                }
//
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", String.valueOf(id));
                        map.put("status", "Declined");
                        return map;
                    }
                };

// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    static class ViewHolders extends RecyclerView.ViewHolder {
        TextView visitorName, visitorType;
        Button acceptButton, declineButton;


        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            visitorName=itemView.findViewById(R.id.visitorNameRecycle);
            visitorType = itemView.findViewById(R.id.visitorTypeRecycle);

            acceptButton = itemView.findViewById(R.id.acceptButton);
            declineButton = itemView.findViewById(R.id.declineButton);
        }
    }
}
