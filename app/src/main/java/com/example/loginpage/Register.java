package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionScene;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Register extends AppCompatActivity {

//    private TextView textView;
//    private Button btn;
//    private EditText userName;
//    private EditText Email;
//    private EditText Password;
//    private EditText Phone;

    private FirebaseAuth auth;
    private EditText signupEmail,signupPassword,signupName,signupPhone;
    private Button signupButton;
    private TextView loginRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //firebase code
        auth = FirebaseAuth.getInstance();
        signupName = findViewById(R.id.fullname);
        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        signupPhone = findViewById(R.id.phone);
        signupButton = findViewById(R.id.registerBtn);
        loginRedirectText = findViewById(R.id.createtext);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernametext = signupName.getText().toString();
                String usermailtext = signupEmail.getText().toString().trim();
                String userpasstext = signupPassword.getText().toString().trim();
                String userphonetext = signupPhone.getText().toString();

                if (!usermailtext.contains("@")) {
                    signupEmail.setError("User email can't be empty");
                }
                if (userpasstext.isEmpty()) {
                    signupPassword.setError("User password can't be empty");

                }
                 else {
                    auth.createUserWithEmailAndPassword(usermailtext, userpasstext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registeration Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(Register.this, "Registeration Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Redirect();
            }
        });

    }
    public void Redirect()
    {
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }

}