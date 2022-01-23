package com.example.finalprojects2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegister extends AppCompatActivity implements View.OnClickListener {

    private Button registerUser;
    private TextView login;
    private EditText etNama, etNo, etEmail, etPass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    static DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        mAuth = FirebaseAuth.getInstance();
        login = (TextView) findViewById(R.id.tvLogin);
        login.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.btnReg);
        registerUser.setOnClickListener(this);

        etNama = (EditText) findViewById(R.id.etFullName);
        etNo = (EditText) findViewById(R.id.etUmur);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvLogin:
                startActivity(new Intent(this, UserLogin.class));
                break;
            case R.id.btnReg:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        String fullname = etNama.getText().toString().trim();
        String no = etNo.getText().toString().trim();

        if (fullname.isEmpty()){
            etNama.setError("Isi Nama Lengkap Anda!");
            etNama.requestFocus();
            return;
        }
        if (no.isEmpty()){
            etNo.setError("Isi Nomor Anda Anda!");
            etNo.requestFocus();
            return;
        }
        if (email.isEmpty()){
            etEmail.setError("Isi Email Anda!");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Masukkan Email Yang Valid");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            etPass.setError("Isi Password !");
            etPass.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPass.setError("Minimal Kata Sandi Harus 6 Karakter !");
            etPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullname, no, email, password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserRegister.this, "Berhasil Registrasi, Silahkan Login !", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(UserRegister.this, UserLogin.class));
                                    } else {
                                        Toast.makeText(UserRegister.this, "Registrasi Gagal! Silahkan Coba Lagi", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(UserRegister.this, "Registrasi Gagal! Silahkan Coba Lagi", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
    public static void getuser(){
        databaseUsers=FirebaseDatabase.getInstance().getReference("Users");
    }
}