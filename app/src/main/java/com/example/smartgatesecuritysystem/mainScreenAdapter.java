package com.example.smartgatesecuritysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Darshan Soni on 22/4/21.
 */
public class mainScreenAdapter extends RecyclerView.Adapter<mainScreenAdapter.ViewHolders> {

    SocietyMainScreen securityRequests;
    ArrayList<String> visitor_name;
    ArrayList<String> status;
    ArrayList<String> id;

    public mainScreenAdapter(SocietyMainScreen securityRequests, ArrayList<String> visitor_name, ArrayList<String> status, ArrayList<String> id) {

        this.securityRequests = securityRequests;
        this.visitor_name = visitor_name;
        this.status = status;
        this.id = id;

    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) securityRequests.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.statusitem_file, parent, false);
        ViewHolders v = new ViewHolders(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        holder.status.setText(status.get(position));
        holder.visitorName.setText(visitor_name.get(position));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    static class ViewHolders extends RecyclerView.ViewHolder {
        TextView visitorName, status;


        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            visitorName=itemView.findViewById(R.id.visitorNameStatus);
            status = itemView.findViewById(R.id.visitorTypeStatus);
        }
    }
}
