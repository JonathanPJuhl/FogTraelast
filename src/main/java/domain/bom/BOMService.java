package domain.bom;

import domain.bom.calculators.*;
import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.Roof.Roof;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;
import domain.material.MaterialService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public BOM calculateBom(HashMap constructionList, UsersChoice usersChoice) {
        Carport carport = (Carport)constructionList.get("carport");
        Shed shed = (Shed) constructionList.get("shed");
        Roof roof = (Roof) constructionList.get("roof");

        Construction construction = new Construction(roof, carport);

        if (!(shed == null)) {
            construction.setShed(shed);
        }

        BOM bom = new BOM();
        carportMaterialCalculator = new CarportMaterialCalculator(construction);
        claddingMaterialCalculator = new CladdingMaterialCalculator(construction);
        flatRoofMaterialCalculator = new FlatRoofMaterialCalculator(construction);
        pitchedRoofMaterialCalculator = new PitchedRoofMaterialCalculator(usersChoice,constructionList);
        shedMaterialCalculator = new ShedMaterialCalculator(construction);

        Material roofMaterialCladding = construction.getRoof().getCladding();

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
