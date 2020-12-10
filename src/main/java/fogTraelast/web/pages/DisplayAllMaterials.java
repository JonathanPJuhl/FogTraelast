package fogTraelast.web.pages;

import domain.construction.Construction;
import domain.construction.Material;
import domain.construction.NoSuchMaterialExists;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/OtherOptions", "/OtherOptions/*"})
public class DisplayAllMaterials extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Construction construction = (Construction) req.getSession().getAttribute("construction");
        if (req.getPathInfo() == null && !(construction.equals(null))) {
            try {
                List<Material> BOMRoofOptions = api.findMaterialsForRoof(construction);
                render("Fog Trælast", "/WEB-INF/pages/roofOptions.jsp", req, resp);
                req.setAttribute("claddingOptions", BOMRoofOptions);
            } catch (NoSuchMaterialExists noSuchMaterialExists) {
                noSuchMaterialExists.printStackTrace();
            }

        } else if (!(req.getPathInfo() == null) && !(construction.equals(null))) {
            String cmd = req.getPathInfo().substring(1);

            if (cmd.equals("BOM")) {
                    render("Fog Trælast", "/WEB-INF/pages/displayAllMaterials.jsp", req, resp);//TODO
            } else {
                //TODO
            }
        } else {
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
