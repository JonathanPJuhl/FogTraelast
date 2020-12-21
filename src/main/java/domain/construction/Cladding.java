package domain.construction;

import domain.material.Material;

public class Cladding extends ConstructionPart{
    private final int sidesQuanitatyForCarport;
    private final Material material;

    public Cladding(int side, int height, Category category, Material material) {
        super(side, height, category);
        this.material = material;
        this.sidesQuanitatyForCarport = 3;
    }



    public int getSidesQuanitatyForCarport() {
        return sidesQuanitatyForCarport;
    }

    public Material getMaterial() {
        return material;
    }
}
