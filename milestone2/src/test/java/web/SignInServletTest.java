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
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author hebebebebe
 */
public class SignInServletTest {

    private SignInServlet servlet;

// the mock DAO
    private CustomerDAO mockDAO;

// mock request, response, context, and config
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private ServletConfig mockConfig;
    private ServletContext mockContext;
    private HttpSession mockSession;

    @BeforeEach
    public void setUp() throws Exception {
        mockDAO = mock(CustomerDAO.class);
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockConfig = mock(ServletConfig.class);
        mockContext = mock(ServletContext.class);
        mockSession = mock(HttpSession.class);

        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("CustomerDAO")).thenReturn(mockDAO);

        servlet = new SignInServlet();
        servlet.init(mockConfig);

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCorrectLogin() throws Exception {
        Customer customer = new Customer();
        customer.setUsername("Doris123");
        customer.setPassword("password123");

        when(mockRequest.getParameter("username")).thenReturn("Doris123");
        when(mockRequest.getParameter("password")).thenReturn("password123");
        when(mockDAO.getByUsername("Doris123")).thenReturn(customer);

        servlet.doPost(mockRequest, mockResponse);

        verify(mockSession).setAttribute("customer", customer);
        verify(mockResponse).sendRedirect("index.jsp");
    }

    @Test
    public void testWrongPassword() throws Exception {
        Customer customer = new Customer();
        customer.setUsername("Doris123");
        customer.setPassword("password123");

        when(mockRequest.getParameter("username")).thenReturn("Doris123");
        when(mockRequest.getParameter("password")).thenReturn("wrongpassword");
        when(mockDAO.getByUsername("Doris123")).thenReturn(customer);

        servlet.doPost(mockRequest, mockResponse);

        verify(mockSession).setAttribute("validation", "Invalid username or password");
        verify(mockResponse).sendRedirect("sign-in.jsp");
    }

}
