/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pages;

import dao.CustomerDAO;
import dao.ProductDAO;
import domain.Customer;
import domain.Product;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import org.eclipse.jetty.ee8.webapp.Configuration;
import org.eclipse.jetty.ee8.webapp.Configurations;
import org.eclipse.jetty.ee8.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author hebebebebe
 * 
 * adapted from StudentSystem
 */
public class ViewProductsPageTest {

    private static WebDriver driver = new ChromeDriver();

    private static final Integer PORT = 8083;
    private static final String CONTEXT_PATH = "/milestone1";
    private Server server = new Server();
    private ProductDAO mockProductDAO;
    private CustomerDAO mockCustomerDAO;

    @BeforeEach
    public void setUp() throws Exception {
        mockProductDAO = Mockito.mock(ProductDAO.class);
        mockCustomerDAO = Mockito.mock(CustomerDAO.class);

        ServerConnector connector = new ServerConnector(server);

        connector.setPort(PORT);
        connector.setHost("localhost");

        WebAppContext context = new WebAppContext();
        context.setAttribute("CustomerDAO", mockCustomerDAO);
        context.setAttribute("ProductDAO", mockProductDAO);

        context.setConfigurations(Configurations.getServerDefault(server).toArray(new Configuration[0]));

        context.setBaseResource(context.getResourceFactory().newResource(Path.of("build/deploy")));
        context.setContextPath(CONTEXT_PATH);
        context.setParentLoaderPriority(true);
        context.setAttribute("CustomerDAO", mockCustomerDAO);
        context.setAttribute("ProductDAO", mockProductDAO);

        context.getServletContext().setAttribute("CustomerDAO", mockCustomerDAO);
        context.getServletContext().setAttribute("ProductDAO", mockProductDAO);

        server.addConnector(connector);
        server.setHandler(context);

        server.start();

        System.out.println("\nWidgetShop server ready on:\n\n" + server.getURI());

    }

    @AfterEach
    public void tearDown() throws Exception {
        //driver.close();
        server.stop();
    }

    @AfterAll
    public static void tearDownAll() {
        driver.quit();
    }

    private void signIn(String username, String password) throws Exception {
        Customer existingCustomer = new Customer();
        existingCustomer.setUsername(username);
        existingCustomer.setPassword(password);
        Mockito.when(mockCustomerDAO.getByUsername(username)).thenReturn(existingCustomer);

        driver.get("http://localhost:8083/milestone1/sign-in.jsp");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("sign-in-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) d
                -> !d.getCurrentUrl().equals("http://localhost:8083/milestone1/sign-in.jsp"));
    }

    @Test
    public void TestProductsDisplayed() throws Exception {

        Product createdProduct = new Product();
        createdProduct.setName("Slimy Widget");
        createdProduct.setProductId("WD1234");
        createdProduct.setDescription("A widget that is covered in some kind of nasty shmoo.");
        createdProduct.setCategory("Widgets");
        createdProduct.setListPrice(new BigDecimal("7.32"));
        createdProduct.setQuantityInStock(new BigDecimal(35));
        Mockito.when(mockProductDAO.getProducts()).thenReturn(List.of(createdProduct));

        signIn("Doris123", "password123");
        driver.get("http://localhost:8083/milestone1/products.jsp");

        verify(mockProductDAO).getProducts();

        assertThat(driver.getPageSource(), containsString("Slimy Widget"));
        assertThat(driver.getPageSource(), containsString("A widget that is covered in some kind of nasty shmoo."));
        assertThat(driver.getPageSource(), containsString("7.32"));
        assertThat(driver.getPageSource(), containsString("35"));
    }

    @Test
    public void TestCategoriesDisplayed() throws Exception {
        Product widget = new Product();
        widget.setName("Slimy Widget");
        widget.setProductId("WD1234");
        widget.setDescription("A widget that is covered in some kind of nasty shmoo.");
        widget.setCategory("Widgets");
        widget.setListPrice(new BigDecimal("7.32"));
        widget.setQuantityInStock(new BigDecimal(35));
        Mockito.when(mockProductDAO.getProducts()).thenReturn(List.of(widget));
        Mockito.when(mockProductDAO.getCategories()).thenReturn(List.of("Widgets"));

        signIn("Doris123", "password123");
        driver.get("http://localhost:8083/milestone1/products.jsp");

        verify(mockProductDAO).getCategories();
        assertThat(driver.getPageSource(), containsString("Widgets"));

    }

    @Test
    public void TestFilterByWidgets() throws Exception {
        Product widget = new Product();
        widget.setName("Slimy Widget");
        widget.setProductId("WD1234");
        widget.setDescription("A widget that is covered in some kind of nasty shmoo.");
        widget.setCategory("Widgets");
        widget.setListPrice(new BigDecimal("7.32"));
        widget.setQuantityInStock(new BigDecimal(35));
        Mockito.when(mockProductDAO.getCategories()).thenReturn(List.of("Widgets", "Doohickies"));
        Mockito.when(mockProductDAO.filterByCategory("Widgets")).thenReturn(List.of(widget));

        signIn("Doris123", "password123");
        driver.get("http://localhost:8083/milestone1/products.jsp");

        driver.findElement(By.linkText("Widgets")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) d
                -> d.getCurrentUrl().contains("category=Widgets"));

        verify(mockProductDAO).filterByCategory("Widgets");

        assertThat(driver.getPageSource(), containsString("Slimy Widget"));
        assertThat(driver.getPageSource(), containsString("A widget that is covered in some kind of nasty shmoo."));
        assertThat(driver.getPageSource(), containsString("7.32"));
        assertThat(driver.getPageSource(), containsString("35"));
        assertThat(driver.getPageSource(), not(containsString("Dodgy Doohicky")));

    }

    @Test
    public void TestFilterByDoohickies() throws Exception {

        Product doohicky = new Product();
        doohicky.setName("Dodgy Doohicky");
        doohicky.setProductId("DH8832");
        doohicky.setDescription("A doohicky that might work, or it might not...");
        doohicky.setCategory("Doohickies");
        doohicky.setListPrice(new BigDecimal("12.32"));
        doohicky.setQuantityInStock(new BigDecimal(5));
        Mockito.when(mockProductDAO.getCategories()).thenReturn(List.of("Widgets", "Doohickies"));
        Mockito.when(mockProductDAO.filterByCategory("Doohickies")).thenReturn(List.of(doohicky));

        signIn("Doris123", "password123");
        driver.get("http://localhost:8083/milestone1/products.jsp");

        driver.findElement(By.linkText("Doohickies")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) d
                -> d.getCurrentUrl().contains("category=Doohickies"));

        verify(mockProductDAO).filterByCategory("Doohickies");

        assertThat(driver.getPageSource(), containsString("Dodgy Doohicky"));
        assertThat(driver.getPageSource(), containsString("A doohicky that might work, or it might not..."));
        assertThat(driver.getPageSource(), containsString("12.32"));
        assertThat(driver.getPageSource(), containsString("5"));
        assertThat(driver.getPageSource(), not(containsString("Slimy Widget")));

    }

}
