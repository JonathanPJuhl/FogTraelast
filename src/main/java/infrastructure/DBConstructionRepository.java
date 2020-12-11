package infrastructure;

import domain.construction.Category;
import domain.construction.Construction;
import domain.material.Material;
import domain.construction.NoSuchMaterialExists;
import domain.material.MaterialService;
import domain.material.MaterialType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConstructionRepository implements MaterialService {

    final Database db;

    public DBConstructionRepository(Database db) {
        this.db = db;
    }

    public List<Material> findMaterialsForRoof(Construction construction) throws NoSuchMaterialExists { //TODO
        List<Material> roofItems = null;
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM fogtraelast.materialer LEFT JOIN fogtraelast.materialer_kategorier mk " +
                    "ON materialer.materialeID = mk.materialID " +
                    "RIGHT JOIN fogtraelast.kategorier k on k.kategoriID = mk.kategoriID " +
                    "WHERE k.kategori = ?";
            PreparedStatement smt = conn.prepareStatement(sql);
            if (construction.getRoof().isFlat()){
            smt.setString(1, "Flat tag");
            } else{
                smt.setString(1, "Skråt tag");
            }
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            while (set.next()) {
                if (parseMaterialList(set).equals(null)){
                    throw new NoSuchMaterialExists();
                }
                else {
                    roofItems.add(parseMaterialList(set));
                }
            }
            return roofItems;
        } catch (SQLException throwables) {
            throw new UnexpectedDBError(throwables);
        }
    }

    private Material parseMaterialList(ResultSet set) throws SQLException {
        return new Material(
                set.getString("materialer.name"),
                set.getInt("materialer.width"),
                set.getString("materialetyper.color"),
                set.getDouble("materialer.price"),
                MaterialType.valueOf(set.getString("materialer.type")),
                Category.valueOf(set.getString("materialer.category")),
                set.getInt("materialer.height")
        );

    }


    @Override
    public void insertMaterialIntoDB(Material material) {

    }

    @Override
    public Material findMaterial(MaterialType type, int width, String color, double price, Category category, int height) {
        return null;
    }
}
