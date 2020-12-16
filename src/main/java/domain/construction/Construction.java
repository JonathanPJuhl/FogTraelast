package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Construction {

    private Map partForConstruction;
    private Carport carport;
    private Roof roof;
    private Shed shed;

    public Construction( Roof roof, Carport carport) {
        this.carport = carport;
        this.roof = roof;
        partForConstruction = new HashMap();
        partForConstruction.put("roof", roof);
        partForConstruction.put("carport", carport);
        this.shed = (Shed) partForConstruction.get("shed");
    }

    public void addShed(Shed shed){
        partForConstruction.put("shed", shed);
    }

    public Map getPartForConstruction() {
        return partForConstruction;
    }

    public Carport getCarport() {
        return carport;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setCarport(Carport carport){
        this.carport = carport;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
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
