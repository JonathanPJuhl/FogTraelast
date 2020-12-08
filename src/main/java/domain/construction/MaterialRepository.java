package domain.construction;

import java.util.List;

public interface MaterialRepository {

    void insertMaterialIntoDB(Material material);

    Material findSpecificMaterial(int MaterialID) throws NoSuchMaterialExists;

    List<Material> findAllMaterails() throws NoSuchMaterialExists;

}
