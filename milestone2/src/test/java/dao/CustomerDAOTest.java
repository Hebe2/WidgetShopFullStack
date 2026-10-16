/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public abstract class CustomerDAOTest {
    protected abstract CustomerDAO createDAO();
    
    private CustomerCollectionsDAO customerCollection;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    //private CustomerDAO customerCollection;
    
    
    @BeforeAll
    public static void initialise() {
        try{
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @BeforeEach
    public void setUp() {

        customerCollection = new CustomerCollectionsDAO();
        //customerCollection = JdbiDaoFactory.getCustomerDAO();

        for (Customer customer : new ArrayList<>(customerCollection.getAll())) {
            customerCollection.delete(customer);
        }

        customer1 = new Customer();
        customer1.setCustomerId(1234);
        customer1.setUsername("Doris123");
        customer1.setFirstName("doris");
        customer1.setSurname("smith");
        customer1.setShippingAddress("123 abc street");
        customer1.setEmailAddress("doris@gmail.com");
        customer1.setPassword("password123");

        customer2 = new Customer();
        customer2.setCustomerId(4321);
        customer2.setUsername("Ben321");
        customer2.setFirstName("Ben");
        customer2.setSurname("Johnson");
        customer2.setShippingAddress("321 efg road");
        customer2.setEmailAddress("ben@gmail.com");
        customer2.setPassword("ben123password");

        customer3 = new Customer();
        customer3.setCustomerId(5678);
        customer3.setUsername("Mary678");
        customer3.setFirstName("Mary");
        customer3.setSurname("Jane");
        customer3.setShippingAddress("678 qwerty road");
        customer3.setEmailAddress("mary@gmail.com");
        customer3.setPassword("mary12345678");

        customerCollection.save(customer1);
        customerCollection.save(customer2);

    }

    @AfterEach
    public void tearDown() {
        customerCollection.delete(customer1);
        customerCollection.delete(customer2);
        customerCollection.delete(customer3);
    }

    @Test
    public void TestSave() {
        assertThat(customerCollection.doesCustomerExist(5678), is(false));
        assertThat(customerCollection.getAll(), hasSize(2));

        customerCollection.save(customer3);

        assertThat(customerCollection.doesCustomerExist(5678), is(true));
        assertThat(customerCollection.getAll(), hasSize(3));
    }

    @Test
    public void testDelete() {

        // make sure that item1 does already exist
        assertThat(customerCollection.doesCustomerExist(1234), is(true));
        assertThat(customerCollection.getAll(), hasSize(2));

        // delete it
        customerCollection.delete(customer1);

        // make sure that it is gone
        assertThat(customerCollection.doesCustomerExist(1234), is(false));
        assertThat(customerCollection.getAll(), hasSize(1));
    }

    @Test
    public void testGetAll() {
        assertThat(customerCollection.getAll(), containsInAnyOrder(customer1, customer2));
    }

    @Test
    public void testGetByID() {
        assertThat(customerCollection.getByID(1234), is(customer1));
        assertThat(customerCollection.getByID(5678), is(nullValue()));
    }

    @Test
    public void testGetByUsername() {
        assertThat(customerCollection.getByUsername("Doris123"), is(customer1));
        assertThat(customerCollection.getByUsername("Mary678"), is(nullValue()));
    }

    @Test
    public void testDoesCustomerExist() {
        assertThat(customerCollection.doesCustomerExist(1234), is(true));
        assertThat(customerCollection.doesCustomerExist(5678), is(false));

    }

    @Test
    public void testIsValidLogin() {
        Customer c = customerCollection.getByID(1234);
        assertThat(c.getUsername(), is("Doris123"));
        assertThat(c.getPassword(), is("password123"));

        assertThat(c.getUsername(), is(not("Ben321")));
        assertThat(c.getPassword(), is(not("ben123password")));
    }

    
}
