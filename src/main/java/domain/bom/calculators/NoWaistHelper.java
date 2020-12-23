package domain.bom.calculators;

import domain.construction.ConstructionPart;
import domain.material.Material;

import java.util.*;


public class NoWaistHelper { //TODO Færdiggør refactoringen hvis der er tid

    private int materialLength;
    private int materialWidth;
    private int quantitySide;

    public HashMap quantitiesPlatesAreaCalculated(int constructionPartLength, int constructionPartWidth, Material material, TreeSet<Integer> materialOptionsWidth, TreeSet<Integer> materialOptionsLength) {
        //TODO - Note til rapport: Vi mapper målene med Materiale(navn) som key, for senere at kunne hente dette specifikke Materiales længder og bredder, som der vil være på evt. lager;
        // Vi har fået at vide af vores product owvner, FOG, på andet springmøde (i virkeligheden første møde med læreren, Christian) at der er f.eks. alle mål med 30 cm mellem
        // størrelserne på diverse længder (Gælder dog kun træ materialer). Dog er bredderne forskellige afhængigt af materiale. Et type materiale kan dog godt fås i forskellige bredder;

        HashMap<Integer, HashMap<Material, int[]>> mapOfQts = new HashMap();
        int lengthRest = constructionPartLength;
        int widthRest = constructionPartWidth;
        int quantityWidth = 0;
        int quantityLength = 0;
        int ID = 0;
        HashMap<Material, int[]> quantities = new HashMap<>();

        //Sætter største tal først i en liste af målemuligheder
        Iterator lengthOptionsBiggestFirst = fromLargeToSmallOrder(materialOptionsLength);
        Iterator widthOptionsBiggestFirst = fromLargeToSmallOrder(materialOptionsWidth);
        ArrayList<int[]> meassuresCombo = sizeOptionsArray(lengthOptionsBiggestFirst, widthOptionsBiggestFirst);

        //Kører alle målemuligheder igennem for at hente hhv længde og bredde ud til beregninger
        for (int[] meassuresOptions : meassuresCombo) {
            this.materialLength = meassuresOptions[0];
            this.materialWidth = meassuresOptions[1];
            //ID bruges senere nede så nøglerne ikke bliver det samme og overskriver værdierne fra det
            ID++;

            //Hvis et materiale har dækket det sidste på konstruktionsdelen
            if (lengthRest == 0 && widthRest == 0) {
                return mapOfQts;
            }

            //Regnestykkerne er baseret på at hver stykke materiale har et overlap, hvis overlap ikke er 0, undtagen det første stykke materiale

            //Beregner antal for bredde og længde af konstrucitonsdelen
            int startMaterialWidth = startValueForQntySideArea(this.materialWidth, material.getOverlap(), mapOfQts);
            quantityWidth = quantitySideCounter(this.materialWidth, widthRest, startMaterialWidth, material.getOverlap());
            int startMaterialLength = startValueForQntySideArea(this.materialLength, material.getOverlap(), mapOfQts);
            quantityLength = quantitySideCounter(this.materialLength, lengthRest, startMaterialLength, material.getOverlap());

            //Bregner restværdilængderne ud og bruger dem til senere materialer for at beregne rest længde og rest bredde
            int potentialRestWidth = restConstructionPartSide(widthRest, this.materialWidth, material.getOverlap(), quantityWidth);
            widthRest = validationOfSideRest(potentialRestWidth);
            int potentialRestLength = restConstructionPartSide(lengthRest, this.materialLength, material.getOverlap(), quantityLength);
            lengthRest = validationOfSideRest(potentialRestLength);

            //Materialet bliver ikke tilføjet hvis antallet er 0
            if (!(quantityLength == 0) && !(quantityWidth == 0)) {
                //For at sikres os at materialet her i denne beregning ikke skal laves som areal beregning hvis ikke
                //det er der første antal materiale (med mål)
                if (mapOfQts.size() > 1) {
                    //Indsætter materialelængde og -bredde samt antal der passer til disse
                    HashMap<Material, int[]> materialLengthWidthQuanity = new HashMap<>();
                    //TODO NOTE TIL RAPPORTEN HVIS MANGEL PÅ TID - Vi går ikke ud fra at der er mere end en række eller kollonne når dette her er tilfældet
                    int finalQuantity = quantityLength + quantityWidth;

                    int[] finalMaterialMeauseresAndQt = new int[3];
                    finalMaterialMeauseresAndQt[0] = this.materialLength;
                    finalMaterialMeauseresAndQt[1] = this.materialWidth;
                    finalMaterialMeauseresAndQt[2] = finalQuantity;

                    materialLengthWidthQuanity.put(material, finalMaterialMeauseresAndQt);
                    mapOfQts.put(ID, materialLengthWidthQuanity);
                } else {
                    //ArealBeregning i tilfældet at det er det første materiale der bliver beregnet på
                    quantities = addQuantityForSquare(quantityLength, quantityWidth, material, materialLength, materialWidth);
                    mapOfQts.put(ID, quantities);
                }
            }
        }
        //For at sikre sig man ikke sammenligner med et tomt materiale har ID dette kriterie
        //Denne beregning skal ske hvis der er en tilbagestående rest efter at have kørt alle potentielle mål
        //igennem for materialet
        if (ID > 1 && (widthRest > 0 || lengthRest > 0)) {
            int quantityLengthFinal = addOneMoreToSide(lengthRest, material, this.materialLength, quantityLength);
            int quantityWidthFinal = addOneMoreToSide(widthRest, material, this.materialWidth, quantityWidth);
            int[] materialMeassuresMaterialBefore = meassuresFromMaterialBefore(mapOfQts);
            int materialBeforeLength = materialMeassuresMaterialBefore[0];
            int materialBeforewidth = materialMeassuresMaterialBefore[1];
            HashMap<Material, int[]> materialAndFinalMeassuresBeforeID = addQuantityForSquare(quantityLengthFinal, quantityWidthFinal, material, materialBeforeLength, materialBeforewidth);
            mapOfQts.put(ID - 1, materialAndFinalMeassuresBeforeID);
        }
        return mapOfQts;
    }

    //TODO Ret denne til
    public HashMap quantitiesAtSideCalculated(int sideConstuctionPart, TreeSet<Integer> lengthOptions, Material
            material) {
        //hhv. material og sidelængde
        HashMap<Integer, Material> materialMeasures = new HashMap<>();
        //Hhv. sidelængde (material og antal)
        HashMap<Integer, HashMap<Material, Integer>> mapOfQnty = new HashMap<>();
        HashMap<Integer, Material> quantitiesAndMaterials = new HashMap<>();
        int measureSideQnty = 0;
        HashMap<Integer, HashMap<Material, int[]>> mapOfQntys = new HashMap<>();
        HashMap<Material, int[]> qntysAndMaterial = new HashMap<>();
        int[] lengthAndQnty = new int[2];


        Iterator listInOrderBiggestFirst = fromLargeToSmallOrder(lengthOptions);
        ArrayList<Integer> listInOrderLengthOptions = lengthOptionsListed(listInOrderBiggestFirst);

        int sideRest = sideConstuctionPart;

        for (int i = 1; i <= materialMeasures.size(); i++) {
            for (int lengthOption : listInOrderLengthOptions) {

                if (sideRest == 0) {
                    return mapOfQnty;
                }

                int materialStarterValue = startValueForQntyLength(lengthOption, material.getOverlap(), quantitiesAndMaterials);
                this.quantitySide = quantitySideCounter(lengthOption, sideConstuctionPart, materialStarterValue, material.getOverlap());

                if (this.quantitySide != 0) {
                    sideRest = restConstructionPartSide(sideRest, lengthOption, material.getOverlap(), this.quantitySide);
                    quantitiesAndMaterials.put(this.quantitySide, material);
                }

                if (quantitySide != 0) {
                    lengthAndQnty[0] = lengthOption;
                    lengthAndQnty[1] = this.quantitySide;
                    qntysAndMaterial.put(material, lengthAndQnty);
                    mapOfQntys.put(i, qntysAndMaterial);
                }
            }
            int ID = mapOfQnty.size();
            if (i > 1 && sideRest != 0) {
                TreeSet reverseHelper = new TreeSet();
                reverseHelper.addAll(listInOrderLengthOptions);
                ArrayList<Integer> listSortetSmallestFirst = (ArrayList) reverseHelper.iterator();

                for (int matLength : listSortetSmallestFirst) {
                    ID--;
                    if (sideRest < (matLength - material.getOverlap())) {
                        measureSideQnty++;
                        lengthAndQnty[0] = matLength;
                        lengthAndQnty[1] = this.quantitySide;
                        qntysAndMaterial.put(material, lengthAndQnty);
                        mapOfQntys.put(ID, qntysAndMaterial);
                        break;
                    }
                }
            }

        }
        return mapOfQntys;
    }

    public int restConstructionPartSide(int tmpConstructionPartSide, int materialSize, int overlap, int quantitySide) {
        int constructionPartSide;
        constructionPartSide = tmpConstructionPartSide % ((quantitySide * (materialSize - overlap)) + overlap);
        return constructionPartSide;
    }

    //Så sørge vi får at areal beregningen ikke bliver 0, hvis den ene side har et antal
    public int validationOfSideRest(int quantitySide) {
        int restConstructionPart;

        if (quantitySide == 0) {
            return 1;
        } else {
            return quantitySide;
        }
    }

    public Iterator<Integer> fromLargeToSmallOrder(TreeSet size) {
        Set inOrderLargeFirst = size.descendingSet();
        Iterator<Integer> listLengths = inOrderLargeFirst.iterator();
        return listLengths;
    }

    public ArrayList<int[]> sizeOptionsArray(Iterator<Integer> lengthOptionsOrdered, Iterator<Integer> widthOptionsOrdered) {
        int lengthOption;
        int widthOption;
        int[] meassurments;
        TreeSet orderedSavedWidthOptions = new TreeSet();
        ArrayList<Integer> saveInList = new ArrayList<>();
        ArrayList<int[]> sizeOptions = new ArrayList<>();
        while (lengthOptionsOrdered.hasNext()) {
            lengthOption = lengthOptionsOrdered.next();
            while (widthOptionsOrdered.hasNext()) {
                widthOption = widthOptionsOrdered.next();
                saveInList.add(widthOption);
                meassurments = new int[2];
                meassurments[0] = lengthOption;
                meassurments[1] = widthOption;
                sizeOptions.add(meassurments);
            }
            while (widthOptionsOrdered.hasNext()) {
                saveInList.add(widthOptionsOrdered.next());
            }
            orderedSavedWidthOptions.addAll(saveInList);
            widthOptionsOrdered = orderedSavedWidthOptions.descendingSet().iterator();
        }
        return sizeOptions;
    }

    public ArrayList<Integer> lengthOptionsListed(Iterator<Integer> lengthOptionsOrdered) {
        int lengthOption;
        ArrayList<Integer> sizeOptions = new ArrayList<>();
        while (lengthOptionsOrdered.hasNext()) {
            lengthOption = lengthOptionsOrdered.next();
            sizeOptions.add(lengthOption);
        }
        return sizeOptions;
    }

    public int[] meassuresFromMaterialBefore(HashMap<Integer, HashMap<Material, int[]>> mapOfQtnys) {
        HashMap<Material, int[]> materialBeforeValue = new HashMap<>();
        int[] measureOption = new int[3];
        //Hvis antallet er 0, så lægger vi et materiale mere fra den forrige størrelse til og overskriver den forhenværende
        //Vi sikrer os også på at det er ikke bliver det første materiale der er på tale
        if (mapOfQtnys.size() > 1)
            materialBeforeValue = mapOfQtnys.get(mapOfQtnys.size() - 1);
        for (Map.Entry meassures : materialBeforeValue.entrySet()) {
            measureOption = (int[]) meassures.getValue();
        }
        return measureOption;

    }

    public HashMap<Material, int[]> addQuantityForSquare(int quantityLength, int quantityWidth, Material material, int materialLength, int materialWidth) {
        HashMap<Material, int[]> materialLengthWidthQuanity = new HashMap<>();
        int[] finalMaterialMeauseresAndQt = new int[3];

        //ArealBeregning
        int finalQuantity = quantityLength * quantityWidth;

        finalMaterialMeauseresAndQt[0] = materialLength;
        finalMaterialMeauseresAndQt[1] = materialWidth;
        finalMaterialMeauseresAndQt[2] = finalQuantity;

        materialLengthWidthQuanity.put(material, finalMaterialMeauseresAndQt);
        return materialLengthWidthQuanity;
    }

    public int addOneMoreToSide(int constructionPartSideRest, Material material, int materialSide, int quantitySide) {
        if (constructionPartSideRest != 0) {
            if (constructionPartSideRest < (materialSide - material.getOverlap())) {
                quantitySide++;
            }
        }
        return quantitySide;
    }

    public int startValueForQntyLength(int materialSide, int overlap, HashMap<Integer, Material> listToValidate) {
        //her ungås overlap på første materiale
        int tmpMaterialSide = materialSide;

        //Skelner mellem om der er et materiale brugt før til samme længde ved at se om der er andre materialer i mappet
        if (listToValidate.size() != 0) {
            tmpMaterialSide = materialSide - overlap;
        }
        return tmpMaterialSide;
    }


    public int startValueForQntySideArea(int materialSide, int overlap, HashMap<Integer, HashMap<Material, int[]>>
            listToValidate) {
        //her ungås overlap på første materiale
        int tmpMaterialSide = materialSide;

        //Skelner mellem om der er et materiale brugt før til samme længde ved at se om der er andre materialer i mappet
        if (listToValidate.size() != 0) {
            tmpMaterialSide = materialSide - overlap;
        }
        return tmpMaterialSide;
    }

    public int quantitySideCounter(int materialSide, int sideConstructionPart, int tmpMaterialSideStartValue,
                                   int overlap) {
        int quanitiy = 0;
        for (int i = materialSide; i <= sideConstructionPart; i = i + tmpMaterialSideStartValue) {
            quanitiy++;
            tmpMaterialSideStartValue = materialSide - overlap;
        }
        return quanitiy;
    }

}
