package domain.bom.calculators;

import domain.construction.ConstructionPart;
import domain.material.Material;

import java.security.Key;
import java.util.*;


public class NoWaistHelper { //TODO Færdiggør refactoringen hvis der er tid

    private int materialLength;
    private int materialWidth;
    private int quantitySide;
    private int lengthRest;
    private int widthRest;

    public HashMap quantitiesPlatesAreaCalculated(int constructionPartLength, int constructionPartWidth, Material material, TreeSet<Integer> materialOptionsWidth, TreeSet<Integer> materialOptionsLength) {
        //TODO - Note til rapport: Vi mapper målene med Materiale(navn) som key, for senere at kunne hente dette specifikke Materiales længder og bredder, som der vil være på evt. lager;
        // Vi har fået at vide af vores product owvner, FOG, på andet springmøde (i virkeligheden første møde med læreren, Christian) at der er f.eks. alle mål med 30 cm mellem
        // størrelserne på diverse længder (Gælder dog kun træ materialer). Dog er bredderne forskellige afhængigt af materiale. Et type materiale kan dog godt fås i forskellige bredder;

        HashMap<Integer, HashMap<Material, int[]>> mapOfQts = new HashMap();
        lengthRest = constructionPartLength;
        widthRest = constructionPartWidth;
        int quantityWidth = 0;
        int quantityLength = 0;
        HashMap<Material, int[]> quantities = new HashMap<>();
        int lengthQntyTmp = 0;
        int widthQntyTmp = 0;

        //Sætter største tal først i en liste af målemuligheder
        Iterator<Integer> lengthOptionsBiggestFirst = fromLargeToSmallOrder(materialOptionsLength);
        Iterator<Integer> widthOptionsBiggestFirst = fromLargeToSmallOrder(materialOptionsWidth);
        ArrayList<int[]> meassuresCombo = sizeOptionsArray(lengthOptionsBiggestFirst, widthOptionsBiggestFirst);
        int[] meassuresOptions;
        boolean isLastWidthLenghtComboOptions = false;
        int ID = 0;

        HashMap<Material, int[]> materialOptionsAndQnty;
        int quantityLengthFinal = quantityLength;
        HashMap<Material, int[]> materialLengthWidthQuanity = new HashMap<>();
        int[] optionsAndQnty = new int[3];
        int quantityWidthFinal = quantityWidth;
        int widthOption = 0;
        int materialWidthOptionBigFirst = 0;
        int materialLengthOptionBigFirst = 0;
        int optionLength = 0;
        int lengthOption = 0;
        int quantityWidthToAdd = 0;
        int quantityLengthToAdd = 0;
        int qntyWholeMaterialsForLastRow = 0;
        boolean addedEkstraWidthQnt = false;
        boolean addedEkstraLengthQnt = false;
        int widthOptionToAdd = 0;
        int lengthOptionToAdd = 0;
        int IDToAdd = 0;

        //Kører alle målemuligheder igennem for at hente hhv længde og bredde ud til beregninger
        // Vi kører den en gang til for at sammenligne restlængden af beregningen med det sidste
        // mål kombination for at se om en i det samlede map af antal, målCombo og materiale skal ændres
        for (int i = 1; i <= (meassuresCombo.size() + 1); i++) {
            //Det i bruges som ID senere nede så nøglerne ikke bliver det samme og overskriver værdierne fra det

            //For at sikre sig man ikke sammenligner med et tomt materiale har ID dette kriterie
            //Denne beregning skal ske hvis der er en tilbagestående rest efter at have kørt alle potentielle mål
            //igennem for materialet


            if (meassuresCombo.size() >= i) {
                meassuresOptions = meassuresCombo.get(i - 1);
                this.materialLength = meassuresOptions[0];
                this.materialWidth = meassuresOptions[1];
            } else {
                isLastWidthLenghtComboOptions = true;
            }



            if (isLastWidthLenghtComboOptions) {
                if (this.widthRest != 0) {
                    Iterator<Integer> widthOptionsBiggestFirstNew = materialOptionsWidth.iterator(); //TODO Ændrer big til small
                    while (widthOptionsBiggestFirstNew.hasNext()) {
                        materialWidthOptionBigFirst = widthOptionsBiggestFirstNew.next();
                        for (Map.Entry toCompareFromCountedMap : mapOfQts.entrySet()) {
                            ID = (int) toCompareFromCountedMap.getKey();
                            materialOptionsAndQnty = (HashMap<Material, int[]>) toCompareFromCountedMap.getValue();
                            optionsAndQnty = materialOptionsAndQnty.get(material);
                            int lengthOptionToCompare = optionsAndQnty[0];
                            if (quantityWidthFinal > 0 && constructionPartLength > lengthOptionToCompare) {
                                addedEkstraWidthQnt = true;
                                break;
                            }
                            lengthOption = optionsAndQnty[0];
                            widthOptionToAdd = optionsAndQnty[1];
                            int quantity = optionsAndQnty[2];

                            quantityWidthToAdd = quantitySideCounter(lengthOption, constructionPartLength, material.getOverlap());
                            double restWidthPercentagePrOption = (double) widthRest / (double) widthOptionToAdd;
                            if (restWidthPercentagePrOption <= 0.5) {
                                double qntyMaterialsForRow = restWidthPercentagePrOption * quantityLengthToAdd;
                                qntyWholeMaterialsForLastRow = (int) qntyMaterialsForRow;
                            }
                            if (widthOptionToAdd == materialWidthOptionBigFirst) {
                                quantityWidthFinal = quantityWidthToAdd + quantity - qntyWholeMaterialsForLastRow;
                            }
                            IDToAdd = ID;

                        }
                        if (addedEkstraWidthQnt) {
                            break;
                        }
                    }
                    materialLengthWidthQuanity = new HashMap<>();
                    int[] optionsAndQntyFinally = new int[3];
                    optionsAndQntyFinally[0] = lengthOption;
                    optionsAndQntyFinally[1] = widthOptionToAdd;
                    optionsAndQntyFinally[2] = quantityWidthFinal;
                    materialLengthWidthQuanity.put(material, optionsAndQntyFinally);
                    mapOfQts.put((IDToAdd), materialLengthWidthQuanity);
                }

                if (this.lengthRest != 0) {
                    Iterator<Integer> lengthOptionsBiggestFirstNew = materialOptionsLength.iterator();//TODO Ændrer big til small
                    while (lengthOptionsBiggestFirstNew.hasNext()) {
                        materialLengthOptionBigFirst = lengthOptionsBiggestFirstNew.next();
                        for (Map.Entry toCompareFromCountedMap : mapOfQts.entrySet()) {
                            ID = (int) toCompareFromCountedMap.getKey();
                            materialOptionsAndQnty = (HashMap<Material, int[]>) toCompareFromCountedMap.getValue();
                            optionsAndQnty = materialOptionsAndQnty.get(material);
                            int widthOptionToCompare = optionsAndQnty[0];
                            if (quantityLengthFinal > 0 && constructionPartWidth > widthOptionToCompare ) {//
                                addedEkstraLengthQnt = true;//
                                break;
                            }
                            lengthOptionToAdd = optionsAndQnty[0];
                            widthOption = optionsAndQnty[1];
                            int quantity = optionsAndQnty[2];

                            quantityLengthToAdd = quantitySideCounter(widthOption, constructionPartWidth, material.getOverlap());
                            double restWidthPercentagePrOption = (double) lengthRest / (double) lengthOptionToAdd;
                            if (restWidthPercentagePrOption <= 0.5) {
                                double qntyMaterialsForRow = restWidthPercentagePrOption * quantityWidthToAdd;
                                qntyWholeMaterialsForLastRow = (int) qntyMaterialsForRow;
                            }
                            if (lengthOptionToAdd == materialLengthOptionBigFirst) {
                                quantityLengthFinal = quantityLengthToAdd + quantity - qntyWholeMaterialsForLastRow;
                            }
                            IDToAdd = ID;
                        }
                        if (addedEkstraLengthQnt) {
                            break;
                        }
                    }
                    materialLengthWidthQuanity = new HashMap<>();
                    int[] optionsAndQntyFinally = new int[3];
                    optionsAndQntyFinally[0] = lengthOptionToAdd;
                    optionsAndQntyFinally[1] = widthOption;
                    optionsAndQntyFinally[2] = quantityLengthFinal;
                    materialLengthWidthQuanity.put(material, optionsAndQntyFinally);
                    mapOfQts.put(IDToAdd, materialLengthWidthQuanity);
                }

//                HashMap<Material, int[]> changeSmallestMaterialOption = new HashMap<>();
//                int[] smallestMaterialOptionsCalc;
//                if (addedEkstraLengthQnt && addedEkstraWidthQnt) {
//                    for (int j = 1; j <= mapOfQts.size(); j++) {
//                        ID = j;
//                        for (Map.Entry smallestInMap : mapOfQts.get(j).entrySet()) {
//                            smallestMaterialOptionsCalc = (int[]) smallestInMap.getValue();
//                            if (smallestMaterialOptionsCalc[0] == materialOptionsLength.iterator().next()) {
//                                if (smallestMaterialOptionsCalc[1] == materialOptionsWidth.iterator().next()) {
//                                    int qnty = smallestMaterialOptionsCalc[2];
//                                    smallestMaterialOptionsCalc[2] = qnty + 1;
//                                    changeSmallestMaterialOption.put(material, smallestMaterialOptionsCalc);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }

                //mapOfQts.put(ID, changeSmallestMaterialOption);
                int lengthOptionLooping = 0;
                int widthOptionLooping = 0;
                materialLengthWidthQuanity = new HashMap<>();

                if (lengthRest > 0 && widthRest > 0) {
                    for (Map.Entry toCompareFromCountedMap : mapOfQts.entrySet()) {
                        ID = (int) toCompareFromCountedMap.getKey();
                        materialOptionsAndQnty = (HashMap<Material, int[]>) toCompareFromCountedMap.getValue();
                        optionsAndQnty = materialOptionsAndQnty.get(material);
                        lengthOptionLooping = optionsAndQnty[0];
                        widthOptionLooping = optionsAndQnty[1];
                        if (materialLengthOptionBigFirst == lengthOptionLooping && widthOptionLooping == materialWidthOptionBigFirst) {
                            optionsAndQnty[0] = lengthOptionToAdd;
                            optionsAndQnty[1] = widthOptionToAdd;
                            optionsAndQnty[2] = quantityLengthFinal++;
                            materialLengthWidthQuanity.put(material, optionsAndQnty);
                            mapOfQts.put(ID, materialLengthWidthQuanity);
                        }
                    }

                    this.lengthRest = 0;
                    this.widthRest = 0;
                }
                if (lengthRest == 0 && widthRest == 0) {
                    return mapOfQts;
                }
            }

            /////Regnestykkerne er baseret på at hver stykke materiale har et overlap, hvis overlap ikke er 0, undtagen det første stykke materiale

            //Beregner antal for bredde og længde af konstrucitonsdelen
            //int startMaterialWidth = startValueForQntySideArea(this.materialWidth, material.getOverlap(), mapOfQts); //TODO If qtny er 0 på forrtige materiale
            int valQntyWidth= quantitySideCounter(this.materialWidth, widthRest, material.getOverlap());
            int valQntyLength= quantitySideCounter(this.materialLength, lengthRest, material.getOverlap());
            quantityWidth= validationOfSideRest(valQntyWidth, valQntyLength);
            quantityLength = validationOfSideRest(valQntyLength, valQntyWidth);

//            /*//Materialet bliver ikke tilføjet hvis antallet er 0
//            if (!(quantityLength == 0) && !(quantityWidth == 0)) {
//                //For at sikres os at materialet her i denne beregning ikke skal laves som areal beregning hvis ikke
//                //det er der første antal materiale (med mål)
//                if (!mapOfQts.isEmpty()) {
//                    *//*//*/Indsætter materialelængde og -bredde samt antal der passer til disse
//                    //TODO NOTE TIL RAPPORTEN HVIS MANGEL PÅ TID - Vi går ikke ud fra at der er mere end en række eller kollonne når dette her er tilfældet
//                    int finalQuantity = quantityLength + quantityWidth;
//
//                    int[] finalMaterialMeauseresAndQt = new int[3];
//                    finalMaterialMeauseresAndQt[0] = this.materialLength;
//                    finalMaterialMeauseresAndQt[1] = this.materialWidth;
//                    finalMaterialMeauseresAndQt[2] = finalQuantity;
//
//                    materialLengthWidthQuanity = new HashMap<>();
//                    materialLengthWidthQuanity.put(material, finalMaterialMeauseresAndQt);
//                    mapOfQts.put(i, materialLengthWidthQuanity);*//*
//                }
//            }*/

            //ArealBeregning i tilfældet at det er det første materiale der bliver beregnet på
            if (isLastWidthLenghtComboOptions == false && mapOfQts.isEmpty()) {
                quantities = addQuantityForSquare(quantityLength, quantityWidth, material, materialLength, materialWidth, mapOfQts, lengthRest, widthRest);
                mapOfQts.put(i, quantities);
            } else if (isLastWidthLenghtComboOptions == false) {
                int tmpLengthQnty = lengthQntyTmp * quantityWidth;
                int tmpWidthQnty = widthQntyTmp * quantityLength;
                int qntyExtra = tmpLengthQnty + tmpWidthQnty;
                int[] finalMaterialMeauseresAndQt = new int[3];
                finalMaterialMeauseresAndQt[0] = materialLength;
                finalMaterialMeauseresAndQt[1] = materialWidth;
                finalMaterialMeauseresAndQt[2] = qntyExtra;

                materialLengthWidthQuanity = new HashMap<>();
                materialLengthWidthQuanity.put(material, finalMaterialMeauseresAndQt);
                mapOfQts.put(i, materialLengthWidthQuanity);
            }

            //Beregner restværdilængderne ud og bruger dem til senere materialer for at beregne rest længde og rest bredde
            this.widthRest = restConstructionPartSide(this.widthRest, this.materialWidth, material.getOverlap(), quantityWidth);
            this.lengthRest = restConstructionPartSide(this.lengthRest, this.materialLength, material.getOverlap(), quantityLength);

            if (!(quantityLength == 0 || quantityWidth == 0)) {
                lengthQntyTmp = quantityLength;
                widthQntyTmp = quantityWidth;
            }
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

                //int materialStarterValue = startValueForQntyLength(lengthOption, material.getOverlap(), quantitiesAndMaterials);
                this.quantitySide = quantitySideCounter(lengthOption, sideConstuctionPart, material.getOverlap());

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

    public int restConstructionPartSide(int tmpConstructionPartSide, int materialSize, int overlap,
                                        int quantitySide) {
        int constructionPartSide;
        if (quantitySide != 0)
            constructionPartSide = tmpConstructionPartSide % ((quantitySide * (materialSize - overlap)) + overlap);
        else {
            constructionPartSide = tmpConstructionPartSide;
        }
        return constructionPartSide;
    }

    //Så sørge vi får at areal beregningen ikke bliver 0, hvis den ene side har et antal
    public int validationOfSideRest(int quantitySideQnty, int oppsiteSideQtny) {
        if (quantitySideQnty == 0 && oppsiteSideQtny > 0) {
            return 1;
        } else {
            return quantitySideQnty;
        }
    }

    public Iterator<Integer> fromLargeToSmallOrder(TreeSet size) {
        Set inOrderLargeFirst = size.descendingSet();
        Iterator<Integer> listLengths = inOrderLargeFirst.iterator();
        return listLengths;
    }

    public ArrayList<int[]> sizeOptionsArray
            (Iterator<Integer> lengthOptionsOrdered, Iterator<Integer> widthOptionsOrdered) {
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
        if (mapOfQtnys.size() < 1) {
            materialBeforeValue = mapOfQtnys.get(mapOfQtnys.size() - 1);
            for (Map.Entry meassures : materialBeforeValue.entrySet()) {
                measureOption = (int[]) meassures.getValue();
            }
        }
        return measureOption;

    }

    public HashMap<Material, int[]> addQuantityForSquare(int quantityLength, int quantityWidth, Material material,
                                                         int materialLength, int materialWidth, HashMap<Integer, HashMap<Material, int[]>> listToValidate,
                                                         int constructionPartTotalLength, int constructionPartTotalWidth) {
        int tmpMaterialWidthStartValue;
        int tmpMaterialLengthStartValue;
        HashMap<Material, int[]> materialLengthWidthQuanity = new HashMap<>();
        int[] finalMaterialMeauseresAndQt = new int[3];
        int finalQuantity = 0;

        if (listToValidate.size() > 0) { // TODO Virker det ? eller skal der stå større end 1?
            //ArealBeregning
            finalQuantity = quantityLength * quantityWidth;
        } else if (!(materialWidth == 0 || materialLength == 0)) {
            tmpMaterialLengthStartValue = materialLength;
            //Dette sker kun ved listens første materiale - derfor hvor mange der kan være i fulde længde og bredde med overlap på begge led undetagen første materiale i kollone og række
            finalQuantity = quantityLength * quantityWidth;
        }
        finalMaterialMeauseresAndQt[0] = materialLength;
        finalMaterialMeauseresAndQt[1] = materialWidth;
        finalMaterialMeauseresAndQt[2] = finalQuantity;

        materialLengthWidthQuanity.put(material, finalMaterialMeauseresAndQt);
        return materialLengthWidthQuanity;
    }

    public int addOneMoreToSide(int constructionPartSideRest, Material material, int materialSide, int quantitySide) {
        if (constructionPartSideRest != 0) {
            quantitySide = quantitySide * 2;
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
        if (materialSide != 0) {
            tmpMaterialSide = materialSide - overlap;
        }
        return tmpMaterialSide;
    }

    public int quantitySideCounter(int materialSide, int sideRestConstructionPart,
                                   int overlap) {
        int quanitiy = 0;
        int tmpMaterialSideStartValue = materialSide - overlap;

        for (int i = materialSide; i <= sideRestConstructionPart; i = i + tmpMaterialSideStartValue) {
            quanitiy++;
        }

//        while(materialSide <= sideRestConstructionPart){
//            quanitiy++;
//            materialSide =+ tmpMaterialSideStartValue;
//        }

        return quanitiy;
    }

}
