package com.sourceedge.bhagyalakshmi.orders.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Centura User1 on 07-12-2016.
 */

public class Order {
    private String Id;
    private ArrayList<OrderProduct> Products;
    private OrderRole User;
    private OrderRole Client;
    private Double TotalAmount;
    private String Status;
    private String OrderNumber;
    private String TimeStamp;

    public Order(){
        Id="";
        Products=new ArrayList<OrderProduct>();
        User=new OrderRole();
        Client=new OrderRole();
        TotalAmount=0.0;
        Status="";
        OrderNumber="";
        TimeStamp="";
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ArrayList<OrderProduct> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<OrderProduct> products) {
        Products = products;
    }

    public OrderRole getUser() {
        return User;
    }

    public void setUser(OrderRole user) {
        User = user;
    }

    public OrderRole getClient() {
        return Client;
    }

    public void setClient(OrderRole client) {
        Client = client;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
