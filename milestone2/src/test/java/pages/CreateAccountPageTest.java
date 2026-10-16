/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pages;

//import javax.xml.datatype.Duration;
import dao.CustomerDAO;
import domain.Customer;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.eclipse.jetty.ee8.webapp.Configuration;
import org.eclipse.jetty.ee8.webapp.Configurations;
import org.eclipse.jetty.ee8.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

/**
 *
 * @author leuhe253
 * 
 * adapted from StudentSystem
 */
public class CreateAccountPageTest {

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
        context.getServletContext().setAttribute("CustomerDAO", mockDAO);
        server.addConnector(connector);
        server.setHandler(context);

        server.start();

        System.out.println("\nWidgetShop server ready on:\n\n" + server.getURI());

    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.close();
        server.stop();
    }

    @AfterAll
    public static void tearDownAll() {
        driver.quit();
    }

    @Test
    public void testAddStudent() throws Exception {

        driver.get("http://localhost:8083/milestone1/create-account.jsp");
        driver.findElement(By.name("username")).sendKeys("Doris123");
        driver.findElement(By.name("first-name")).sendKeys("Doris");
        driver.findElement(By.name("last-name")).sendKeys("Smith");
        driver.findElement(By.name("address")).sendKeys("123 abc Street");
        driver.findElement(By.name("email")).sendKeys("doris@aaa.com");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.id("create-account-button")).click();
        // wait for a maximum of 5 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // wait until the page is no longer add-student.jsp
        wait.until((ExpectedCondition<Boolean>) d -> {
            return !d.getCurrentUrl().equals("http://localhost:8083/milestone1/create-account.jsp");
        });

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(mockDAO).save(customerCaptor.capture());
        Customer createdStudent = customerCaptor.getValue();

        // verify that the student details are correct
        assertThat(createdStudent.getUsername(), is("Doris123"));
        assertThat(createdStudent.getFirstName(), is("Doris"));
        assertThat(createdStudent.getSurname(), is("Smith"));
        assertThat(createdStudent.getShippingAddress(), is("123 abc Street"));
        assertThat(createdStudent.getEmailAddress(), is("doris@aaa.com"));
        assertThat(createdStudent.getPassword(), is("password123"));
        

    }

}
