package fogTraelast.web.pages;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/Orders", "/Orders/*"})
public class Orders extends BaseServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            try {
                List<Order> orderList = api.findAllOrders();
                req.setAttribute("list", orderList);
                render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }
            } else if (req.getPathInfo().substring(1) == "new" || req.getPathInfo().substring(1).equals("new")){
            render("Fog Trælast", "/WEB-INF/pages/createOrder.jsp", req, resp);
            } else {
            int orderID = Integer.parseInt(req.getParameter("orderID"));//req.getContextPath().substring(1));
            try {
                Order order = api.findOrder(orderID);
                req.setAttribute("list", order);
                render("Fog Trælast", "/WEB-INF/pages/displayOrderPage.jsp", req, resp);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }

        }


        }



        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            if(req.getParameter("newButton").equals(null) || req.getParameter("newButton").equals("")
            || req.getParameter("orderID").equals(null) || req.getParameter("orderID").equals("")) {
                resp.sendRedirect(req.getContextPath() + "/Orders/");
            } else if (req.getParameter("newButton").equals("new")){
                resp.sendRedirect(req.getContextPath() + "/Orders/new");
            } else if (!(req.getParameter("orderID")==null) && !(req.getParameter("orderID").equals(""))){
                int orderID = Integer.parseInt(req.getParameter("orderID"));
                resp.sendRedirect(req.getContextPath() + "/Orders/"+orderID);
            }
        }
    }



