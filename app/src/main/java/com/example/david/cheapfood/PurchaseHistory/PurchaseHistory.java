package com.example.david.cheapfood.PurchaseHistory;

import java.util.Date;

public class PurchaseHistory {
    private long id;
    private long customerId;
    private String offerName;
    private double offerPrice;
    private long quantity;
    private Date orderDate;

    public PurchaseHistory(long id, long customerId, String offerName, double offerPrice, long quantity, Date orderDate) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
