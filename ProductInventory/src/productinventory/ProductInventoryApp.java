/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productinventory;

import java.io.IOException;
import productinventory.ops.ProductInventoryController;

/**
 *
 * @author apprentice
 */
public class ProductInventoryApp {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ProductInventoryController controller = new ProductInventoryController();
        controller.run();
    }

}
