package domain.construction;

import domain.material.Material;

public class Cladding extends ConstructionPart{
    private final Category category;
    private final int sidesQuanitatyForCarport;
    private final Material material;

    public Cladding(int side, int hight, Category category, Material material) {
        this.category = category;
        this.material = material;
        this.sidesQuanitatyForCarport = 3;
    }

    public Category getCategory() {
        return category;
    }

    public int getSidesQuanitatyForCarport() {
        return sidesQuanitatyForCarport;
    }

    public Material getMaterial() {
        return material;
    }
}
