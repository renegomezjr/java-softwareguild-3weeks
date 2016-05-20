/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.ops;

import com.swcguild.vendingmachine.dao.VendingMachineDAO;
import com.swcguild.vendingmachine.dto.MakeChange;
import com.swcguild.vendingmachine.dto.VendingItem;
import com.swcguild.vendingmachine.ui.ConsoleIO;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {

    private ConsoleIO console = new ConsoleIO();
    private VendingMachineDAO daoLayer = new VendingMachineDAO();
    private boolean keepRunning = false;

    MakeChange changeMaker = new MakeChange();

    public void run() throws IOException {

        try {
            daoLayer.loadInventoryFile();
        } catch (FileNotFoundException ex) {
            console.print("Inventory file was not found.");
        }
        displayItems();
        Double userMoney = console.readDouble("Please insert your money: ");
        //maybe show money
        String userChoice = console.readString("Please type the item you would like to purchase: ");
        VendingItem chosenItem = itemCheck(userChoice);

        while (!keepRunning) {
            if (canAfford(userMoney, chosenItem)) {
                buyItem(chosenItem);
                changeMaker.makeChange(userMoney - priceCheck(chosenItem));
                console.print("Enjoy your " + userChoice + ". And here's your change:");
                console.print(changeMaker.getQuarters() + " Quarters\n"
                        + changeMaker.getDimes() + " Dimes\n"
                        + changeMaker.getNickels() + " Nickels\n"
                        + changeMaker.getPennies() + " Pennies\n");
                keepRunning = true;
            } else {
                int cancelOrAdd = console.readInt("You can't afford that item.\n"
                        + " Would you like to cancel(1) or add more money(2)? ");
                if(cancelOrAdd == 2){
                userMoney += console.readDouble("Please enter more money: ");
                }else{
                    changeMaker.makeChange(userMoney);
                    console.print("Your change sir/madam: ");
                    console.print(changeMaker.getQuarters() + " Quarters\n"
                        + changeMaker.getDimes() + " Dimes\n"
                        + changeMaker.getNickels() + " Nickels\n"
                        + changeMaker.getPennies() + " Pennies\n");
                    keepRunning = true; 
                }
            }
        }
        
        daoLayer.writeInventory();
    }

    private Double priceCheck(VendingItem chosenItem) {
        return daoLayer.priceCheck(chosenItem);

    }

    private void buyItem(VendingItem chosenItem) {
        daoLayer.buyItem(chosenItem);

    }

    private VendingItem itemCheck(String userChoice) {
        return daoLayer.itemCheck(userChoice);
    }

    private boolean canAfford(Double userMoney, VendingItem chosenItem) {
        return chosenItem.getItemPrice() <= userMoney;
    }

    private void displayItems() {
        //get ArrayList from dao
        console.print("Here's our wonderful selection!\n");
        for (VendingItem i : daoLayer.displayItems()) {
            console.print(i.getItemName() + " - $" + i.getItemPrice());

        }
    }
}
