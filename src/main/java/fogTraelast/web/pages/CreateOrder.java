package fogTraelast.web.pages;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateOrder/*")
public class CreateOrder extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            render("Olsker Cupcakes", "/WEB-INF/pages/createUsr.jsp", req, resp);
        } else {
            int userID = Integer.parseInt(req.getPathInfo().substring(1));
            log(req, "Accessing user: " + " : " + userID);
            try {
                Order orderList = api.findOrder(userID);
                req.setAttribute("list", orderList);
                render("Olsker Cupcakes: "/* + orderList.getName()*/, "/WEB-INF/pages/displayUserPage.jsp", req, resp);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                resp.sendError(404, "User does not exist");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double length = Double.parseDouble(req.getParameter("length"));
        double width = Double.parseDouble(req.getParameter("width"));
        String customerEmail = req.getParameter("email");
        String customerPhone = req.getParameter("phone");
        if (customerEmail == null || customerEmail.equals("")) {
            resp.sendError(400, "Mangler email");
        } else if(customerPhone == null || customerPhone.equals("")){
            resp.sendError(400, "Mangler tlf");
        } else{
            Order list = api.createOrder(length, width, customerPhone, customerEmail);
            resp.sendRedirect(req.getContextPath() + "/CreateUsr/" + list.getOrderID());
        }
    }
}
