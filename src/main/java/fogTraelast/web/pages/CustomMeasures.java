package fogTraelast.web.pages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/byg_selv_carport")
public class CustomMeasures extends BaseServlet {

    //BRuger ikke

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        /*resp.setContentType("text/html");
        resp.getWriter().println("<h1>Hello, World</h1>");*/
        render("Byg selv","/WEB-INF/customMeasures.jsp", req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int chosenLength = Integer.parseInt((String)req.getSession().getAttribute("chosenLength"));
        int chosenWidth = Integer.parseInt((String)req.getSession().getAttribute("chosenWidth"));
    }
}