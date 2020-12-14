package domain.construction;

public class UsersChoice {
    private final int width;
    private final int length;
    private String roofChoice;
    private boolean shedOrNo;
    private boolean claddingChoice;

    public UsersChoice(int width, int length, String roofChoice, Integer shedOrNo, Integer claddingChoice) { //TODO skriv attributer til tilsvarende brugerinput
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = convert(shedOrNo);
        this.claddingChoice = convert(claddingChoice);
    }

    public Boolean convert(Integer digilBoolean){
        if( digilBoolean.equals(1))
            return true;
        else if (digilBoolean.equals(0))
            return false;
        else{
            throw new IllegalArgumentException("Dette kan ikke konverteres til noget der er der eller ikke er (boolean"); //TODO
        }
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public String getRoofChoice() {
        return roofChoice;
    }

    public boolean isShedOrNo() {
        return shedOrNo;
    }

    public boolean isCladdingChoice() {
        return claddingChoice;
    }
}
