package domain.construction.Roof;

import domain.construction.Material;

public class PitchedRoof extends Roof {

    public PitchedRoof(int height, int length, int width, Material cladding, int angle) {
        super(height, length, width, false, cladding, angle);
    }


}
