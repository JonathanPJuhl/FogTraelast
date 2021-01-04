package api;

import domain.bom.BOMFromDB;
import domain.bom.BOMItem;
import domain.construction.Category;
import domain.construction.ConstructionFactory;
import domain.construction.UsersChoice;
import domain.material.Material;
import domain.material.MaterialService;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;
import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class FogTraelast {
    private String VERSION = "0.1"; //TODO Rediger db version
    private final UserRepository userLists;
    private final OrderRepository orderLists;
    private final MaterialService materialService;
    private final ConstructionFactory constructionFactory;


    public FogTraelast(UserRepository userLists, OrderRepository orderLists, MaterialService materialService, ConstructionFactory constructionFactory) {
        this.userLists = userLists;
        this.orderLists = orderLists;
        this.materialService = materialService;
        this.constructionFactory = constructionFactory;

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

    public Order createOrder(UsersChoice usersChoice) {
        return orderLists.insertOrderIntoDB(usersChoice);
    }

    public User loginSalesman(String salesmanEmail, String password) throws NoSuchUserExists {
        return userLists.loginSalesman(salesmanEmail, password);
    }

    public User findSalesman(int id) throws NoSuchUserExists {
        return userLists.findUser(id);
    }

    public List<Order> findAllOrders() throws NoSuchOrderExists {
        return orderLists.findAllOrders();
    }



    public ArrayList<Material> roofMaterials(Category category) {
        return materialService.roofMaterials(category);
    }



    public List<Material> findMaterialsByCategory(Category category) {
        return materialService.findMaterialsByCategory(category);
    }

    public void insertMaterialIntoDB(Material material) {
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

    public List<Order> displayOrderByStatus(String wantedStatus) {
        return orderLists.displayOrdersByStatus(wantedStatus);
    }

    public List<Order> displayOrderBySalesman(int wantedSalesman) {
        return orderLists.displayOrdersBySalesman(wantedSalesman);
    }

    public Material findMaterial(String name) {
        return materialService.findMaterial(name);
    }

    public Material findMaterialByID(int id) {
        return materialService.findMaterialByID(id);
    }

    public TreeSet<Integer>allWidthsForMaterials(){
        return materialService.allWidthsForMaterials();
    }

    public TreeSet<Integer>allLenghtsForMaterials(){
        return materialService.allLenghtsForMaterials();
    }

    public void storeBOM(BOMItem bomItem, Order order, int materialByCategoryID){
        materialService.storeBOM(bomItem,order,materialByCategoryID);
    }

    public int findMaterialByCategoryID(Material material, Category category){
        return materialService.findMaterialByCategoryID(material,category);
    }

    public double findBOMPriceByOrderID(int orderID){
        return materialService.findBOMPriceByOrderID(orderID);
    }

    public ArrayList<BOMFromDB> findBom(int orderID){
        return materialService.findBOMByOrderID(orderID);
    }

    public void insertSVGintoOrder(String SVG, int orderID){
        orderLists.insertSVGIntoOrder(SVG, orderID);
    }
}
