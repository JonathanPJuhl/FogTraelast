package infrastructure;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOrderRepository implements OrderRepository {
    private final Database db;

    public DBOrderRepository(Database db) {
        this.db = db;
    }

    @Override
    public Order insertOrderIntoDB(double length, double width, String customerPhone, String customerEmail) {
        System.out.println("l: " + length + "w: " + width + customerPhone + customerEmail);
        int newID;
        try(Connection conn = db.connect()){
            String sql = "INSERT INTO orders (length, width, customerPhone, customerEmail) VALUES (?, ?, ?, ?);";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setDouble(1, length);
            smt.setDouble(2, width);
            smt.setString(3, customerPhone);
            smt.setString(4, customerEmail);
            smt.executeUpdate();
            ResultSet set = smt.getGeneratedKeys();
            if(set.next()) {
                newID = set.getInt(1);
            }else {
                throw new RuntimeException("Unexpected error");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        try {
            System.out.println("ID: " + newID);
            return findSpecificOrder(newID);

        } catch (NoSuchOrderExists e) {
            throw new RuntimeException();
        }

    }


    @Override
    public Order findSpecificOrder(int id) throws NoSuchOrderExists {
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM orders WHERE orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if (set.next()) {
                return parseOrderList(set);
            } else {
                throw new NoSuchOrderExists();
            }
        } catch (SQLException throwables) {
            throw new UnexpectedDBError(throwables);
        }

    }

    @Override
    public List<Order> findAllOrders() throws NoSuchOrderExists {
        ArrayList<Order> list = new ArrayList<>();
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM orders;";
            var smt = conn.prepareStatement(sql);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();

            while(set.next()) {
                list.add(parseOrderList(set));
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoSuchOrderExists();
        }

        return list;
    }


    @Override
    public void editOrder(String columnName, String columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set ? = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, columnName);
            smt.setString(2, columnValue);
            smt.setInt(3, orderID);
            smt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Order parseOrderList(ResultSet set) throws SQLException {
        return new Order(
                set.getInt("orders.orderID"),
                set.getString("orders.orderStatus"),
                set.getInt("orders.length"),
                set.getInt("orders.width"),
                set.getString("orders.customerPhone"),
                set.getString("orders.customerEmail"),
                set.getDouble("orders.price"),
                set.getInt("orders.salesmanID")
        );
    }
}



