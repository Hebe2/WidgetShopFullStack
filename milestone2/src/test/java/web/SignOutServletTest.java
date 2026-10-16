package web;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import dao.CustomerDAO;
import domain.Customer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author hebebebebe
 */
public class SignOutServletTest {

    private SignOutServlet servlet;

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

        when(mockRequest.getSession(false)).thenReturn(mockSession);
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("CustomerDAO")).thenReturn(mockDAO);

        servlet = new SignOutServlet();
        servlet.init(mockConfig);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testDoGet() throws Exception {
        servlet.doGet(mockRequest, mockResponse);

        verify(mockSession).invalidate();
        verify(mockResponse).sendRedirect("index.jsp");
    }

}
