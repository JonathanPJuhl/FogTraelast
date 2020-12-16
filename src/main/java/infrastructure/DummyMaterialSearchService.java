package infrastructure;

import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.Roof.Roof;
import domain.material.Material;
import domain.material.MaterialService;
import domain.material.MaterialType;

import java.util.List;

public class DummyMaterialSearchService implements MaterialService {


    @Override
    public void insertMaterialIntoDB(Material material) {

    }

    @Override
    public Material findMaterial(String nameType, String color, String type, String category, int height) {
        return new Material(0,"Dummy" + type, color, 0.0, type, category, height);
    }

    @Override
    public Material findMaterialByID(int id) {
        return null;
    }

    @Override
    public List<Material> findMaterialsByCategory(Category category) {
        return null;
    }


    @Override
    public List<Material> roofMaterials(String roofType, int width) {
        return null;
    }


    @Override
    public List<Material> allMaterialsInDB() {
        return null;
    }

}
