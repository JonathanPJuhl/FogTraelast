package domain.bom;

public class BOMFromDB {
private final int bomID;
private final int orderID;
private final int materialID_By_Category;
private final int length;
private final int width;
private final String description;
private final int quantity;


    public BOMFromDB(int bomID, int orderID, int materialID_by_category, int length, int width, String description, int quantity) {
        this.bomID = bomID;
        this.orderID = orderID;
        this.materialID_By_Category = materialID_by_category;
        this.length = length;
        this.width = width;
        this.description = description;
        this.quantity = quantity;
    }

    public int getBomID() {
        return bomID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getMaterialID_By_Category() {
        return materialID_By_Category;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}
