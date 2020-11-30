package domain.orders;

import java.util.List;

public interface OrderRepository {

    Order insertOrderIntoDB(double length, double width, String customerPhone, String customerEmail);
    Order findSpecificOrder(int id) throws NoSuchOrderExists;

    List<Order>  findAllOrders() throws NoSuchOrderExists;
}
