package com.example.david.cheapfood;

public class Offer {

    private long id;
    private String name;
    private double price;
    private String description;
    private String address;
    private long contingent;

    public Offer(long id, String name, double price, String description, String address, long contigent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.contingent = contigent;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContigent() {
        return contingent;
    }

    public void setContigent(int contigent) {
        this.contingent = contigent;
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

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name;
    }
}
