/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanager;

import bankaccountmanager.ops.bankAccountManagerController;

/**
 *
 * @author apprentice
 */
public class bankAccountApp {

    public static void main(String[] args) {
        bankAccountManagerController controller = new bankAccountManagerController();
        controller.run();
    }
}
