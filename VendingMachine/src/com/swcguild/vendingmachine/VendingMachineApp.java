/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine;

import com.swcguild.vendingmachine.ops.VendingMachineController;
import java.io.IOException;

/**
 *
 * @author apprentice
 */
public class VendingMachineApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        VendingMachineController controller = new VendingMachineController();
        controller.run();

    }

}
