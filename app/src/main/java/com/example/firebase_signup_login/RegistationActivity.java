package com.example.firebase_signup_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistationActivity extends AppCompatActivity {
    EditText username,password;
    Button signup;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        mAuth=FirebaseAuth.getInstance();

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.regi);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail=username.getText().toString().trim();
                String mPass=password.getText().toString().trim();

                if (TextUtils.isEmpty(mEmail)){
                    username.setError("Required Field..");
                    return;
                }if (TextUtils.isEmpty(mPass)) {
                    password.setError("Require Field....");
                }
                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(RegistationActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegistationActivity.this, "Somethig went Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }
}
