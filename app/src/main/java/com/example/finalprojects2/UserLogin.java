package com.example.finalprojects2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserLogin extends AppCompatActivity  implements View.OnClickListener {

    private TextView register, resetPass;
    private EditText etEmail, etPw;
    private Button masuk;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        register = (TextView) findViewById(R.id.tvReg);
        register.setOnClickListener(this);

        masuk = (Button) findViewById(R.id.btnLogin);
        masuk.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPw = (EditText) findViewById(R.id.etPass);

        resetPass = (TextView) findViewById(R.id.tvForgotPass);
        resetPass.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


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
            case R.id.tvReg:
                startActivity(new Intent(this, UserRegister.class));
                break;
            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.tvForgotPass:
                startActivity(new Intent(this, UserResetPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPw.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Isi Email Anda!");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Masukkan Email Yang Valid");
            etEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPw.setError("Isi Password !");
            etPw.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPw.setError("Minimal Kata Sandi Harus 6 Karakter !");
            etPw.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                finish();
                                startActivity(new Intent(UserLogin.this, HomePage.class));
                                progressBar.setVisibility(View.GONE);
                                finish();
                            } else {
                                user.sendEmailVerification();
                                Toast.makeText(UserLogin.this, "Cek Email Anda Untuk Verifikasi !", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        } else {
                            Toast.makeText(UserLogin.this, "Email Atau Password Salah!\n Silahkan Coba Lagi", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

}