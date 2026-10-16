/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;


/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public class ProductJdbiDAOTest extends ProductDAOTest{
    
    @Override
    protected ProductDAO createDAO() {
        return JdbiDaoFactory.getProductDAO();
    }
    
}
