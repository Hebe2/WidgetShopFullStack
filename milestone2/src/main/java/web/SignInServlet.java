/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import dao.CustomerDAO;
import dao.CustomerCollectionsDAO;
import dao.JdbiDaoFactory;
import domain.Customer;

/**
 *
 * @author hebebebebe
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/sign-in"})
public class SignInServlet extends HttpServlet {

    private CustomerDAO dao;

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer = dao.getByUsername(username);

        if (customer != null && customer.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("validation", "Invalid username or password");
            response.sendRedirect("sign-in.jsp");
        }
    }

    @Override
    public void init() throws ServletException {
        //this.dao = JdbiDaoFactory.getCustomerDAO();
        CustomerDAO contextDao = (CustomerDAO) getServletContext().getAttribute("CustomerDAO");
        this.dao = (contextDao != null) ? contextDao : JdbiDaoFactory.getCustomerDAO();
    }
}
