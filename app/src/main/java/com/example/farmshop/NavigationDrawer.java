package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TooManyListenersException;

public class NavigationDrawer extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    TextView camera,emailname;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    BottomNavigationView bottomNavigationView;
    BottomSheetDialog bottomSheetDialog;
    ArrayList<String > obj;

    List<HandlerRecyclerViewClass_dealer> items;
    Myadapter myadapter;

    String str_foodname="no foodname";
    String str_user="no user";
    FloatingActionButton floatingActionButton;


    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference secondRef;
   // DatabaseReference db;
    String db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);


        drawerLayout=findViewById(R.id.drawer_layout);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        toolbar=findViewById(R.id.toolbarr);

        floatingActionButton=findViewById(R.id.floting_btn);
        camera=findViewById(R.id.cameratext);


        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_list_24);

        navigationView=findViewById(R.id.navigation_view);

        mAuth = FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();




        firebaseDatabase = FirebaseDatabase.getInstance();



        secondRef=FirebaseDatabase.getInstance().getReference().child("Details").child(mUser.getUid());
       // db=FirebaseDatabase.getInstance().getReference().child("Details").child("Foodlisting").child(mUser.getUid());
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Details");


       // checkIfFoodAreAdded();







        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog=new BottomSheetDialog(NavigationDrawer.this,R.style.BottomSheetStyle);

                View view1= LayoutInflater.from(NavigationDrawer.this).inflate(R.layout.activity_bottomsheet,
                        null);

                bottomSheetDialog.setContentView(view1);

                bottomSheetDialog.show();

                view1.findViewById(R.id.cameratext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(NavigationDrawer.this,giveAwayFood.class);
                        startActivity(intent);
                        Toast.makeText(NavigationDrawer.this, "Give away food ", Toast.LENGTH_SHORT).show();
                    }
                });

                view1.findViewById(R.id.phototext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(NavigationDrawer.this,giveAwayFood.class);
                        startActivity(intent);
                        Toast.makeText(NavigationDrawer.this, "Give away food ", Toast.LENGTH_SHORT).show();
                    }
                });


                view1.findViewById(R.id.documenttext).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(NavigationDrawer.this, "For future ", Toast.LENGTH_SHORT).show();
                    }
                });





            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        Toast.makeText(NavigationDrawer.this, "Home", Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(NavigationDrawer.this,NavigationDrawer.class));
                        return true;
                    case R.id.nav_listing:
                        Toast.makeText(NavigationDrawer.this, "Listing", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_setting:
                        Toast.makeText(NavigationDrawer.this, "setting", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_logout:
                      logoutview(NavigationDrawer.this);
                        return true;
                    case R.id.nav_account:
                        Toast.makeText(NavigationDrawer.this, "Account", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NavigationDrawer.this,account.class));
                        return true;
                    case R.id.nav_share:
                        Toast.makeText(NavigationDrawer.this, "share", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.terms_and_conditons:
                        Toast.makeText(NavigationDrawer.this, "terms and conditions", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.help:
                        Toast.makeText(NavigationDrawer.this, "help", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(NavigationDrawer.this,help.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        Toast.makeText(NavigationDrawer.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_account:
                        Toast.makeText(NavigationDrawer.this, "Account", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NavigationDrawer.this,account.class));
                        break;
                    case R.id.nav_help:
                        Intent intent=new Intent(NavigationDrawer.this,help.class);
                        startActivity(intent);
                        Toast.makeText(NavigationDrawer.this, "help", Toast.LENGTH_SHORT).show();
                        break;
                }


                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        String type=intent.getStringExtra("types");
        if(type.equals("farmer"))
        {
           // floatingActionButton.setVisibility(View.INVISIBLE);
            checkIfFoodAreAdded();
        }
        else{
             floatingActionButton.setVisibility(View.INVISIBLE);
           getFoodItemsForDealer();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void logoutview(NavigationDrawer navigationDrawer)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(navigationDrawer);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure want to logout? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(NavigationDrawer.this, "Logout", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(NavigationDrawer.this);
        alertDialog.setTitle("Exit App ");
        alertDialog.setMessage("Are you sure want to Exit?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finishAffinity();

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });

        alertDialog.show();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void intitRecycleview()
    {

        recyclerView=findViewById(R.id.recycleview);
        linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myadapter=new Myadapter(items);
        recyclerView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();


        myadapter.setOnItemClickListener(new Myadapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent=new Intent(NavigationDrawer.this,ViewItemsFor_dealer.class);
//                intent.putExtra("position",String.valueOf(position));
//                startActivity(intent);
            }

            @Override
            public void onClickDeleteItem(int position) {


                deleteItem(position);
                items.remove(position);
                myadapter.notifyItemRemoved(position);

            }
        });




    }


    private void checkIfFoodAreAdded(){

        secondRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    getAlltheFoods();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void getAlltheFoods()
    {
        setUsernameRestaurent();

        secondRef.child("Foodlisting").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items=new ArrayList<>();
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        String foodcount=dataSnapshot.child("foodamount").getValue(String.class);
                        String uri=dataSnapshot.child("imageview").getValue(String.class);
                        String fooddescription=dataSnapshot.child("Description").getValue(String.class);
                        String foodname=dataSnapshot.child("Foodname").getValue(String.class);
                        String phoneno=dataSnapshot.child("phoneno").getValue(String.class);

                        items.add(new HandlerRecyclerViewClass_dealer(fooddescription,foodcount,foodname,Uri.parse(uri),phoneno));
                    }
                    intitRecycleview();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setUsernameRestaurent(){
        secondRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("Foodname").exists() ){
                        str_foodname=snapshot.child("Foodname").getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });
    }


    private void getFoodItemsForDealer(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()) {
                    if (!snap.getKey().isEmpty() && snap.hasChild("Foodlisting")) {
                        String detailsKey = snap.getKey();
                        DatabaseReference foodlistingRef = snap.child("Foodlisting").getRef();
                        items=new ArrayList<>();
                        foodlistingRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                for(DataSnapshot snap1: snapshot1.getChildren()) {
//                                    String foodlistingKey = snap1.getKey();

                                    String foodcount=snap1.child("foodamount").getValue(String.class);
                                    String uri=snap1.child("imageview").getValue(String.class);
                                    String fooddescription=snap1.child("Description").getValue(String.class);
                                    String foodname=snap1.child("Foodname").getValue(String.class);
                                    String phoneno=snap1.child("phoneno").getValue(String.class);

                                    items.add(new HandlerRecyclerViewClass_dealer(fooddescription,foodcount,foodname,Uri.parse(uri),phoneno));
                                }
                                intitRecycleview();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private void deleteItem(int pos){
        DatabaseReference fordelete= secondRef.child("Foodlisting");
        secondRef.child("Foodlisting").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    int i=0;
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        if(i==pos){
                            db=dataSnapshot.getKey();
                           // deleteFromFireStorage();
                            assert db!=null;
                            fordelete.child(db).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(NavigationDrawer.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;

                        }
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search ");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                String input=newText.toLowerCase();
//                ArrayList<ColorSpace.Model> arrayList=new ArrayList<>();
//                for()


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}