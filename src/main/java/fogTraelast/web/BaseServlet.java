package fogTraelast.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {

    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        //resp.setContentType("text/html");
        //resp.getWriter().println("<h1>Hello, World</h1>");
        //req.setAttribute("navbar", new Navbar(req));
        req.setAttribute("title",title);
        req.setAttribute("content",content);
        req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req,resp);
    }

   /* private static CupCakeAppRepository createCupCakeApp() throws IOException, SQLException {
        Migrate.runMigrations();
        return new CupCakeAppRepository(db, db, db);
    }
*/
    protected void setup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }

    protected void log(HttpServletRequest req, String message) {
        System.err.println("(" + LocalDateTime.now() + "): " + this.getClass().getCanonicalName() + ": \"" + req.getRequestURI() + "\": " + message);
    }
}
