/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbook;

import com.swcguild.addressbook.ops.AddressBookController;
import java.io.IOException;

/**
 *
 * @author apprentice
 */
public class AddressBookApp {

    public static void main(String[] args) throws IOException{

        AddressBookController controller = new AddressBookController();

        controller.run();
    }
}
