package domain.construction.Roof;

import domain.construction.Construction;
import domain.construction.Material;
import domain.construction.NoSuchMaterialExists;

import java.util.List;

public interface RoofMaterialRepository {

    List<Material> findMaterialsForRoof (Construction construction) throws NoSuchMaterialExists;
    //TODO er dette rigtigt kastet med exceptionen?

    List<Material> setRoofBOM (Material material, int quantity);

    void insertRoofBOM (List<Material> roofBOM);

}
