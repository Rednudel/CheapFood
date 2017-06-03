package com.example.david.cheapfood.PurchaseHistory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PurchaseHistory {
    private long id;
    private long customerId;
    private String offerName;
    private double offerPrice;
    private long quantity;
    private String orderDate;

    public PurchaseHistory(long id, long customerId, String offerName, double offerPrice, long quantity, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.offerName = offerName;
        this.offerPrice = offerPrice;
        this.quantity = quantity;
        this.orderDate = setOrderDate(orderDate);
    }
    public PurchaseHistory(long id, long customerId, String offerName, double offerPrice, long quantity, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.offerName = offerName;
        this.offerPrice = offerPrice;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getOrderDateString() {
        return orderDate;
    }

    public String setOrderDate(Date orderDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
        return dateFormat.format(orderDate);
        //this.orderDate = orderDate;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public String toString() {
        return "PurchaseHistory - ID: " + id +
                " | CustomerId: " + customerId +
                " | OfferName: " + offerName +
                " | OfferPrice: " + offerPrice +
                " | Quantity: " + quantity +
                " | OrderDate: " + orderDate;
    }
}
