<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/layout.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Home Page</title>
    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf" %>
            <div class = "banner">
                <h1>Welcome to the WidgetShop</h1>
            </div>

            <div id="products-container">

                <div class ="img-container">
                    <img class ="product-img" src="images/widget.jpeg" alt="slimy widget">
                </div>
                <div class ="img-container">
                    <img class ="product-img" src="images/pop-widget.jpeg" alt="Description of image">
                </div>

            </div>
        </main>

        <footer>
            <h3 class="footer-text"> we sell the largest range of widgets</h3>
        </footer>
    </body>
</html>
