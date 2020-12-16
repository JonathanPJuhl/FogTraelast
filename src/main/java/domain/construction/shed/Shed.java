package domain.construction.shed;

import domain.construction.*;
import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Shed extends ConstructionPart {
    private final int length;
    private final int width;
    private final int height;
    private final Category category;

    public Shed(Construction construction) {
        this.length = construction.getShedLength();
        this.width = construction.getWidth();
        this.height = construction.getCarport().getHeight();
        this.category = Category.Shed;
    }

    public Cladding[] addCladdingToShed(Material material){
        Cladding[] wallsForShed = new Cladding[4];

        Cladding claddingBack = new Cladding(width, height, Category.Carport,material);
        Cladding claddingFront = new Cladding(width, height, Category.Carport,material);
        Cladding claddingSide = new Cladding(length, height, Category.Carport, material);

        wallsForShed[0] = claddingBack;
        wallsForShed[1] = claddingSide;
        wallsForShed[2] = claddingSide;
        wallsForShed[3] = claddingFront;

        return wallsForShed;
    }

    public List<ConstructionPart> addCladding(Cladding[] claddingWalls){
        List<ConstructionPart> claddingShed = new ArrayList();

        for (Cladding claddingWall : claddingWalls ) {
            claddingShed.add(claddingWall);
        }
        return claddingShed;
    }

}
