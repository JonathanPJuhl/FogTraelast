package domain.bom.calculators;

import domain.construction.Category;
import domain.construction.ConstructionPart;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.material.Material;
import domain.material.MaterialType;

import java.util.*;


public class NoWaistHelper {


    public static void main(String[] args) {
        System.out.println("Du er fjollet");
        Material material = new Material(1, "Trapez", "pink", 20.00, MaterialType.roofPlades.getDanishName(), Category.Flat.getDanishName(), 10, 10);
        NoWaistHelper noWaistHelper = new NoWaistHelper();
        RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();
        UsersChoice usersChoice = new UsersChoice(300, 900, "Flat", 0, 0,
                material, 0, 0, 0, null);
        Roof flat = new FlatRoof(usersChoice, roofSizeCalculator);
        flat.toString();
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(600);
        numbers.add(300);
        HashMap<Material, Integer> input = new HashMap();
        input.put(material, 100);
        HashMap<Integer, HashMap<Material, int[]>> soonBOMItemMap = noWaistHelper.quantitiesInSquareAreaCalculated(flat, numbers, input);
        for (int i = 1; i < soonBOMItemMap.size(); i++) {
            for (Map.Entry m : soonBOMItemMap.get(i).entrySet()) {
                System.out.println(m.getKey().toString());
                System.out.println(m.getValue().toString());
                //TODO TIL TEST = opdel attributter her og sig assertequals
            }
        }

    }


    //TODO I hele programmet eller skriv i rapport - RET ALLE LISTS/MAPS/SETS TIL SPICFIKKE TYPER DER INDEHOLDER DISSE TYPER!!!!!!
    private final HashMap<Material, int[]> quantities = new HashMap<>();
    private final HashMap<Integer, HashMap<Material, int[]>> availableMaterialsAndMeasures = new HashMap<>();
    int materialLength;
    int materialWidth;
    int count = 0;
    int[] finalMaterialMeauseresAndQt = new int[3];
    int[] materialMeauseresAndQt = new int[3];
    int overlap;
    int lengthRest;
    int widthRest;
    int quantityLength = 0;
    int quantityWidth = 0;
    HashMap<Material, int[]> materialBeforeValue;
    HashMap<Integer, HashMap<Material, int[]>> mapOfQts = new HashMap<>();
    int[] meassures;
    int finalQuantity;
    Material material;

    public HashMap quantitiesInSquareAreaCalculated(ConstructionPart constructionPart, TreeSet<Integer> lengthOptions, HashMap<Material, Integer> materialAndWidths) {
        //TODO - Note til rapport
        //Vi mapper målene med Materiale(navn) som key, for senere at kunne hente dette specifikke Materiales længder og bredder, som der vil være på evt. lager;
        //Vi har fået at vide af vores product owvner, FOG, på andet springmøde (i virkeligheden første møde med læreren, Christian) at der er f.eks. alle mål med 30 cm mellem
        //størrelserne på diverse længder (Gælder dog kun træ materialer). Dog er bredderne forskellige afhængigt af materiale. Et type materiale kan dog godt fås i forskellige bredder;


        //Mapper længdemuligheder med materialer og deres bredder - Dette bliver sorteret i rækkefølge med længderne i det længste materiale (og dertil vidde) først
        for (Map.Entry materialAndWidth : materialAndWidths.entrySet()) {
            material = (Material) materialAndWidth.getKey();
            materialWidth = (int) materialAndWidth.getValue();
            Set inOrderLargeFirst = lengthOptions.descendingSet();
            Iterator<Integer> listLengths = inOrderLargeFirst.iterator();
            while (listLengths.hasNext()) {
                count++;
                materialLength = listLengths.next();
                HashMap<Material, int[]> materialMeasures = new HashMap<>();
                int[] measures = new int[2];
                measures[0] = materialLength;
                measures[1] = materialWidth;
                materialMeasures.put(material, measures);
                availableMaterialsAndMeasures.put(count, materialMeasures);
            }
        }

        lengthRest = constructionPart.getLength();
        widthRest = constructionPart.getWidth();

        //Beregner antal ud via givende conditions pr materialemulighed (pr. materiale med forskellige længder og bredder)
        for (int i = 1; i <= availableMaterialsAndMeasures.size() + 1; i++) {

            for (Map.Entry materialInOrder : availableMaterialsAndMeasures.get(i).entrySet()) {
                meassures = ((int[]) materialInOrder.getValue());
                material = (Material) materialInOrder.getKey();
                overlap = material.getOverlap();
                materialLength = meassures[0];
                materialWidth = meassures[1];

                if (lengthRest == 0 && widthRest == 0) {
                    return mapOfQts;
                }

                //Regnestykkerne er baseret på at hver materiale har et overlap, undtagen det første materiale
                if (quantityLength == 0 && quantityWidth != 0) {
                    quantityLength = 1;
                    quantityWidth = quantitySide(materialWidth, widthRest, overlap, mapOfQts);
                    widthRest = lengthRest % ((quantityWidth * (materialWidth - material.getOverlap())) + material.getOverlap());

                } else if (quantityLength != 0 && quantityWidth == 0) {
                    quantityWidth = 1;
                    quantityLength = quantitySide(materialLength, lengthRest, overlap, mapOfQts);
                    lengthRest = lengthRest % ((quantityLength * (materialLength - material.getOverlap())) + material.getOverlap());

                } else {
                    quantityLength = quantitySide(materialLength, lengthRest, overlap, mapOfQts);
                    quantityWidth = quantitySide(materialWidth, widthRest, overlap, mapOfQts);
                }
                if (quantityWidth > 0) {
                    widthRest = widthRest % ((quantityWidth * (materialWidth - material.getOverlap())) + material.getOverlap());
                }
                if (quantityLength > 0) {
                    lengthRest = lengthRest % ((quantityLength * (materialLength - material.getOverlap())) + material.getOverlap());
                }

                //Materialet bliver ikke tilføjet hvis antallet er 0
                if (!(quantityLength == 0) || !(quantityWidth == 0)) {
                    //ArealBeregning
                    finalQuantity = quantityLength * quantityWidth;
                    finalMaterialMeauseresAndQt[0] = materialLength;
                    finalMaterialMeauseresAndQt[1] = materialWidth;
                    finalMaterialMeauseresAndQt[2] = finalQuantity;
                    quantities.put(material, finalMaterialMeauseresAndQt);
                    mapOfQts.put(i, quantities);
                }

                //Hvis antallet er 0, så lægger vi et materiale mere fra den forrige størrelse til og overskriver den forhenværende
                //Vi sikrer os også på at det er ikke bliver det første materiale der er på tale
                if (i > 1) {
                    materialBeforeValue = mapOfQts.get(i - 1);
                    materialMeauseresAndQt = materialBeforeValue.get(material);
                    if (lengthRest < materialLength - overlap) {
                        quantityLength++;
                    }
                    if (widthRest < materialWidth - overlap) {
                        quantityWidth++;
                    }
                    //ArealBeregning
                    finalQuantity = quantityLength * quantityWidth;

                    finalMaterialMeauseresAndQt[0] = materialMeauseresAndQt[0];
                    finalMaterialMeauseresAndQt[1] = materialMeauseresAndQt[1];
                    finalMaterialMeauseresAndQt[2] = finalQuantity;

                    materialBeforeValue.put(material, finalMaterialMeauseresAndQt);
                    mapOfQts.put(i - 1, materialBeforeValue);
                    return mapOfQts;
                }
            }
        }
        return mapOfQts;
    }

    public int quantitySide(int materialSide, int sideConstructionPart, int overlap, HashMap<Integer, HashMap<Material, int[]>> listToValidate) {
        int tmpMaterialSide;
        int quanitiy = 0;

        if (listToValidate.size() == 0) {
            tmpMaterialSide = materialSide;
        } else {
            tmpMaterialSide = materialSide - overlap;
        }

        for (int i = materialSide; i <= sideConstructionPart; i = i + tmpMaterialSide) {
            quanitiy++;
            tmpMaterialSide = materialSide - overlap;
        }
        return quanitiy;
    }

    public HashMap quantitiesAtSideCalculated(ConstructionPart constructionPart, TreeSet<Integer> lengthOptions, Material material) {
        
        return mapOfQts;
    }
}
