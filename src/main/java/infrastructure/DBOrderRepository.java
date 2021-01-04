package infrastructure;

import domain.construction.UsersChoice;
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
    public Order insertOrderIntoDB(UsersChoice usersChoice) {
        int newID;
        try(Connection conn = db.connect()){
            String sql = "INSERT INTO orders (length, width, customerPhone, customerEmail, roofType, shedOrNo, wallsOrNo, shedLength, shedWidth) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setDouble(1, usersChoice.getLength());
            smt.setDouble(2, usersChoice.getWidth());
            smt.setString(3, usersChoice.getCustomerPhone());
            smt.setString(4, usersChoice.getCustomerEmail());
            smt.setString(5, usersChoice.getRoofChoice());
            smt.setInt(6, usersChoice.getShedOrNo());
            smt.setInt(7, usersChoice.getCladdingChoice());
            smt.setInt(8,usersChoice.getShedLength());
            smt.setInt(9,usersChoice.getShedwidth());

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
    public void editRoofType(String columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set roofType = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, columnValue);
            smt.setInt(2, orderID);
            smt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void editShedOrNo(int columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set shedOrNo = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, columnValue);
            smt.setInt(2, orderID);
            smt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void editCladding(int columnValue, int orderID) throws NoSuchOrderExists{
        try (Connection conn = db.connect()){
            String sql = "UPDATE orders set wallsOrNo = ? where orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, columnValue);
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
                set.getInt("orders.shedOrNo"),
                set.getInt("orders.wallsOrNo"),
                set.getInt("orders.shedLength"),
                set.getInt("orders.shedWidth"));
    }
}



