package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.bom.BOMService;
import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.ConstructionFactory;
import domain.construction.Roof.Roof;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.construction.shed.TooLargeException;
import domain.orders.Economy;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import infrastructure.DBMaterialRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

@WebServlet({"/BOM", "/BOM/*"})
public class BOM_Kladde extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean param = Boolean.parseBoolean(req.getParameter("payCheck"));
        HttpSession session = req.getSession();
       /* if (client != null) {
            req.setAttribute("alreadyCustomer", true);
            render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
        } else*/ if (param) {
            req.setAttribute("alreadyCustomer", false);
            req.setAttribute("svg", false);
            HashMap construction = (HashMap) req.getSession().getAttribute("construction");
            UsersChoice usersChoice = (UsersChoice) req.getSession().getAttribute("secondUserChoice");
            if (!(usersChoice.getRoofCladding() == null)) {
                BOMService bs = new BOMService(new DBMaterialRepository(db));
                TreeSet lengths = api.allLenghtsForMaterials();
                TreeSet widths = api.allWidthsForMaterials();
                BOM bom = bs.calculateBom(construction, widths, lengths);
                ArrayList<BOMItem> bomI = (bom.getItems());
                req.setAttribute("usersChoices", usersChoice);
                req.getSession().setAttribute("bom", bomI);
                Category category = Category.Shed;

                Order order = api.createOrder(usersChoice);

                for (BOMItem bomItem : bomI) {
                    int materialCategoryID = api.findMaterialByCategoryID(bomItem.getMaterial(), bomItem.getCategory());
                    api.storeBOM(bomItem, order, materialCategoryID);
                }
                double priceBOM = api.findBOMPriceByOrderID(order.getOrderID());
                Economy economy = new Economy();
                double priceWithCoverage = economy.withCoverage(25, priceBOM);
                try {
                    api.editPrice(priceWithCoverage, order.getOrderID());
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }
//                InetAddress inetAddress = InetAddress.getByName(req.getLocalAddr());
//                final Client client = createClient(inetAddress, 8080);
                render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
            } else if(req.getPathInfo().substring(1).equals("/BOM/Customer")) {
                req.setAttribute("svg", true);
                Order order = (Order)req.getSession().getAttribute("order");
                ConstructionFactory cf = new ConstructionFactory();
                UsersChoice usersChoice1 = new UsersChoice((int)order.getWidth(),(int)order.getLength(),order.getRoofType(),order.getShedOrNo(),
                        order.getCladding(),order.getCustomerPhone(), order.getCustomerPhone()); // TODO Ordre skal have flere værdier og sætte ind her f.eks. før det vil virke
                Roof roof = cf.createRoof(usersChoice1);
                Carport carport = cf.createCarport(usersChoice1);
                Construction cons = cf.createConstruction(roof, carport);
                Shed shed = null;
                try {
                    shed = cf.createShed(usersChoice1, cons, carport);
                } catch (TooLargeException e) {
                    e.printStackTrace();
                }
                render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
            }else{
                resp.sendError(307, "You need to order a carport first!");
            }
        } else {
            resp.sendError(400, "unathorized access");
        }
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = null;
    }*/
}


