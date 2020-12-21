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
        Material material =
        new Material(1,"Trapez", "pink",20.00, MaterialType.roofPlades.getDanishName(), Category.Flat.getDanishName(), 10, 10);
        NoWaistHelper noWaistHelper = new NoWaistHelper();
        RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();
        UsersChoice usersChoice = new UsersChoice(200, 600, "Flat", 0, 0,
                material, 0, 0,0,null);
        Roof flat = new FlatRoof(usersChoice,roofSizeCalculator);
        flat.toString();
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(600);
        numbers.add(300);
        HashMap<Material, Integer> materialNWidth = new HashMap<Material, Integer>();
        materialNWidth.put(material, 110);
        HashMap<Material, int[]> soonBOMItemMap = noWaistHelper.quantitiesInSquareAreaCalculated(flat,numbers,materialNWidth);
        for (Map.Entry m: soonBOMItemMap.entrySet()) {
            System.out.println(m.getKey().toString());
            System.out.println(m.getValue().toString());
        }
        System.out.println("Du er fjollet");
    }


    //TODO I hele programmet eller skriv i rapport - RET ALLE LISTS/MAPS/SETS TIL SPICFIKKE TYPER DER INDEHOLDER DISSE TYPER!!!!!!
    private final HashMap<Material, int[]> quantities = new HashMap<>();
    private final HashMap<Material,  int[]> availableMaterialsAndMeasures = new HashMap<>();

    Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            int[] first = o1;
            int sumFirst = first[0] + first[1];
            int[] second = o2;
            int sumSecond = second[0] + second[1];
            if(sumSecond<sumFirst)
            return 1;
            else{
                return 0;
            }
        }
    };

    public HashMap quantitiesInSquareAreaCalculated(ConstructionPart constructionPart, TreeSet<Integer> lengthOptions, HashMap<Material, Integer> materialsAndWidth) {
        //TODO - Note til rapport
        //Vi mapper målene med Materiale(navn) som key, for senere at kunne hente dette specifikke Materiales længder og bredder, som der vil være på evt. lager;
        //Vi har fået at vide af vores product owvner, FOG, på andet springmøde (i virkeligheden første møde med læreren, Christian) at der er f.eks. alle mål med 30 cm mellem
        //størrelserne på diverse længder (Gælder dog kun træ materialer). Dog er bredderne forskellige afhængigt af materiale. Et type materiale kan dog godt fås i forskellige bredder;


        Material material;
        int materialLength;
        int materialWidth;

        for (Map.Entry materialAndWidth : materialsAndWidth.entrySet()) {
            material = (Material) materialAndWidth.getKey();
            materialWidth = (int) materialAndWidth.getValue();

            for (Integer lengthOption : lengthOptions) {
                materialLength = lengthOption;
                int[] measures = new int[2];
                measures[0] = materialLength;
                measures[1] = materialWidth;
                availableMaterialsAndMeasures.put(material, measures);
            }
        }

        LinkedHashMap<Material, int[]> linkedHashMap = new LinkedHashMap<Material, int[]>();

        Set <Map.Entry<Material, int[]>> set = availableMaterialsAndMeasures.entrySet();
        List<int[]> convertList = new ArrayList<int[]>();
        for (Map.Entry materialMeasures1: availableMaterialsAndMeasures.entrySet()) {
            for (Map.Entry materialMeasures2 : availableMaterialsAndMeasures.entrySet()) {
                int[] array1 = (int[]) (materialMeasures1.getValue());
                int[] array2 = (int[]) (materialMeasures1.getValue());
                comparator.compare(array1,array2);
            }
        }

        //
        int[] finalMaterialMeauseresAndQt = new int[3];
        int overlap;
        int lengthRest = constructionPart.getLength();
        int widthRest = constructionPart.getWidth();
        int quantityLength;
        int quantityWidth;

        for (Map.Entry materialMeasuresMap : availableMaterialsAndMeasures.entrySet()) {

            material = (Material) materialMeasuresMap.getKey();
            overlap = material.getOverlap();

            int[] materialMeauseres = availableMaterialsAndMeasures.get(material);
            materialLength = materialMeauseres[0];
            materialWidth = materialMeauseres[1];

            if(lengthRest == 0 && widthRest == 0){
                return quantities;
            } else if (widthRest>0 || lengthRest>0){

                quantityLength = quantitySide(materialLength, lengthRest, overlap);
                quantityWidth = quantitySide(materialLength, widthRest, overlap);
                //Regnestykket i disse to rest er baseret på at hver materiale har et overlap, undtagen det første materiale
                lengthRest = lengthRest % ((quantityLength * (materialLength - material.getOverlap())) + material.getOverlap());
                widthRest = lengthRest % ((quantityWidth * (materialWidth - material.getOverlap())) + material.getOverlap());


                finalMaterialMeauseresAndQt[0] = materialLength;
                finalMaterialMeauseresAndQt[1] = materialWidth;
                finalMaterialMeauseresAndQt[2] = quantityLength + quantityWidth;
                quantities.put(material, finalMaterialMeauseresAndQt);
            }
        }
        return quantities;

    }

    private int quantitySide(int materialSide, int sideConstructionPart, int overlap) {
        int tmpMaterialSide = materialSide;
        int quanitiy = 0;

        for (int i = materialSide; i <= sideConstructionPart; i = + materialSide) {
            quanitiy++;
            tmpMaterialSide = materialSide - overlap;
        }
        return quanitiy;
    }

}
