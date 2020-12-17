package domain.bom;

import domain.bom.calculators.*;
import domain.construction.Construction;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofFactory;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;
import domain.material.MaterialService;
import domain.material.MaterialType;

import java.util.HashMap;
import java.util.List;

public class BOMService {

    private final MaterialService materials;

    public static final int WOODDIFFERENCES = 300; //TODO HVordan gør jeg den private?

    private CarportMaterialCalculator carportMaterialCalculator;
    private CladdingMaterialCalculator claddingMaterialCalculator;
    private FlatRoofMaterialCalculator flatRoofMaterialCalculator;
    private PitchedRoofMaterialCalculator pitchedRoofMaterialCalculator;
    private ShedMaterialCalculator shedMaterialCalculator;

    public BOMService(MaterialService materials, RoofFactory constructionFactory) {
        this.materials = materials;
    }

    public BOM calculateBom(HashMap constructionList, UsersChoice usersChoice, RoofFactory constructionFactory) {
        Carport carport = constructionFactory.createCarport(usersChoice);
        Roof roof = constructionFactory.createRoof(usersChoice);
        Shed shed = constructionFactory.createShed(usersChoice);

        Construction construction = new Construction(roof, carport);

        if (usersChoice.getShedOrNo() == 1) {
            construction.setShed(shed);
        }

        BOM bom = new BOM();
        carportMaterialCalculator = new CarportMaterialCalculator(construction);
        claddingMaterialCalculator = new CladdingMaterialCalculator(construction);
        flatRoofMaterialCalculator = new FlatRoofMaterialCalculator(construction);
        pitchedRoofMaterialCalculator = new PitchedRoofMaterialCalculator(usersChoice,constructionList);
        shedMaterialCalculator = new ShedMaterialCalculator(construction);

        Material roofMaterialCladding = construction.getRoof().getCladding();
        List<Material> materialListRoof = materials.findMaterialsByCategory(construction.getRoof().getCategory());
        HashMap materialMapperRoofWood = new HashMap();

        for (Material woodMaterial: materialListRoof) {
            if (woodMaterial.getType().equals(MaterialType.wood)){
                materialMapperRoofWood.put(woodMaterial.getNametype()+" "+woodMaterial.getHeight(),woodMaterial);
            }
        }

        if (construction.getRoof().isFlat()) {
            bom.addItem(new BOMItem(roofMaterialCladding, flatRoofMaterialCalculator.getTrapezPlatez().quantityOfT600ForRoof(1090), flatRoofMaterialCalculator.getTrapezPlatez().getT600ROOFPLADELENGTH(),"tagplader monteres på spær"));
            bom.addItem(new BOMItem(roofMaterialCladding, flatRoofMaterialCalculator.getTrapezPlatez().quantityOfT300ForRoof(1090), flatRoofMaterialCalculator.getTrapezPlatez().getT300ROOFPLADELENGTH(),"tagplader monteres på spær"));
            bom.addItem(new BOMItem((Material) materialMapperRoofWood.get("spærtræ 25"), flatRoofMaterialCalculator.getRaft().quantity(),flatRoofMaterialCalculator.getRaft().length(), flatRoofMaterialCalculator.getRaft().description("Spær,monteres på rem")));
            bom.addItem(new BOMItem((Material) materialMapperRoofWood.get("trykimp. Brædt 25"), flatRoofMaterialCalculator.getUnderSternWidth().quantity(),flatRoofMaterialCalculator.getUnderSternWidth().length(), flatRoofMaterialCalculator.getUnderSternWidth().description("understernbrædder til for & bag ende")));
            bom.addItem(new BOMItem((Material) materialMapperRoofWood.get("trykimp. Brædt 25"), flatRoofMaterialCalculator.getUnderSternLength().quantity()*2,flatRoofMaterialCalculator.getUnderSternLength().length(), flatRoofMaterialCalculator.getUnderSternLength().description("understernbrædder til siderne")));
            bom.addItem(new BOMItem((Material) materialMapperRoofWood.get("trykimp. Brædt 25"), flatRoofMaterialCalculator.getOverSternFront().quantity(),flatRoofMaterialCalculator.getOverSternFront().length(), flatRoofMaterialCalculator.getOverSternFront().description("oversternsbrædder til forenden")));
            bom.addItem(new BOMItem((Material) materialMapperRoofWood.get("trykimp. Brædt 25"), flatRoofMaterialCalculator.getOverSternside().quantity()*2,flatRoofMaterialCalculator.getOverSternside().length(), flatRoofMaterialCalculator.getOverSternside().description("oversternbrædder til siderne")));
        }else{
            bom.addItem(new BOMItem(roofMaterialCladding, pitchedRoofMaterialCalculator.amountOfRoofTiles(),pitchedRoofMaterialCalculator.getRoofTilesWidth(),"monteres på taglægter"));
            bom.addItem(new BOMItem((Material) materialMapperRoofWood.get(""), pitchedRoofMaterialCalculator.amountOfRoofTiles(),pitchedRoofMaterialCalculator.getRoofTilesWidth(),"monteres på taglægter"));
            //TODO
        }

//TODO
            return bom;
    }

}
