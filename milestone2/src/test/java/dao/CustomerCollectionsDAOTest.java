package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public class CustomerCollectionsDAOTest extends CustomerDAOTest {

    @Override
    protected CustomerDAO createDAO() {
        return new CustomerCollectionsDAO();
    }
}
