package domain.construction.Roof;

import domain.construction.Cover;
import domain.construction.Material;

import java.util.ArrayList;
import java.util.List;

public class Roof {
    private final int height;
    private final int length;
    private final int width;
    private List<Material> roofMaterials; // TODO final ?
    private final boolean flat;
    //TODO hedder det det?
    private final Cover cladding;
    private final double degree;
    private final String color;


    //TODO Skal den have et parameter med højde?
    public Roof(int height, int length, int width, boolean flat, Cover cladding, double degree, String color) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.degree = degree;
        this.color = color;
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

    public String getColor() {
        return color;
    }

}
