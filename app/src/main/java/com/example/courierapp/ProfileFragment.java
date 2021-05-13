package com.example.courierapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    public ProfileFragment(){
        super(R.layout.fragment_profile);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        requireActivity().setTitle("Profile");

        TextView name = (TextView) view.findViewById(R.id.user_name);
        TextView email = (TextView) view.findViewById(R.id.user_email);
        name.setText(Preference.getName(getActivity()));
        email.setText(Preference.getEmail(getActivity()));

        return view;
    }
}