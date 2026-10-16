
import dao.JdbiDaoFactory;
import java.nio.file.Path;
import org.eclipse.jetty.ee8.webapp.Configuration;
import org.eclipse.jetty.ee8.webapp.Configurations;
import org.eclipse.jetty.ee8.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServer {

    private static final Integer PORT = 8083;
    private static final String CONTEXT_PATH = "/milestone1";
    private static Logger logger = LoggerFactory.getLogger(JettyServer.class);


    public static void main(String[] args) throws Exception {

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);

        connector.setPort(PORT);
        connector.setHost("localhost");

        WebAppContext context = new WebAppContext();
        //CustomerDAO customerDAO = JdbiDaoFactory.getCustomerDAO();
        context.setAttribute("CustomerDAO", JdbiDaoFactory.getCustomerDAO());
        context.setAttribute("ProductDAO", JdbiDaoFactory.getProductDAO());

        context.setConfigurations(Configurations.getServerDefault(server).toArray(new Configuration[0]));

        context.setBaseResource(context.getResourceFactory().newResource(Path.of("build/deploy")));
        context.setContextPath(CONTEXT_PATH);
        context.setParentLoaderPriority(true);
        server.addConnector(connector);
        server.setHandler(context);

        server.start();

        logger.info("WidgetShop server ready on:" + server.getURI());

        server.join();
    }

}
