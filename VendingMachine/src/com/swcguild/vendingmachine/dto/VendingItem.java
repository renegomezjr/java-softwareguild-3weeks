/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.dto;

/**
 *
 * @author apprentice
 */
public class VendingItem {

    private Double itemPrice;
    private int itemQuantity;
    private String itemName;

    public VendingItem(Double itemPrice, int itemQuantity, String itemName) {
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
