package api;

import domain.construction.ConstructionRepository;
import domain.construction.Material;
import domain.construction.NoSuchMaterialExists;
import domain.construction.Roof.Roof;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;
import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;

import java.util.List;

public class FogTraelast {
    private String VERSION = "0.1";
    private final UserRepository userLists;
    private final OrderRepository orderLists;
    private final ConstructionRepository constructionLists;

    public FogTraelast(UserRepository userLists, OrderRepository orderLists, ConstructionRepository constructionLists) {
        this.userLists = userLists;
        this.orderLists = orderLists;
        this.constructionLists = constructionLists;
    }

    public Object getVERSION() {
        return VERSION;
    }

    public User createUser(String name, String password) {

        return userLists.createUsr(name, password);
    }

    public Order findOrder(int id) throws NoSuchOrderExists {
        return orderLists.findSpecificOrder(id);
    }

    public Order createOrder(double length, double width, String customerPhone, String customerEmail) {
        return orderLists.insertOrderIntoDB(length, width, customerPhone, customerEmail);
    }

    public User loginSalesman(String salesmanEmail, String password) throws NoSuchUserExists {
        return userLists.loginSalesman(salesmanEmail, password);
    }

    public User findSalesman(int id) throws NoSuchUserExists{
        return userLists.findUser(id);
    }

    public List<Order> findAllOrders() throws NoSuchOrderExists {
        return orderLists.findAllOrders();
    }

    public List<Material> findMaterialsForRoof (Roof roof) throws NoSuchMaterialExists{
        return constructionLists.findMaterialsForRoof(roof);
    }

    public List<Material> setRoofBOM (Material material, int quantity){
        return constructionLists.setRoofBOM(material, quantity);
    }

    public void insertRoofBOM (List<Material> roofBOM){
        constructionLists.insertRoofBOM(roofBOM);
    }
}
