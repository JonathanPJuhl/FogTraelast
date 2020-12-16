package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.ConstructionPart;
import domain.construction.UsersChoice;
import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Roof extends ConstructionPart {

    private static final RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();

    private final double height;
    private final int length;
    private final int width;
    private final boolean flat;
    private final Material cladding;
    private final double degree;
    private final Category category;
    private final UsersChoice usersChoice;


    //TODO Skal den have et parameter med højde?
    public Roof(double degree, int length, int width, boolean flat, Material cladding, double height, Category category, UsersChoice usersChoice) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.degree = degree;
        this.flat = flat;
        this.cladding = cladding;
        this.category = category;
        this.usersChoice = usersChoice;
    }

    public double getDegree() {
        return degree;
    }

    public boolean isFlat() {
        return flat;
    }

    public Category getCategory() {
        return category;
    }
    public int getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Material getCladding() {
        return cladding;
    }

    @Override
    public String toString() {
        return "Roof{" +
                "height=" + height +
                ", length=" + length +
                ", width=" + width +
                ", flat=" + flat +
                ", cladding=" + cladding +
                ", degree=" + degree +
                ", category=" + category +
                ", usersChoice=" + usersChoice +
                '}';
    }
}
