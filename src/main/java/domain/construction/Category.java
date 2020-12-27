package domain.construction;

public enum Category {

    Flat("fladt tag"),
    Pitched("tag med rejsning"),
    Carport ("carport"),
    Shed("skur"),
    Cladding("beklædning");

    Category(String danish) {
    this.danish = danish;
    }

    private String danish;

    public String getDanishName(){
        return danish;
    };
}
