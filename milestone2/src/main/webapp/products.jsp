<%-- 
    Document   : products
    Created on : 21 Aug 2026, 23:03:11
    Author     : hebebebebe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Collection" %>
<%@ page import="domain.Product" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import = "dao.JdbiDaoFactory" %>
<%@page import="dao.ProductCollectionsDAO"%>
<%
    ProductDAO dao = (ProductDAO) application.getAttribute("ProductDAO");
    if (dao == null) {
        dao = JdbiDaoFactory.getProductDAO();
    }
%>
<!DOCTYPE html>
<!--Adapted this jsp file from view-students.jsp from student system-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Products Page</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <div class = "banner">
                <h1>Products</h1>
            </div>
            <div class="category-filter">
                <a href="products.jsp?category=All"><button class="category-button">All</button></a>
                <%
                    Collection<String> categories = dao.getCategories();

                    for (String category : categories) {
                %>

                <a href="products.jsp?category=<%= category%>"><button class="category-button"><%= category%></button></a>

                <%
                    }
                %>
            </div>
            <table  id = "products-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Available</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String selectedCategory = request.getParameter("category");

                        String filterByCategory = request.getParameter("category");

                        Collection<Product> products;

                        if (selectedCategory == null || selectedCategory.equals("All")) {
                            products = dao.getProducts();
                        } else {
                            products = dao.filterByCategory(selectedCategory);
                        }

                        for (Product product : products) {
                    %>
                    <tr id ="product-rows">
                        <td><%= product.getName()%></td>
                        <td><%= product.getDescription()%></td>
                        <td>$<%= product.getListPrice()%></td>
                        <td><%= product.getQuantityInStock()%></td>
                        <td><form action="buy-product" method="POST"><input type="hidden" name="name" value="<%= product.getName()%>"><button class="buy-button">Buy</button></form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

        </main>
    </body>
</html>
