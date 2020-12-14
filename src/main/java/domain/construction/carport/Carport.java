package domain.construction.carport;

import domain.construction.Category;
import domain.construction.Construction;
import domain.material.Material;

import java.util.List;

public class Carport {

    private final int height;
    private final List<Material> materials;
    private final Category category;
    private Construction construction;

    public Carport(List<Material> materials, Construction construction) {
        this.category = Category.carport;
        this.height = 3000; //Den er fast - indtilvidere //TODO var det tre meter vi fastsatte?
        this.materials = materials;
        this.construction = construction;
    }

    public int width() {
        //TODO
        return construction.getWidth() - 0;
    }

    public int length() {
        //TODO
        return construction.getLength() - 0;
    }

    public int getHeight() {
        return height;
    }


}
