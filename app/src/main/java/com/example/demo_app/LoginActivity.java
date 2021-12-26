package com.example.demo_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    private EditText emailtxt,passwordtxt;
    private Button loginBtn;
    private TextView forgotPwd;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_login);


        emailtxt = findViewById(R.id.emailTxt);
        passwordtxt = findViewById(R.id.passwordTxt);
        loginBtn = findViewById(R.id.loginBtn);
        forgotPwd = findViewById(R.id.forgotPassword);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(LoginActivity.this, resetPassword.class);
                startActivity(forgot);
            }
        });

    }

    private void Login() {
        final String email = emailtxt.getText().toString().trim();
        final String password = passwordtxt.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            emailtxt.setError("email is required!");
            return;
        }
        if (TextUtils.isEmpty(password)){
            passwordtxt.setError("Password is Required!");
            return;
        }
        if (password.length()< 6){
            passwordtxt.setError("Min length for password is 6 charachters");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ClientActivity.class));

                }else {
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                    Toast.makeText(LoginActivity.this,"Logging in failed"+ errorCode,Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}