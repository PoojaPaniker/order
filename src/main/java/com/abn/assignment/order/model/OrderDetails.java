package com.abn.assignment.order.model;

//this POJO class is used for display purpose only
public class OrderDetails {

    private int orders_id;
    private Double total_cost;

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public OrderDetails() {
    }

    public OrderDetails(int orders_id, Double total_cost) {
        this.orders_id = orders_id;
        this.total_cost = total_cost;
    }
}
