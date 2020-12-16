package domain.construction.carport;

import domain.construction.Category;
import domain.construction.Cladding;
import domain.construction.ConstructionPart;
import domain.construction.UsersChoice;
import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Carport extends ConstructionPart {

    private final int height;
    private final int length;
    private final int width;
    private final int fromRoofToCarportMin;

    public Carport(UsersChoice usersChoice) {
        this.fromRoofToCarportMin = 180;
        this.height = 2500; //TODO
        this.length = usersChoice.getLength()-fromRoofToCarportMin;
        this.width = usersChoice.getWidth()-fromRoofToCarportMin;

    }

    public Cladding[] threeWallswithCladding(Material material){
        Cladding[] threeWalls = new Cladding[3]; //TODO

        Cladding claddingBack = new Cladding(width, height, Category.Carport,material);
        Cladding claddingSide = new Cladding(length, height, Category.Carport, material);

        threeWalls[0] = claddingBack;
        threeWalls[1] = claddingSide;
        threeWalls[2] = claddingSide;

        return threeWalls;
    }

    public List<ConstructionPart> addCladding(Cladding[] claddingWalls){
        List<ConstructionPart> claddingCarport = new ArrayList();

        for (Cladding claddingWall : claddingWalls ) {
        claddingCarport.add(claddingWall);
        }
        return claddingCarport;
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

}
