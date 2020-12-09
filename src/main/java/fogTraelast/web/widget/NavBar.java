package fogTraelast.web.widget;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//TODO Note til Cath - få 100% styr på hvordan dette virker
public class NavBar {
    private final HttpServletRequest req;

    public NavBar(HttpServletRequest req) {
        this.req = req;
    }

    //Changes this to Map.ofEntries instead of Map.of, since that could only contain up to 10 k-p values, this is "limitless"
    private static final Map<String, String> SITES = Map.ofEntries(
            Map.entry("Index", "/"),
            Map.entry("DisplayAllOrders", "/DisplayAllOrders"),
            Map.entry("Orders/new", "/Orders/new"),
            Map.entry("DisplayOrderPage", "/DisplayOrderPage"),
            Map.entry("Orderhandling", "/Orderhandling"),
            Map.entry("SalesmanLogin", "/SalesmanLogin"),
            Map.entry("SalesmanLogin/Logout", "/SalesmanLogin/Logout"),
            Map.entry("Orders/SortByNew", "/Orders/SortByNew"),
            Map.entry( "Orders/SortByProcessing", "/Orders/SortByProcessing"),
            Map.entry("Orders/SortByDone", "/Orders/SortByDone"),
            Map.entry("Orders/SortBySalesman", "/Orders/SortBySalesman")
    );

    public String findUrl(String name) {
        return req.getContextPath() + SITES.get(name);
    }

    public static Map<String, String> getSITES() {
        return SITES;
    }
}
