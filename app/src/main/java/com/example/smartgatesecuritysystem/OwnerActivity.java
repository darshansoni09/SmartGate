package com.example.smartgatesecuritysystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class OwnerActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageView;

    TextInputLayout ownerName, tanentName, flat_number, block_number, password;

    private Bitmap bitmap = null;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        toolbar = findViewById(R.id.ownerToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Owner Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.ownerImage);
        ownerName = findViewById(R.id.ownerActivityName);
        flat_number = findViewById(R.id.ownerFlatNumber);
        block_number = findViewById(R.id.ownerBlockNumber);
        password = findViewById(R.id.ownerPassword);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            try{

                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

        }
    }

    public void ownerSubmit(View view) {

        String name = ownerName.getEditText().getText().toString();
        String flat = flat_number.getEditText().getText().toString();
        String block = block_number.getEditText().getText().toString();
        String ownerPassword = password.getEditText().getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://unremedied-injectio.000webhostapp.com/Smart_Gate/memberRegistration.php";

        Log.d("DataURL",url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (!response.equals("Not")) {

                            Toast.makeText(OwnerActivity.this, "You have Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(OwnerActivity.this, SocietyMainScreen.class);

                            intent.putExtra("name", name);
                            startActivity(intent);
                        } else {
                            Toast.makeText(OwnerActivity.this, "Please Login with different Username,email,Password!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OwnerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                map.put("block_number", block);
                map.put("flat_no", flat);
                map.put("name", name);
                map.put("type", "owner");
                map.put("password", ownerPassword);

                return map;
            }
        };
        queue.add(stringRequest);
    }
}