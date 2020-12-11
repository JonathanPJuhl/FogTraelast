package domain.bom;

import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BOM {

    private final List<BomItem> items = new ArrayList<>();

    public void addItem(Material material, int size, int length, String description) {
        items.add(new BomItem());
    }

    public class BomItem {}

}
