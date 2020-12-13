package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;

public class Construction {

    private final int width;
    private final int length;
    private Roof roof;
    private Carport carport;
    private Shed shed;
    private Cladding cladding;
    private String roofChoice;
    private boolean shedOrNo;
    private boolean claddingChoice;

    public Construction(int width, int length, String roofChoice, int shedOrNo, int claddingChoice) { //TODO skriv attributer til tilsvarende brugerinput
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = convert(shedOrNo);
        this.claddingChoice = convert(claddingChoice);
    }

    public Construction(int width, int length, Roof roof, Carport carport, Shed shed, Cladding cladding) {
        this.width = width;
        this.length = length;
        this.roof = roof;
        this.carport = carport;
        this.shed = shed;
        this.cladding = cladding;
    }

    public Boolean convert(int digilBoolean){
        if( digilBoolean == 1)
           return true;
        else{
            return false;
        }
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof){
    this.roof = roof;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Carport getCarport() {
        return carport;
    }

}
