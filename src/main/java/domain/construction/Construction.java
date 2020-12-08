package domain.construction;

import domain.construction.Roof.Roof;

public class Construction {

    private final int width;
    private final int length;
    private final Roof roof;


    public Construction(int width, int length, Roof roof) {
        this.width = width;
        this.length = length;
        this.roof = roof;
    }

    public Roof getRoof() {
        return roof;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
