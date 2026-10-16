/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public abstract class ProductDAOTest {
    protected abstract ProductDAO createDAO();
    
    private ProductCollectionsDAO productCollection;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    
     //private ProductDAO productCollection;
    
    
    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {
        productCollection = new ProductCollectionsDAO();
        //productCollection = JdbiDaoFactory.getProductDAO();


        //delete things that were set up in the DAO for testing 
        for (Product p : new ArrayList<>(productCollection.getProducts())) {
            productCollection.removeProduct(p);
        }

        product1 = new Product();
        product1.setName("Slimy Widget");
        product1.setProductId("WD1234");
        product1.setDescription("A widget that is covered in some kind of nasty shmoo.");
        product1.setCategory("Widgets");
        product1.setListPrice(new BigDecimal("7.32"));
        product1.setQuantityInStock(new BigDecimal(35));

        product2 = new Product();
        product2.setName("Green Widget");
        product2.setProductId("WD4321");
        product2.setDescription("A widget that has gone mouldy.");
        product2.setCategory("Widgets");
        product2.setListPrice(new BigDecimal("21.43"));
        product2.setQuantityInStock(new BigDecimal(3));

        product3 = new Product();
        product3.setName("Dodgy Doohicky");
        product3.setProductId("DH8832");
        product3.setDescription("A doohicky that might work, or it might not...");
        product3.setCategory("Doohickies");
        product3.setListPrice(new BigDecimal("12.32"));
        product3.setQuantityInStock(new BigDecimal(5));

        product4 = new Product();
        product4.setName("Polkadot Doohicky");
        product4.setProductId("DH8837");
        product4.setDescription("A doohicky that is covered in spots.");
        product4.setCategory("Doohickies");
        product4.setListPrice(new BigDecimal("43.23"));
        product4.setQuantityInStock(new BigDecimal(6));

        productCollection.saveProduct(product1);
        productCollection.saveProduct(product2);
        productCollection.saveProduct(product3);

    }

    @AfterEach
    public void tearDown() {
        for (Product p : new ArrayList<>(productCollection.getProducts())) {
            productCollection.removeProduct(p);
        }

    }

    @Test
    public void testSaveProduct() {
        //check its not there
        assertThat(productCollection.searchById("DH8837"), is(nullValue()));
        assertThat(productCollection.getProducts(), hasSize(3));

        // add it
        productCollection.saveProduct(product4);

        // check that it was added
        assertThat(productCollection.searchById("DH8837"), is(product4));
        assertThat(productCollection.getProducts(), hasSize(4));
    }

    @Test
    public void testRemoveProduct() {
        assertThat(productCollection.searchById("WD1234"), is(product1));
        assertThat(productCollection.getProducts(), hasSize(3));

        productCollection.removeProduct(product1);

        assertThat(productCollection.searchById("WD1234"), is(nullValue()));
        assertThat(productCollection.getProducts(), hasSize(2));

    }

    @Test
    public void testGetProducts() {
        assertThat(productCollection.getProducts(), containsInAnyOrder(product1, product2, product3));

    }

    @Test
    public void TestGetCategories() {
       assertThat(productCollection.getCategories(), containsInAnyOrder("Widgets", "Doohickies"));
    }

    @Test
    public void testSearchById() {
       assertThat(productCollection.searchById("WD4321"), is(product2));
    }

    @Test
    public void testFilterByCategory() {
        assertThat(productCollection.filterByCategory("Widgets"), contains(product1,product2));
        assertThat(productCollection.filterByCategory("Doohickies"), contains(product3));
    }
}
