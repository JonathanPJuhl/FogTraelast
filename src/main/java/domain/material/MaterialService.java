package domain.material;

import domain.construction.Category;
import domain.construction.Construction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MaterialService {

    public void insertMaterialIntoDB(Material material); //TODO

    public Material findMaterial(int id, String nametype, String color, double price, String type, String category, int height);

    public List<Material> roofMaterials(String roofType);


}
