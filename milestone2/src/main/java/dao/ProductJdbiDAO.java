/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author hebebebebe
 * 
 * adapted from studentsystem
 */
public interface ProductJdbiDAO extends ProductDAO {

    @Override
    @SqlQuery("select product_id AS productId, product_name AS name, description, category, list_price AS listPrice, quantity_in_stock AS quantityInStock from Product where product_id = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("productId") String productId);

    @Override
    @SqlUpdate("insert into Product(product_id, product_name, description, category, list_price, quantity_in_stock) values (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product aProduct);

    @Override
    @SqlUpdate("delete from Product where product_id = :productId")
    public void removeProduct(@BindBean Product aProduct);

    @Override
    @SqlQuery("SELECT product_id AS productId, product_name AS name, description, category, list_price AS listPrice, quantity_in_stock AS quantityInStock FROM Product ORDER BY product_Id")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();

    @Override
    @SqlQuery("select distinct category from Product order by category ASC")
    public Collection<String> getCategories();

    @Override
    @SqlQuery("SELECT product_id AS productId, product_name AS name, description, category, list_price AS listPrice, quantity_in_stock AS quantityInStock FROM Product WHERE category = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);

}
