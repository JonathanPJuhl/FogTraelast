package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.bom.BOMService;
import domain.construction.Category;
import domain.construction.UsersChoice;
import domain.orders.Order;
import domain.users.Client;
import infrastructure.DBMaterialRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

@WebServlet({"/BOM", "/BOM/*"})
public class BOM_Kladde extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean param = Boolean.parseBoolean(req.getParameter("payCheck"));
        HttpSession session = req.getSession();
        if (client != null) {
            req.setAttribute("alreadyCustomer", true);
            render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
        } else if (param) {
            req.setAttribute("alreadyCustomer", false);
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
                Order order = api.createOrder(usersChoice.getLength(), usersChoice.getWidth(),
                        usersChoice.getCustomerPhone(), usersChoice.getCustomerEmail(), usersChoice.getRoofChoice(),
                        usersChoice.getShedOrNo(), usersChoice.getCladdingChoice());
                for (BOMItem bomItem : bomI) {
                    int materialCategoryID = api.findMaterialByCategoryID(bomItem.getMaterial(), bomItem.getCategory());
                    api.storeBOM(bomItem, order, materialCategoryID);
                }
                InetAddress inetAddress = InetAddress.getByName(req.getLocalAddr());
                final Client client = createClient(inetAddress, 8080);
                render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
            } else if(req.getPathInfo().substring(1).equals("/BOM/Customer")) {

            }else{
                resp.sendError(307, "You need to order a carport first!");
            }
        } else {
            resp.sendError(400, "unathorized access");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = null;
    }
}


