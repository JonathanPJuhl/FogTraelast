package domain.construction;

import java.util.ArrayList;
import java.util.List;

public abstract class Roof {
    private final int height;
    private final int lenght;
    private final int width;
    private final List<Material> roofmaterials;
    private final boolean flat;
    //TODO
    private final Cover cladding;
    //TODO angle for både hæld?


    public Roof(int height, int lenght, int width, boolean flat, Cover cladding) {
        this.height = height;
        this.lenght = lenght;
        this.width = width;
        this.roofmaterials = new ArrayList<>();
        this.flat = flat;
        this.cladding = cladding;
    }
}
