package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.construction.Construction;
import domain.material.Material;

public class CladdingMaterialCalculator implements BOMItemSpecifications {

    private final Material material;

    public CladdingMaterialCalculator(Material material) {
        this.material = material;
    }


    @Override
    public int length() {
        return 0;
    }

    @Override
    public int width(int widthFromDB) {
        return 0;
    }
    
    @Override
    public int quantity() {
        return 0;
    }

    @Override
    public String description(String adminDescription) {
        return null;
    }
}
