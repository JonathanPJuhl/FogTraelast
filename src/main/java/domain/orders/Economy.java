package domain.orders;

import domain.bom.BOM;
import domain.bom.BOMItem;

import java.util.ArrayList;

public class Economy {
    public Economy() {
    }

    public double withCoverage(int coverageInProcent, double priceForMaterials){
        double price;
        double coverageInDecimal= (coverageInProcent/100.00)+1.00;
        price = priceForMaterials*coverageInDecimal;
        return price;
    }
}
