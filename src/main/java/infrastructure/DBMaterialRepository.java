package infrastructure;

import domain.bom.BOMFromDB;
import domain.bom.BOMItem;
import domain.construction.Category;
import domain.material.Material;
import domain.material.NoSuchMaterialExists;
import domain.material.MaterialService;
import domain.material.MaterialType;
import domain.orders.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DBMaterialRepository implements MaterialService {

    private final Database db;

    public DBMaterialRepository(Database db) {
        this.db = db;
    }

    public Material parseMaterialList(ResultSet set) throws SQLException, NoSuchMaterialExists {
        return new Material(
                set.getInt("materials.materialID"),
                set.getString("materials.name"),
                set.getString("materials.color"),
                set.getDouble("materials.price"),
                set.getString("materials.type"),
                set.getString("category"),
                set.getInt("materials.height"),
                set.getInt("materials.overlap")); //TODO lav Materialer om så alle har et overlap i db gemt

    }

    @Override
    public void insertMaterialIntoDB(Material material) {
//TODO
    }
    @Override
    public Material findMaterial(String nametype) {
        Material material = null;
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID where materials.name=?;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString( 1, nametype);
            //TODO
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if (set.next()) {
                material = parseMaterialList(set);
            }
            return material;
        } catch (SQLException | NoSuchMaterialExists throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    @Override
    public Material findMaterialByID(int id) {
        Material material = null;
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID where materials.materialID=?;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setInt( 1, id);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if (set.next()) {
                material = parseMaterialList(set);
            }
            return material;
        } catch (SQLException | NoSuchMaterialExists throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    @Override
    public List<Material> findMaterialsByCategory(Category category) {
        List<Material> roofItems = new ArrayList();
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID WHERE C.category=?;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString( 1, category.getDanishName());
            smt.executeQuery();
            ResultSet set = smt.getResultSet();

            while (set.next()) {
                Material material = parseMaterialList(set);
                roofItems.add(material);
            }
            return roofItems;
        } catch (SQLException | NoSuchMaterialExists throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    @Override
    public ArrayList<Material> roofMaterials(Category category) {
        ArrayList<Material> roofItems = new ArrayList();
        String roofMaterialType;
        int roofMaterialHeight;
        if(category.equals(Category.Flat)) {
            roofMaterialType = MaterialType.roofPlades.getDanishName();
            roofMaterialHeight = 10;
        }else {
            roofMaterialType = MaterialType.roofTiles.getDanishName();
            roofMaterialHeight = 30;
        }
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID WHERE C.category=? and materials.type = ? and materials.height = ?";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString( 1, category.getDanishName());
            smt.setString(2,roofMaterialType);
            smt.setInt(3,roofMaterialHeight);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();

            while (set.next()) {
                Material material = parseMaterialList(set);
                roofItems.add(material);
            }
            return roofItems;
        } catch (SQLException | NoSuchMaterialExists throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    @Override
    public List<Material> allMaterialsInDB() {
        List<Material> roofItems = new ArrayList();
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while (set.next()) {
                Material material = parseMaterialList(set);
                roofItems.add(material);
            }
            return roofItems;
        } catch (SQLException | NoSuchMaterialExists throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    @Override
    public TreeSet<Integer> allWidthsForMaterials() {
        TreeSet<Integer> widths = new TreeSet<>();
        try (Connection conn = db.connect()) {
            String sql = "SELECT widths.width FROM fogtraelast.widths;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while (set.next()) {
                widths.add(set.getInt("width"));
            }
            return widths;
        } catch (SQLException e) {
            throw new UnexpectedDBError();
        }
    }

    @Override
    public TreeSet<Integer> allLenghtsForMaterials() {
        TreeSet<Integer> lenghts = new TreeSet<>();
        try (Connection conn = db.connect()) {
            String sql = "SELECT lengths.length FROM fogtraelast.lengths;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while (set.next()) {
                lenghts.add(set.getInt("length"));
            }
            return lenghts;
        } catch (SQLException e) {
            throw new UnexpectedDBError();
        }
    }

    @Override
    public void storeBOM(BOMItem bomItem, Order order, int materialByCategoryID) {
        double priceQnty = bomItem.getQuantity()*bomItem.getMaterial().getPrice();
        try (Connection conn = db.connect()) {
            String sql = "INSERT INTO fogtraelast.bom (orderID, materialID_By_Category, length, width, describtion, quantity, qnty_price) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setInt( 1, order.getOrderID());
            smt.setInt(2, materialByCategoryID);
            smt.setInt( 3, bomItem.getLength());
            smt.setInt( 4, bomItem.getWidth());
            smt.setString(5, bomItem.getDescription());
            smt.setInt(6, bomItem.getQuantity());
            smt.setDouble(7, priceQnty);
            smt.executeUpdate();

            } catch (SQLException throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    @Override
    public int findMaterialByCategoryID(Material material, Category category) {
        int materialsByCategoryID = -1;
        String danish = category.getDanishName();
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials " +
                    "LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID " +
                    "RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID " +
                    "WHERE C.category = ? and materials.materialID = ?;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, danish);
            smt.setInt(2, material.getId());
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if (set.next()) {
                materialsByCategoryID = set.getInt("materials_CategoryID");
            }
            return materialsByCategoryID;
        } catch (SQLException e) {
            throw new UnexpectedDBError();
        }
    }

    @Override
    public double findBOMPriceByOrderID(int orderID) {
        double priceTotal = 0.0;
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.bom where bom.orderID = ?;";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setInt(1, orderID);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while (set.next()) {
                priceTotal += set.getDouble("qnty_price");
            }
            return priceTotal;
        } catch (SQLException e) {
            throw new UnexpectedDBError();
        }


    }

    @Override
    public ArrayList<BOMFromDB> findBOMByOrderID(int orderID) {
        ArrayList<BOMFromDB> bom = new ArrayList<>();
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM bom WHERE orderID=?;";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, orderID);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while (set.next()) {
                bom.add(parseBOMList(set));
            }
        } catch (SQLException throwables) {
            throw new UnexpectedDBError(throwables);
        }
        return bom;
    }
    @Override
    public BOMFromDB parseBOMList(ResultSet set) throws SQLException {
        return new BOMFromDB(
                set.getInt("bom.bomID"),
                set.getInt("bom.orderID"),
                set.getInt("bom.materialID_By_Category"),
                set.getInt("bom.length"),
                set.getInt("bom.width"),
                set.getString("bom.describtion"),
                set.getInt("bom.quantity"));

    }

    }


