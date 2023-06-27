package com.example.farmshop;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>
{

    private List<HandlerRecyclerViewClass_dealer> itemlist;

    public interface  onItemClickListener
    {
        void onItemClick(int position);
        void onClickDeleteItem(int position);
    }

    private static onItemClickListener listener;
    public void setOnItemClickListener(onItemClickListener listener){this.listener=listener;}

    public Myadapter(List<HandlerRecyclerViewClass_dealer>itemlist){this.itemlist=itemlist;}





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        String foodname=itemlist.get(position).getFoodname();
       String description=itemlist.get(position).getDescription();
       String foodcount=itemlist.get(position).getFeedcount();
        Uri image=itemlist.get(position).getImage();
        String phoneno=itemlist.get(position).getPhoneno();

        holder.setData(image,foodname,description,foodcount,phoneno);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private TextView foodname_textview;
        private TextView description_textview;
        private  TextView foodcount_textview;

        private  TextView phoneno_textview;
        private ImageView deleteImg;
        private  ImageView addimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodname_textview=itemView.findViewById(R.id.foodname_list);
            description_textview=itemView.findViewById(R.id.description_list);
            foodcount_textview=itemView.findViewById(R.id.foodcount_list);
           addimage=itemView.findViewById(R.id.imageview);
            deleteImg=itemView.findViewById(R.id.deleteItem);
            phoneno_textview=itemView.findViewById(R.id.phone_list);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listener!=null)
                    {
                        listener.onItemClick(position);
                    }
                }
            });

            deleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAbsoluteAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listener!=null)
                    {
                        listener.onItemClick(position);
                    }

                }
            });
        }



                public void setData(Uri image,String food_name,String description,String food_prize,String phone_no)
        {

//            Glide.with(itemView.getContext())
//                            .load(image)
//                                    .apply(new RequestOption().overraid)
            addimage.setImageURI(image);
            foodcount_textview.setText(food_prize);
            description_textview.setText(description);
            foodname_textview.setText(food_name);
            phoneno_textview.setText(phone_no);


        }

    }

    //public class ViewHolder extends RecyclerView.ViewHolder {
    }






//public class Myadapter extends RecyclerView.Adapter<Myadapter> {
//
//   // private List<HandlerRecyclerViewClass_dealer> itemList;
//    public interface onItemClickListener
//    {
//        void onItemClick(int position);
//        void onClickDeleteItem(int position);
//    }
//
//
//    Context context;
//    List<item> items;
//
//    public static onItemClickListener listener;
//
//
//
//
//
//    public Myadapter(onItemClickListener listener, List<item> items) {
//        this.items = items;
//        this.listener=listener;
//    }
//
//
//    @NonNull
//    @Override
//    public myviewhold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new myviewhold(LayoutInflater.from(context).inflate(R.layout.itemview,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Myadapter holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull myviewhold holder, int position) {
//
////        holder.imageView.setImageResource(items.get(position).getImage());
////        holder.foodcount.setText(items.get(position).getFeedcount());
////        holder.desption.setText(items.get(position).getDescription());
////        holder.foodname.setText(items.get(position).getFoodname());
//
//        String foodname=items.get(position).getFoodname();
//        String description=items.get(position).getDescription();
//        String foodcount=items.get(position).getFeedcount();
//        Uri image=items.get(position).getImage();
//
//        holder.setData(image,foodname,description,foodcount);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public static class myviewhold extends RecyclerView.ViewHolder{
//        private TextView foodname_textview;
//        private TextView description_textview;
//        private TextView feedcount_textview;
//        private ImageView addimage;
//        private ImageView delimage;
//
//
//        public myviewhold(@NonNull View itemView) {
//            super(itemView);
//
//
//
//            foodname_textview=itemView.findViewById(R.id.foodname_list);
//            description_textview=itemView.findViewById(R.id.description_list);
//            feedcount_textview=itemView.findViewById(R.id.foodcount_list);
//            addimage=itemView.findViewById(R.id.imageview);
//            delimage=itemView.findViewById(R.id.deleteItem);
//
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position=getAdapterPosition();
//                    if(position!=RecyclerView.NO_POSITION && listener!=null)
//                    {
//                        listener.onItemClick(position);
//                    }
//                }
//            });
//
//            delimage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    int position=getAdapterPosition();
//                    if(position!=RecyclerView.NO_POSITION && listener!=null)
//                    {
//                        listener.onItemClick(position);
//                    }
//
//                }
//            });
//        }
//
//
//        public void setData(Uri image,String food_name,String description,String food_prize)
//        {
//            addimage.setImageURI(image);
//            feedcount_textview.setText(food_prize);
//            description_textview.setText(description);
//            foodname_textview.setText(food_name);
//
//        }
//
//    }
//
//    //public class ViewHolder extends RecyclerView.ViewHolder {
//    }
//}
//

