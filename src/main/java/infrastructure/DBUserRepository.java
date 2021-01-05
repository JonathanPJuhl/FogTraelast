package infrastructure;

import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBUserRepository implements UserRepository {
    private final Database db;

    public DBUserRepository(Database db) {
        this.db = db;
    }

    @Override
    public User createUsr(String email, String password) {
        return null;
    }

    @Override
    public User loginSalesman(String email, String password) throws NoSuchUserExists {
        //SHA256 encrypt = new SHA256();
        //String encryptedPass = encrypt.sha256(password);
        String passCheck = "";
        boolean isAdmin = false;
        int id = 0;

        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM salesmen WHERE email = ?";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, email);
            ResultSet set = smt.executeQuery();
            System.out.println(smt.toString());
            if (set.next()) {
                id = parseUsrList(set).getID();
                passCheck = parseUsrList(set).getPassword();
                System.out.println("pass: " + passCheck);
            }
            System.out.println("ID: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(password + "Comparrison: " + passCheck);
        if (password.equals(passCheck)) {
            try {
                return findUser(id);
            } catch (NoSuchUserExists e) {
                throw new RuntimeException();
            }
        } else {
            System.out.println("You returned null");
            return null;
        }
    }
    private User parseUsrList(ResultSet set) throws SQLException {
        return new User(set.getInt("salesmen.salesmanID"),
                set.getString("salesmen.name"),
                set.getString("salesmen.phone"),
                set.getString("salesmen.email"),
                set.getString("salesmen.password"),
                set.getInt("salesmen.role"));

    }
    @Override
    public User findUser(int id) throws NoSuchUserExists {
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM salesmen WHERE salesmanID=?";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if(set.next()) {
                return parseUsrList(set);
            }else {
                throw new RuntimeException("Unexpected error");
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoSuchUserExists();
        }
    }
    @Override
    public List<User> findAllSalesmen() throws NoSuchUserExists {

        ArrayList<User> list = new ArrayList<>();
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM salesmen;";
            var smt = conn.prepareStatement(sql);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();

            while(set.next()) {
                list.add(parseUsrList(set));
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoSuchUserExists();
        }

        return list;

    }
}
