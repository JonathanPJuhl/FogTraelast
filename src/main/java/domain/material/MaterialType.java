package domain.material;

public enum MaterialType {

    wood("Træ"),
    roofPlades("Tagplade"),
    roofTiles("Tagsten"),
    screwsAndFittings("Skruer & beslag"),
    other ("Andet");

    private final String danish;

    MaterialType(String danish) {
        this.danish = danish;
    }

    public String getDanishName(){
        return danish;
    };
}
