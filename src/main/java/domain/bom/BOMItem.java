package domain.bom;

import domain.material.Material;

public class BOMItem {
    private final Material material;
    private final int quantity;
    private final int length;
    private final String description;
    private final int width;


    public BOMItem(Material material, int quantity, int length, String description, int width) {
        this.material = material;
        this.length = length;
        this.quantity = quantity;
        this.description = description;
        this.width = width;
    }

    public Material getMaterial() {
        return material;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public int getWidth() {
        return width;
    }
}
