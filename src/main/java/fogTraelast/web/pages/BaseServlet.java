package fogTraelast.web.pages;

import api.FogTraelast;
import fogTraelast.web.widget.NavBar;
import infrastructure.DBOrderRepository;
import infrastructure.DBUserRepository;
import infrastructure.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {

    protected static final FogTraelast api;

    //Dette er gjort på dette format, da vi ikke har lyst til at instantiere et nyt API hver gang render køres, i det
    //kan give problemer med at dele det imellem vores servlets. Til dette bruges en class-constructor, fordi emnet
    //er static.
    static {
        Database db = new Database();
        api = createOrder();
    }
    private static FogTraelast createOrder() {
        Database db = new Database();
        return new FogTraelast(new DBUserRepository(db), new DBOrderRepository(db));
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