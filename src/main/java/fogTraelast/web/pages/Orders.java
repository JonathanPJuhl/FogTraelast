package fogTraelast.web.pages;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
        } else {
            String cmd = req.getPathInfo().substring(1);

            if (cmd.equals("new")) {
                render("Fog Trælast", "/WEB-INF/pages/createOrder.jsp", req, resp);
            } else if (cmd.equals("edit")) {
                Order order;

                try {
                    HttpSession session = req.getSession();
                    int orderID = (Integer) session.getAttribute("editID");
                    order = api.findOrder(orderID);
                    List<Order> orderList = new ArrayList<>();
                    orderList.add(order);
                    req.setAttribute("orderList", orderList);
                    render("Fog Trælast", "/WEB-INF/pages/editOrder.jsp", req, resp);
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }

            } else {
                try {
                    int orderID = Integer.parseInt(req.getPathInfo().substring(1));
                    Order order = api.findOrder(orderID);
                    req.setAttribute("list", order);
                    render("Fog Trælast", "/WEB-INF/pages/displayOrderPage.jsp", req, resp);
                } catch (NumberFormatException e) {
                    resp.sendError(400, "Badly formated request");
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    resp.sendError(404, "No such order exist");
                }

            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().substring(1).equals("create")) {
            int length = Integer.parseInt(req.getParameter("length"));
            int width = Integer.parseInt(req.getParameter("width"));
            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");


            if (customerEmail == null || customerEmail.equals("")) {
                resp.sendError(400, "Mangler email");
            } else if (customerPhone == null || customerPhone.equals("")) {
                resp.sendError(400, "Mangler tlf");
            } else {
                api.createOrder(length, width, customerPhone, customerEmail);
            }
        } else if (req.getPathInfo().substring(1).equals("edit")) {
            //Bruger indtaster orderId på den ønskede ordre og bliver dernæst sendt til "editOrder.jsp" som skal føre tilbage hertil
            //og variablerne gives dermed værdier, der ændres i db'en

            int orderID = Integer.parseInt(req.getParameter("orderID"));
            HttpSession session = req.getSession();
            session.setAttribute("editID", orderID);
            resp.sendRedirect(req.getContextPath() + "/Orders/edit");

        } else if (req.getPathInfo().substring(1).equals("editOrder")) {
            //Bruger indtaster orderId på den ønskede ordre og bliver dernæst sendt til "editOrder.jsp" som skal føre tilbage hertil
            //og variablerne gives dermed værdier, der ændres i db'en
            HttpSession session = req.getSession();
            int orderID = (Integer) session.getAttribute("editID");
            String status = req.getParameter("orderStatus");
            try {
                //Edits orderstatus field
                api.editStatus(status, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }
            int salesmanID = Integer.parseInt(req.getParameter("salesmanID"));
            try {
                //Edits salesman field
                api.editSalesman(salesmanID, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }
            double price = Double.parseDouble(req.getParameter("price"));
            try {
                //Edits price field
                api.editPrice(price, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }


        } else if (req.getParameter("newButton").equals(null) || req.getParameter("newButton").equals("")
                || req.getParameter("orderID").equals(null) || req.getParameter("orderID").equals("")) {
            resp.sendRedirect(req.getContextPath() + "/Orders/");
        } else if (req.getParameter("newButton").equals("new")) {

            resp.sendRedirect(req.getContextPath() + "/Orders/new");
        }/*else if (!(req.getParameter("orderID") == null) && !(req.getParameter("orderID").equals(""))) {
            int orderID = Integer.parseInt(req.getParameter("orderID"));
            HttpSession session = req.getSession();
            session.setAttribute("editID", orderID);
            resp.sendRedirect(req.getContextPath() + "/Orders/edit");
        }*/


    }
}



