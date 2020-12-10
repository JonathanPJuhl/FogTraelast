/*
package fogTraelast.web.pages;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DisplayAllOrders/*")
public class DisplayAllOrders extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        */
/*if (req.getPathInfo() == null) {

            render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
        } else {*//*

            */
/*int orderID = Integer.parseInt(req.getPathInfo().substring(1));
            log(req, "Accessing order: " + " : " + orderID);
            if(orderID<=0 || req.getPathInfo().substring(1)==null){*//*

                try {
                    List<Order> orderList = api.findAllOrders();
                    System.out.println("str: " + orderList);
                    req.setAttribute("list", orderList);
                    render("Fog Trælast", "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }
            */
/*} else{
                try {
                    Order orderList = api.findOrder(orderID);
                    req.setAttribute("list", orderList);
                    render("Fog Trælast" + orderList, "/WEB-INF/pages/displayAllOrders.jsp", req, resp);
                } catch (NoSuchOrderExists noSuchOrderExists) {
                    noSuchOrderExists.printStackTrace();
                }
            }*//*


           // }
        }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        */
/*int id = Integer.parseInt(req.getParameter("id"));
        if(id>0) {
            resp.sendRedirect(req.getContextPath() + "/DisplayAllOrders/" + id);
        } else {*//*

            resp.sendRedirect(req.getContextPath() + "/DisplayAllOrders/");
        //}
        }
    }

*/
