package fogTraelast.web.pages;

import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.PitchedRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofCalculator;
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
            render("Fog Trælast", "/WEB-INF/pages/createOrder.jsp", req, resp);
        } else {
            int userID = Integer.parseInt(req.getPathInfo().substring(1));
            log(req, "Accessing order: " + " : " + userID);
            try {
                Order orderList = api.findOrder(userID);
                req.setAttribute("list", orderList);

                render("Fog Trælast" + orderList.toString(), "/WEB-INF/pages/displayOrderPage.jsp", req, resp);
            } catch (NoSuchOrderExists noSuchOrderExists) {
                resp.sendError(404, "User does not exist");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int length = Integer.parseInt(req.getParameter("length"));
        int width = Integer.parseInt(req.getParameter("width"));
        String roofType = req.getParameter("roofType"); //TODO
        //int roofAngle = Integer.parseInt(req.getParameter("roofangle")); //TODO
        String customerEmail = req.getParameter("email");
        String customerPhone = req.getParameter("phone");

        if (customerEmail == null || customerEmail.equals("")) {
            resp.sendError(400, "Mangler email");
        } else if(customerPhone == null || customerPhone.equals("")){
            resp.sendError(400, "Mangler tlf");
        } else{
            Order list = api.createOrder(length, width, customerPhone, customerEmail);
            resp.sendRedirect(req.getContextPath() + "/CreateOrder/" + list.getOrderID());

        Construction construction = new Construction(width, length, null);

        Roof roof;
        if (roofType.equals("pitched"))
            roof = new PitchedRoof(0, length, width, null, 0); //TODO
        else if (roofType.equals("flat")){
            roof = new FlatRoof(0,length,width, null); //TODO
        }else{
            throw new IllegalArgumentException("Dette er ikke et type tag");
        }
        construction.setRoof(roof);
        final RoofCalculator roofCalculator = new RoofCalculator(construction);//TODO Fjern parameter
        int roofHeight = roofCalculator.roofHeight(construction.getRoof().isFlat(),length,width);
        construction.getRoof().setHeight(roofHeight);
        req.getSession().setAttribute("construction", construction);
        }
    }
}
