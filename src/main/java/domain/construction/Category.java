package domain.construction;

public enum Category {

    flatRoof("fladt tag"),
    pitchedRoof ("tag med rejsning"),
    carport ("carport"),
    shed("skur"),
    cladding("beklædning"),
    nothing(null);

    Category(String danish) {
    this.danish = danish;
    }

    private String danish;

    public String getDanishName(){
        return danish;
    };
}
