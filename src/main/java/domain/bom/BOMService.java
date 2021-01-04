package domain.bom;

import domain.bom.calculators.*;
import domain.construction.*;
import domain.construction.Roof.*;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;
import domain.material.MaterialService;
import domain.material.MaterialType;

import java.util.*;

public class BOMService {

    private final MaterialService materials;

    private CarportMaterialCalculator carportMaterialCalculator;
    private CladdingMaterialCalculator claddingMaterialCalculator;
    private FlatRoofMaterialCalculator flatRoofMaterialCalculator;
    private PitchedRoofMaterialCalculator pitchedRoofMaterialCalculator;
    private ShedMaterialCalculator shedMaterialCalculator;
    private NoWasteHelper noWasteHelper = new NoWasteHelper(); //TODO lav static?
    RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();

    private Material claddingMaterial;

    public BOMService(MaterialService materials) {
        this.materials = materials;
    }

    public BOM calculateBom(HashMap<Category, ConstructionPart> constructionList, TreeSet<Integer> widths, TreeSet<Integer> lengths/*, UsersChoice usersChoice, ConstructionFactory constructionFactory*/) {

        //Nyt stykliste objekt oprette
        BOM bom = new BOM();

        List<Material> materialListRoof;
        HashMap materialMapRoofWood = new HashMap();


        Carport carport = (Carport) constructionList.get(Category.Carport);
        FlatRoof flatRoof = (FlatRoof) constructionList.get(Category.Flat);
        PitchedRoof pitchedRoof = (PitchedRoof) constructionList.get(Category.Pitched);
        Roof roof;
        Shed shed = (Shed) constructionList.get(Category.Shed);
        if(shed != null){
            claddingMaterial = shed.getCladding();
        }else if(carport != null){
            claddingMaterial = carport.getCladding();
        }

        //Tag materialer defineres med hhv. tagbeklædning og andre tagmaterialer der er nødvendig for det specifikke tag (udover carport)
        if (flatRoof==null) {
            materialListRoof = materials.findMaterialsByCategory(pitchedRoof.getCategory());
            pitchedRoofMaterialCalculator = new PitchedRoofMaterialCalculator(constructionList);
            roof = pitchedRoof;

        } else {
            materialListRoof = materials.findMaterialsByCategory(flatRoof.getCategory());
            flatRoofMaterialCalculator = new FlatRoofMaterialCalculator(flatRoof, roofSizeCalculator, noWasteHelper);
            roof = flatRoof;
        }

        for (Material woodMaterial : materialListRoof) {
            if (woodMaterial.getType().equals(MaterialType.wood.getDanishName())) {
                materialMapRoofWood.put(woodMaterial.getNametype() + " " + woodMaterial.getHeight(), woodMaterial);

            }
        }

        //Tag materialer tilføjes til stykliste alt efter tagtype som brugeren har valgt
        int width = 0;
        int quantity = 0;
        int length = 0;

        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(roof.isFlat(), roof.getLength(), roof.getDegree());
        int roofWidthSurface = roofSizeCalculator.roofWidthSurface(roof.isFlat(), roof.getWidth(), roof.getDegree());

        int[] meassuresAndQnty;
        //Hvis taget er fladt
        if (roof.isFlat()) {
            HashMap<Integer, HashMap<Material, int[]>> trapezPlates = noWasteHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofWidthSurface, roof.getCladding(), widths, lengths);
            for (int i = 1; i <= trapezPlates.size(); i++) {
                for (Map.Entry meassuresMaterialAndQnty : trapezPlates.get(i).entrySet()) {
                    meassuresAndQnty = (int[]) meassuresMaterialAndQnty.getValue();
                    length = meassuresAndQnty[0];
                    width = meassuresAndQnty[1];
                    quantity = meassuresAndQnty[2];
                    if (quantity != 0)
                        bom.addItem(new BOMItem(roof.getCladding(), quantity, length, "tagplader monteres på spær", width, Category.Flat));
                }
            }
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getUnderSternWidth().quantity() * 2, flatRoofMaterialCalculator.getUnderSternWidth().length(), flatRoofMaterialCalculator.getUnderSternWidth().description("understernbrædder til for & bag ende"), width, Category.Flat));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getUnderSternLength().quantity() * 2, flatRoofMaterialCalculator.getUnderSternLength().length(), flatRoofMaterialCalculator.getUnderSternLength().description("understernbrædder til siderne"), width, Category.Flat));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getOverSternFront().quantity(), flatRoofMaterialCalculator.getOverSternFront().length(), flatRoofMaterialCalculator.getOverSternFront().description("oversternsbrædder til forenden"), width, Category.Flat));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), flatRoofMaterialCalculator.getOverSternSide().quantity() * 2, flatRoofMaterialCalculator.getOverSternSide().length(), flatRoofMaterialCalculator.getOverSternSide().description("oversternbrædder til siderne"), width,Category.Flat));
        } else {
            //Hvis taget har rejsning
            int tilesMaterialWidth = 200;
            int tilesMateriallength = 200;
            int roofGableMatertialWidth = 100;
            int roofSternMatertialWidth = 100;
            int roofLathMaterialWidth = 73;
            int roofVindskedeMaterialWidth = 150;

            bom.addItem(new BOMItem(roof.getCladding(), pitchedRoofMaterialCalculator.amountOfRoofTiles(), tilesMateriallength, "monteres på taglægter", tilesMaterialWidth, Category.Pitched));
            bom.addItem(new BOMItem(roof.getCladding(), pitchedRoofMaterialCalculator.quantityRygsten(), tilesMateriallength, "monteres på toplægte med medfølgende beslag se tagstens vejledning", tilesMaterialWidth , Category.Pitched));

            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("Færdigskåret (byg-selv-spær) 8 stk. 30"), pitchedRoofMaterialCalculator.spærQuantity(), pitchedRoofMaterialCalculator.spærFullQuantityOfPlanksTotal(), "byg-selv spær (skal samles)",width, Category.Pitched));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 19"), pitchedRoofMaterialCalculator.gavlOverlayQuantity(roofGableMatertialWidth,pitchedRoof.getWidth()), pitchedRoof.getWidth(),"beklædning af gavle 1 på 2",roofGableMatertialWidth, Category.Pitched));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("taglægte T1 38"), pitchedRoofMaterialCalculator.qntyOfRoofFirstLathBothSides(), pitchedRoof.getWidth(),"toplægte til montering af rygsten lægges i toplægte holder", roofLathMaterialWidth, Category.Pitched));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("taglægte T1 38"), pitchedRoofMaterialCalculator.qntyOfRoofLathBothSides(), pitchedRoof.getWidth(),"til montering på spær, "+ ((pitchedRoofMaterialCalculator.qntyOfRoofLathBothSides())/2) + " rækker lægter på hver skiftevis 1 hel & 1 halv lægte", roofLathMaterialWidth, Category.Pitched));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), pitchedRoofMaterialCalculator.amountOfVindskeder(), roofWidthSurface,"Vindskeder på rejsning",roofVindskedeMaterialWidth, Category.Pitched));
            bom.addItem(new BOMItem((Material) materialMapRoofWood.get("trykimp. Bræt 25"), pitchedRoofMaterialCalculator.amountOfStern(), roofLengthSurface,"Sternbrædder til siderne Carport del", roofSternMatertialWidth, Category.Pitched));
        }

        //Carport materiale - del
        carportMaterialCalculator = new CarportMaterialCalculator();

        int postWidth = 97;
        List<Material> materialListCarport = materials.findMaterialsByCategory(Category.Carport);
        HashMap caportMaterialMap = new HashMap();
        for (Material carportMaterial : materialListCarport) {
            caportMaterialMap.put(carportMaterial.getNametype() + " " + carportMaterial.getHeight(), carportMaterial);
        }

        int widthMaterialRem = 195;

        bom.addItem(new BOMItem((Material) caportMaterialMap.get( "trykimp. Stolpe 97"), carportMaterialCalculator.qntyPost(carport.getWidth(),carport.getLength()), carport.getHeight(),"Stolper nedgraves 90 cm. i jord + skråstiver", postWidth, Category.Carport));
        bom.addItem(new BOMItem((Material) caportMaterialMap.get("spærtræ ubh. 45"), carportMaterialCalculator.remQnty(), carportMaterialCalculator.remLengthFullLength(carport), "Remme i side, sadles ned i stolper Carport del", widthMaterialRem,Category.Carport));

        //evt. skur materiale - del
        if (shed != null) {
            CladdingMaterialCalculator claddingShedCalculatedWidths = new CladdingMaterialCalculator(widthMaterialRem, shed.getWidth(),carport.getHeight()); // Der skal være to af disse sider
            CladdingMaterialCalculator claddingShedCalculatedLengths = new CladdingMaterialCalculator(widthMaterialRem, shed.getLength(), carport.getHeight()); //Der skal være to af disse sider også
            shedMaterialCalculator = new ShedMaterialCalculator(constructionList);
            List<Material> materialListShed = materials.findMaterialsByCategory(Category.Shed);
            HashMap shedMaterialMap = new HashMap();
            for (Material shedMaterial : materialListShed) {
                shedMaterialMap.put(shedMaterial.getNametype() + " " + shedMaterial.getHeight(), shedMaterial);
            }
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("trykimp. Bræt 25"), shedMaterialCalculator.getStern().quantity(), shedMaterialCalculator.getStern().length(), shedMaterialCalculator.getStern().description("Sternbrædder til siderne - Skur del (deles)"), width, Category.Shed));
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("trykimp. Stolpe 97"), shedMaterialCalculator.getPostAdded().quantity(), shedMaterialCalculator.getPostAdded().length(), shedMaterialCalculator.getPostAdded().description("Stolper nedgraves 90 cm. i jord + skråstiver"), width, Category.Shed));
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("spærtræ ubh. 45"), shedMaterialCalculator.getRim().quantity(), shedMaterialCalculator.getRim().length(), shedMaterialCalculator.getRim().description("Remme i sider, sadles ned i stolper - Skur del"), width, Category.Shed));
            bom.addItem(new BOMItem((Material) shedMaterialMap.get("reglar ubh. 45"), shedMaterialCalculator.getQuanityLoesHolterFront(), shedMaterialCalculator.getLengthLoesHolterFront(), shedMaterialCalculator.getLoesHolterBack().description("Remme i sider, sadles ned i stolper - Skur del"), width, Category.Shed));
            bom.addItem(new BOMItem(claddingMaterial, claddingShedCalculatedLengths.quantity()*2, claddingShedCalculatedLengths.length(), claddingShedCalculatedLengths.description("Beklædning skur sider"), width, Category.Cladding));
            bom.addItem(new BOMItem(claddingMaterial, claddingShedCalculatedWidths.quantity()*2, claddingShedCalculatedLengths.length(), claddingShedCalculatedLengths.description("Beklædning skur foran og bagved - dør skæres til efter ønske om placering"), width, Category.Cladding));
        }
        Cladding cladding = (Cladding) constructionList.get(Category.Cladding);
        claddingMaterial = carport.getCladding();
        CladdingMaterialCalculator claddingMaterialCalculatorFront = new CladdingMaterialCalculator(widthMaterialRem, carport.getWidth(), carport.getHeight());
        CladdingMaterialCalculator claddingMaterialCalculatorSide = new CladdingMaterialCalculator(widthMaterialRem, carport.getLength(), carport.getHeight()); // To af disse sider er beklædt hvis man vælge vægge med beklædning

        if (carport.getCladding() != null) {
            bom.addItem(new BOMItem(claddingMaterial, claddingMaterialCalculatorFront.quantity(), claddingMaterialCalculatorFront.length(), claddingMaterialCalculatorFront.description("Beklædning af carport bag til"), widthMaterialRem, Category.Cladding));
            bom.addItem(new BOMItem(claddingMaterial, claddingMaterialCalculatorSide.quantity()*2, claddingMaterialCalculatorSide.length(), claddingMaterialCalculatorSide.description("Beklædning af carportvægge på siderne"), widthMaterialRem, Category.Cladding));
        }
//TODO
        return bom;

    }

}
