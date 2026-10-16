/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hebebebebe
 * 
 * adapted from studentsystem
 */

public class CustomerCollectionsDAO implements CustomerDAO {

    private static final Map<Integer, Customer> customers = new HashMap<>();

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public CustomerCollectionsDAO() {
        if (customers.isEmpty()) {
            Customer c1 = new Customer(1, "Doris123", "Doris", "Smith", "123 abc Street", "doris@aaa.com");
            c1.setPassword("password123");
            save(c1);

            Customer c2 = new Customer(2, "Ben456", "Ben", "Johnson", "456 def Road", "ben@aaa.com");
            c2.setPassword("pass456");
            save(c2);

            Customer c3 = new Customer(3, "Amy789", "Amy", "Taylor", "789 qwerty Street", "amy@aaa.com");
            c3.setPassword("amypass789");
            save(c3);

            Customer c4 = new Customer(4, "Leo321", "Leo", "Wong", "321 jkl Road", "leo@aaa.com");
            c4.setPassword("leopass321");
            save(c4);

        }
    }

    @Override
    public boolean isValidLogin(String username, String password) {
        Customer customer = getByUsername(username);
        if (customer.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean doesCustomerExist(Integer customerId) {
        return customers.containsKey(customerId);
    }

    @Override
    public Customer getByID(Integer customerId) {
        return customers.get(customerId);
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    @Override
    public void delete(Customer customer) {
        customers.remove(customer.getCustomerId());
    }

    @Override
    public Customer getByUsername(String username) {
        for (Customer customer : customers.values()) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Collection<Customer> getAll() {
        return customers.values();
    }
}
