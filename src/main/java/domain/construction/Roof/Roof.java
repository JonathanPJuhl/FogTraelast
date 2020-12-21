package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.ConstructionPart;
import domain.construction.UsersChoice;
import domain.material.Material;

public class Roof extends ConstructionPart {

    private static final RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();

    private final double height;
    private final boolean flat;
    private final Material cladding;
    private final double degree;
    private final Category category;
    private final UsersChoice usersChoice;


    //TODO Skal den have et parameter med højde?
    public Roof(double degree, int length, int width, boolean flat, Material cladding, double height, Category category, UsersChoice usersChoice) {
        super(length, width, category);
        this.height = height;
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

    public double getHeight() {
        return height;
    }

    public Material getCladding() {
        return cladding;
    }

    @Override
    public String toString() {
        return "Roof{" +
                "height=" + height +
                ", flat=" + flat +
                ", cladding=" + cladding +
                ", degree=" + degree +
                ", category=" + category +
                ", usersChoice=" + usersChoice +
                '}';
    }
}
