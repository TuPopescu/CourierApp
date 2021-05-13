package com.example.courierapp;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class UsersActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private final AppCompatActivity activity = UsersActivity.this;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper = new DatabaseHelper(activity);

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.recycle_menu);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Delivery delivery = new Delivery(0, "Clare Arellano", "Talha Carrillo", "2493  Boone Crockett Lane");
                databaseHelper.addDelivery(delivery);
            }
        }, 10000);

        ArrayList<Delivery> deliveryList = databaseHelper.getDeliveryData();
        RecyclerViewDeliveryAdapter recyclerViewDeliveryAdapter = new RecyclerViewDeliveryAdapter(deliveryList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewDeliveryAdapter);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.Home:
                Intent homeIntent = new Intent(this, UsersActivity.class);
                startActivity(homeIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.Profile:
                fragment = new ProfileFragment();
                break;
            case R.id.PendingDeliveries:
                fragment = new PendingDeliveriesFragment();
                break;
            case R.id.Deliveries:
                fragment = new DeliveriesFragment();
                break;
            case R.id.log_out:
                databaseHelper.deleteAllDeliveries();
                Preference.savePassword(null, this);
                Preference.saveEmail(null, this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    //.replace(R.id.frame_container, fragment)
                    .add(R.id.fragment_profile, ProfileFragment.class, null)
                    .commit();
            return true;
        }
        return false;
    }
}
