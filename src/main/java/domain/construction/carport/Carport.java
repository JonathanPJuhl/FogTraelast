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
    private final int fromRoofToCarportMin;

    public Carport(UsersChoice usersChoice) {
        super(usersChoice.getLength()-180 ,usersChoice.getWidth(), Category.Carport);
        this.fromRoofToCarportMin = 180;
        this.height = 2500; //TODO
    }

    public Cladding[] threeWallswithCladding(Material claddingMaterial){

        Cladding[] threeWalls = new Cladding[3]; //TODO

        Cladding claddingBack = new Cladding(getWidth(), height, Category.Carport, claddingMaterial);
        Cladding claddingSide = new Cladding(getLength(), height, Category.Carport, claddingMaterial);

        threeWalls[0] = claddingBack;
        threeWalls[1] = claddingSide;
        threeWalls[2] = claddingSide;

        return threeWalls;
    }

    public List<Cladding> addCladding(Cladding[] claddingWalls){
        List<Cladding> claddingCarport = new ArrayList();

        for (Cladding claddingWall : claddingWalls ) {
            claddingCarport.add(claddingWall);
        }
        return claddingCarport;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Carport{" +
                "height=" + height +
                ", length=" + getLength() +
                ", width=" + getWidth()+
                ", fromRoofToCarportMin=" + fromRoofToCarportMin +
                '}';
    }
}