package domain.bom;

import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BOM {

    private final List<BOMItem> items = new ArrayList<>();

    public void addItem(BOMItem bomItem) {
        items.add(new BOMItem(bomItem.getMaterial(), bomItem.getWidth(), bomItem.getLength(), bomItem.getDescription()));
    }

}
