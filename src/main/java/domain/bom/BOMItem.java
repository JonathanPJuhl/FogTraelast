package domain.bom;

import domain.material.Material;

public class BOMItem {
    private final Material material;
    private final int width;
    private final int length;
    private final String description;

    public BOMItem(Material material, int width, int length, String description) {
        this.material = material;
        this.width = width;
        this.length = length;
        this.description = description;
    }

    public Material getMaterial() {
        return material;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }
}
