package infrastructure;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.Roof.Roof;
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

    private Material parseMaterialList(ResultSet set) throws SQLException, NoSuchMaterialExists {
        return new Material(
                set.getInt("materialer.materialeID"),
                set.getString("materialer.navn"),
                set.getString("materialer.farve"),
                set.getDouble("materialer.pris"),
                materialType(set),
                category(set),
                set.getInt("materialer.højde")
        );

    }

    private MaterialType materialType (ResultSet set) throws SQLException, NoSuchMaterialExists {
        String materialName;
        materialName = set.getString("materialer.type");
        for (MaterialType m : MaterialType.values()) {
            if (m.getDanishName().equals(materialName))
                return m;
        }
        throw new NoSuchMaterialExists();
    }

    private Category category (ResultSet set) throws SQLException, NoSuchMaterialExists {
        String categoryName;
        categoryName = set.getString("kategori"); //TODO kan være et problem på droplet
        for (Category c : Category.values()) {
            if (c.getDanishName().equals(categoryName))
                return c;
        }
        throw new NoSuchMaterialExists();
    }

    @Override
    public void insertMaterialIntoDB(Material material) {

    }

    @Override
    public Material findMaterial(int id, String nameType, String color, double price, MaterialType type, Category category, int height) {
        return null;
    }

    @Override
    public List<Material> roofMaterials(String roofType) {
        List<Material> roofItems = new ArrayList();
        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM fogtraelast.materialer LEFT JOIN fogtraelast.materialer_kategorier mk " +
                    "ON materialer.materialeID = mk.materialID " +
                    "RIGHT JOIN fogtraelast.kategorier k on k.kategoriID = mk.kategoriID " +
                    "WHERE k.kategori = ?";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, roofType);
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

}
