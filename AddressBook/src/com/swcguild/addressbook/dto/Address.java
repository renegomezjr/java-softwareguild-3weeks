/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbook.dto;

/**
 *
 * @author apprentice
 */
public class Address {

    private String streetAddress, firstName, lastName;

    public Address(String streetAddress, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String createFullName(){
        return this.firstName +"_"+ this.lastName;
    }

}
