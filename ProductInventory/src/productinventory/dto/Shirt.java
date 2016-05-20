/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventory.dto;

/**
 *
 * @author apprentice
 */
public class Shirt extends Product {
    private String type = "Shirt";
    
    
    @Override
    public String getType() {
        return type;
    }
    
    
    public Shirt(String size, int qty, int sku, Double price, int reorderLevel) {
        this.size = size;
        this.qty = qty;
        this.sku = sku;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

}
