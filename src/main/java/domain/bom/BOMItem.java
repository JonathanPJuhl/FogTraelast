package domain.bom;

import domain.material.Material;

public class BOMItem {
    private final Material material;
    private final int quantity;
    private final int length;
    private final String description;


    public BOMItem(Material material, int quantity, int length, String description) {
        this.material = material;
        this.length = length;
        this.quantity = quantity;
        this.description = description;
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
}
