package domain.construction.Roof;

import domain.construction.Material;
import domain.construction.NoSuchMaterialExists;

import java.util.List;

public interface RoofMaterialRepository {

    List<Material> findMaterialsForRoof (Roof roof) throws NoSuchMaterialExists;

    List<Material> setRoofBOM (Material material, int quantity);

    void insertRoofBOM (List<Material> roofBOM);

}
