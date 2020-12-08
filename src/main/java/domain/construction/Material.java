package domain.construction;

import java.util.IllegalFormatException;

public class Material {

    private String nametype;
    private int width;
    private String color;
    private final double price;
    private final String type;
    private String category;
    private final String name;
    private int thickness;

    public Material(String nametype, int width, String color, double price, String type, String category, int thickness, String enhed) throws IllegalFormatException {
        this.nametype = nametype;
        this.width = width;
        this.color = color;
        this.price = price;
        this.type = type;
        this.category = category;
        this.name = nameConstructor(nametype, width, color, enhed);
        this.thickness = thickness;
    }

    //TODO Må man lave to constructors ?
    public Material(double price, String type, String name) {
        this.price = price;
        this.type = type;
        this.name = name;
    }

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
}
