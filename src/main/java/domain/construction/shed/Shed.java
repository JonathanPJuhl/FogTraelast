package domain.construction.shed;

import domain.construction.*;
import domain.construction.carport.Carport;
import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Shed extends ConstructionPart {
    private final int length;
    private final int width;
    private final Category category;
    private final int heigth;
    Construction construction;

    public Shed(UsersChoice usersChoice, Construction construction) {
        this.length = usersChoice.getShedLength();
        this.width = usersChoice.getShedwidth();
        this.category = Category.Shed;
        this.heigth = construction.getCarport().getHeight();
    }

    public Cladding[] addCladdingToShed(Material material, Carport carport){
        Cladding[] wallsForShed = new Cladding[4];

        Cladding claddingBack = new Cladding(width, carport.getHeight(), Category.Carport,material);
        Cladding claddingFront = new Cladding(width, carport.getHeight(), Category.Carport,material);
        Cladding claddingSide = new Cladding(length, carport.getHeight(), Category.Carport, material);

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

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Category getCategory() {
        return category;
    }

    public int getHeigth() {
        return heigth;
    }

    @Override
    public String toString() {
        return "Shed{" +
                "length=" + length +
                ", width=" + width +
                ", category=" + category +
                '}';
    }
}
