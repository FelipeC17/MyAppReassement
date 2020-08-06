package com.example.myapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {


    EditText rFullname, rEmail, rPassword;
    Button rButton;
    TextView Glogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rFullname = findViewById(R.id.fullname);
        rEmail = findViewById(R.id.email);
        rPassword = findViewById(R.id.password1);
        rButton = findViewById(R.id.Registerbutton);
        Glogin = findViewById(R.id.textView3);
        fAuth = FirebaseAuth.getInstance();


        if (fAuth.getCurrentUser() != null) {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }


        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    rEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    rPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6 ) {

                    rPassword.setError("Password must contain 6 or more characters");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }

        }) ;

        Glogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Login.class));

            }
        });


    }


}