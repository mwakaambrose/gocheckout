package com.ensibuuko.ambrose.gocheckout.Models;

public class Cart {
    public String id;
    public String itemName;
    public String itemDescription;
    public String itemImageUrl;
    public String itemShoppingId;

    public Cart() {
    }

    public Cart(String itemName, String itemDescription, String itemImageUrl) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImageUrl = itemImageUrl;
    }

    public Cart(String id, String itemName, String itemDescription, String itemImageUrl, String itemShoppingId) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImageUrl = itemImageUrl;
        this.itemShoppingId = itemShoppingId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getItemShoppingId() {
        return itemShoppingId;
    }

    public void setItemShoppingId(String itemShoppingId) {
        this.itemShoppingId = itemShoppingId;
    }
}