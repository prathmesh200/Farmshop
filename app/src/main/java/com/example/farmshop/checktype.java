package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class checktype extends AppCompatActivity {
    String user_id;
    String types;
    View view;

    AlertDialog dialog;
    AlertDialog.Builder builder;

    ProgressBar progressBar;
    Button farmerbtn;
    Button dealerbtn;




    FirebaseAuth mAuth;
    FirebaseUser muser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checktype);


        builder = new AlertDialog.Builder(this);

        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Details");
        user_id = muser.getUid();

//        checkTypeIsPresent();


//        view = getLayoutInflater().inflate(R.layout.dialog_box, null);
//        farmerbtn = view.findViewById(R.id.farmer_btn);
//        dealerbtn = view.findViewById(R.id.dealer_btn);


//        farmerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                types = "farmer";
//                insertData(types);
//                checkTypeIsPresent();
//            }
//        });
//
//        dealerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                types = "dealer";
//                insertData(types);
//                checkTypeIsPresent();
//            }
//        });
    }


    protected void onResume()
    {
        super.onResume();
        checkTypeIsPresent();
    }

    private void insertData(String types) {
        databaseReference.child("types").setValue(types);
    }

    private void checkTypeIsPresent() {
        databaseReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        if (Objects.equals(data.getValue(), "farmer")) {
                            Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);
                            intent.putExtra("types", "farmer");
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                        }

                        if (data.getValue().equals("dealer")) {
                            Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);
                            intent.putExtra("types", "dealer");
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                } else {
                    builder.setView(view);
                    dialog = builder.create();
                    dialog.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}


