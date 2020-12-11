package infrastructure;

import domain.construction.Category;
import domain.material.Material;
import domain.material.MaterialService;
import domain.material.MaterialType;

public class DummyMaterialSearchService implements MaterialService {


    @Override
    public void insertMaterialIntoDB(Material material) {

    }

    @Override
    public Material findMaterial(MaterialType type, int width, String color, double price, Category category, int height) {
        return new Material("Dummy" + type, width, color, price, type, category, height);
    }
}
