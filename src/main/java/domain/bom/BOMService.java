package domain.bom;

import domain.construction.Category;
import domain.construction.Construction;
import domain.material.MaterialService;
import domain.material.MaterialType;

public class BOMService {

    private final MaterialService materials;

    public BOMService(MaterialService materials) {
        this.materials = materials;
    }

    public BOM calculateBom(Construction construction) {
        BOM bom = new BOM();

        //Dette er bare for at teste
        bom.addItem(new BOMItem(materials.findMaterial(-1 , "Din mor", "blue", 01.01,
                MaterialType.wood, Category.carport, -1), 2, 3000, "Dummy beskrivelse"));

        return bom;
    }

}
