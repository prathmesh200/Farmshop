package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class giveAwayFood extends AppCompatActivity {

    private Toolbar toolbar;

    EditText foodname,fooddescription,foodamountperkg,foodphoneno;
    Button addfoodbtn;
    DatabaseReference secoundRef;
    String randomid;
    Uri uri;
    String foodName,foodDescription,foodAmountPerKg,foodPHONENO;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;





   ImageView cover,imagebtn;
//    FloatingActionButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_away_food);


        toolbar=findViewById(R.id.giveawayfoodback);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodname=findViewById(R.id.giveAway_food_name);
        cover=findViewById(R.id.Image);
        imagebtn=findViewById(R.id.image_Btn);
        foodphoneno=findViewById(R.id.give_away_phoneno);

        fooddescription=findViewById(R.id.give_away_description);
        foodamountperkg=findViewById(R.id.amount_per_kg);

        addfoodbtn=findViewById(R.id.giveawayfood_button);

        mAuth = FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        firebaseDatabase = FirebaseDatabase.getInstance();



        secoundRef=FirebaseDatabase.getInstance().getReference().child("Details").child(mUser.getUid());


        addfoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initialize_details();





                if(!foodName.isEmpty() && !foodDescription.isEmpty() && !foodAmountPerKg.isEmpty() ){

                  Toast.makeText(giveAwayFood.this, "YOur food is add successfully", Toast.LENGTH_SHORT).show();
                  addFoodListing(secoundRef);

              }
              else{
                  Toast.makeText(giveAwayFood.this, "Please fill the information", Toast.LENGTH_SHORT).show();
              }

            }
        });


        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(giveAwayFood.this)
                          .crop()
                        .cropSquare()
                        .compress(1024)
                        .cameraOnly()
                        .maxResultSize(1080,1080)
                        .start();
            }
        });




    }




    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         uri=data.getData();
        cover.setImageURI(uri);
    }


    private void addFoodListing(DatabaseReference secoundRef){

        secoundRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String order=secoundRef.child("Foodlisting").push().getKey();
                Map<String, Object> updateData=new HashMap<>();

                updateData.put("order",order);
                updateData.put("imageview",uri.toString());
                updateData.put("Foodname",foodName);
                updateData.put("Description",foodDescription);
                updateData.put("foodamount",foodAmountPerKg);
                updateData.put("phoneno",foodPHONENO);

                randomid=secoundRef.child("Foodlisting").push().getKey();
                assert randomid!=null;
                secoundRef.child("Foodlisting").child(randomid).setValue(updateData);
              //  uploadImageInStorage();


                onBackPressed();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private  void initialize_details(){

        foodName=foodname.getText().toString();
        foodDescription=fooddescription.getText().toString();
        foodAmountPerKg=foodamountperkg.getText().toString();
        foodPHONENO=foodphoneno.getText().toString();



    }

}