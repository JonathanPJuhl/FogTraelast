package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.bom.BOMService;
import domain.construction.Category;
import domain.construction.UsersChoice;
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
        boolean param = Boolean.parseBoolean(req.getParameter("payCheck"));
        if (param) {
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
                Order order = api.createOrder(usersChoice.getLength(),usersChoice.getWidth(),
                        usersChoice.getCustomerPhone(),usersChoice.getCustomerEmail(), usersChoice.getRoofChoice(),
                        usersChoice.getShedOrNo(), usersChoice.getCladdingChoice());
                for (BOMItem bomItem: bomI) {
                    int materialCategoryID = api.findMaterialByCategoryID(bomItem.getMaterial(),bomItem.getCategory());
                    api.storeBOM(bomItem,order,materialCategoryID);
                }
                render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
            } else {
                resp.sendError(307, "You need to order a carport first!");
            }
        } else {
            resp.sendError(400, "unathorized access");
        }
    }

}


