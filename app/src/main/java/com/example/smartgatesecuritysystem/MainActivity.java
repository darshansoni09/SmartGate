package com.example.smartgatesecuritysystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView registration, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.mainActivityToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_app_name);

        registration = findViewById(R.id.societyRegister);
        login = findViewById(R.id.societyLogin);
    }

    public void registerButtton(View view) {

        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);

    }

    public void loginButton(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void securityLogin(View view) {

        Intent intent = new Intent(MainActivity.this, SecurityLogin.class);
        startActivity(intent);

    }

    public void memberActivity(View view) {

        Intent intent = new Intent(MainActivity.this, MainScreen.class);
        startActivity(intent);

    }
}