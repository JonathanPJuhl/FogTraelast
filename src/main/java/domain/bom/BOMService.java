package domain.bom;

import domain.bom.calculators.*;
import domain.construction.Category;
import domain.construction.Construction;
import domain.material.Material;
import domain.material.MaterialService;

import java.util.List;

public class BOMService {

    private final MaterialService materials;

    private CarportMaterialCalculator carportMaterialCalculator;
    private CladdingMaterialCalculator claddingMaterialCalculator;
    private FlatRoofMaterialCalculator flatRoofMaterialCalculator;
    private PitchedRoofMaterialCalculator pitchedRoofMaterialCalculator;
    private ShedMaterialCalculator shedMaterialCalculator;

    public BOMService(MaterialService materials) {
        this.materials = materials;
    }

    public BOM calculateBom(Construction construction) {
        BOM bom = new BOM();
        carportMaterialCalculator = new CarportMaterialCalculator(construction);
        claddingMaterialCalculator = new CladdingMaterialCalculator(construction);
        flatRoofMaterialCalculator = new FlatRoofMaterialCalculator(construction);
        pitchedRoofMaterialCalculator = new PitchedRoofMaterialCalculator(construction);

        Material roofMaterialCladding = construction.getRoofCladding();

        if (construction.getRoof().isFlat()) {
            bom.addItem(new BOMItem(roofMaterialCladding, flatRoofMaterialCalculator.quantityOfT600ForRoof(1090), flatRoofMaterialCalculator.getT600ROOFPLADELENGTH(),"tagplader monteres på spær"));
            bom.addItem(new BOMItem(roofMaterialCladding, flatRoofMaterialCalculator.quantityOfT300ForRoof(1090), flatRoofMaterialCalculator.getT300ROOFPLADELENGTH(),"tagplader monteres på spær"));
        }
        else{
            bom.addItem(new BOMItem(roofMaterialCladding, pitchedRoofMaterialCalculator.getRoofTilesWidth(),pitchedRoofMaterialCalculator.amountOfRoofTiles(),"monteres på taglægter"));
        }
            return bom;
    }

}
