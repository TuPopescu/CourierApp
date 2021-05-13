package com.example.courierapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    /*public String name(){

        Context context = getContext();
        DatabaseHelper db = new DatabaseHelper(context);
        String name = db.getUsername();
        return name;
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, null);
        requireActivity().setTitle("Profile");
        //name();

        //TextView name = (TextView)findViewById(R.id.user_name);
        //name.setText(name + "");
        return view;
    }
}