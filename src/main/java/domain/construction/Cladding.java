package domain.construction;

import domain.material.Material;

import java.util.List;

public class Cladding {
    private final List<Material> bom;
    private final Category category;
    private final int sidesQuanitatyForCarport;

    public Cladding(List<Material> bom, Category category) {
        this.bom = bom;
        this.category = category;
        this.sidesQuanitatyForCarport = 3;
    }
}
