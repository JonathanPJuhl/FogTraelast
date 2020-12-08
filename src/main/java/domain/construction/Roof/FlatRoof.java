package domain.construction.Roof;

import domain.construction.Cover;

public class FlatRoof extends Roof {

    private static final double TILTTODEGREE = (double) Math.round(Math.toDegrees(Math.atan(3.0 / 100.0)));

    public FlatRoof(int height, int length, int width, Cover cladding, String color) {
        super(height, length, width, true, cladding, TILTTODEGREE, color);
    }

}
