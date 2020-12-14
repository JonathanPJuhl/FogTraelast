package domain.material;

import domain.construction.Category;
import domain.construction.Construction;

import java.util.List;

public interface MaterialService {

    public void insertMaterialIntoDB(Material material); //TODO

    public Material findMaterial(int id, String nametype, String color, double price, MaterialType type, Category category, int height);

    public List<Material> roofMaterials(Construction construction);


}
