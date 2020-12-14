package domain.construction.shed;

import domain.construction.Category;
import domain.construction.Cladding;
import domain.construction.carport.Carport;

public class Shed {
    private final int length;
    private final int width;
    private final int height;
    private final Cladding cladding;
    private final int claddingQuanitaty;
    private final Category category;

    public Shed(int length, int width, Carport carport, Cladding cladding) {
        this.length = length;
        this.width = width;
        this.height = carport.getHeight();
        this.cladding = cladding;
        this.claddingQuanitaty = 4;
        this.category = Category.shed;
    }
}
