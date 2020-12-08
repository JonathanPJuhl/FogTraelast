package infrastructure;

import domain.construction.ConstructionRepository;
import domain.construction.Material;
import domain.construction.NoSuchMaterialExists;
import domain.construction.Roof.Roof;
import domain.orders.NoSuchOrderExists;
import domain.orders.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConstructionRepository implements ConstructionRepository {

    final Database db;

    public DBConstructionRepository(Database db) {
        this.db = db;
    }

    @Override
    public List<Material> findMaterialsForRoof(Roof roof) throws NoSuchMaterialExists {
        List<Material> roofItems = null;
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM fogtraelast.materialer LEFT JOIN fogtraelast.materialer_kategorier mk " +
                    "ON materialer.materialeID = mk.materialID " +
                    "RIGHT JOIN fogtraelast.kategorier k on k.kategoriID = mk.kategoriID " +
                    "WHERE k.kategori = ?";
            PreparedStatement smt = conn.prepareStatement(sql);
            if (roof.isFlat()){
            smt.setString(1, "FLat tag");
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
                set.getDouble("materialer.pris"),
                set.getString("materialetyper.type"),
                set.getString("materialer.navn")
        );
    }

    @Override
    public List<Material> setRoofBOM(Material material, int quantity) {

        return null;
    }

    @Override
    public void insertRoofBOM(List<Material> roofBOM) {

    }
}
