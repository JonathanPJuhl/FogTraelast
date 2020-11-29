package infrastructure;

import domain.users.NoSuchUserExists;
import domain.users.User;
import domain.users.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUserRepository implements UserRepository {
    private final Database db;

    public DBUserRepository(Database db) {
        this.db = db;
    }

    @Override
    public User createUsr(String email, String password) {
     /*   int newID;
        SHA256 encrypt = new SHA256();
        String encryptedPass = encrypt.sha256(password);
        try (Connection conn = db.connect()) {
            String sql = "INSERT INTO user (email, password, isAdmin) VALUES (?, ?, 0)";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setString(1, email);
            smt.setString(2, encryptedPass);
            smt.executeUpdate();
            ResultSet set = smt.getGeneratedKeys();
            if (set.next()) {
                newID = set.getInt(1);
            } else {
                throw new RuntimeException("Unexpected error");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        try {
            return findUser(newID);
        } catch (NoSuchUserExists e) {
            throw new RuntimeException();
        }*/
        return null;
    }

    @Override
    public User loginUsr(String email, String password) throws NoSuchUserExists {
        SHA256 encrypt = new SHA256();
        String encryptedPass = encrypt.sha256(password);
        String passCheck = "";
        boolean isAdmin = false;
        int id = 0;
/*
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM user WHERE email = ?";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, email);
            ResultSet set = smt.executeQuery();
            System.out.println(smt.toString());
            if (set.next()) {
                id = parseUsrList(set).getId();
                passCheck = parseUsrList(set).getPassword();
                System.out.println("pass: " + passCheck);
            }
            System.out.println("ID: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } */
        System.out.println(encryptedPass + "Comparrison: " + passCheck);
        if (encryptedPass.equals(passCheck)) {
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
        /*return new User(set.getInt("user.id"),
                set.getString("user.email"),
                set.getString("user.password"),
                set.getDouble("user.balance"),
                set.getBoolean("user.isAdmin")
        );*/
        return null;
    }
    @Override
    public User findUser(int id) throws NoSuchUserExists {
        /*try (Connection conn = db.connect()){
            String sql = "SELECT * FROM user WHERE id=?";
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
        }*/
        return null;
    }
}
