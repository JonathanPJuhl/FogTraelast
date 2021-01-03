package domain.orders;

import domain.bom.BOM;
import domain.bom.BOMItem;

import java.util.ArrayList;

public class Economy {

    /*public double forOnlyMaterialPrice(){
        double price = 0.0;
        ArrayList<BOMItem> bomList = this.bom.getItems();
        for (BOMItem bomItem: bomList) {
            double lengthInMeter = bomItem.getLength()/1000.00;
            //prisen er pr meter fra db
            price =+ bomItem.getMaterial().getPrice()*lengthInMeter*bomItem.getQuantity();
        }
        return price;
    }*/

    public double withCoverage(int coverageInProcent, double priceForMaterials){
        double price;
        double coverageInDecimal= (coverageInProcent/100.00)+1.00;
        price = priceForMaterials*coverageInDecimal;
        return price;
    }
}
