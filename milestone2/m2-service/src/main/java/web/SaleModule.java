/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.SaleDAO;
import io.jooby.Jooby;
import domain.Sale;
import io.jooby.StatusCode;

/**
 *
 * @author hebebebebe
 */
public class SaleModule extends Jooby {

    private Sale sale;

    public SaleModule(SaleDAO dao) {

        post("/api/sales", ctx -> {
            Sale sale = ctx.body(Sale.class);

            if (sale == null || sale.getItems() == null || sale.getItems().isEmpty()) {
                return ctx.send(StatusCode.BAD_REQUEST);
            }

            System.out.println(sale); 
            
            dao.save(sale); 

            ctx.setResponseCode(StatusCode.CREATED);
            return sale;
        });

    }
}
