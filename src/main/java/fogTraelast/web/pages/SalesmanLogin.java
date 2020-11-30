package fogTraelast.web.pages;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
//import domain.users.CostumerUser;
import domain.users.NoSuchUserExists;
import domain.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SalesmanLogin/*")
public class SalesmanLogin  extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            render("Fog Trælast", "/WEB-INF/pages/loginSalesman.jsp", req, resp);
        } else {
            int userID = Integer.parseInt(req.getPathInfo().substring(1));
            log(req, "Accessing user: " + " : " + userID);
            try {
                User userList = api.findSalesman(userID);
                req.setAttribute("list", userList.getName());
                render("Fog Trælast" + userList.getName(), "/WEB-INF/pages/salesmanPage.jsp", req, resp);
            } catch (NoSuchUserExists noSuchUserExists) {
                resp.sendError(404, "User does not exist");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


        String salesmanEmail = req.getParameter("email");
        String password = req.getParameter("password");



        if (salesmanEmail == null || salesmanEmail.equals("")) {
            resp.sendError(400, "Mangler email");
        } else if(password == null || password.equals("")){
            resp.sendError(400, "Mangler tlf");
        } else{
            User list = null;
            try {
                list = api.loginSalesman(salesmanEmail, password);
            } catch (NoSuchUserExists noSuchUserExists) {
                noSuchUserExists.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/SalesmanLogin/" + list.getSalesmanID());
        }
    }
}
