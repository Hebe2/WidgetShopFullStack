<%-- 
    Document   : create-account
    Created on : 19 Aug 2026, 15:01:27
    Author     : hebebebebe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--Adapted this jsp file from add-student.jsp in student system-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Create Account Page</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <div class = "banner">
                <h1>Create Account</h1>
            </div>


            <form action="create-account" method="POST">
                <div id ="form-container">

                    <fieldset id="account-details">

                        <legend>Account Details</legend>
                        <%
                        String validation = (String)session.getAttribute("validation");
                        validation = validation != null ? validation : "";
                        session.removeAttribute("validation");
   
                        %>

                        <p><%= validation %></p>

                        <label>Username:</label><input type="text" name="username" required/>
                        <label>First Name:</label><input type="text" name="first-name" required/>
                        <label>Last Name:</label><input type="text" name="last-name" required/>
                        <label>Address:</label><textarea name="address" required></textarea>
                        <label>Email:</label><input type="text" name="email" required/>
                        <label>Password</label><input type="password" name="password" required/>

                        <button class="button" id ="create-account-button" type="submit">Create Account</button>
                        
                        
                    </fieldset>
                </div>
            </form>
                        <div id="signin-button-container">
            <button class="button" id="createpage-signin-button"><a href="sign-in.jsp">Sign-in</button>
                        </div>
        </main>

    </body>
</html>
