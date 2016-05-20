/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventory.dto;

import static java.lang.System.console;

/**
 *
 * @author apprentice
 */
public abstract class Product {

    protected String size;
    protected int qty;
    protected Double price;
    protected int reorderLevel;
    protected int sku;
    public String type;

    public void updateQty(int qtyAdjustment) {
        qty = qty + (qtyAdjustment);
    }

    public boolean reorderCheck() {
        return (qty <= reorderLevel);
    }

    public abstract String getType();

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

}
