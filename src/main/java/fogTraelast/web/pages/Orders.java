package fogTraelast.web.pages;

import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.PitchedRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofCalculator;
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
                    try {
                        List<User> salesmen = api.findAllSalesmen();
                        req.setAttribute("salesmen", salesmen);
                    } catch (NoSuchUserExists noSuchUserExists) {
                        noSuchUserExists.printStackTrace();
                    }
                    req.setAttribute("orderList", orderList);
                    render("Fog Trælast", "/WEB-INF/pages/editOrder.jsp", req, resp);
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }

            } else if(cmd.equals("SortByNew")){
                    String status = "New";
                    List<Order> sortedList = api.displayOrderByStatus(status);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                }else if(cmd.equals("SortByProcessing")){
                    String status = "Processing";
                    List<Order>sortedList = api.displayOrderByStatus(status);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                }else if(cmd.equals("SortByDone")){
                    String status = "Done";
                    List<Order> sortedList = api.displayOrderByStatus(status);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                }
            else if(cmd.equals("SortBySalesman")){
                HttpSession session = req.getSession();
                int salesman = (Integer)session.getAttribute("userID");
                List<Order> sortedList = api.displayOrderBySalesman(salesman);
                req.setAttribute("list", sortedList);
                render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
            }
            else {
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
            String roofType = req.getParameter("roofType"); //TODO
            Integer shedOrNo = Integer.parseInt((req.getParameter("shedOrNo")));
            Integer cladding = Integer.parseInt(req.getParameter("cladding"));

            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");

            if (customerEmail == null || customerEmail.equals("")) {
                resp.sendError(400, "Mangler email");
            } else if(customerPhone == null || customerPhone.equals("")){
                resp.sendError(400, "Mangler tlf");
            } else{
                Order list = api.createOrder(length, width, customerPhone, customerEmail, roofType, shedOrNo, cladding);
                resp.sendRedirect(req.getContextPath());

                // Create new method
                Construction construction = new Construction(width, length, null);

                Roof roof;
                if (roofType.equals("pitched"))
                    roof = new PitchedRoof(0, length, width, null, 0); //TODO
                else if (roofType.equals("flat")){
                    roof = new FlatRoof(0,length,width, null); //TODO
                }else{
                    throw new IllegalArgumentException("Dette er ikke et type tag");
                }
                //int roofAngle = Integer.parseInt(req.getParameter("roofangle")); //TODO
                construction.setRoof(roof);
                final RoofCalculator roofCalculator = new RoofCalculator(construction);//TODO Fjern parameter
                int roofHeight = roofCalculator.roofHeight(construction.getRoof().isFlat(),length,width);
                construction.getRoof().setHeight(roofHeight);
                req.getSession().setAttribute("construction", construction);
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
            String rooftype = req.getParameter("roofType");
            try {
                //Edits salesman field
                api.editRoofType(rooftype, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }int shedOrNo = Integer.parseInt(req.getParameter("shedOrNo"));
            try {
                //Edits salesman field
                api.editShedOrNo(shedOrNo, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }int cladding = Integer.parseInt(req.getParameter("cladding"));
            try {
                //Edits salesman field
                api.editCladding(cladding, orderID);
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



