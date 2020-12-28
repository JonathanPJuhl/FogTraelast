package domain.construction.shed;

import domain.construction.*;
import domain.construction.carport.Carport;
import domain.material.Material;

import java.util.ArrayList;

public class Shed extends ConstructionPart {
    private final int heigth;
    private final Construction construction;

    public Shed(UsersChoice usersChoice, Construction construction) {
        super(usersChoice.getShedLength(), usersChoice.getShedwidth(), Category.Shed);
        this.heigth = construction.getCarport().getHeight();
        this.construction = construction;
    }

    public Cladding[] addCladdingToShed(Material material, Carport carport){
        Cladding[] wallsForShed = new Cladding[4];

        Cladding claddingBack = new Cladding(getWidth(), carport.getHeight(), Category.Carport,material);
        Cladding claddingFront = new Cladding(getWidth(), carport.getHeight(), Category.Carport,material);
        Cladding claddingSide = new Cladding(getLength(), carport.getHeight(), Category.Carport, material);

        wallsForShed[0] = claddingBack;
        wallsForShed[1] = claddingSide;
        wallsForShed[2] = claddingSide;
        wallsForShed[3] = claddingFront;

        return wallsForShed;
    }

    public ArrayList<ConstructionPart> addCladding(Cladding[] claddingWalls){
        ArrayList<ConstructionPart> claddingShed = new ArrayList();

        for (Cladding claddingWall : claddingWalls ) {
            claddingShed.add(claddingWall);
        }
        return claddingShed;
    }

    public int getHeigth() {
        return heigth;
    }

    @Override
    public String toString() {
        return "Shed{" +
                "length=" + getLength() +
                ", width=" + getWidth() +
                ", category=" + getCategory() +
                '}';
    }
}
