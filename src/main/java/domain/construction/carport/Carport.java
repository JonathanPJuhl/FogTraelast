package domain.construction.carport;

import domain.construction.Category;
import domain.construction.Construction;
import domain.material.Material;

import java.util.List;

public class Carport {

    private final int height;
    private final List<Material> materials;
    private final Category category;

    public Carport(List<Material> materials) {
        this.category = Category.carport;
        this.height = 3000; //Den er fast - indtilvidere //TODO var det tre meter vi fastsatte?
        this.materials = materials;
    }

    private int width(Construction construction) {
        //TODO
        return construction.getWidth() - 0;
    }

    private int length(Construction construction) {
        //TODO
        return construction.getLength() - 0;
    }

    public int getHeight() {
        return height;
    }

}
