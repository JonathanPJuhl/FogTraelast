package fogTraelast.web.pages;

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
        req.setAttribute("title",title);
        req.setAttribute("content",content);
        req.getRequestDispatcher("/WEB-INF/pages/base.jsp").forward(req,resp);
    }


    protected void log(HttpServletRequest req, String message) {
        System.err.println("(" + LocalDateTime.now() + "): " + this.getClass() + ": \"" + req.getRequestURI() + "\": " + message);
    }
}
