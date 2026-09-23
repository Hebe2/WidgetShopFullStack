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
 *adapted from studentsystem
 */
public interface CustomerDAO {

    boolean doesCustomerExist(Integer customerId);
    
    boolean isValidLogin(String username, String password);

    Customer getByID(Integer customerId);

    void save(Customer customer);

    void delete(Customer customer);

    Customer getByUsername(String username);
    
    Collection<Customer> getAll();
}

