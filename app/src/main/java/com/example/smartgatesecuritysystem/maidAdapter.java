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
public class maidAdapter extends RecyclerView.Adapter<maidAdapter.ViewHolders> {

    maidDetailsActivity maidDetailsActivity;
    ArrayList<String> maidId;
    ArrayList<String> maidName;
    ArrayList<String> availableTime;
    ArrayList<String> phoneNumber;

    public maidAdapter(maidDetailsActivity maidDetailsActivity, ArrayList<String> maidId, ArrayList<String> maidName, ArrayList<String> availableTime, ArrayList<String> phoneNumber) {
        this.maidDetailsActivity = maidDetailsActivity;
        this.maidId = maidId;
        this.maidName = maidName;
        this.availableTime = availableTime;
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) maidDetailsActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.statusitem_file, parent, false);
        ViewHolders v = new ViewHolders(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {

        holder.maid_name.setText(maidName.get(position));
        holder.maidAvailableTime.setText(availableTime.get(position));
        holder.phone.setText(phoneNumber.get(position));

    }

    @Override
    public int getItemCount() {
        return maidId.size();
    }

    static class ViewHolders extends RecyclerView.ViewHolder {
        TextView maid_name, maidAvailableTime, phone;


        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            maid_name=itemView.findViewById(R.id.maidNameItemFile);
            maidAvailableTime = itemView.findViewById(R.id.maidAvailableTimingItemFile);
            phone = itemView.findViewById(R.id.phoneNumberItemFile);
        }
    }
}
