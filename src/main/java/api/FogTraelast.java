package api;

import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.ConstructionRepository;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.material.Material;
import domain.material.NoSuchMaterialExists;
import domain.material.MaterialService;
import domain.material.MaterialType;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;
import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;

import java.util.List;

public class FogTraelast {
    private String VERSION = "0.1"; //TODO Rediger db version
    private final UserRepository userLists;
    private final OrderRepository orderLists;
    private final MaterialService materialService;

    public FogTraelast(UserRepository userLists, OrderRepository orderLists, MaterialService materialService) {
        this.userLists = userLists;
        this.orderLists = orderLists;
        this.materialService = materialService;
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

    public Order createOrder(double length, double width, String customerPhone, String customerEmail, String  roofType, int shedOrNo, int cladding) {
        return orderLists.insertOrderIntoDB(length, width, customerPhone, customerEmail, roofType, shedOrNo, cladding);
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

    public Material findMaterial (String typeName, String type, int width, String color, String category, int height) throws NoSuchMaterialExists{
        return materialService.findMaterial(typeName, color, type, category, height);
    }

    public List<Material> roofMaterials(String roofType, int height){
        return materialService.roofMaterials(roofType, height);
    }

    /*public Roof createRoof(UsersChoice usersChoice){
        return constructionRepository.createRoof(usersChoice);
    }*/

    public List<Material> findMaterialsByCategory(Category category){
        return materialService.findMaterialsByCategory(category);
    }
    public void insertMaterialIntoDB(Material material){
        materialService.insertMaterialIntoDB(material);
    }

    public void editPrice(double columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editPrice(columnValue, orderID);
    }
    public void editSalesman(int columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editSalesman(columnValue, orderID);
    }
    public void editStatus(String columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editStatus(columnValue, orderID);
    }
    public void editRoofType(String columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editRoofType(columnValue, orderID);
    }
    public void editShedOrNo(int columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editShedOrNo(columnValue, orderID);
    }
    public void editCladding(int columnValue, int orderID) throws NoSuchOrderExists {
        orderLists.editCladding(columnValue, orderID);
    }

    public List<User> findAllSalesmen() throws NoSuchUserExists {
        return userLists.findAllSalesmen();
    }
    public List<Order> displayOrderByStatus(String wantedStatus){
        return orderLists.displayOrdersByStatus(wantedStatus);
    }
    public List<Order> displayOrderBySalesman(int wantedSalesman){
        return orderLists.displayOrdersBySalesman(wantedSalesman);
    }

}
