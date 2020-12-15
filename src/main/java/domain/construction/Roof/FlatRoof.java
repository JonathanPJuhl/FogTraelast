package domain.construction.Roof;

import domain.construction.Category;
import domain.material.Material;

public class FlatRoof extends Roof {

    private static final double TILTTODEGREE = (double) Math.round(Math.toDegrees(Math.atan(3.0 / 100.0))); //TODO kunne ændre?

    public FlatRoof(int height, int length, int width, Material cladding) {
        super(height, length, width, true, cladding, TILTTODEGREE, Category.flatRoof);
    }

}
