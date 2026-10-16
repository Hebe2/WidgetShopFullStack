/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pages;

import dao.CustomerDAO;
import domain.Customer;
import java.nio.file.Path;
import java.time.Duration;
import org.eclipse.jetty.ee8.webapp.Configuration;
import org.eclipse.jetty.ee8.webapp.Configurations;
import org.eclipse.jetty.ee8.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;
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
 */
public class SignInPageTest {

    private static WebDriver driver = new ChromeDriver();

    private static final Integer PORT = 8083;
    private static final String CONTEXT_PATH = "/milestone1";
    private Server server = new Server();
    private CustomerDAO mockDAO;

    @BeforeEach
    public void setUp() throws Exception {
        mockDAO = Mockito.mock(CustomerDAO.class);

        ServerConnector connector = new ServerConnector(server);

        connector.setPort(PORT);
        connector.setHost("localhost");

        WebAppContext context = new WebAppContext();
        context.setAttribute("CustomerDAO", mockDAO);

        context.setConfigurations(Configurations.getServerDefault(server).toArray(new Configuration[0]));

        context.setBaseResource(context.getResourceFactory().newResource(Path.of("build/deploy")));
        context.setContextPath(CONTEXT_PATH);
        context.setParentLoaderPriority(true);
        context.setAttribute("CustomerDAO", mockDAO);
        context.getServletContext().setAttribute("CustomerDAO", mockDAO);

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

    @Test
    public void testCorrectSignIn() throws Exception {

        Customer existingCustomer = new Customer();
        existingCustomer.setUsername("Doris123");
        existingCustomer.setPassword("password123");
        Mockito.when(mockDAO.getByUsername("Doris123")).thenReturn(existingCustomer);

        driver.get("http://localhost:8083/milestone1/sign-in.jsp");
        driver.findElement(By.name("username")).sendKeys("Doris123");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.id("sign-in-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) d
                -> !d.getCurrentUrl().equals("http://localhost:8083/milestone1/sign-in.jsp"));

        verify(mockDAO).getByUsername("Doris123");

        assertThat(driver.getCurrentUrl(), is("http://localhost:8083/milestone1/index.jsp"));
    }

    @Test
    public void testWrongSignIn() throws Exception {

        Customer existingCustomer = new Customer();
        existingCustomer.setUsername("Doris123");
        existingCustomer.setPassword("password123");
        Mockito.when(mockDAO.getByUsername("Doris123")).thenReturn(existingCustomer);

        driver.get("http://localhost:8083/milestone1/sign-in.jsp");
        driver.findElement(By.name("username")).sendKeys("Doris123");
        driver.findElement(By.name("password")).sendKeys("wrongPassword");
        driver.findElement(By.id("sign-in-button")).click();

        assertThat(driver.findElement(By.id("error")).getText(), is("Invalid username or password"));
    }

}
