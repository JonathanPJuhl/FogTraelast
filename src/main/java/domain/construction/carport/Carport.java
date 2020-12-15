package domain.construction.carport;

import domain.construction.UsersChoice;


public class Carport {

    private final int height;
    private final int length;
    private final int width;
    private UsersChoice construction;
    private final int fromRoofToCarportMin;

    public Carport(UsersChoice usersChoice) { //TODO
        this.fromRoofToCarportMin = 180;
        this.height = 2500; //TODO
        this.construction = construction;
        this.length = construction.getLength();
        this.width = construction.getWidth();

    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getFromRoofToCarportMin() {
        return fromRoofToCarportMin;
    }
}
