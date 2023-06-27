package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText loginemail;
    EditText loginpassward;
    Button loginbutton;
     String email;
    TextView logintosignup,forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            auth=FirebaseAuth.getInstance();
            loginemail=findViewById(R.id.logineditemail);
            loginpassward=findViewById(R.id.logineditpassward);
            loginbutton=findViewById(R.id.loginbutton);
            logintosignup=findViewById(R.id.loginlogin);
            forgot=findViewById(R.id.forgotemailpass);






            logintosignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(loginActivity.this,signUpActivity.class));

                    Toast.makeText(loginActivity.this, "Sign up", Toast.LENGTH_SHORT).show();

                }
            });



            forgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validataData();
                }
            });





        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=loginemail.getText().toString();
                String pass=loginpassward.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email,pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Intent intent=new Intent(loginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(loginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(loginActivity.this, "Login Failed !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else
                    {
                        loginpassward.setError("Passward cannot be empty");
                    }
                }else if(email.isEmpty()){
                    loginemail.setError("Email cannot be empty");

                }
                else{
                    loginemail.setError("Please enter valid email");
                }
            }
        });







        }


        private void validataData()
        {

            email=loginemail.getText().toString();
            if(email.isEmpty())
            {
                loginemail.setError("Email required");
            }
            else
            {
                forgotpass();
            }

        }




        private void forgotpass()
        {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(loginActivity.this, "Check your Email", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(loginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
