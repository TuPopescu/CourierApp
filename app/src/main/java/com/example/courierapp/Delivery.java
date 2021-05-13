package com.example.courierapp;

public class Delivery {
    private int id;
    private String sender_name;
    private String receiver_name;
    private String address;

    public Delivery(){

    }

    public Delivery(int id, String sender_name, String receiver_name, String address){
        this.id = id;
        this.sender_name = sender_name;
        this.receiver_name = receiver_name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
