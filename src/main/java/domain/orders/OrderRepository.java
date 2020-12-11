package domain.orders;

import domain.users.NoSuchUserExists;

import java.util.List;

public interface OrderRepository {

    Order insertOrderIntoDB(double length, double width, String customerPhone, String customerEmail, String roofType, int shedOrNo, int cladding);
    Order findSpecificOrder(int id) throws NoSuchOrderExists;

    List<Order>  findAllOrders() throws NoSuchOrderExists;



    void editSalesman(int columnValue, int orderID) throws NoSuchOrderExists;

    void editStatus(String columnValue, int orderID) throws NoSuchOrderExists;

    void editPrice(double columnValue, int orderID) throws NoSuchOrderExists;

    void editRoofType(String columnValue, int orderID) throws NoSuchOrderExists;

    void editShedOrNo(int columnValue, int orderID) throws NoSuchOrderExists;

    void editCladding(int columnValue, int orderID) throws NoSuchOrderExists;

    List<Order> displayOrdersByStatus(String wantedStatus);

    List<Order> displayOrdersBySalesman(int wantedSalesman);
}
