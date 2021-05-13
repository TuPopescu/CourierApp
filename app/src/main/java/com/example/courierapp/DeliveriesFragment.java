package com.example.courierapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DeliveriesFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    public DeliveriesFragment(){
        super(R.layout.fragment_deliveries);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliveries, null);
        requireActivity().setTitle("Deliveries");

        recyclerView = view.findViewById(R.id.recycle_menu_deliveries);
        databaseHelper = new DatabaseHelper(view.getContext());

        ArrayList<Delivery> deliveryList = databaseHelper.getDeliveryData();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(deliveryList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }
}