package com.abn.assignment.order.model;

import javax.persistence.*;

@Entity
@Table(name = "OrderItems")
public class OrderItems {
    @Id
    @GeneratedValue
    private int orderItems;
    @Column(name = "items_id")
    private int itemId;


    public OrderItems(int itemId) {
        this.itemId = itemId;
    }

    public OrderItems() {
    }

    public int getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(int orderItems) {
        this.orderItems = orderItems;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public OrderItems(int orderItems, int itemId) {
        this.orderItems = orderItems;
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderItems=" + orderItems +
                ", itemId=" + itemId +
                '}';
    }
}
