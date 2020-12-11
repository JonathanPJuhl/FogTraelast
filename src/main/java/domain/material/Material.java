package domain.material;

import domain.construction.Category;

import java.util.IllegalFormatException;

public class Material {

    private String nametype;
    private int width;
    private String color;
    private final double price;
    private final MaterialType type;
    private Category category;
    private int height;

    //TODO Må man lave to constructors ?

    public Material(String nametype, int width, String color, double price, MaterialType type, Category category, int height) {
        this.nametype = nametype;
        this.width = width;
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
}
