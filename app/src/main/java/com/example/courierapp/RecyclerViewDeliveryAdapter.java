package com.example.courierapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDeliveryAdapter extends RecyclerView.Adapter<RecyclerViewDeliveryAdapter.MyViewHolder>{
    private List<Delivery> deliveryList;

    RecyclerViewDeliveryAdapter(ArrayList<Delivery> deliveryList){
        this.deliveryList = deliveryList;
    }

    @NonNull
    @Override
    public RecyclerViewDeliveryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewDeliveryAdapter.MyViewHolder holder, final int position){
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
}
