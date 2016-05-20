/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbook.ops;

import com.swcguild.addressbook.dao.AddressBookDAO;
import com.swcguild.addressbook.dto.Address;
import com.swcguild.addressbook.ui.ConsoleIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
//import static jdk.nashorn.internal.objects.NativeObject.keys;

/**
 *
 * @author apprentice
 */
public class AddressBookController {

    private ConsoleIO console = new ConsoleIO();
    private AddressBookDAO daoLayer = new AddressBookDAO();

    public void run() throws IOException {

        boolean keepRunning = true;
        int menuSelect = 0;
        
        try {
            daoLayer.loadAddressBook();
        } catch(FileNotFoundException ex) {
            keepRunning = false;
            console.print("Address book file not found.");
        }

        while (keepRunning) {
            printMenu();
            menuSelect = console.readInt("Select an option...");
            switch (menuSelect) {

                case 1: //add
                    addAddress();
                    break;

                case 2: //remove
                    removeAddress();
                    break;

                case 3: //total addresses listed
                    howManyAddresses();
                    break;

                case 4: //list all
                    listAllAddresses();
                    break;

                case 5: //search by last name
                    searchByLastName();
                    break;

                case 6: //edit address
                    editAddress();
                    break;

                case 7: //exit
                    daoLayer.writeAddressBook();
                    keepRunning = false;
                    
                    break;
            }
        }

    }

    public void printMenu() {
        console.print("Main Menu");
        console.print("1. Add Address");
        console.print("2. Remove Address");
        console.print("3. Total Addresses Listed");
        console.print("4. List All");
        console.print("5. Search by Last Name");
        console.print("6. Edit an Address");
        console.print("7. Exit");

    }

    private void addAddress() {
        String lastName = console.readString("Enter the last name.");
        String firstName = console.readString("Enter the first name.");
        String address = console.readString("Enter the address (123 Fake St., Bumblefuck, KY 12345)");

        Address add = new Address(address, firstName, lastName);

        //add address to HashMap
        daoLayer.addAddress(add);

    }

    private void removeAddress() {
        String firstName = console.readString("Please enter the first name of the entry to delete: ");
        String lastName = console.readString("Please enter the last tname of the entry to delete: ");
        Address removedAddress = daoLayer.removeAddress(firstName + "_" + lastName);
        console.print(removedAddress.getFirstName() + " " + removedAddress.getLastName()
                + " has been removed from the address book.");
        console.readString("Please press enter to continue...");

    }

    private void howManyAddresses() {

        console.print("There are a total of " + daoLayer.howManyAddresses() + " entries in the address book.");
    }

    private void listAllAddresses() {
        Collection<Address> addresses = daoLayer.listAddresses();
        for (Address add : addresses) {
            console.print("\n" + add.getFirstName() + " " + add.getLastName() + "\n");
            console.print(add.getStreetAddress() + "\n");
        }
    }

    private void searchByLastName() {
//        daolayer
        String lastName = console.readString("Please enter the last name of the person you would like to look up: ");
        ArrayList<Address> searchedAddress = daoLayer.searchAddressBook(lastName);
        if (searchedAddress.isEmpty()) {
            console.print("That name is not in your address book.");
        } else {
            for (int i = 0; i < searchedAddress.size(); i++) {
                console.print("\n" + searchedAddress.get(i).getFirstName() + " " + searchedAddress.get(i).getLastName() + "\n");
                console.print(searchedAddress.get(i).getStreetAddress() + "\n");
            }
        }
    }

    private void editAddress() {
        String firstName = console.readString("Please enter the first name of the person you would like to edit: ");
        String lastName = console.readString("Please enter the last name of the person you would like to edit: ");
        Address addressToEdit = daoLayer.editAddress(firstName + "_" + lastName);

        if (addressToEdit == null) {
            console.print("The person you entered is not in the address book.");
        } else {
            console.print(addressToEdit.getStreetAddress() + "\n");
            String newAddress = console.readString("Enter the new address: ");
            if (!newAddress.isEmpty()) {
                addressToEdit.setStreetAddress(newAddress);
                console.print("The address book entry was successfully updated.");
            } else {
                console.print("The address book entry was not changed.");
            }
        }

    }
}
