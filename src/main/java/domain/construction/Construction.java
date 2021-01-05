package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Construction {

    private HashMap<Category, ConstructionPart> partForConstruction;
    private Carport carport;
    private Roof roof;

    public Construction(Roof roof, Carport carport) {
        this.carport = carport;
        this.roof = roof;
        partForConstruction = new HashMap();
        partForConstruction.put(roof.getCategory(), roof);
        partForConstruction.put(carport.getCategory(), carport);
    }

    public void addShed(Shed shed){
        partForConstruction.put(shed.getCategory(), shed);
    }

    public void addCladding(Cladding[] claddings){
        partForConstruction.put(claddings[0].getCategory(), claddings[0]);
        partForConstruction.put(claddings[1].getCategory(), claddings[1]);
        partForConstruction.put(claddings[2].getCategory(), claddings[2]);
    }

    public HashMap<Category, ConstructionPart> getPartForConstruction() {
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





}
