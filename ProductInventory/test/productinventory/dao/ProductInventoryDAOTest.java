package productinventory.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import productinventory.dto.Pant;
import productinventory.dto.Product;
import productinventory.dto.Shirt;
import productinventory.dto.Shoes;

/**
 *
 * @author apprentice
 */
public class ProductInventoryDAOTest {

    Shirt newShirt = new Shirt("M", 10, 333, 10.00, 2);
    Pant newPant = new Pant("33", 10, 345, 10.00, 2);
    Shoes newShoes = new Shoes("9.5", 10, 567, 10.00, 2);

    ProductInventoryDAO testObj = new ProductInventoryDAO();

    public ProductInventoryDAOTest() {
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
    public void testAddProduct() {
        testObj.addProduct("Shirt", newShirt.getSize(), newShirt.getQty(), newShirt.getSku(), newShirt.getPrice(), newShirt.getReorderLevel());
        Assert.assertTrue(testObj.inventory.size() == 1);
    }
    
    @Test
    public void testAddThreeProducts() {
        testObj.addProduct("Shirt", newShirt.getSize(), newShirt.getQty(), newShirt.getSku(), newShirt.getPrice(), newShirt.getReorderLevel());
        testObj.addProduct("Pant", newPant.getSize(), newPant.getQty(), newPant.getSku(), newPant.getPrice(), newPant.getReorderLevel());
        testObj.addProduct("Shoes", newShoes.getSize(), newShoes.getQty(), newShoes.getSku(), newShoes.getPrice(), newShoes.getReorderLevel());
        
        Assert.assertTrue(testObj.inventory.size() == 3);
    }    
    

    @Test
    public void testLoadInventory() throws FileNotFoundException {
        testObj.loadInventory();
        Assert.assertFalse(testObj.inventory.size() < 0);
    }
    
    @Test
    public void testEmptyLoadInventory() throws FileNotFoundException {
        testObj.loadInventory();
        Assert.assertNotNull(testObj.inventory.size());
    }

    @Test
    public void fetchProduct() {
        
        Product testShirt = null;
        testObj.addProduct("Shirt", newShirt.getSize(), newShirt.getQty(), newShirt.getSku(), newShirt.getPrice(), newShirt.getReorderLevel());
        testShirt = testObj.fetchProduct(333);
        Assert.assertNotNull(testShirt);
    }
    
    @Test
    public void fetchMoreProducts() {
        Product testShirt = null;
        Product testPants = null;
        Product testShoes = null;
        
        testObj.addProduct("Shirt", newShirt.getSize(), newShirt.getQty(), newShirt.getSku(), newShirt.getPrice(), newShirt.getReorderLevel());
        testObj.addProduct("Pant", newPant.getSize(), newPant.getQty(), newPant.getSku(), newPant.getPrice(), newPant.getReorderLevel());
        testObj.addProduct("Shoes", newShoes.getSize(), newShoes.getQty(), newShoes.getSku(), newShoes.getPrice(), newShoes.getReorderLevel());        
                
        testShirt = testObj.fetchProduct(333);
        testPants = testObj.fetchProduct(345);
        testShoes = testObj.fetchProduct(567);
        
        ArrayList<Product> testList = new ArrayList<>();
        testList.add(testShirt);
        testList.add(testPants);
        testList.add(testShoes);
        
        Assert.assertNotNull(testList);
    }
    
    @Test
    public void fetchAllProducts() throws FileNotFoundException {
        testObj.loadInventory();
        Collection testCollection = testObj.fetchAllProducts();
        Assert.assertNotNull(testCollection);
    }

}
