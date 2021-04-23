package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        toolbar = findViewById(R.id.mainScreenToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smart Gate");
    }

    public void newTanent(View view) {
        Intent intent = new Intent(MainScreen.this,NewTanentAcitvity.class);
        startActivity(intent);
    }

    public void newOwnership(View view) {
        Intent intent = new Intent(MainScreen.this,OwnerActivity.class);
        startActivity(intent);
    }

    public void newTanentLogin(View view) {

        Intent intent = new Intent(MainScreen.this,TanentLogin.class);
        startActivity(intent);

    }


    public void ownerLoginMain(View view) {

        Intent intent = new Intent(MainScreen.this,OwnerLogin.class);
        startActivity(intent);
    }
}