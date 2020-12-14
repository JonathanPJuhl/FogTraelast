package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;

public class Construction extends UsersChoice {


    private Roof roof;
    private Carport carport;
    private Shed shed;
    private Cladding cladding;


    /*public Construction(int width, int length, String roofChoice, Integer shedOrNo, Integer claddingChoice) { //TODO skriv attributer til tilsvarende brugerinput
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = convert(shedOrNo);
        this.claddingChoice = convert(claddingChoice);
    }*/

    public Construction(int width, int length, String roofChoice, Integer shedOrNo, Integer claddingChoice, Roof roof, Carport carport, Shed shed, Cladding cladding) {
        super(width, length, roofChoice, shedOrNo, claddingChoice);
        this.roof = roof;
        this.carport = carport;
        this.shed = shed;
        this.cladding = cladding;
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

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof){
    this.roof = roof;
    }


    public Carport getCarport() {
        return carport;
    }

    public Shed getShed() {
        return shed;
    }

}
