/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author hebebebebe
 */
public class DaoFactory {

    private static CustomerDAO customerDAO = new CustomerCollectionsDAO();
    private static ProductDAO productDAO = new ProductCollectionsDAO();

    public static CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public static ProductDAO getProductDAO() {
        return productDAO;
    }

    public static void setProductDAO(ProductDAO dao) {
        productDAO = dao;
    }
}
