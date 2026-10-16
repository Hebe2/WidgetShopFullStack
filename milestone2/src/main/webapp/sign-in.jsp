<%-- 
    Document   : sign-in
    Created on : 19 Aug 2026, 15:02:31
    Author     : hebebebebe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--Adapted this jsp file from add-student.jsp from student system-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Sign In Page</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <div class ="banner">
                <h1>Sign In</h1>
            </div>

            <form action="sign-in" method="POST">
                <div id ="sign-in-container">
                    <fieldset id="account-details">

                        <legend>Account Details</legend>
                        <%
                        String validation = (String)session.getAttribute("validation");
                        validation = validation != null ? validation : "";
                        session.removeAttribute("validation");
   
                        %>

                        <p id="error"><%= validation %></p>

                        <label>Username:</label><input type="text" name="username" required/>
                        <label>Password</label><input type="password" name="password" required/>

                        <button id="sign-in-button" type="submit">Sign In </button>
                        
                    </fieldset>
                </div>
            </form>
            
            <h3>If you don't have an account the you can <a href="create-account.jsp">create one</a></h3>
        </main>
    </body>
</html>
