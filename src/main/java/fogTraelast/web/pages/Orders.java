package fogTraelast.web.pages;

import domain.construction.*;
import domain.construction.Roof.*;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;
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
            } else if (cmd.equals("constructionOverview")) {
                HttpSession session = req.getSession();
                UsersChoice usersChoice = (UsersChoice) session.getAttribute("tempConstruction");
                System.out.println(usersChoice.toString());

                if (!(usersChoice == null)) {//TODO Fejl håndtering (Denne vil altid være true)
                    List<Material> claddingOpts = api.roofMaterials(usersChoice.getRoofChoice()); //TODO burde lave noget smartere
                    List<Material> claddingOptsShedCarport = api.findMaterialsByCategory(Category.Cladding);
                    /*ArrayList<Integer> degreeOpts = new ArrayList<>();
                    for (int i=5; i<50; i+=5){
                        degreeOpts.add(i);
                    }*/
                    System.out.println("Material: " + claddingOptsShedCarport.size());
                    req.setAttribute("claddingOptionsRoof", claddingOpts);
                    req.setAttribute("userChoice", usersChoice);
                    req.setAttribute("claddingOptionsShedCarport", claddingOptsShedCarport);
                    render("Fog Trælast", "/WEB-INF/pages/customizedOptionsPage.jsp", req, resp);
                } else {
                    resp.sendError(400, "Badly formated request");
                }
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

            } else if (cmd.equals("SortByNew")) {
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
            int length = Integer.parseInt(req.getParameter("length"))*10;
            int width = Integer.parseInt(req.getParameter("width"))*10;
            String roofType = req.getParameter("roofType"); //TODO
            int shedOrNo = Integer.parseInt((req.getParameter("shedOrNo")));
            int cladding = Integer.parseInt(req.getParameter("cladding"));

            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");

            if (customerEmail == null || customerEmail.equals("")) {
                resp.sendError(400, "Mangler email");
            } else if (customerPhone == null || customerPhone.equals("")) {
                resp.sendError(400, "Mangler tlf");
            } else {
                //CREATE LATER ON
                //Order list = api.createOrder(length, width, customerPhone, customerEmail, roofType, shedOrNo, cladding);
                // Create new method
                UsersChoice tempConstruction = new UsersChoice(width, length, roofType, shedOrNo, cladding);
                //System.out.println("Shed: " + shedOrNo.toString());
                HttpSession session = req.getSession();
                session.setAttribute("tempConstruction", tempConstruction);
                resp.sendRedirect(req.getContextPath() + "/Orders/constructionOverview");

            }

        }
        else if (req.getPathInfo().substring(1).equals("constructionOverview")) {
            HttpSession session = req.getSession();
            UsersChoice consFirst = (UsersChoice) session.getAttribute("tempConstruction");
            int roofMaterialID = Integer.parseInt(req.getParameter("roofMaterialOption")); //TODO virker det når det ikke er parameter?


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
            if (consFirst.getShedOrNo()==1) {
                shedlenght = Integer.parseInt(req.getParameter("shedLength"));
                System.out.println("shedLength" + shedlenght);
                shedwitdh = Integer.parseInt(req.getParameter("shedWidth"));
            }
            if (consFirst.getCladdingChoice() == 1 || consFirst.getShedOrNo() == 1) {
                carportShedCladdingID = Integer.parseInt(req.getParameter("carportCladding"));
                claddingMaterial = api.findMaterialByID(carportShedCladdingID);
            }

            UsersChoice constructionSecondChoice = new UsersChoice(consFirst.getWidth(), consFirst.getLength(),
                    consFirst.getRoofChoice(), consFirst.getShedOrNo(), consFirst.getCladdingChoice(), api.findMaterialByID(roofMaterialID),
                    degreeOption, shedlenght, shedwitdh, claddingMaterial);

            req.getSession().setAttribute("secondUserChoice", constructionSecondChoice);
            ConstructionFactory constructionFactory = new ConstructionFactory();
            Roof roof = constructionFactory.createRoof(constructionSecondChoice);
            Carport carport = constructionFactory.createCarport(constructionSecondChoice);
            Construction construction = constructionFactory.createConstruction(roof, carport);

            if (consFirst.getShedOrNo() == 1) {
                Shed shed = constructionFactory.createShed(constructionSecondChoice, construction);
                 shed.addCladdingToShed(claddingMaterial, carport);
                construction.addShed(shed);
            }
            /*if (constructionSecondChoice.getCladdingChoice() == 1 && constructionSecondChoice.getShedOrNo() == 1) {
                construction.addCladding(carport.threeWallswithCladding(claddingMaterial));

            }*/ else if (constructionSecondChoice.getCladdingChoice() == 1) {
                Carport carportTmp = (Carport) construction.getPartForConstruction().get("carport");
                carportTmp.addCladding(carportTmp.threeWallswithCladding(claddingMaterial)); // TODO SKal man indsætte igen i Map? TEST DET
            }

            session.setAttribute("construction", construction.getPartForConstruction());
            resp.sendRedirect(req.getContextPath()+"/SVG");
           // resp.sendRedirect(req.getContextPath() + "/BOM"); // TODO skal vise SVG Senere

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

            resp.sendRedirect(req.getContextPath() + "/Orders");
        }/* else if (req.getParameter("newButton").equals(null) || req.getParameter("newButton").equals("")
                || req.getParameter("orderID").equals(null) || req.getParameter("orderID").equals("")) {
            resp.sendRedirect(req.getContextPath() + "/Orders/");
        } *//* else if (req.getParameter("newButton").equals("new")) {

            resp.sendRedirect(req.getContextPath() + "/Orders/new");*/

        //}
    /*else if (!(req.getParameter("orderID") == null) && !(req.getParameter("orderID").equals(""))) {
            int orderID = Integer.parseInt(req.getParameter("orderID"));
            HttpSession session = req.getSession();
            session.setAttribute("editID", orderID);
            resp.sendRedirect(req.getContextPath() + "/Orders/edit");
        }*/

    }
}


/*
package fogTraelast.web.pages;

import domain.construction.Category;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.PitchedRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.material.Material;
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
import java.util.InvalidPropertiesFormatException;
import java.util.List;

@WebServlet({"/Orders", "/Orders/*"})
public class Orders extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Construction construction = (Construction) req.getSession().getAttribute("construction");
        Material materialRoof = (Material) req.getSession().getAttribute("claddingRoof");
//TODO Lav Swtcih statement for overskuelighedens skyld?
        if (req.getPathInfo() == null && user.getRoleID() == 2) { //TODO ændre rolleID
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
            } else if (cmd.equals("viewSVG")) {
                if (!(construction.equals(null)) && !(materialRoof.equals(null)))
                    render("Fog Trælast", "/WEB-INF/pages/svg.jsp", req, resp);
                else {
                    resp.sendError(403, "Du skal ydfylde alle informationer til en carport før tegning kan vises");
                }
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
            } else if (cmd.equals("otherOptions"))
                if (!(construction.equals(null))) {
                    List<Material> roofOptionsForCladding = api.roofMaterials(construction);
                    req.setAttribute("claddingOptionsRoof", roofOptionsForCladding);
                    render("Fog Trælast", "/WEB-INF/pages/roofOptions.jsp", req, resp);
                } else {
                    resp.sendError(403, "Du skal forholde dig til mål, tagtype, skur og beklædning");
                }
            else if (cmd.equals("SortByNew")) {
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
        Construction construction = (Construction) req.getSession().getAttribute("construction");
        if (req.getPathInfo().substring(1).equals("new")) {
            int length = Integer.parseInt(req.getParameter("length"));
            int width = Integer.parseInt(req.getParameter("width"));
            String roofType = req.getParameter("roofType");
            Integer shedOrNo = Integer.parseInt((req.getParameter("shedOrNo")));
            Integer cladding = Integer.parseInt(req.getParameter("cladding"));

            String customerEmail = req.getParameter("email");
            String customerPhone = req.getParameter("phone");

            if (customerEmail == null || customerEmail.equals("")) {
                resp.sendError(400, "Mangler email");
            } else if (customerPhone == null || customerPhone.equals("")) {
                resp.sendError(400, "Mangler tlf");
            } else {
                //Order list = api.createOrder(length, width, customerPhone, customerEmail, roofType, shedOrNo, cladding);
                //resp.sendRedirect(req.getContextPath());
                // Create new method
                construction = new Construction(width, length, roofType, shedOrNo, cladding);
                construction.setRoof(api.createRoof(roofType, 0*/
/*TODO*//*
, construction, null, 0));
                api.roofMaterials(construction);
                req.getSession().setAttribute("construction", construction);
                */
/*req.getSession().setAttribute("length", length);
                req.getSession().setAttribute("width", width);
                req.getSession().setAttribute("roofType", roofType);
                req.getSession().setAttribute("shedOrNo", shedOrNo);
                req.getSession().setAttribute("cladding", cladding);*//*

                //TODO skal de hellere være session objekter til en start ?
                resp.sendRedirect(req.getContextPath() + "/Orders/otherOptions");
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


        */
/*} else if (req.getParameter("newButton").equals(null) || req.getParameter("newButton").equals("")
                || req.getParameter("orderID").equals(null) || req.getParameter("orderID").equals("")) {
            resp.sendRedirect(req.getContextPath() + "/Orders/");
        } else if (req.getParameter("newButton").equals("new")) {

            resp.sendRedirect(req.getContextPath() + "/Orders/new");
        *//*
 */
/*}else if (!(req.getParameter("orderID") == null) && !(req.getParameter("orderID").equals(""))) {
            int orderID = Integer.parseInt(req.getParameter("orderID"));
            HttpSession session = req.getSession();
            session.setAttribute("editID", orderID);
            resp.sendRedirect(req.getContextPath() + "/Orders/edit");
        }*//*

        } else if (req.getPathInfo().substring(1).equals("viewSVG") && !(construction.equals(null))) {
            //Material roofCladingChoice = (Material) req.getParameter("claddingRoof");
            //Roof roof = api.createRoof(roofSizeCalculator, );
        }

    }
}



*/
