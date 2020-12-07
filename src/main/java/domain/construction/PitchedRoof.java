package domain.construction;

import java.util.List;

public class PitchedRoof extends Roof{

    private final int vinkel;

    public PitchedRoof(int height, int length, int width, Cover cladding, int vinkel) {
        super(height, length, width, false, cladding);
        this.vinkel = vinkel;
    }
}
