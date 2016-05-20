/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.dao;

import com.swcguild.vendingmachine.dto.VendingItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class VendingMachineDAO {

    public static final String INVENTORY_FILE = "VendingInventory.txt";
    private static final String DELIMITER = "::";

    ArrayList<VendingItem> allItems = new ArrayList<>();

    public void loadInventoryFile() throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));

        while (sc.hasNextLine()) {
            String recordLine = sc.nextLine();
            String[] recordProperties = recordLine.split(DELIMITER);
            if (recordProperties.length != 3) {
                continue;
            }

            String itemName = recordProperties[0];
            int itemQuantity = Integer.parseInt(recordProperties[1]);
            Double itemPrice = Double.parseDouble(recordProperties[2]);

            VendingItem item = new VendingItem(itemPrice, itemQuantity, itemName);
            allItems.add(item);

        }
    }

    public void writeInventory() throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(INVENTORY_FILE));

        for (VendingItem v : allItems) {
            writer.println(v.getItemName() + DELIMITER
                    + v.getItemQuantity() + DELIMITER
                    + v.getItemPrice());

        }
        writer.flush();
        writer.close();
    }

    public ArrayList<VendingItem> displayItems() {
        ArrayList<VendingItem> availableItems = new ArrayList<>();
        for (VendingItem i : allItems) {
            if (i.getItemQuantity() > 0) {
                availableItems.add(i);
            }
        }
        return availableItems;
    }

    public VendingItem itemCheck(String userChoice) {
        VendingItem itemToReturn = null;

        for (VendingItem v : allItems) {
            if (v.getItemName().equals(userChoice)) {
                itemToReturn = v;
            }
        }
        return itemToReturn;
    }

    public void buyItem(VendingItem chosenItem) {
        chosenItem.setItemQuantity(chosenItem.getItemQuantity() - 1);
    }

    public Double priceCheck(VendingItem chosenItem) {
        return chosenItem.getItemPrice();
    }

}
