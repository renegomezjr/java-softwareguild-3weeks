/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbook.dao;

import com.swcguild.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class AddressBookDAO {

    public static final String ADDRESS_BOOK_FILE = "AddressBook.txt";
    private static final String DELIMITER = "::";

    HashMap<String, Address> addressBook = new HashMap<>();
    //print properties

    public Address removeAddress(String fullName) {
        return addressBook.remove(fullName);

    }

    public void addAddress(Address fullEntry) {

        addressBook.put(fullEntry.createFullName(), fullEntry);
    }

    public int howManyAddresses() {
        int numberOfAddresses = addressBook.size();
        return numberOfAddresses;

    }

    public Collection<Address> listAddresses() {
//        Set<String> keys = addressBook.keySet();
        Collection<Address> addresses = addressBook.values();
        return addresses;
    }

    public ArrayList<Address> searchAddressBook(String lastName) {
        Collection<Address> addresses = addressBook.values();
        ArrayList<Address> matchingAddresses = new ArrayList<>();
        for (Address addSearch : addresses) {
            if (addSearch.getLastName().equalsIgnoreCase(lastName)) {
                matchingAddresses.add(addSearch);
            }
        }
        return matchingAddresses;
    }

    public Address editAddress(String fullName) {

        Address addressToEdit = addressBook.get(fullName);
//        ArrayList<Address> matchingAddresses = new ArrayList<>();
//        for (Address addSearch : addresses){
//            if(addSearch.getLastName().equalsIgnoreCase(fullName)){
//                matchingAddresses.add(addSearch);
//            }

        return addressToEdit;
    }

    public void loadAddressBook() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(ADDRESS_BOOK_FILE)));
        while (sc.hasNextLine()) {

            String recordLine = sc.nextLine();
            String[] recordProperties = recordLine.split(DELIMITER);

            if (recordProperties.length != 3) {
                continue;
            }

            String address = recordProperties[0];
            String firstName = recordProperties[1];
            String lastName = recordProperties[2];

            Address addressRecord = new Address(address, firstName, lastName);
            addressBook.put(addressRecord.createFullName(), addressRecord);
        }
    }

    public void writeAddressBook() throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(ADDRESS_BOOK_FILE));
        Collection<Address> allAddressesInHashMap = addressBook.values();

        for (Address address : allAddressesInHashMap) {
            writer.println(address.getStreetAddress() + DELIMITER
                    + address.getFirstName() + DELIMITER
                    + address.getLastName());
        }

        writer.flush();
        writer.close();

    }
}
