package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class listActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView=findViewById(R.id.recycleview);

        List<HandlerRecyclerViewClass_dealer> items=new ArrayList<HandlerRecyclerViewClass_dealer>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       // recyclerView.setAdapter(new Myadapter(getApplicationContext(),));
    }
}