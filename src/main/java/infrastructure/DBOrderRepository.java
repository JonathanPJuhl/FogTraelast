package infrastructure;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.orders.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOrderRepository implements OrderRepository {
    private final Database db;

    public DBOrderRepository(Database db) {
        this.db = db;
    }

    @Override
    public Order insertOrderIntoDB(double length, double width, String customerPhone, String customerEmail) {
        int newID;
        SHA256 encrypt = new SHA256();
        //String encryptedPass = encrypt.sha256(password);
        try(Connection conn = db.connect()){
            String sql = "INSERT INTO orders (length, width, costumerPhone, customerEmail) VALUES (?, ?, ?, ?, ?)";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setDouble(2, length);
            smt.setDouble(3, width);
            smt.setString(4, customerPhone);
            smt.setString(5, customerEmail);
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
            //Put no such order exception into order package
        } catch (NoSuchOrderExists e) {
            throw new RuntimeException();
        }

    }

//SKAL RETTES TIL
    @Override
    public Order findSpecificOrder(int id) throws NoSuchOrderExists {
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM orders WHERE orderID=?";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if(set.next()) {
                return parseOrderList(set);
            }else {
                throw new RuntimeException("Unexpected error");
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoSuchOrderExists();
        }

    }
//SKAL RETTES TIL
    private Order parseOrderList(ResultSet set) throws SQLException {
        return new Order(
                set.getInt("orders.orderID"),
                set.getString("orders.orderStatus"),
                set.getDouble("orders.length"),
                set.getDouble("orders.width"),
                set.getString("orders.costumerPhone"),
                set.getString("orders.costumerEmail"),
                set.getDouble("orders.price"),
                set.getInt("orders.salesmanID")
        );
    }
}



