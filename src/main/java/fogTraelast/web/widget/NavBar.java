package fogTraelast.web.widget;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//TODO HVORDAN VIRKER DENNE ???!!!!!! HILSEN CATH
public class NavBar {

    private final HttpServletRequest req;

    public NavBar(HttpServletRequest req) {
        this.req = req;
    }

    private static final Map<String, String> SITES = Map.of(
            "Index", "/",
            "DisplayAllOrders", "/DisplayAllOrders",
            "CreateOrder", "/CreateOrder",
            "DisplayOrderPage", "/DisplayOrderPage",
            "Orderhandling", "/Orderhandling",
            "SalesmanLogin", "/SalsmanLogin"
    );

    public String findUrl(String name) {
        return req.getContextPath() + SITES.get(name);
    }

    public static Map<String, String> getSITES() {
        return SITES;
    }
}
