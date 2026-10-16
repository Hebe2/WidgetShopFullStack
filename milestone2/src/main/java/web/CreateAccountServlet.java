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
import dao.CustomerDAO;
import domain.Customer;
import net.sf.oval.Validator;
import javax.servlet.http.HttpSession;
import net.sf.oval.exception.ConstraintsViolatedException;
import net.sf.oval.ConstraintViolation;
import javax.servlet.annotation.WebServlet;
import dao.JdbiDaoFactory;
import javax.servlet.ServletContext;

/**
 *
 * @author hebebebebe
 * adapted from AddStudentServlet from studentsystem
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/create-account"})
public class CreateAccountServlet extends HttpServlet {

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

        try {
            // extract the form data
            Integer customerId = (int) (Math.random() * 1000 + 1);
            String username = request.getParameter("username");
            String firstName = request.getParameter("first-name");
            String lastName = request.getParameter("last-name");
            String shippingAddress = request.getParameter("address");
            String emailAddress = request.getParameter("email");
            String password = request.getParameter("password");

            Customer customer = new Customer(customerId, username, firstName, lastName, shippingAddress, emailAddress);
            customer.setPassword(password);

            if (dao.doesCustomerExist(customerId)) {
                Customer oldCustomer = dao.getByID(customerId);
                dao.delete(oldCustomer);
            }

            new Validator().assertValid(customer);
            dao.save(customer);
            response.sendRedirect("sign-in.jsp");

        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("validation", "You have entered an invalid ID");
            response.sendRedirect("create-account.jsp");
        } catch (ConstraintsViolatedException ex) {

            // get the violated constraints from the exception
            ConstraintViolation[] violations = ex.getConstraintViolations();

            // create a nice error message for the user
            String msg = "Please fix the following input problems:";

            msg += "<ul class=\"input_violations\">";
            for (ConstraintViolation cv : violations) {
                msg += "<li>" + cv.getMessage() + "</li>";
            }
            msg += "</ul>";

            request.getSession().setAttribute("validation", msg);
            response.sendRedirect("create-account.jsp");
        }
    }

    @Override
    public void init() throws ServletException {
//        CustomerDAO contextDao = (CustomerDAO) getServletContext().getAttribute("CustomerDAO");
//    this.dao = (contextDao != null) ? contextDao : JdbiDaoFactory.getCustomerDAO();
    ServletContext context = getServletContext();
        this.dao = (CustomerDAO) context.getAttribute("CustomerDAO");
    }

}
