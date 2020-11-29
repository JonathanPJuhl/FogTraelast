package domain.orders;

public interface OrderRepository {

    Order insertOrderIntoDB(double length, double width, String customerPhone, String customerEmail);
    Order findSpecificOrder(int id) throws NoSuchOrderExists;
}
