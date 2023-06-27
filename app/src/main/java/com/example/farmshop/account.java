package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class account extends AppCompatActivity {
    private Toolbar toolbar;
    DatabaseReference databaseReference;
    EditText fname,lname,address,city;
    String uid;
    Button saveaccount;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        toolbar=findViewById(R.id.account_toolbar);
        fname=findViewById(R.id.first_name);
        lname=findViewById(R.id.last_name);
        address=findViewById(R.id.address_name);
        city=findViewById(R.id.city_name);
        saveaccount=findViewById(R.id.saveAccount);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
         uid=firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Details").child(uid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validity())
                {

                    Map<String,Object> p=new HashMap<>();

                    p.put("first name",fname.getText().toString().trim());
                    p.put("last name",lname.getText().toString().trim());
                    p.put("city",city.getText().toString().trim());
                    p.put("Address",address.getText().toString().trim());
                    databaseReference.updateChildren(p);
                    Toast.makeText(account.this, "Your information is added successfully !", Toast.LENGTH_SHORT).show();



//                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            String firstname=snapshot.child("first name").getValue(String.class);
//                            String lastname=snapshot.child("last name").getValue(String.class);
//                            String address=snapshot.child("Address").getValue(String.class);
//                            String cityy=snapshot.child("city").getValue(String.class);
//
//
//                            Intent intent=new Intent(getApplicationContext(),account.class);
//
//
//                            intent.putExtra("first name",firstname);
//                            intent.putExtra("last name",lastname);
//                            intent.putExtra("Address",address);
//                            intent.putExtra("city",cityy);
//
//                            startActivity(intent);
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });








                }
                else{

                }






            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==android.R.id.home)
       {
           finish();
       }
        return super.onOptionsItemSelected(item);
    }

    public boolean validity()
    {
        String firname=fname.getText().toString().trim();
        String latname=lname.getText().toString().trim();
        String cty=city.getText().toString().trim();
        String adress=address.getText().toString().trim();

        if(firname.isEmpty()){
            fname.setError("first name  cannot be empty");
            return false;
        }
        if(latname.isEmpty()){
            lname.setError("Last name cannot be  empty ");
            return false;
        }
        if(cty.isEmpty()){
            city.setError("city name cannot be empty ");
            return false;
        }
        if(adress.isEmpty()){
            address.setError("Address cannot be  empty ");
            return false;
        }

       return true;

    }

}