package domain.construction.Roof;

import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Roof {
    private int height;
    private final int length;
    private final int width;
    private List<Material> roofMaterials; // TODO final ?
    private final boolean flat;
    //TODO hedder det det?
    private final Material cladding;
    private final double degree;


    //TODO Skal den have et parameter med højde?
    public Roof(int height, int length, int width, boolean flat, Material cladding, double degree) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.degree = degree;
        this.flat = flat;
        this.cladding = cladding;
        roofMaterials = new ArrayList();
    }

    public double getDegree() {
        return degree;
    }

    public boolean isFlat() {
        return flat;
    }

    public void setHeight(int height){
        this.height = height;
    }
}
