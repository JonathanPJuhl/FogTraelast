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

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", nametype='" + nametype + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", height=" + height +
                ", overlap=" + overlap +
                '}';
    }
}