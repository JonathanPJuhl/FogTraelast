package domain.material;

import domain.construction.Category;

public interface MaterialService {

    public void insertMaterialIntoDB(Material material);

    public Material findMaterial(MaterialType type, int width, String color, double price, Category category, int height);

}
