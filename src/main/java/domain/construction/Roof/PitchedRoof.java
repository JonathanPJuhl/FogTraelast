package domain.construction.Roof;

import domain.construction.Cover;

public class PitchedRoof extends Roof {

    public PitchedRoof(int height, int length, int width, Cover cladding, int angle, String color) {
        super(height, length, width, false, cladding, angle, color);
    }
}
