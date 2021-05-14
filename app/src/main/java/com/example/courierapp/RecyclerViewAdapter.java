package com.example.courierapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private ArrayList<Delivery> deliveryList;

    RecyclerViewAdapter(ArrayList<Delivery> deliveryList){
        this.deliveryList = deliveryList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position){
        final Delivery delivery = deliveryList.get(position);
        holder.name.setText(delivery.getReceiver_name());
        holder.address.setText(delivery.getAddress());
    }

    @Override
    public int getItemCount(){
        return deliveryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout deliveryLayout;
        public TextView name, address;

        public MyViewHolder(View deliveryView){
            super(deliveryView);
            name = deliveryView.findViewById(R.id.deliveryReceiver);
            address = deliveryView.findViewById(R.id.deliveryAddress);
            deliveryLayout = deliveryView.findViewById(R.id.deliveryLayout);
        }
    }

    public Delivery getDelivery(int position){
        return this.deliveryList.get(position);
    }
}
