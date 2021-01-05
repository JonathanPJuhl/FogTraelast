package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMFromDB;
import domain.bom.BOMService;
import domain.construction.*;
import domain.construction.Roof.*;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.construction.shed.TooLargeException;
import domain.material.Material;
import domain.orders.Economy;
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


import static domain.construction.Roof.FlatRoof.TILTTODEGREE;

@WebServlet({"/Orders", "/Orders/*"})
public class Orders extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String cmd = req.getPathInfo().substring(1);


            if (cmd.equals("new")) {
                render("Fog Trælast", "/WEB-INF/pages/createOrder.jsp", req, resp);
            } else if (cmd.equals("constructionOverview")) {
                HttpSession session = req.getSession();
                UsersChoice usersChoice = (UsersChoice) session.getAttribute("tempConstruction");

                List<Material> claddingOpts = null;
                if (!(usersChoice == null)) {
                    if (usersChoice.getRoofChoice().equals("Flat")) {
                        claddingOpts = api.roofMaterials(Category.Flat);
                    } else {
                        claddingOpts = api.roofMaterials(Category.Pitched);
                    }
                    List<Material> claddingOptsShedCarport = api.findMaterialsByCategory(Category.Cladding);
                    req.setAttribute("claddingOptionsRoof", claddingOpts);
                    req.setAttribute("userChoice", usersChoice);
                    req.setAttribute("claddingOptionsShedCarport", claddingOptsShedCarport);
                    render("Fog Trælast", "/WEB-INF/pages/customizedOptionsPage.jsp", req, resp);
                } else {
                    resp.sendError(400, "Badly formated request");
                }
            } else if (cmd.equals("constructionOverview/error")) {
                HttpSession session = req.getSession();
                UsersChoice usersChoice = (UsersChoice) session.getAttribute("tempConstruction");
                ArrayList<Material> claddingOpts = null;
                if (usersChoice.getRoofChoice().equals("Flat")) {
                    claddingOpts = api.roofMaterials(Category.Flat);
                } else {
                    claddingOpts = api.roofMaterials(Category.Pitched);
                }
                List<Material> claddingOptsShedCarport = api.findMaterialsByCategory(Category.Cladding);
                req.setAttribute("claddingOptionsRoof", claddingOpts);
                req.setAttribute("userChoice", usersChoice);
                req.setAttribute("claddingOptionsShedCarport", claddingOptsShedCarport);
                req.setAttribute("tooBigShed", true);
                render("Fog Trælast", "/WEB-INF/pages/customizedOptionsPage.jsp", req, resp);
            } else if (cmd.equals("displayOrder")) {
                int orderID = Integer.parseInt(req.getParameter("orderNumber"));
                String phone = req.getParameter("tlf");
                try {
                    Order order = api.findOrder(orderID);
                    if(phone.equals(order.getCustomerPhone())){
                        double priceBOM = api.findBOMPriceByOrderID(orderID);
                        Economy economy = new Economy();
                        double priceWithCoverage = economy.withCoverage(25, priceBOM);
                        double roundedPrice = Math.round(priceWithCoverage * 100.0) / 100.0;
                        req.setAttribute("priceWithCoverage", roundedPrice);
                        List<Order> orderList = new ArrayList<>();
                        req.setAttribute("svgScaleHeight", order.getLength()+200);
                        req.setAttribute("svgScaleWidth", order.getWidth()+200);
                        orderList.add(order);
                        req.setAttribute("orderList", orderList);
                        List<BOMFromDB> bomList = api.findBom(orderID);
                        req.setAttribute("bomList", bomList);
                        render("Fog Trælast", "/WEB-INF/pages/displaySingleOrder.jsp", req, resp);
                    }
                    else if(!(phone.equals(order.getCustomerPhone()))){
                        req.setAttribute("OrderID", orderID);
                        render("Fog Trælast", "/WEB-INF/pages/findSingleOrder.jsp", req, resp);
                    }
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }


            }else if (cmd.equals("findOrder")) {
                render("Fog Trælast", "/WEB-INF/pages/findSingleOrder.jsp", req, resp);
            }

            if (!(req.getSession().getAttribute("userID") == null)) {
                if (cmd.equals("edit")) {
                    Order order;
                    try {
                        HttpSession session = req.getSession();

                        int orderID = (Integer) session.getAttribute("editID");
                        order = api.findOrder(orderID);

                        double priceBOM = api.findBOMPriceByOrderID(orderID);
                        req.setAttribute("priceBOM", priceBOM);
                        Economy economy = new Economy();
                        double priceWithCoverage = economy.withCoverage(25, priceBOM);
                        req.setAttribute("priceWithCoverage", priceWithCoverage);

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

                }  else if (cmd.equals("SortByNew")) {
                    String status = "New";
                    List<Order> sortedList = api.displayOrderByStatus(status);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                } else if (cmd.equals("SortByProcessing")) {
                    String status = "Processing";
                    List<Order> sortedList = api.displayOrderByStatus(status);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                } else if (cmd.equals("SortByDone")) {
                    String status = "Done";
                    List<Order> sortedList = api.displayOrderByStatus(status);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                } else if (cmd.equals("SortBySalesman")) {
                    HttpSession session = req.getSession();
                    int salesman = (Integer) session.getAttribute("userID");
                    List<Order> sortedList = api.displayOrderBySalesman(salesman);
                    req.setAttribute("list", sortedList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                }
            }

        }
        



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().substring(1).equals("create")) {
            int length = Integer.parseInt(req.getParameter("length")) * 10;
            int width = Integer.parseInt(req.getParameter("width")) * 10;
            String roofType = req.getParameter("roofType");
            int shedOrNo = Integer.parseInt((req.getParameter("shedOrNo")));
            int cladding = Integer.parseInt(req.getParameter("cladding"));

            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");

            if (customerEmail == null || customerEmail.equals("")) {
                resp.sendError(400, "Mangler email");
            } else if (customerPhone == null || customerPhone.equals("")) {
                resp.sendError(400, "Mangler tlf");
            } else {
                UsersChoice tempConstruction = new UsersChoice(width, length, roofType, shedOrNo, cladding, customerPhone, customerEmail);
                HttpSession session = req.getSession();
                session.setAttribute("tempConstruction", tempConstruction);
                resp.sendRedirect(req.getContextPath() + "/Orders/constructionOverview");

            }

        } else if (req.getPathInfo().substring(1).equals("constructionOverview") || req.getPathInfo().substring(1).equals("constructionOverview/error")) {
            HttpSession session = req.getSession();
            UsersChoice consFirst = (UsersChoice) session.getAttribute("tempConstruction");
            int roofMaterialID = Integer.parseInt(req.getParameter("roofMaterialOption"));

            double degreeOption;
            int shedlenght = 0;
            int shedwitdh = 0;
            int carportShedCladdingID = 0;
            Material claddingMaterial = null;

            if ((req.getParameter("degreeOption") == null)) {
                degreeOption = TILTTODEGREE;
            } else {
                degreeOption = Integer.parseInt(req.getParameter("degreeOption"));
            }
            if (consFirst.getShedOrNo() == 1) {
                shedlenght = Integer.parseInt(req.getParameter("shedLength")) * 10;
                shedwitdh = Integer.parseInt(req.getParameter("shedWidth")) * 10;
                carportShedCladdingID = Integer.parseInt(req.getParameter("carportCladding"));
                claddingMaterial = api.findMaterialByID(carportShedCladdingID);
            }
            if (consFirst.getCladdingChoice() == 1 || consFirst.getShedOrNo() == 1) {
                carportShedCladdingID = Integer.parseInt(req.getParameter("carportCladding"));
                claddingMaterial = api.findMaterialByID(carportShedCladdingID);
            }

            UsersChoice constructionSecondChoice = new UsersChoice(consFirst.getWidth(), consFirst.getLength(),
                    consFirst.getRoofChoice(), consFirst.getShedOrNo(), consFirst.getCladdingChoice(),
                    consFirst.getCustomerPhone(), consFirst.getCustomerEmail(), api.findMaterialByID(roofMaterialID),
                    degreeOption, shedlenght, shedwitdh, claddingMaterial);

            req.getSession().setAttribute("secondUserChoice", constructionSecondChoice);
            ConstructionFactory constructionFactory = new ConstructionFactory();
            Roof roof = constructionFactory.createRoof(constructionSecondChoice);
            Carport carport = constructionFactory.createCarport(constructionSecondChoice);
            Construction construction = constructionFactory.createConstruction(roof, carport);
            try {
                if (constructionSecondChoice.getShedOrNo() == 1) {

                    Shed shed = constructionFactory.createShed(constructionSecondChoice, construction, carport);
                    shed.addCladdingToShed(claddingMaterial, carport);
                    construction.addShed(shed);

                }
                session.setAttribute("construction", construction.getPartForConstruction());
                resp.sendRedirect(req.getContextPath() + "/SVG");
            } catch (TooLargeException e) {
                System.err.println(e);
                resp.sendRedirect(req.getContextPath() + "/Orders/constructionOverview/error");
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
            if(!(req.getParameter("price") == null)) {
                double price = Double.parseDouble(req.getParameter("price"));
                try {
                    //Edits price field
                    api.editPrice(price, orderID);
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }
            }
            String rooftype = req.getParameter("roofType");
            try {
                //Edits salesman field
                api.editRoofType(rooftype, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }
            int shedOrNo = Integer.parseInt(req.getParameter("shedOrNo"));
            try {
                //Edits salesman field
                api.editShedOrNo(shedOrNo, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();
            }
            int cladding = Integer.parseInt(req.getParameter("cladding"));
            try {
                //Edits salesman field
                api.editCladding(cladding, orderID);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                noSuchOrderExists.printStackTrace();

            }

            resp.sendRedirect(req.getContextPath() + "/Orders/SortByNew");
        }
    }
}
