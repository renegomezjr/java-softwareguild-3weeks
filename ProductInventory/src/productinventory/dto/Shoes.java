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
public class Shoes extends Product {
    private String type = "Shoes";
    

    @Override
    public String getType() {
        return type;
    }
    
    

    public Shoes(String size, int qty, int sku, Double price, int reorderLevel) {
        this.size = size;
        this.qty = qty;
        this.sku = sku;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }

    public int getSku() {
        return sku;
    }

}
