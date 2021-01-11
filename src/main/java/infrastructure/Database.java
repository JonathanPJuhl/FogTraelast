package infrastructure;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;

public class Database {

    //Credentials
    private final String URL;
    private final String USER;

    // Database version
    private static final int version = 4;

    public Database(String url, String user) {
        this.URL = url == null ? "jdbc:mysql://localhost:3306/fogtraelast?serverTimezone=CET" : url;
        this.USER = user == null ? "fogtraelast" : user;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Database(){
    this(null,null);
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, null);
    }

    public static int getVersion() {
        return version;
    }

    public int getCurrentVersion() {
        try (Connection conn = connect()) {
            Statement s = conn.createStatement();


            String sql = "SELECT value FROM properties WHERE name = 'VERSION';";
            PreparedStatement smt = conn.prepareStatement(sql);

            ResultSet rs = smt.executeQuery();

            if(rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            } else {
                System.err.println("No version in properties.");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

   /* public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, null);
    }*/

    public void runMigration(){
        try {
            runMigrations();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public  void runMigrations() throws IOException, SQLException {
        int version = getCurrentVersion();
        while (version < Database.getVersion()) {
            System.out.printf("Current DB version %d is smaller than expected %d\n", version, Database.getVersion());
            runMigration(version + 1);
            int new_version = getCurrentVersion();
            if (new_version > version) {
                version = new_version;
                System.out.println("Updated database to version: " + new_version);
            } else {
                throw new RuntimeException("Something went wrong, version not increased: " + new_version);
            }
        }
    }

    public  void runMigration(int i) throws IOException, SQLException {
        String migrationFile = String.format("Migrate/%d.sql", i);
        System.out.println("Running migration: " + migrationFile);
        InputStream stream = Migrate.class.getClassLoader().getResourceAsStream(migrationFile);
        if (stream == null) {
            System.out.println("Migration file, does not exist: " + migrationFile);
            throw new FileNotFoundException(migrationFile);
        }
        try(Connection conn = connect()) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        }
        System.out.println("Done running migration");
    }


}
