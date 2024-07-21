package com.example.foodislife;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.media.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<user> list;
    OnItemClickListener Listener;

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    // method
    public void setOnItemClickListener(OnItemClickListener clickListener) {

        Listener = clickListener;
    }

    public Adapter(Context context, ArrayList<user> list, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            //return  new MyViewHolder(v);
            return new MyViewHolder(v, Listener, recyclerViewInterface);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        user users = list.get(position);

        holder.organization.setText(users.getOrganization());
        holder.food_Type.setText(users.getFoodtype());
        holder.phone.setText(users.getPhone());
        holder.address.setText(users.getAddress());
        holder.description.setText(users.getDescription());
        holder.ID.setText(users.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RiderPortal.class);

                intent.putExtra("Organization",list.get(holder.getAdapterPosition()).getOrganization());
                intent.putExtra("Food type",list.get(holder.getAdapterPosition()).getFoodtype());
                intent.putExtra("phone",list.get(holder.getAdapterPosition()).getPhone());
                intent.putExtra("address",list.get(holder.getAdapterPosition()).getAddress());
                intent.putExtra("description",list.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Assigned ID", list.get(holder.getAdapterPosition()).getUserid());

                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView del;
        TextView organization, food_Type, phone, description, address, ID;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener Listener, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            organization= itemView.findViewById(R.id.DDorg);
            food_Type = itemView.findViewById(R.id.DDName);
            phone = itemView.findViewById(R.id.DPhone);
            description = itemView.findViewById(R.id.Ddescription);
            address = itemView.findViewById(R.id.DAddress);
            ID = itemView.findViewById(R.id.DID);

            del = itemView.findViewById(R.id.erase);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Listener.onItemClick(getAdapterPosition());

                }
            });
        }
    }
}