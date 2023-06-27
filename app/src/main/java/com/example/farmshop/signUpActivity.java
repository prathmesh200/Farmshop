package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText signupemail;
    EditText signuppassward;
    EditText signupconfirmpassward;
    EditText signupphoneno;
    Button signupbutton;
    TextView signuptologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

            auth=FirebaseAuth.getInstance();
            signupemail=findViewById(R.id.signupeditemail);
            signuppassward=findViewById(R.id.signupeditpassward);
            signupconfirmpassward=findViewById(R.id.signupeditpassward2);
            signupphoneno=findViewById(R.id.signupeditTextPhone);
            signupbutton=findViewById(R.id.signupbutton);
            signuptologin=findViewById(R.id.singuplogintext);


        signupphoneno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validaMobile(signupphoneno.getText().toString())) {
                    signupbutton.setEnabled(true);
                } else {
                    signupbutton.setEnabled(false);
                    signupphoneno.setError("Invalid Phone no ");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });




        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=signupemail.getText().toString().trim();
                String pass=signuppassward.getText().toString().trim();
                String confirmpass=signupconfirmpassward.getText().toString().trim();
                String phoneno=signupphoneno.getText().toString().trim();

                if(user.isEmpty() ){
                    signupemail.setError("Email cannot be empty");
                }
                if(pass.isEmpty() ){
                    signuppassward.setError("Passward cannot be  empty ");
                }
                if(confirmpass.isEmpty() && isvalid(confirmpass) ){

                    signupconfirmpassward.setError("Confirm passward cannot be empty and passward must be same ");
                }
                if(phoneno.isEmpty()){
                    signupphoneno.setError("Phone no cannot be  empty ");
                }


                else{


                    if( pass.compareTo(confirmpass)==0) {

                        if(isvalid(pass) && isvalid(confirmpass)) {


                            auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {




                                        if (task.isSuccessful()) {

                                            Toast.makeText(signUpActivity.this, "Signup Successfully !", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(signUpActivity.this, loginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(signUpActivity.this, "Signup Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }


                                }
                            });

                        }
                        else{
                            Toast.makeText(signUpActivity.this, "Passward must contain at least 8 character,having letter,digit and specific symbol", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Passeard and Confirm Passward didn't match ",Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });


            signuptologin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(signUpActivity.this,loginActivity.class));

                    Toast.makeText(signUpActivity.this, "Login page", Toast.LENGTH_SHORT).show();

                }
            });





        }


    boolean validaMobile(String input){
        Pattern p=Pattern.compile("[6-9][0-9]{9}");

        Matcher m=p.matcher(input);
        return m.matches();
    }



    public static boolean isvalid(String passwardere){
        int f1=0,f2=0,f3=0;
        if(passwardere.length()<8){
            return false;
        }
        else{
            for(int p=0;p<passwardere.length();p++){
                if(Character.isLetter(passwardere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwardere.length();r++){
                if(Character.isDigit(passwardere.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<passwardere.length();s++){
                char c=passwardere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }


    private boolean valiateEmailAddress(EditText edemail){
        String emilInput=edemail.getText().toString();

        if(!emilInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emilInput).matches()){

            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Email Address  ",Toast.LENGTH_SHORT).show();
            return false;

        }
    }


}
