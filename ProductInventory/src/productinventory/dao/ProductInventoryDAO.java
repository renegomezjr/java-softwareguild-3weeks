package productinventory.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import productinventory.dto.Pant;
import productinventory.dto.Product;
import productinventory.dto.Shirt;
import productinventory.dto.Shoes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apprentice
 */
public class ProductInventoryDAO {

    public static final String INVENTORY_LIST = "Inventory.txt";
    private static final String DELIMITER = "::";

    HashMap<Integer, Product> inventory = new HashMap<>();

    public int addProduct(String type, String size, int qty, int sku, Double price, int reorderLevel) {

        switch (type) {
            case "Shirt":
                Shirt newShirt = new Shirt(size, qty, sku, price, reorderLevel);
                inventory.put(sku, newShirt);
                return newShirt.getSku();
            case "Pant":
                Pant newPant = new Pant(size, qty, sku, price, reorderLevel);
                inventory.put(sku, newPant);
                return newPant.getSku();
            case "Shoes":
                Shoes newShoes = new Shoes(size, qty, sku, price, reorderLevel);
                inventory.put(sku, newShoes);
                return newShoes.getSku();
            default:
                return 0;
        }
    }

    public void loadInventory() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(INVENTORY_LIST)));

        while (sc.hasNextLine()) {
            String recordLine = sc.nextLine();
            String[] recordProperties = recordLine.split(DELIMITER);

            if (recordProperties.length != 6) {
                continue;
            }

            String type = recordProperties[0];
            String size = recordProperties[1];
            int qty = Integer.parseInt(recordProperties[2]);
            int sku = Integer.parseInt(recordProperties[3]);
            Double price = Double.parseDouble(recordProperties[4]);
            int reorderLevel = Integer.parseInt(recordProperties[5]);

            switch (type) {
                case "Shirt":
                    Shirt newShirt = new Shirt(size, qty, sku, price, reorderLevel);
                    inventory.put(sku, newShirt);
                    break;
                case "Pant":
                    Pant newPant = new Pant(size, qty, sku, price, reorderLevel);
                    inventory.put(sku, newPant);
                    break;
                case "Shoes":
                    Shoes newShoes = new Shoes(size, qty, sku, price, reorderLevel);
                    inventory.put(sku, newShoes);
                    break;
            }
        }
    }

    public Product fetchProduct(int sku) {
        return inventory.get(sku);
    }

    public Collection<Product> specificProducts(String products) {
        return fetchAllProducts();

    }

    public Collection<Product> fetchAllProducts() {
        return inventory.values();
    }

    public void writeInventory() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(INVENTORY_LIST));
        Collection<Product> allProducts = fetchAllProducts();

        for (Product p : allProducts) {
            writer.println(p.getType() + DELIMITER + p.getSize() + DELIMITER + p.getQty() + DELIMITER + p.getSku() + DELIMITER + p.getPrice() + DELIMITER + p.getReorderLevel());

        }
        writer.flush();
        writer.close();
    }
}
