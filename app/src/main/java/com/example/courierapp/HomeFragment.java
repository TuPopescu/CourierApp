package com.example.courierapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;


    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        requireActivity().setTitle("Home");

        recyclerView = view.findViewById(R.id.recycle_menu_home);
        databaseHelper = new DatabaseHelper(view.getContext());

        ArrayList<Delivery> deliveryList = databaseHelper.getDeliveryData();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(deliveryList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        this.configureOnClickRecyclerView();

        return view;
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_home)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v){
                        ArrayList<Delivery> deliveryList = databaseHelper.getDeliveryData();
                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(deliveryList);
                        Delivery delivery = recyclerViewAdapter.getDelivery(position);
                        Toast.makeText(getContext(), "You selected delivery on " + delivery.getAddress(), Toast.LENGTH_SHORT).show();

                        DeliveryInfoFragment nextFrag= new DeliveryInfoFragment();
                        Bundle args = new Bundle();
                        args.putString("SenderName", delivery.getSender_name());
                        args.putString("ReceiverName", delivery.getReceiver_name());
                        args.putString("Address", delivery.getAddress());
                        nextFrag.setArguments(args);
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, nextFrag, null)
                                .addToBackStack(null)
                                .commit();
                    }
                });
    }
}