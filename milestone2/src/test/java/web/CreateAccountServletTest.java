/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 *
 * @author hebebebebe
 */
public class CreateAccountServletTest {

    private CreateAccountServlet servlet;

// the mock DAO
    private CustomerDAO mockDAO;

// mock request, response, context, and config
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private ServletContext mockContext;
    private ServletConfig mockConfig;

    @BeforeEach
    public void setUp() throws Exception {
        mockDAO = mock(CustomerDAO.class);
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockContext = mock(ServletContext.class);
        mockConfig = mock(ServletConfig.class);

        // stub the request getParameter method to return student details
        when(mockRequest.getParameter("username")).thenReturn("Doris123");
        when(mockRequest.getParameter("first-name")).thenReturn("Doris");
        when(mockRequest.getParameter("last-name")).thenReturn("Smith");
        when(mockRequest.getParameter("address")).thenReturn("123 Some Street");
        when(mockRequest.getParameter("email")).thenReturn("doris@example.com");
        when(mockRequest.getParameter("password")).thenReturn("password123");

        // stub the servlet config to return the mock context
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        // inject the mock DAO dependency via the mock context
        when(mockContext.getAttribute("CustomerDAO")).thenReturn(mockDAO);
        // create and initialise servlet
        servlet = new CreateAccountServlet();
        servlet.init(mockConfig);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testDoPost() throws Exception {
        servlet.doPost(mockRequest, mockResponse);
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(mockDAO).save(customerCaptor.capture());
        // capture the student		
        Customer createdCustomer = customerCaptor.getValue();

        // verify that the customer details are correct
        assertThat(createdCustomer.getUsername(), is("Doris123"));
        assertThat(createdCustomer.getFirstName(), is("Doris"));
        assertThat(createdCustomer.getSurname(), is("Smith"));
        assertThat(createdCustomer.getShippingAddress(), is("123 Some Street"));
        assertThat(createdCustomer.getEmailAddress(), is("doris@example.com"));
        assertThat(createdCustomer.getPassword(), is("password123"));
        assertThat(createdCustomer.getCustomerId(), is(both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(1000))));
        // verify the sendRedirect was called on the response with the correct page
        verify(mockResponse).sendRedirect("sign-in.jsp");
    }

}
