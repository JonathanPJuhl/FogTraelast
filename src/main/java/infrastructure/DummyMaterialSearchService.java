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
    public Material findMaterial(int id, String nameType, String color, double price, MaterialType type, Category category, int height) {
        return new Material(id, "Dummy" + type, color, price, type, category, height);
    }

    @Override
    public List<Material> roofMaterials(Construction construction) {
        return null;
    }

    @Override
    public Roof createRoof(Category category, Construction construction) {
        return null;
    }
}
