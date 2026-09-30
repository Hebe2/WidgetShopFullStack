package web;

import dao.CustomerDAO;
import dao.CustomerJdbiDAO;
import dao.JdbiDaoFactory;
import dao.ProductDAO;
import dao.ProductJdbiDAO;
import dao.SaleDAO;
import dao.SaleJdbiDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;
import io.jooby.handler.Cors;
import io.jooby.handler.CorsHandler;
import io.jooby.netty.NettyServer;
import java.util.Set;
import dao.CustomerCollectionsDAO;
import dao.ProductCollectionsDAO;
import dao.SaleCollectionsDAO;

public class Server extends Jooby {

    private static final int PORT = 8087;

    public Server() {
        this(new ProductCollectionsDAO(), new CustomerCollectionsDAO(), new SaleCollectionsDAO());
    }

    public Server(ProductDAO product_dao, CustomerDAO customer_dao, SaleDAO sale_dao) {

        Cors cors = new Cors()
                .setOrigin("*")
                .setHeaders("*")
                .setMethods("GET", "POST", "PUT", "DELETE");
        
        use(new CorsHandler(cors));
        install(new GsonModule());
        install(new BasicAccessAuth(customer_dao, Set.of("/api/.*"), Set.of("/api/register")));

        // mount your modules here
        mount(new ProductModule(product_dao));
        mount(new CustomerModule(customer_dao));
        mount(new SaleModule(sale_dao));

    }

    public static void main(String[] args) {
        System.out.println("\nStarting Server.");
        ServerOptions options = new ServerOptions().setPort(PORT);
        
        
    ProductDAO product_dao = JdbiDaoFactory.getProductDAO();
    CustomerDAO customer_dao = JdbiDaoFactory.getCustomerDAO();
    SaleDAO sale_dao = JdbiDaoFactory.getSaleDAO();

    
        runApp(args, new NettyServer(options), () -> new Server(product_dao, customer_dao, sale_dao));
    }

}
