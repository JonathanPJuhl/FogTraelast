package infrastructure;

import domain.construction.Construction;
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
                set.getDouble("materialer.pris"),
                set.getString("materialetyper.type"),
                set.getString("materialer.navn")
        );
    }

    @Override
    public List<Material> setRoofBOM(Material material, int quantity) {
//TODO
        return null;
    }

    @Override
    public void insertRoofBOM(List<Material> roofBOM) {
//TODO
    }

    @Override
    public void insertMaterialIntoDB(Material material) {
//TODO
    }

    @Override
    public Material findSpecificMaterial(int MaterialID) throws NoSuchMaterialExists {
        //TODO
        return null;
    }

    @Override
    public List<Material> findAllMaterails() throws NoSuchMaterialExists {
        //TODO
        return null;
    }
}
