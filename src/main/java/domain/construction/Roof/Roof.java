package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.ConstructionPart;
import domain.construction.UsersChoice;
import domain.material.Material;

public class Roof extends ConstructionPart {

    private final boolean flat;
    private final Material cladding;
    private final double degree;
    private final Category category;
    private final UsersChoice usersChoice;



    public Roof(UsersChoice usersChoice, double degree, int length, int width, boolean flat, Material cladding, Category category ) {
        super(length, width, category);
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

    public Material getCladding() {
        return cladding;
    }

    @Override
    public String toString() {
        return "Roof{" +
                ", flat=" + flat +
                ", cladding=" + cladding +
                ", degree=" + degree +
                ", category=" + category +
                ", usersChoice=" + usersChoice +
                '}';
    }
}
