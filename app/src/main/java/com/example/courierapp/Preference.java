package com.example.courierapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {
    public Preference(){
    }

    public static boolean saveEmail(String email, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(Constants.KEY_EMAIL, email);
        preferencesEditor.apply();
        return true;
    }

    public static String getEmail(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Constants.KEY_EMAIL, null);
    }

    public static boolean savePassword(String password, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(Constants.KEY_PASSWORD, password);
        preferencesEditor.apply();
        return true;
    }

    public static String getPassword(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Constants.KEY_PASSWORD, null);
    }

    public static boolean saveName(String name, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(Constants.KEY_NAME, name);
        preferencesEditor.apply();
        return true;
    }

    public static String getName(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Constants.KEY_NAME, null);
    }

}
