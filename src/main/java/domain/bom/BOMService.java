package domain.bom;

import domain.bom.calculators.*;
import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.Roof.Roof;
import domain.construction.Roof.ConstructionFactory;
import domain.construction.Roof.RoofSizeCalculator;
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

    private CarportMaterialCalculator carportMaterialCalculator;
    private CladdingMaterialCalculator claddingMaterialCalculator;
    private FlatRoofMaterialCalculator flatRoofMaterialCalculator;
    private PitchedRoofMaterialCalculator pitchedRoofMaterialCalculator;
    private ShedMaterialCalculator shedMaterialCalculator;

    private Material claddingMaterial;

    public BOMService(MaterialService materials) {
        this.materials = materials;
    }

    public BOM calculateBom(HashMap constructionList, UsersChoice usersChoice, ConstructionFactory constructionFactory) {

        //Bygger construction ud fra UserChoice og opretter beregnere
        Carport carport = constructionFactory.createCarport(usersChoice);
        Roof roof = constructionFactory.createRoof(usersChoice);

        Construction construction = constructionFactory.createConstruction(roof,carport);

        RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();
        carportMaterialCalculator = new CarportMaterialCalculator(construction);
        flatRoofMaterialCalculator = new FlatRoofMaterialCalculator(construction, roofSizeCalculator);
        pitchedRoofMaterialCalculator = new PitchedRoofMaterialCalculator(usersChoice,constructionList);

        if (usersChoice.getCladdingChoice() == 1) {
            claddingMaterial = usersChoice.getShedAndCarportCladding();
            carport.threeWallswithCladding(usersChoice.getShedAndCarportCladding());
            claddingMaterialCalculator = new CladdingMaterialCalculator(claddingMaterial);
        }
        if (usersChoice.getShedOrNo() == 1) {
            Shed shed = constructionFactory.createShed(usersChoice,construction);
            construction.addShed(shed);
            shedMaterialCalculator = new ShedMaterialCalculator(construction);
        }

        //Nyt stykliste objekt oprette
        BOM bom = new BOM();

        //Tag materialer defineres med hhv. tagbeklædning og andre tagmaterialer der er nødvendig for det specifikke tag (udover carport)
        Material roofMaterialCladding = usersChoice.getRoofCladding();
        List<Material> materialListRoof = materials.findMaterialsByCategory(construction.getRoof().getCategory());

        HashMap materialMapRoofWood = new HashMap();

        for (Material woodMaterial: materialListRoof) {
            if (woodMaterial.getType().equals(MaterialType.wood.getDanishName())) {
                materialMapRoofWood.put(woodMaterial.getNametype() + " " + woodMaterial.getHeight(), woodMaterial);
            }
        }

        int width = 0; //TODO !!!!!!!!!!!!! SÆT DEN RIGTIGE BREDDE PÅ ALLE STYKLISTEMATERIALER NÅR I NÅR SÅ LANGT !

        //Tag materialer tilføjes til stykliste alt efter tagtype som brugeren har valgt
        if (construction.getRoof().isFlat()) {
            bom.addItem(new BOMItem(roofMaterialCladding, flatRoofMaterialCalculator.getTrapezPlates().quantityOfT600ForRoof(1090), flatRoofMaterialCalculator.getTrapezPlates().getT600ROOFPLADELENGTH(),"tagplader monteres på spær", width));
                bom.addItem(new BOMItem(roofMaterialCladding, flatRoofMaterialCalculator.getTrapezPlates().quantityOfT300ForRoof(1090), flatRoofMaterialCalculator.getTrapezPlates().getT300ROOFPLADELENGTH(), "tagplader monteres på spær", width));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("spærtræ ubh. 45"), flatRoofMaterialCalculator.getRaft().quantity(),flatRoofMaterialCalculator.getRaft().length(), flatRoofMaterialCalculator.getRaft().description("Spær, monteres på rem"), width));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getUnderSternWidth().quantity()*2,flatRoofMaterialCalculator.getUnderSternWidth().length(), flatRoofMaterialCalculator.getUnderSternWidth().description("understernbrædder til for & bag ende"), width));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getUnderSternLength().quantity()*2,flatRoofMaterialCalculator.getUnderSternLength().length(), flatRoofMaterialCalculator.getUnderSternLength().description("understernbrædder til siderne"), width));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getOverSternFront().quantity(),flatRoofMaterialCalculator.getOverSternFront().length(), flatRoofMaterialCalculator.getOverSternFront().description("oversternsbrædder til forenden"), width));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getOverSternSide().quantity()*2,flatRoofMaterialCalculator.getOverSternSide().length(), flatRoofMaterialCalculator.getOverSternSide().description("oversternbrædder til siderne"), width));
        }else{
            bom.addItem(new BOMItem(roofMaterialCladding, pitchedRoofMaterialCalculator.amountOfRoofTiles(),pitchedRoofMaterialCalculator.getRoofTilesWidth(),"monteres på taglægter", width));
            //bom.addItem(new BOMItem((Material) materialMapRoofWood.get(""), pitchedRoofMaterialCalculator);
            //TODO
        }

        if (usersChoice.getCladdingChoice() == 1) {
        bom.addItem(new BOMItem(claddingMaterial, claddingMaterialCalculator.quantity(), claddingMaterialCalculator.length(), claddingMaterialCalculator.description("Beklædning af væg og skur"), width));
        //TODO
        }

        if (usersChoice.getShedOrNo() == 1) {
            List<Material> materialListShed = materials.findMaterialsByCategory(Category.Shed);
            HashMap shedMaterialMap = new HashMap();
            for (Material shedMaterial: materialListShed) {
                    shedMaterialMap.put(shedMaterial.getNametype()+" "+shedMaterial.getHeight(),shedMaterial);
            }

            bom.addItem(new BOMItem((Material) shedMaterialMap.get("trykimp. Brædt 25"), shedMaterialCalculator.getStern().quantity(), shedMaterialCalculator.getStern().length(), shedMaterialCalculator.getStern().description("Sternbrædder til siderne Skur del (deles)"), width));
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("trykimp. Stolpe 97"), shedMaterialCalculator.getPostAdded().quantity(), shedMaterialCalculator.getPostAdded().length(), shedMaterialCalculator.getPostAdded().description("Stolper nedgraves 90 cm. i jord + skråstiver"), width));
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("spærtræ ubh. 45"), shedMaterialCalculator.getRim().quantity(), shedMaterialCalculator.getRim().length(), shedMaterialCalculator.getRim().description("Remme i sider, sadles ned i stolper Skur del"), width));
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("reglar ubh 45"), shedMaterialCalculator.getQuanityLoesHolterFront(), shedMaterialCalculator.getLengthLoesHolterFront(), shedMaterialCalculator.getLoesHolterBack().description("Remme i sider, sadles ned i stolper Skur del"), width));
            //TODO
        }

        //TODO
            return bom;
    }

}
