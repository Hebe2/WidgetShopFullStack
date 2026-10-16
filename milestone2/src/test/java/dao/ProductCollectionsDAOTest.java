/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;



/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public class ProductCollectionsDAOTest extends ProductDAOTest{

    @Override
    protected ProductDAO createDAO() {
        return new ProductCollectionsDAO();
    }
}
