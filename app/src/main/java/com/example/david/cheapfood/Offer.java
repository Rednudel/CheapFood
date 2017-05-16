package com.example.david.cheapfood;

/**
 * Created by David on 09.05.2017.
 */

public class Offer {

    private int id;
    private String name;
    private double price;
    private String description;
    private String address;
    private int contigent;

    public Offer(int id, String name, double price, String description, String address, int contigent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.contigent = contigent;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContigent() {
        return contigent;
    }

    public void setContigent(int contigent) {
        this.contigent = contigent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
