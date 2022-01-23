package com.example.finalprojects2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    EditText username,pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.etEmail1);
        pass = findViewById(R.id.etPass1);
        login = findViewById(R.id.btnLogin1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = pass.getText().toString();

                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                    startActivity(new Intent(AdminLogin.this, AdminPage.class));
                                    finish();
                                }
                            } else {
                                Toast.makeText(AdminLogin.this, "Kata sandi salah", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(AdminLogin.this, "Data belum terdaftar", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getDataLogin(this)) {
            if (Preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, AdminPage.class));
                finish();
            } else {
                startActivity(new Intent(this, HomePage.class));
                finish();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}