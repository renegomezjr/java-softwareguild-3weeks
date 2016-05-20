/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventory.ops;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import productinventory.dao.ProductInventoryDAO;
import productinventory.dto.Product;
import productinventory.dto.Shirt;
import productinventory.ui.ConsoleIO;

/**
 *
 * @author apprentice
 */
public class ProductInventoryController {

    ConsoleIO console = new ConsoleIO();
    ProductInventoryDAO daoLayer = new ProductInventoryDAO();

    public void run() throws IOException {
        try {
            daoLayer.loadInventory();
        } catch (FileNotFoundException ex) {
            console.print("Your file did not load.");
        }

        boolean keepRunning = true;
        int menuSelect;

        while (keepRunning) {
            printMenu();
            reorderCheckAll();
            menuSelect = console.readInt("Enter the number of your selection...", 0, 5);

            switch (menuSelect) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    valueProduct();
                    break;
                case 4:
                    valueInventory();
                    break;
                case 5:
                    daoLayer.writeInventory();
                    keepRunning = false;
                    break;
                default:
                    daoLayer.writeInventory();
                    keepRunning = false;
                    break;

            }
        }

    }

    private void printMenu() {
        console.print("Main Menu\n"
                + "1. Add a product\n"
                + "2. Update stock of a product\n"
                + "3. Total value of a product\n"
                + "4. Total value of entire inventory\n"
                + "5. Exit\n");

    }

    private void addProduct() {
        //String size, int qty, int sku, Double price, int reorderLevel
        String type = console.readString("type (\"Shirt\", \"Pant\" or \"Shoes\"): ");
        String size = console.readString("\nsize: ");
        int qty = console.readInt("\nQty: ");
        int sku = console.readInt("\nSKU: ");
        Double price = console.readDouble("\nPrice: ");
        int reorderLevel = console.readInt("\nReorder Level: ");
        daoLayer.addProduct(type, size, qty, sku, price, reorderLevel);

    }

    private void updateProduct() {
        int skuToUpdate = console.readInt("SKU: ");
        int qtyAdjustment = console.readInt("Quantity to add (enter a negative number to decrease quantity): ");

        Product updatedProduct = daoLayer.fetchProduct(skuToUpdate);
        updatedProduct.updateQty(qtyAdjustment);
        if (updatedProduct.reorderCheck()) {
            console.print("Low quantity--You need to reorder this product.");
        }
        console.print("New quantity is : " + updatedProduct.getQty());
    }

    private void valueProduct() {
        String typeToValue = console.readString("Enter the type of product for which you want the total value (\"Shirt\", \"Pant\" or \"Shoes\"): ");
        Collection<Product> products = daoLayer.specificProducts(typeToValue);
        Double totalValue = 0.0;

        for (Product p : products) {
            if (p.getType().equals("Shirt")) {
                totalValue += p.getQty() * p.getPrice();
            }

        }

        console.print("The total value of " + typeToValue + " is $" + totalValue + ".");

    }

    private void valueInventory() {
        Collection<Product> allProducts = daoLayer.fetchAllProducts();
        Double totalValue = 0.0;

        for (Product p : allProducts) {
            totalValue += p.getQty() * p.getPrice();

        }
        console.print("The total value of the full inventory is $" + totalValue + ".");
    }

    private void reorderCheckAll() {
        Collection<Product> allProducts = daoLayer.fetchAllProducts();
        console.print("The following SKUs need to be reordered:\n");
        for (Product p : allProducts) {
            if (p.reorderCheck()) {

                console.print("SKU: " + p.getSku());
            }
        }
    }
}
