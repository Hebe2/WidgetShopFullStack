package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.ProductCollectionsDAO;
import dao.ProductDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;
import io.jooby.handler.Cors;
import io.jooby.handler.CorsHandler;
import io.jooby.netty.NettyServer;

public class Server extends Jooby {

    private static final int PORT = 8087;
    
    public Server() {
        this(new ProductCollectionsDAO(),new CustomerCollectionsDAO());
    }
    

    public Server(ProductDAO product_dao, CustomerDAO customer_dao) {

        Cors cors = new Cors()
                .setOrigin("*")
                .setHeaders("*")
                .setMethods("GET", "POST", "PUT", "DELETE");

        use(new CorsHandler(cors));
        install(new GsonModule());

        // mount your modules here
        mount(new ProductModule(product_dao));
        mount(new CustomerModule(customer_dao));
        //mount(new SaleModule(sale_dao));

    }

    public static void main(String[] args) {
        System.out.println("\nStarting Server.");
        ServerOptions options = new ServerOptions().setPort(PORT);
        runApp(args, new NettyServer(options), () -> new Server());
    }

}
