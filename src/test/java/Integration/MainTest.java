package Integration;

import api.FogTraelast;
import domain.construction.ConstructionFactory;
import domain.construction.UsersChoice;
import domain.material.Material;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;
import infrastructure.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Tag("IntegrationTest")
public class MainTest {

    FogTraelast api;

    static void resetTestDB() {
        String URL = "jdbc:mysql://localhost:3306/fogtraelasttest?serverTimezone=CET";
        String USER = "fogtraelasttest";


            InputStream stream = Migrate.class.getClassLoader().getResourceAsStream("init.sql");
            if (stream == null) {
                throw new RuntimeException("init.sql not found");
            }
        try (Connection conn = DriverManager.getConnection(URL, USER, null)){
                conn.setAutoCommit(false);
                ScriptRunner runner = new ScriptRunner(conn);
                runner.setStopOnError(true);
                runner.runScript(new BufferedReader(new InputStreamReader(stream)));
                conn.commit();
            }
        catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Done running migrationssssss");
    }



    @BeforeEach
    void setupAPI(){
        resetTestDB();

        String URL = "jdbc:mysql://localhost:3306/fogtraelasttest?serverTimezone=CET";
        String USER = "fogtraelasttest";

        Database db = new Database(URL, USER);
        db.runMigration();




        DBOrderRepository orderRepository = new DBOrderRepository(db);
        DBUserRepository userRepository = new DBUserRepository(db);
        DBMaterialRepository materialRepository = new DBMaterialRepository(db);
        ConstructionFactory cf = new ConstructionFactory();
        api = new FogTraelast(userRepository, orderRepository, materialRepository, cf);
    }


    //As productowner i'd like incoming orders to be saved, so that i can access them in the future
    @Test
    public void orderShouldBeCreated(){
        //Setup
        Material material = api.findMaterial("reglar");

        UsersChoice choice = new UsersChoice(1200, 1200, "Flat", 0, 0,
                "+4512345678", "test@test.dk", material, 1.0, 100, 100, material);

        //createOrder
        api.createOrder(choice);

        //find order
        try {
            Order order = api.findOrder(3);
            UsersChoice orderChoice = new UsersChoice((int)order.getWidth(), (int)order.getLength(), order.getRoofType(),
                    order.getShedOrNo(), order.getWallsOrNo(), order.getCustomerPhone(), order.getCustomerEmail(),
                    material, 1.0, order.getShedLength(), order.getShedWidth(), material);
            assertTrue(order!=null);
            assertEquals(choice.getLength(), orderChoice.getLength());
            assertEquals(choice.getWidth(), orderChoice.getWidth());
            assertEquals(choice.getRoofChoice(), orderChoice.getRoofChoice());
            assertEquals(choice.getShedOrNo(), orderChoice.getShedOrNo());
            assertEquals(choice.getCustomerPhone(), orderChoice.getCustomerPhone());
            assertEquals(choice.getCustomerEmail(), orderChoice.getCustomerEmail());
            assertEquals(choice.getShedLength(), orderChoice.getShedLength());
            assertEquals(choice.getShedwidth(), orderChoice.getShedwidth());


        } catch (NoSuchOrderExists noSuchOrderExists) {
            noSuchOrderExists.printStackTrace();
        }

    }
    //As a salesman, i would like to be able to edit the price of an order, so i can give the client a good deal
    @Test
    public void OrderPriceShouldBeEdited(){
        //edit price of given order
        try {
            api.editPrice(100, 2);
        } catch (NoSuchOrderExists noSuchOrderExists) {
            noSuchOrderExists.printStackTrace();
        }
        double actual= 0;
        //find order
        try {
            Order order = api.findOrder(2);
            actual = order.getPrice();


        } catch (NoSuchOrderExists noSuchOrderExists) {
            noSuchOrderExists.printStackTrace();
        }
        assertEquals(100, actual, 0);
    }
}
