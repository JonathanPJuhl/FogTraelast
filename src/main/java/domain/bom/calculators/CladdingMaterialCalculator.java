package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.material.Material;

public class CladdingMaterialCalculator implements BOMItemSpecifications {

    private final int materialWidth;
    private final int sideConstruction;
    private final int heightCarport;

    public CladdingMaterialCalculator(int materialWidth, int sideConstruction, int heigthCarport) {
        this.materialWidth = materialWidth;
        this.sideConstruction = sideConstruction;
        this.heightCarport = heigthCarport;
    }

    @Override
    public int length() {
        return sideConstruction;
    }

    @Override
    public int width(int widthFromDB) {
        return widthFromDB;
    }
    
    @Override
    public int quantity() {
        int qnty = heightCarport/materialWidth;
        return qnty;
    }

    @Override
    public String description(String adminDescription) {
        return adminDescription;
    }
}
