package fogTraelast.web.pages;

import domain.construction.Construction;
import domain.construction.ConstructionFactory;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.SVG.SvgCarport;
import domain.construction.SVG.SvgCarportFront;
import domain.construction.SVG.SvgCarportSide;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.construction.shed.TooLargeException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/SVG"})
public class SVG extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersChoice usersChoice = (UsersChoice) req.getSession().getAttribute("secondUserChoice");
        ConstructionFactory cf = new ConstructionFactory();
        Roof roof = cf.createRoof(usersChoice);
        Carport carport = cf.createCarport(usersChoice);
        Construction cons = cf.createConstruction(roof, carport);
        Shed shed = null;
        try {
            shed = cf.createShed(usersChoice, cons, carport);
        } catch (TooLargeException e) {
            e.printStackTrace();
        }
        SvgCarport svgCarport = new SvgCarport();
        RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();
        SvgCarportFront svgCarportFront = new SvgCarportFront();
        SvgCarportSide svgCarportSide = new SvgCarportSide();
        HttpSession session = req.getSession();
        req.setAttribute("svgScaleHeight", roof.getLength()+200);
        req.setAttribute("svgScaleWidth", roof.getWidth()+200);
        req.setAttribute("construction1", cons);
        session.setAttribute("svgCarport", svgCarport.Build(roof.getLength(), roof.getWidth(), shed.getWidth(), shed.getLength()));
        //session.setAttribute("svgCarportFront", svgCarportFront.Build(roof.getLength(), roof.getWidth(), shed.getWidth(), shed.getLength(), (carport.getHeight())+roofSizeCalculator.getRoofHeight(roof)));
        //session.setAttribute("svgCarportSide", svgCarportSide.Build(roof.getLength(), roof.getWidth(), shed.getWidth(), shed.getLength(), (carport.getHeight())+roofSizeCalculator.getRoofHeight(roof)));
        render("Fog Trælast", "/WEB-INF/pages/svg.jsp", req, resp);

    }

}