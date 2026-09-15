/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.ProductDAO;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import java.util.Collection;

/**
 *
 * @author hebebebebe
 */
public class ProductModule extends Jooby{
    private ProductDAO dao;

    public ProductModule(ProductDAO dao) {
        this.dao = dao;
        //get products
        get("/products", ctx -> dao.getProducts());

        //get products by id
        get("/api/products/{id}", ctx -> {

            String id = ctx.path("id").value();
            Product product = dao.searchById(id);

            if (product == null) {
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return product;
            }
        });

       //get categories
        get("/api/categories", ctx -> dao.getCategories());
        
        //get products by category
        get("/api/categories/{category}", ctx -> {

            String category = ctx.path("category").value();
            Collection<Product> products = dao.filterByCategory(category);

            if (products.isEmpty() || products == null) {
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return products;
            }
        });
    }
}
