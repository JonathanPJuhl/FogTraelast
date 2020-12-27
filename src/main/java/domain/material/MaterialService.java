package domain.material;

import domain.construction.Category;
import domain.construction.Construction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract interface MaterialService {

    public void insertMaterialIntoDB(Material material); //TODO

    public Material findMaterial(String nametype);

    /*public Material findMaterial(String nametype, String color, String type, String category, int height);*/

    public Material findMaterialByID(int id);

    public List<Material> findMaterialsByCategory(Category category);

    public List<Material> roofMaterials(String roofType);

    public List<Material>allMaterialsInDB();
}
