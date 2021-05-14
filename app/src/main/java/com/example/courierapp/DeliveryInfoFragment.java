package com.example.courierapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DeliveryInfoFragment extends Fragment {
    DatabaseHelper databaseHelper;

    public DeliveryInfoFragment() {
        super(R.layout.fragment_delivery_info);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_info, null);
        requireActivity().setTitle("Home");

        databaseHelper = new DatabaseHelper(view.getContext());
        ArrayList<Delivery> deliveryList = databaseHelper.getDeliveryData();

        TextView name1 = view.findViewById(R.id.deliveryReceiver);
        TextView name2 = view.findViewById(R.id.deliverySender);
        TextView address = view.findViewById(R.id.deliveryAddress);
        name1.setText(getArguments().getString("ReceiverName"));
        name2.setText(getArguments().getString("SenderName"));
        address.setText(getArguments().getString("Address"));

        return view;
    }
}