package domain.material;

public class Material{

    private final int id;
    private final String nametype;
    private final String color;
    private final double price;
    private final String type;
    private final String category;
    private final int height;
    private final int overlap;


    public Material(int id, String nametype, String color, double price, String type, String category, int height, int shouldHaveOverlap) {
        this.id = id;
        this.nametype = nametype;
        this.color = color;
        this.price = price;
        this.type = type;
        this.category = category;
        this.height = height;
        this.overlap = shouldHaveOverlap;
    }

    public int getId() {
        return id;
    }

    public String getNametype() {
        return nametype;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getHeight() {
        return height;
    }

    public int getOverlap() {
        return overlap;
    }
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