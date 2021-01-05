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

        HttpSession session = req.getSession();
        String cmd = req.getPathInfo().substring(1);


        if (cmd.equals("show")) {
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
                    String svg = (String) session.getAttribute("svgCarport");
                    Order order = api.createOrder(usersChoice);
                    api.insertSVGintoOrder(svg, order.getOrderID());
                    req.setAttribute("orderID", order.getOrderID());
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

                    render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
                } else {
                    resp.sendError(307, "You need to order a carport first!");
                }
            }
        }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().substring(1).equals("create")) {
            boolean param = Boolean.parseBoolean(req.getParameter("payCheck"));
            if (param) {
                resp.sendRedirect(req.getContextPath() + "/BOM/show");
            }
            else {
                resp.sendError(400, "unathorized access");
            }
        }
    }
}


