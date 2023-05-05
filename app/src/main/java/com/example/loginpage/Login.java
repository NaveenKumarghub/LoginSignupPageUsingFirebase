package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    //Firebase code

    private FirebaseAuth auth;
    private EditText loginEmail,loginPassword;
    TextView signupRedirectText;
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // code for firebase
        auth=FirebaseAuth.getInstance();
        loginEmail=findViewById(R.id.useremail);
        loginPassword=findViewById(R.id.userpassword);
        loginButton=findViewById(R.id.loginBtn);
        signupRedirectText=findViewById(R.id.loginredirecttext);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailuser = loginEmail.getText().toString();
                String passuser = loginPassword.getText().toString();


                if (!emailuser.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailuser).matches()) {
                    if (!passuser.isEmpty()) {
                        auth.signInWithEmailAndPassword(emailuser, passuser)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Password cannot be empty");
                    }
                } else if (emailuser.isEmpty()) {
                    loginEmail.setError("Email cannot be empty");
                } else {
                    loginEmail.setError("Please enter the valid email");
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegister();
            }
        });
    }

        public void redirectToRegister(){
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
}