/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author hebebebebe
 */
public class CustomerModule extends Jooby {

    private CustomerDAO dao;

    public CustomerModule(CustomerDAO dao) {
        this.dao = dao;
        get("/api/customers", ctx -> dao.getAll());

        get("/api/customers/{username}", ctx -> {

            String username = ctx.path("username").value();
            Customer customer = dao.getByUsername(username);

            if (customer == null) {
                return ctx.send(StatusCode.NOT_FOUND);
            } else {
                return customer;
            }
        });

        post("/api/register", ctx -> {

            // get student from request body
            Customer customer = ctx.body().to(Customer.class);

            dao.save(customer);

            ctx.send(StatusCode.CREATED);
            return customer;
        });

        post("/api/sign-in", ctx -> {
            Customer credentials = ctx.body().to(Customer.class);
            Customer customer = dao.getByUsername(credentials.getUsername());

            if (customer == null || customer.getPassword() == null
                    || !customer.getPassword().equals(credentials.getPassword())) {
                return ctx.send(StatusCode.UNAUTHORIZED);
            }

           return customer.getFirstName();


        });
    }
}
