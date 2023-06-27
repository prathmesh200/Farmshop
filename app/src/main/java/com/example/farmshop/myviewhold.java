package com.example.farmshop;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewhold extends RecyclerView.ViewHolder {


    ImageView imageView;
    TextView foodname,desption,foodcount,phoneno;
    public myviewhold(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.imageview);
        foodname=itemView.findViewById(R.id.foodname_list);
        desption=itemView.findViewById(R.id.description_list);
        foodcount=itemView.findViewById(R.id.foodcount_list);
        phoneno=itemView.findViewById(R.id.phone_list);

        imageView=itemView.findViewById(R.id.deleteItem);
    }


}
