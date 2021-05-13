package com.example.courierapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";


    private static final String TABLE_DELIVERY = "delivery";
    private static final String COLUMN_DELIVERY_ID = "delivery_id";
    private static final String COLUMN_DELIVERY_SENDER_NAME = "delivery_sender_name";
    private static final String COLUMN_DELIVERY_RECEIVER_NAME = "delivery_receiver_name";
    private static final String COLUMN_DELIVERY_ADDRESS = "delivery_address";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME +
                " TEXT," + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_DELIVERY_TABLE = "CREATE TABLE " + TABLE_DELIVERY + "("
                + COLUMN_DELIVERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DELIVERY_SENDER_NAME +
                " TEXT," + COLUMN_DELIVERY_RECEIVER_NAME + " TEXT," + COLUMN_DELIVERY_ADDRESS + " TEXT)";
        db.execSQL(CREATE_DELIVERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        String DROP_DELIVERY_TABLE = "DROP TABLE IF EXISTS " + TABLE_DELIVERY;
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_DELIVERY_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addDelivery(Delivery delivery){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DELIVERY_SENDER_NAME, delivery.getSender_name());
        values.put(COLUMN_DELIVERY_RECEIVER_NAME, delivery.getReceiver_name());
        values.put(COLUMN_DELIVERY_ADDRESS, delivery.getAddress());

        db.insert(TABLE_DELIVERY, null, values);
        db.close();
    }

    public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_PASSWORD, password);

        db.update(TABLE_USER, values, COLUMN_USER_EMAIL+" = ?", new String[] { email });
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        return cursorCount > 0;
    }

    public ArrayList<Delivery> getDeliveryData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM delivery", null);

        ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getColumnIndex("COLUMN_DELIVERY_ID");
                String sender_name = cursor.getString(1);
                String receiver_name = cursor.getString(2);
                String address = cursor.getString(3);
                Delivery delivery = new Delivery(id, sender_name, receiver_name, address);
                deliveryList.add(delivery);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return deliveryList;
    }

    public void deleteAllDeliveries(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DELIVERY);
        db.close();
    }
}
