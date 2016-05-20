/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbook.dao;

import com.swcguild.addressbook.dto.Address;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class AddressBookDAOTest {

    AddressBookDAO dao = new AddressBookDAO();
    Address testAddr = new Address("123 Fake Street", "Pablo", "Escobar");

    public AddressBookDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addAddressTest() {
        dao.addAddress(testAddr);
        Assert.assertTrue(1 == dao.howManyAddresses());

    }

    @Test
    public void removeAddressTest() {
        dao.addAddress(testAddr);
        dao.removeAddress("Pablo_Escobar");
        Assert.assertTrue(0 == dao.howManyAddresses());

    }

    @Test
    public void howManyAddressTest() {
        dao.addAddress(testAddr);
        Assert.assertEquals(1, dao.howManyAddresses());

    }

    @Test
    public void listAddressesTest() {
        dao.addAddress(testAddr);
        Collection<Address> test = dao.listAddresses();
        Assert.assertTrue(test.contains(testAddr));
    }

    @Test
    public void searchAddressBookTest() {
        dao.addAddress(testAddr);

        ArrayList<Address> testArrayList = dao.searchAddressBook("Escobar");
        Assert.assertEquals(testArrayList.get(0), testAddr);

    }

    @Test
    public void editAddressTest() {
        dao.addAddress(testAddr);
        Address testAddressToEdit = dao.editAddress("Pablo_Escobar");
        Assert.assertTrue(testAddr == testAddressToEdit);
        
    }
}
