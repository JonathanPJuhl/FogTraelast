package fogTraelast.web.pages;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;

import domain.users.NoSuchUserExists;
import domain.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/SalesmanLogin","/SalesmanLogin/*"})
public class SalesmanLogin  extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            render("Fog Trælast", "/WEB-INF/pages/loginSalesman.jsp", req, resp);
        } else if (req.getPathInfo().substring(1).equals("Logout")){
            HttpSession session = req.getSession(true);
            session.setAttribute("user", null);
            session.setAttribute("userID", null);
            render("Fog Trælast", "/WEB-INF/pages/index.jsp", req, resp);
        }


        else {
            HttpSession session = req.getSession();

             Integer userID = (Integer)session.getAttribute("userID");
            if(userID==null){
                resp.sendError(403);
                return;
            }
            log(req, "Accessing user: " + " : " + userID);

            try {
                User userList = api.findSalesman(userID);
                session.setAttribute("userName", userList.getName());
                log(req, "user: " + userList);
                render("Fog Trælast", "/WEB-INF/pages/salesmanPage.jsp", req, resp);
            } catch (NoSuchUserExists noSuchUserExists) {
                resp.sendError(404, "User does not exist");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            String salesmanEmail = req.getParameter("email");
            String password = req.getParameter("password");

            if (salesmanEmail == null || salesmanEmail.equals("")) {
                resp.sendError(400, "Mangler email");
            } else if (password == null || password.equals("")) {
                resp.sendError(400, "Mangler tlf");
            } else {
                User list;
                try {
                    list = api.loginSalesman(salesmanEmail, password);
                    HttpSession session = req.getSession(true);
                    session.setAttribute("user", list);
                    session.setAttribute("userID", list.getID());
                } catch (NoSuchUserExists noSuchUserExists) {
                    noSuchUserExists.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/SalesmanLogin/");
            }

    }
}
