package fogTraelast.web.pages;

import api.FogTraelast;
import domain.construction.ConstructionFactory;
import domain.construction.Roof.RoofSizeCalculator;
import domain.users.Client;
import fogTraelast.web.widget.NavBar;
import infrastructure.DBMaterialRepository;
import infrastructure.DBOrderRepository;
import infrastructure.DBUserRepository;
import infrastructure.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {

    protected static final FogTraelast api;
    protected static final RoofSizeCalculator roofSizing;
    protected static final Database db;
    protected static final ConstructionFactory constructionFactory;
    protected static Client client;
    protected static Socket socket;

    //Dette er gjort på dette format, da vi ikke har lyst til at instantiere et nyt API hver gang render køres, i det
    //kan give problemer med at dele det imellem vores servlets. Til dette bruges en class-constructor, fordi emnet
    //er static.
    static {
        db = new Database();
        api = createOrder(); //TODO bedre navngivning
        roofSizing = new RoofSizeCalculator();
        constructionFactory = new ConstructionFactory();
    }

    protected static Client createClient(InetAddress ipAdressProtcol, int port){
        try {
            socket = new Socket(ipAdressProtcol, port);
            client = new Client(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    private static FogTraelast createOrder() {
        return new FogTraelast(new DBUserRepository(db), new DBOrderRepository(db), new DBMaterialRepository(db), constructionFactory);
    }

    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        req.setAttribute("title",title);
        req.setAttribute("content",content);
        req.setAttribute("navBar", new NavBar(req));
        req.getRequestDispatcher("/WEB-INF/pages/base.jsp").forward(req,resp);
    }


    protected void log(HttpServletRequest req, String message) {
        System.err.println("(" + LocalDateTime.now() + "): " + this.getClass() + ": \"" + req.getRequestURI() + "\": " + message);
    }
}
