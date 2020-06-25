package com.abn.assignment.order.model;

public class Stock {
    private int itemId;
    private int availableStock;

    public int getAvailableStock() {
        return availableStock;
    }

    public int getItemId() {
        return itemId;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public Stock() {
    }

    public Stock(int itemId, int availableStock) {
        this.itemId = itemId;
        this.availableStock = availableStock;
    }
}
