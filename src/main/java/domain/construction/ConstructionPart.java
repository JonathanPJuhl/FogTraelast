package domain.construction;

public class ConstructionPart {

    private final int length;
    private final int width;
    private final Category category;

    public ConstructionPart(int length, int width, Category category) {
        this.length = length;
        this.width = width;
        this.category = category;
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
}
