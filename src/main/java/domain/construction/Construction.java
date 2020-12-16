package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Construction extends UsersChoice {

    private final List<ConstructionPart> partForConstruction;
    private final Carport carport;
    private final Roof roof;

    public Construction(int width, int length, String roofChoice, Integer shedOrNo, Integer claddingChoice, Material roofCladding,
                        int degree, int shedLength, int shedwidth, Material shedAndCarportCladding, Carport carport, Roof roof) {
        super(width, length, roofChoice, shedOrNo, claddingChoice, roofCladding,degree,shedLength,shedwidth,shedAndCarportCladding);
        this.carport = carport;
        this.roof = roof;
        partForConstruction = new ArrayList();
    }

    public void addShed(Shed shed){
        partForConstruction.add(shed);
    }

    public List<ConstructionPart> getPartForConstruction() {
        return partForConstruction;
    }

    public Carport getCarport() {
        return carport;
    }

    public Roof getRoof() {
        return roof;
    }

    /*public Construction(int width, int length, String roofChoice, Integer shedOrNo, Integer claddingChoice) { //TODO skriv attributer til tilsvarende brugerinput
        this.width = width;
        this.length = length;
        this.roofChoice = roofChoice;
        this.shedOrNo = convert(shedOrNo);
        this.claddingChoice = convert(claddingChoice);
    }*/

    /*public Boolean convert(Integer digilBoolean){
        if( digilBoolean.equals(1))
           return true;
        else if (digilBoolean.equals(0))
            return false;
        else{
            throw new IllegalArgumentException("Dette kan ikke konverteres til noget der er der eller ikke er (boolean"); //TODO
        }
    }*/



}
