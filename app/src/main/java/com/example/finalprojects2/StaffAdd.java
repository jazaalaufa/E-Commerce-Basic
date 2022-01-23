package com.example.finalprojects2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffAdd extends AppCompatActivity {

    EditText stfName,stfPass,stfRePass;
    Button regStaff;
    static DatabaseReference databaseStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add);

        databaseStaff= FirebaseDatabase.getInstance().getReference("staffReg");
        stfName=(EditText)findViewById(R.id.etName);
        stfPass=(EditText) findViewById(R.id.etPw);
        stfRePass=(EditText)findViewById(R.id.etKonPw);
        regStaff=(Button) findViewById(R.id.btnAddStaff);
        regStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStaff();
            }
        });



    }
    public void registerStaff(){
        String name=stfName.getText().toString();
        String pass=stfPass.getText().toString();
        String repass=stfRePass.getText().toString();
        if(TextUtils.isEmpty(name))
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(pass))
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
        else if(!pass.equals(repass))
            Toast.makeText(this,"Confirmed password does not match the given password",Toast.LENGTH_SHORT).show();
        else{
            String id=databaseStaff.push().getKey();
            StaffReg staffReg=new StaffReg(name,pass,id);
            databaseStaff.child(id).setValue(staffReg);
            Toast.makeText(this,"Staff Registered",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StaffAdd.this, StaffFragment.class));
        }
    }

    public static void getStaff(){
        databaseStaff=FirebaseDatabase.getInstance().getReference("staffReg");
    }

    public void onBackPressed(){
        Intent intent=new Intent(StaffAdd.this, StaffFragment.class);
        intent.putExtra("CALLINGACTIVITY","AddStaff1");
        startActivity(intent);
    }
}
