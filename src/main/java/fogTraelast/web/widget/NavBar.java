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

    private static final Map<String, String> SITES = Map.of(
            "Index", "/",
            "DisplayAllOrders", "/DisplayAllOrders",
            "Orders/new", "/Orders/new",
            "DisplayOrderPage", "/DisplayOrderPage",
            "Orderhandling", "/Orderhandling",
            "SalesmanLogin", "/SalesmanLogin"
    );

    public String findUrl(String name) {
        return req.getContextPath() + SITES.get(name);
    }

    public static Map<String, String> getSITES() {
        return SITES;
    }
}
