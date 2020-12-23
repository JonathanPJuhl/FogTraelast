package domain.bom.calculators;

import domain.construction.ConstructionPart;
import domain.material.Material;

import java.util.*;


public class NoWaistHelper {

    public HashMap quantitiesPlatesAreaCalculated(ConstructionPart constructionPart, Material material, TreeSet<Integer> materialOptionsWidth, TreeSet<Integer> materialOptionsLength) {
        //TODO - Note til rapport
        //Vi mapper målene med Materiale(navn) som key, for senere at kunne hente dette specifikke Materiales længder og bredder, som der vil være på evt. lager;
        //Vi har fået at vide af vores product owvner, FOG, på andet springmøde (i virkeligheden første møde med læreren, Christian) at der er f.eks. alle mål med 30 cm mellem
        //størrelserne på diverse længder (Gælder dog kun træ materialer). Dog er bredderne forskellige afhængigt af materiale. Et type materiale kan dog godt fås i forskellige bredder;

        HashMap<Integer, HashMap<Material, int[]>> mapOfQts = new HashMap();
        int lengthRest = constructionPart.getLength();
        int widthRest = constructionPart.getWidth();
        int materialLength;
        int materialWidth;
        int quantityWidth = 0;
        int quantityLength = 0;
        int ID = 0;
        HashMap<Material, int[]> quantities = new HashMap<>();

        Iterator lengthOptionsBiggestFirst = fromLargeToSmallOrder(materialOptionsLength);
        Iterator widthOptionsBiggestFirst = fromLargeToSmallOrder(materialOptionsWidth);
        ArrayList<int[]> meassuresCombo = sizeOptionsArray(lengthOptionsBiggestFirst, widthOptionsBiggestFirst);


        //Beregner antal ud via givende conditions pr materialemulighed (forskellige længder pr. materiale og bredder)
        //for (int i = 1; i <= meassuresCombo.size() + 1; i++) {

        //Dette hashMap kører kun et tal og et tilknyttet hashMap et af gangen
        for (int[] meassuresOptions : meassuresCombo) {
            materialLength = meassuresOptions[0];
            materialWidth = meassuresOptions[1];
            ID++;
            if (lengthRest == 0 && widthRest == 0) {
                return mapOfQts;
            }

            int startMaterialWidth = startValueForQntySide(materialLength, material.getOverlap(), mapOfQts);
            quantityWidth = quantitySideCounter(materialWidth, constructionPart.getWidth(), startMaterialWidth, material.getOverlap());
            int startMaterialLength = startValueForQntySide(materialLength, material.getOverlap(), mapOfQts);
            quantityLength = quantitySideCounter(materialLength, constructionPart.getLength(), startMaterialLength, material.getOverlap());

            int potentialRestWidth = restConstructionPartSide(widthRest, materialWidth, material.getOverlap(), quantityWidth);
            widthRest = validationOfSideRest(potentialRestWidth);
            int potentialRestLength = restConstructionPartSide(lengthRest, materialLength, material.getOverlap(), quantityLength);
            lengthRest = validationOfSideRest(potentialRestLength);
            //Regnestykkerne er baseret på at hver stykke materiale har et overlap, hvis overlap ikke er 0, undtagen det første stykke materiale

                /*if (quantityLength == 0 && quantityWidth != 0) {
                    quantityLength = 1;




                    //


                } else if (quantityLength != 0 && quantityWidth == 0) {
                    quantityWidth = 1;
                    quantityLength = quantitySide(materialLength, lengthRest, overlap, mapOfQts);
                    lengthRest = lengthRest % ((quantityLength * (materialLength - material.getOverlap())) + material.getOverlap());

                } else {
                    quantityLength = quantitySide(materialLength, lengthRest, overlap, mapOfQts);
                    quantityWidth = quantitySide(materialWidth, widthRest, overlap, mapOfQts);
                }*/
            //TODO SKal Bruges de næste 6 linjer?
//                if (quantityWidth > 0) {
//                    widthRest = widthRest % ((quantityWidth * (materialWidth - material.getOverlap())) + material.getOverlap());
//                }
//                if (quantityLength > 0) {
//                    lengthRest = lengthRest % ((quantityLength * (materialLength - material.getOverlap())) + material.getOverlap());
//                }

            //}
            //Materialet bliver ikke tilføjet hvis antallet er 0


            if (!(quantityLength == 0) || !(quantityWidth == 0)) {
                //ArealBeregning
                quantities = addQuantityForSquare(quantityLength, quantityWidth, material, materialLength, materialWidth);
                    /*finalQuantity = quantityLength * quantityWidth;
                finalMaterialMeauseresAndQt[0] = materialLength;
                finalMaterialMeauseresAndQt[1] = materialWidth;
                finalMaterialMeauseresAndQt[2] = finalQuantity;
                quantities.put(material, finalMaterialMeauseresAndQt);*/
                mapOfQts.put(ID, quantities);


                //  }
            }
            if (ID > 1) {
                int quantityLengthFinal = addOneMoreToSide(lengthRest, material, materialLength, quantityLength);
                int quantityWidthFinal = addOneMoreToSide(widthRest, material, materialWidth, quantityWidth);
                int[] materialMeassuresMaterialBefore = meassuresFromMaterialBefore(mapOfQts, quantities);
                int materialBeforeLength = materialMeassuresMaterialBefore[0];
                int materialBeforewidth = materialMeassuresMaterialBefore[1];
                HashMap<Material, int[]> materialAndFinalMeassuresBeforeID = addQuantityForSquare(quantityLengthFinal, quantityWidthFinal, material, materialBeforeLength, materialBeforewidth);
                mapOfQts.put(ID - 1, materialAndFinalMeassuresBeforeID);
            }
        }
        return mapOfQts;
    }


    public int restConstructionPartSide(int tmpConstructionPartSide, int materialWidth, int overlap, int quantitySide) {
        int constructionPartSide;
        constructionPartSide = tmpConstructionPartSide % ((quantitySide * (materialWidth - overlap)) + overlap);
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
        int[] meassurments = new int[2];
        TreeSet orderedSavedWidthOptions = new TreeSet();
        ArrayList<Integer> saveInList = new ArrayList<>();
        ArrayList<int[]> sizeOptions = new ArrayList<>();
        while (lengthOptionsOrdered.hasNext()) {
            lengthOption = lengthOptionsOrdered.next();
            for (int i = 0; i < sizeOptions.size() ; i++) {
                while (widthOptionsOrdered.hasNext()) {
                    widthOption = widthOptionsOrdered.next();
                    saveInList.add(widthOption);
                    meassurments[0] = lengthOption;
                    meassurments[1] = saveInList.get(i);
                    sizeOptions.add(meassurments);
                }
            }
                orderedSavedWidthOptions.addAll(saveInList);
            widthOptionsOrdered = orderedSavedWidthOptions.iterator();
        }
        return sizeOptions;
    }

    /*public HashMap<int[], Material> materialMeasurementOptions (TreeSet<int[]> meassurementOptions, Material material ){
        int lengthOption;
        int widthOption;
        int[] meassurments = new int[2];
        TreeSet<int[]> sizeOptions = new TreeSet<>();
        while (lengthOptionsOrdered.hasNext()) {
            lengthOption = lengthOptionsOrdered.next();
            while (widthOptionsOrdered.hasNext()) {
                widthOption = widthOptionsOrdered.next();
                meassurments[0] = lengthOption;
                meassurments[1] = widthOption;
                sizeOptions.add(meassurments);
            }
        }
        return sizeOptions;
    }*/


    public int[] meassuresFromMaterialBefore(HashMap<Integer, HashMap<Material, int[]>> mapOfQtnys, HashMap<Material, int[]> materialNMeassuresBeforeID) {
        HashMap<Material, int[]> materialBeforeValue;
        int[] measureOption = new int[3];
        //Hvis antallet er 0, så lægger vi et materiale mere fra den forrige størrelse til og overskriver den forhenværende
        //Vi sikrer os også på at det er ikke bliver det første materiale der er på tale

        //TODO Revurder!
        materialBeforeValue = mapOfQtnys.get(mapOfQtnys.size() - 1);
        for (Map.Entry meassures : materialBeforeValue.entrySet()) {
            measureOption = (int[]) meassures.getKey();
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

    public int startValueForQntySide(int materialSide, int overlap, HashMap<Integer, HashMap<Material, int[]>>
            listToValidate) {
        //her ungås overlap på første materiale
        int tmpMaterialSide = materialSide;

        //Skelner mellem om der er et materiale brugt før til samme længde ved at se om der er andre materialer i mappet
        if (listToValidate.size() == 0) {
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

    //TODO
    /* public HashMap quantitiesAtSideCalculated ( int sideConstuctionPart, TreeSet<Integer > lengthOptions, Material
        material){
            //hhv. material og sidelængde
            HashMap<Material, Integer> materialMeasures = new HashMap<>();
            HashMap<Material, Integer> materialMeasuresTmp = new HashMap<>();
            //Hhv. sidelængde (material og antal)
            HashMap<Integer, HashMap<Material, Integer>> mapOfQnty = new HashMap<>();

            lengthOptionListedInLargestLength(lengthOptions);

            int sideRest = sideConstuctionPart;

            for (int i = 0; i < materialMeasures.size(); i++) {
                for (Map.Entry materialNLength : materialMeasures.entrySet()) {
                    int materialLengthOption = (int) materialNLength.getValue();
                    Material tmpMaterial = ((Material) materialNLength.getKey());
                    int materialOverlap = tmpMaterial.getOverlap();

                    if (sideRest == 0) {
                        return mapOfQnty;
                    }

                    int quantity = quantitySideCounter(materialLengthOption, sideRest, materialLengthOption, materialOverlap);
                    sideRest = sideRest % ((quantity * (materialLengthOption - material.getOverlap())) + material.getOverlap());

                    if (quantity > 0) {
                        sideRest = sideRest % ((quantity * (materialLengthOption - material.getOverlap())) + material.getOverlap());
                        materialMeasuresTmp.put(tmpMaterial, quantity);
                        mapOfQnty.put(materialLengthOption, materialMeasuresTmp);
                    }

                    //Hvis antallet er 0, så lægger vi et materiale mere fra den forrige størrelse til og overskriver den forhenværende
                    //Vi sikrer os også på at det er ikke bliver det første materiale der er på tale
                    int measureSideQnty = 0;
                    int smallestLength;
                    HashMap<Material, Integer> materialBeforeID = new HashMap<>();
                    if (i > 1 && sideRest != 0) {
                        ArrayList<Integer> listSortetSmallest = (ArrayList) lengthOptions.iterator();
                        for (int matLength : listSortetSmallest) {
                            if (lengthRest < (matLength - overlap)) {
                                measureSideQnty++;
                                materialBeforeID.put(material, measureSideQnty);
                                mapOfQnty.put(matLength, materialBeforeID);
                                break;
                            }
                        }
                    }
                }
            }
            return mapOfQts;
        }
*/
        /*public TreeSet<int[]> lengthOptionListedInLargestLength (TreeSet < Integer > lengthOptions) {
            //Hhv. material og sidelængde
            HashMap<Material, Integer> materialMeasures = new HashMap<>();

            //Sorteret længde-muligheder med tilhørende materiale tilknyttet (størt først)
            Set inOrderLargeFirst = lengthOptions.descendingSet();
            Iterator<Integer> listLengths = inOrderLargeFirst.iterator();
            while (listLengths.hasNext()) {
                materialLength = listLengths.next();
                materialMeasures.put(material, materialLength);
            }
            return materialMeasures;
        }*/
}
