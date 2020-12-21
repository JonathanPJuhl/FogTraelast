package infrastructure;

import domain.construction.Category;
import domain.material.Material;
import domain.material.NoSuchMaterialExists;
import domain.material.MaterialService;
import domain.material.MaterialType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Her er category: "+ category.toString());
            smt.setString( 1, category.toString());
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
    public List<Material> roofMaterials(String roofType) {
        List<Material> roofItems = new ArrayList();
        String roofMaterialType;
        int roofMaterialHeight;
        if(roofType.equals("Flat")) {
            roofMaterialType = MaterialType.roofPlades.getDanishName();
            roofMaterialHeight = 10;
        }else {
            roofMaterialType = MaterialType.roofTiles.getDanishName();
            roofMaterialHeight = 30;
        }
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materials LEFT JOIN fogtraelast.materials_By_Category MC ON materials.materialID = MC.materialID RIGHT JOIN fogtraelast.categories C on C.categoryID = MC.categoryID WHERE C.category=? and materials.type = ? and materials.height = ?";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString( 1, roofType);
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

  /* private Category category (String catName) throws SQLException, NoSuchMaterialExists {
      Category cat = null;
        ArrayList<Order> ordersByStatus = new ArrayList<>();
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM categories where ;
            var smt = conn.prepareStatement(sql);

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


    }*/
}
