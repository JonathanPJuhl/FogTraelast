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
    public Order insertOrderIntoDB(double length, double width, String customerPhone, String customerEmail, String roofType, boolean shedOrNo, boolean cladding) {
        int newID;
        try(Connection conn = db.connect()){
            String sql = "INSERT INTO orders (length, width, customerPhone, customerEmail, roofType, shedOrNo, cladding) VALUES (?, ?, ?, ?, ?, ?, ?);";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setDouble(1, length);
            smt.setDouble(2, width);
            smt.setString(3, customerPhone);
            smt.setString(4, customerEmail);
            smt.setString(5, roofType);
            smt.setBoolean(6, shedOrNo);
            smt.setBoolean(7, cladding);
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
    public void editSalesman(int columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set salesmanID = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, columnValue);
            smt.setInt(2, orderID);
            smt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editStatus(String columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set orderStatus = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, columnValue);
            smt.setInt(2, orderID);
            smt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editPrice(double columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set price = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setDouble(1, columnValue);
            smt.setInt(2, orderID);
            smt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public List<Order> displayOrdersByStatus(String wantedStatus){
        ArrayList<Order> ordersByStatus = new ArrayList<>();
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM orders WHERE orderStatus=?;";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, wantedStatus);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while(set.next()) {
                ordersByStatus.add(parseOrderList(set));
            }
        } catch (SQLException throwables) {
            throw new UnexpectedDBError(throwables);
        }
        return ordersByStatus;
    }
    @Override
    public List<Order> displayOrdersBySalesman(int wantedSalesman){
        ArrayList<Order> ordersBySalesman = new ArrayList<>();
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM orders WHERE salesmanID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, wantedSalesman);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while(set.next()) {
                ordersBySalesman.add(parseOrderList(set));
            }
        } catch (SQLException throwables) {
            throw new UnexpectedDBError(throwables);
        }
        return ordersBySalesman;
    }




    private Order parseOrderList(ResultSet set) throws SQLException {
        boolean shed =false;
        boolean cladding = false;
        return new Order(
                set.getInt("orders.orderID"),
                set.getString("orders.orderStatus"),
                set.getInt("orders.length"),
                set.getInt("orders.width"),
                set.getString("orders.customerPhone"),
                set.getString("orders.customerEmail"),
                set.getDouble("orders.price"),
                set.getInt("orders.salesmanID"),
                set.getString("orders.roofType"),
                set.getBoolean("orders.shedOrNo"),
                set.getBoolean("orders.cladding"));
    }
}



