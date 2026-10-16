/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import org.junit.jupiter.api.BeforeAll;


/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public class CustomerJdbiDAOTest extends CustomerDAOTest{
    
    
     @Override
    protected CustomerDAO createDAO() {
        return JdbiDaoFactory.getCustomerDAO();
    }
}
