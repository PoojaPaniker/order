package com.abn.assignment.order.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "orders_id")
    @JsonIgnore
    private int orderId;
    @Column
    private int customerId;
    @Column
    private Date dateOfDelivery;
    @Column
    private String addressOfDelivery;
    @OneToMany(targetEntity = OrderItems.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_fk", referencedColumnName = "orders_id")
    private List<OrderItems> items;
    @Transient
    private List<Items> orderItems;
    @Transient
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Items> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Items> orderItems) {
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getAddressOfDelivery() {
        return addressOfDelivery;
    }

    public void setAddressOfDelivery(String addressOfDelivery) {
        this.addressOfDelivery = addressOfDelivery;
    }

    public List<OrderItems> getItems() {
        return items;
    }

  //  public void setItems(List<OrderItems> items) {
   //     this.items = items;
  //  }

    public Orders() {
    }

    public Orders(int customerId, Date dateOfDelivery, String addressOfDelivery, List<OrderItems> items) {
        this.customerId = customerId;
        this.dateOfDelivery = dateOfDelivery;
        this.addressOfDelivery = addressOfDelivery;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", dateOfDelivery=" + dateOfDelivery +
                ", addressOfDelivery='" + addressOfDelivery + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
