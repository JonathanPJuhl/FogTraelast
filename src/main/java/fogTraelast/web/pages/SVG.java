package fogTraelast.web.pages;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.bom.BOMService;
import domain.construction.Construction;
import domain.construction.Roof.ConstructionFactory;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.SVG.SvgCarport;
import domain.construction.SVG.SvgCarportFront;
import domain.construction.SVG.SvgCarportSide;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
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

@WebServlet({"/SVG"})
public class SVG extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersChoice usersChoice = (UsersChoice) req.getSession().getAttribute("secondUserChoice");
        ConstructionFactory cf = new ConstructionFactory();
        Roof roof = cf.createRoof(usersChoice);
        Carport carport = cf.createCarport(usersChoice);
        Construction cons = cf.createConstruction(roof, carport);
        Shed shed = cf.createShed(usersChoice, cons, carport);
        SvgCarport svgCarport = new SvgCarport();
        RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();
        SvgCarportFront svgCarportFront = new SvgCarportFront();
        SvgCarportSide svgCarportSide = new SvgCarportSide();
        HttpSession session = req.getSession();

        session.setAttribute("construction1", cons);
        session.setAttribute("svgCarport", svgCarport.Build(roof.getLength(), roof.getWidth(), shed.getWidth(), shed.getLength()));
        session.setAttribute("svgCarportFront", svgCarportFront.Build(roof.getLength(), roof.getWidth(), shed.getWidth(), shed.getLength(), (carport.getHeight())+roofSizeCalculator.getRoofHeight(roof)));
        session.setAttribute("svgCarportSide", svgCarportSide.Build(roof.getLength(), roof.getWidth(), shed.getWidth(), shed.getLength(), (carport.getHeight())+roofSizeCalculator.getRoofHeight(roof)));
        render("Fog Trælast", "/WEB-INF/pages/svg.jsp", req, resp);

        /*if (!(usersChoice.getRoofCladding()==null)) {
            BOMService bs = new BOMService(new DBMaterialRepository(db));
            TreeSet lengths = api.allLenghtsForMaterials();
            TreeSet widths = api.allWidthsForMaterials();
            BOM bom = bs.calculateBom(construction, widths, lengths);
            ArrayList<BOMItem> bomI= (bom.getItems());
            req.getSession().setAttribute("bom", bomI);
            render("Fog Trælast", "/WEB-INF/pages/BOM.jsp", req, resp);
        } else {
            //TODO redirect til createOrders.jsp ???
            resp.sendError(307, "Du skal først bestille en carport");//TODO
        }*/
    }

}