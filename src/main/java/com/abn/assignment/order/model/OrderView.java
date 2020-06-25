package com.abn.assignment.order.model;

import java.util.List;

public class OrderView {


    private int order_id;
    private int customer_id;
    private String date_of_delivery;
    private String address;
     private List<Items> Items_in_Order;
     private Double Total_cost;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getDate_of_delivery() {
        return date_of_delivery;
    }

    public void setDate_of_delivery(String date_of_delivery) {
        this.date_of_delivery = date_of_delivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Items> getItems_in_Order() {
        return Items_in_Order;
    }

    public void setItems_in_Order(List<Items> items_in_Order) {
        Items_in_Order = items_in_Order;
    }

    public Double getTotal_cost() {
        return Total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        Total_cost = total_cost;
    }

    public OrderView() {
    }

    public OrderView(int customer_id, String date_of_delivery, String address, List<Items> items_in_Order, Double total_cost) {
        this.customer_id = customer_id;
        this.date_of_delivery = date_of_delivery;
        this.address = address;
        Items_in_Order = items_in_Order;
        Total_cost = total_cost;
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "customer_id=" + customer_id +
                ", date_of_delivery='" + date_of_delivery + '\'' +
                ", address='" + address + '\'' +
                ", Items_in_Order=" + Items_in_Order +
                ", Total_cost=" + Total_cost +
                '}';
    }
}
