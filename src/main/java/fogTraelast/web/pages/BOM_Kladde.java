package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.bom.BOMService;
import domain.construction.Construction;
import domain.construction.ConstructionPart;
import domain.construction.Roof.Roof;
import domain.construction.UsersChoice;
import domain.material.MaterialService;
import infrastructure.DBMaterialRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({"/BOM", "/BOM/*"})
public class BOM_Kladde extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap construction = (HashMap) req.getSession().getAttribute("construction");
        UsersChoice usersChoice = (UsersChoice) req.getSession().getAttribute("secondUserChoice");
        Roof roof = (Roof) construction.get("roof");

        if (!(usersChoice.equals(null)) && !(roof.getCladding().equals(null))) {
            BOMService bs = new BOMService(new DBMaterialRepository(db));
            BOM bom = bs.calculateBom(construction, usersChoice);
            ArrayList<BOMItem> bomI= (bom.getItems());
            req.getSession().setAttribute("bom", bomI);
            render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
        //} else if (!(req.getPathInfo() == null) && !(construction.equals(null))) {
        } else {
            //TODO redirect til createOrders.jsp ???
            resp.sendError(307, "Du skal først bestille en carport");//TODO
        }
    }

}

