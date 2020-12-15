package fogTraelast.web.pages;

import domain.construction.Construction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/BOM","/BOM/*"})
public class BOM_Kladde extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Construction construction = (Construction) req.getSession().getAttribute("construction");
        if (req.getPathInfo() == null && !(construction.equals(null)) && !(construction.getRoof().getCladding().equals(null))) {

        } else if (!(req.getPathInfo() == null) && !(construction.equals(null))) {
            String cmd = req.getPathInfo().substring(1);

            if (cmd.equals("BOM")) {
                render("Fog Trælast", "/WEB-INF/pages/displayAllMaterials.jsp", req, resp);//TODO
            } else {
                //TODO
            }
        } else {
            //TODO redirect til createOrders.jsp ???
            resp.sendError(307, "Du skal først bestille en carport");//TODO
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String claddingColor = req.getParameter("cladding");
        Construction construction = (Construction) req.getSession().getAttribute("contruction");


        //TODO
    }
}

