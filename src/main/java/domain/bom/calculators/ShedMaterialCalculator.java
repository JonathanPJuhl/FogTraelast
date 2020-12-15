package domain.bom.calculators;

import domain.construction.carport.Carport;

import java.util.List;

public class ShedMaterialCalculator {

    private CarportMaterialCalculator carportMaterialCalculator = new CarportMaterialCalculator();//TODO
    private final int DOORWIDTH = 1000;
    private final int POSTSIZE = carportMaterialCalculator.getPOSTWIDTH();

    public int sidePostAmount(int size){
        return carportMaterialCalculator.sidePostAmount(size);
    }

    public int sidePostAmountForShed(int width) {
        //method cunts posts starting from the door not from the very first post. That missing post comes in door calculation
        width = width - DOORWIDTH-POSTSIZE/2;

        return sidePostAmount(width);
    }
}
