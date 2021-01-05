package domain.bom;

import java.util.ArrayList;

public class BOM {

    private final ArrayList<BOMItem> items = new ArrayList<>();

    public void addItem(BOMItem bomItem) {
        items.add(new BOMItem(bomItem.getMaterial(), bomItem.getQuantity(), bomItem.getLength(), bomItem.getDescription(), bomItem.getWidth(), bomItem.getCategory()));
    }

    public ArrayList<BOMItem> getItems() {
        return items;
    }
}
