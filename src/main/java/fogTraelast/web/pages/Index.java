package fogTraelast.web.pages;
import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.users.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;

@WebServlet("")
public class Index extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (client!=null){
            req.setAttribute("alreadyCustomer", true);
        }else {
            req.setAttribute("alreadyCustomer", false);
        }
        render("Fog Trælast", "/WEB-INF/pages/index.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        client = null;
        int orderID = Integer.parseInt(req.getParameter("orderNumber"));
        try {
            Order order = api.findOrder(orderID);
            BOM bom = new BOM();
            ArrayList<BOMItem> Ibom = bom.getItems();
            req.getSession().setAttribute("bom", Ibom);
            resp.sendRedirect(req.getContextPath() + "/BOM");
        } catch (NoSuchOrderExists noSuchOrderExists) {
            noSuchOrderExists.printStackTrace();
        }
    }
}