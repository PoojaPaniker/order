package com.abn.assignment.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @Id
    //@GeneratedValue
    @JsonIgnore
    private int itemsId;
    @Column
    private String name;
    @Column
    private Double cost;

    public int getItemsId() {
        return itemsId;
    }

   // public void setItemsId(int itemsId) {
  //      this.itemsId = itemsId;
   // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemsId=" + itemsId +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Items() {
    }

    public Items(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Items(int itemsId, String name, Double cost) {
        this.itemsId = itemsId;
        this.name = name;
        this.cost = cost;
    }
}
