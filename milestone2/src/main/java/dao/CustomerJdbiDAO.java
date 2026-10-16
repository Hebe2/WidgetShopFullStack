/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
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
public interface CustomerJdbiDAO extends CustomerDAO {

    @Override
    @SqlQuery("select customer_id, username, first_name, last_name, shipping_address, email_address, password "
            + "from Customer where username = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer getByUsername(@Bind("username") String username);

    @Override
    @SqlUpdate("delete from Customer where customer_id = :customerId")
    public void delete(@BindBean Customer aCustomer);

    @Override
    @SqlUpdate("insert into Customer(customer_id, username, first_name, last_name, shipping_address, email_address, password) values (:customerId, :username, :firstName, :surname, :shippingAddress, :emailAddress, :password)")
    public void save(@BindBean Customer aCustomer);

    @Override
    @SqlQuery("select customer_id AS customerId, username, first_name AS firstName, last_name AS surname, "
            + "shipping_address AS shippingAddress, email_address AS emailAddress, password "
            + "from Customer where customer_id = :customerId")
    @RegisterBeanMapper(Customer.class)
    public Customer getByID(@Bind("customerId") Integer customerId);

    @Override
    @SqlQuery("select exists (select * from Customer where customer_id = :id)")
    public boolean doesCustomerExist(@Bind("id") Integer customerId);

    @Override
    @SqlQuery("select exists (select * from Customer where username = :username and password = :password)")
    public boolean isValidLogin(@Bind("username") String username, @Bind("password") String password);

    @Override
    @SqlQuery("select customer_id AS customerId, username, first_name AS firstName, last_name AS surname, "
            + "shipping_address AS shippingAddress, email_address AS emailAddress, password "
            + "from Customer")
    @RegisterBeanMapper(Customer.class)
    public Collection<Customer> getAll();
}
