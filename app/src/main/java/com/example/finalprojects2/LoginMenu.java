package com.example.finalprojects2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginMenu extends AppCompatActivity {

    TextView admin,staff, user,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);

        admin=(TextView) findViewById(R.id.tvAdmLogin);
        staff=(TextView)findViewById(R.id.tvstaffLogin);
        user=(TextView)findViewById(R.id.tvUser);
        about=(TextView)findViewById(R.id.tvAbout);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginMenu.this, UserLogin.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMenu.this, AdminLogin.class));
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMenu.this, StaffLogin.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMenu.this, About.class));
            }
        });
    }
    public void onBackPressed(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}