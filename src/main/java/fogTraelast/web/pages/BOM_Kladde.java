package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.bom.BOMService;
import domain.construction.UsersChoice;
import infrastructure.DBMaterialRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
                   BOM bom = bs.calculateBom(construction, usersChoice, constructionFactory);
                   ArrayList<BOMItem> bomI = (bom.getItems());
                   req.getSession().setAttribute("bom", bomI);
                   render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
               } else {
                   //TODO redirect til createOrders.jsp ???
                   resp.sendError(307, "Du skal først bestille en carport");//TODO
               }
           }else{
               resp.sendError(400, "unathorized access");
           }
    }

    }


