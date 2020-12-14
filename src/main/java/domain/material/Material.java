package domain.material;

import domain.construction.Category;

public class Material {

    private final int id;
    private String nametype;
    private String color;
    private final double price;
    private final MaterialType type;
    private Category category;
    private int height;


    public Material(int id, String nametype, String color, double price, MaterialType type, Category category, int height) {
        this.id = id;
        this.nametype = nametype;
        this.color = color;
        this.price = price;
        this.type = type;
        this.category = category;
        this.height = height;
    }

/*
    private String nameConstructor(String nameType, int width, String color, String enhed) throws IllegalFormatException {
        if (enhed.equals("mm") && color.equals(null)) {
            return nameType + " " + thickness + "X" + width + " mm.";
        } else if (enhed.equals("mm") && !(color.equals(null))) {
            return nameType + " " + color + " " + width + " mm.";
        } else if (enhed.equals("stk") && !(color.equals(null))) {
            return nameType + " " + color + " " + thickness + "X" + width + " mm.";
        } else {
            throw new IllegalArgumentException("There is no such material name with that format"); //TODO IllegalFormatException?
        }
    }

 */

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nametype='" + nametype + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", category=" + category +
                ", height=" + height +
                '}';
    }
}
