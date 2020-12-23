package domain.bom.calculators;

import domain.construction.Construction;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;

import java.util.HashMap;

public class PitchedRoofMaterialCalculator {

    private int numberOfTaglaegter; // T1 taglægter til spær
    private int numberOfStern;
    private int numberOfToplaegteHolder;
    private int numberOfToplaegte; // T1 toplægter til rygsten
    private int numberOfTagfodsLaegte;
    private int numberOfVindskeder;
    private int numberOfVandbraet;
    private int SCREWSPERTAGLÆGTEHOLDER = 5;
    private int LÆGTESTENDISTANCE = 307;
    private int RYGSTENCOVERS = 330;
    private int tagstenNakkekrog;
    private int screwForTaglægter;
    private int screwsForVindskeder;
    private int screwsForVandbræt;
    private int beslagForToplægte;
    private int vindskedeLængde;
    private int vandBrætsLength;
    private int tagstenEntirePitchedRoof;
    private int roofTilesWidth = 200;
    private int rygstenBeslag;
    private int spærFullPlankLength;
    private int spærAmount;
    private int spærFullQuatityOfPlanks;
    private int amountOfRygsten;

    private final Carport carport;
    private final Roof roof;

    private final UsersChoice construction;
    private final RoofSizeCalculator roofSizeCalculator;

    public PitchedRoofMaterialCalculator(UsersChoice construction, HashMap constructionList) {
        this.construction = construction;
        this.carport = (Carport) constructionList.get("carport");
        this.roof = (Roof) constructionList.get("roof");

        roofSizeCalculator = new RoofSizeCalculator();
    }

    public int amoutOfRygstenBeslagCalculated() {
        rygstenBeslag = quantityRygsten();
        return rygstenBeslag;
    }


    public int amountOfRoofTiles(){
        int tagstenHalfePitchedRoof = 0;
        //Vi trækker ikke en tagstenbredde fra i tagets længde i for-loopet fordi vi vil have det hele antal + en hvis
        // der er en rest
        for (int i = 0; i < roofSizeCalculator.roofWidthSurface(construction.roofChoiceConverter(construction.getRoofChoice()), construction.getWidth(),
                construction.getDegree())- LÆGTESTENDISTANCE; i= i + LÆGTESTENDISTANCE) {
            for (int j = 0; j < roofSizeCalculator.roofLengthSurface(false,construction.getLength(),construction.getDegree()); j = j + roofTilesWidth) {
                tagstenHalfePitchedRoof++;
            }
        }
        tagstenEntirePitchedRoof = tagstenHalfePitchedRoof * 2;
        return tagstenEntirePitchedRoof;
    }

    public int tagstenBindereCalculated(){
        int tagstenBinder = amountOfRoofTiles();
        for (int i = 0; i < tagstenBinder; i = i+2) {
            tagstenBinder ++;
        }
        return tagstenBinder;
    }

    public int tagstenNakkekrogeCalculated(){
        //Vi formoder der er en tagstensnakkekrog pr. tagsten
        tagstenNakkekrog = amountOfRoofTiles();
        return tagstenNakkekrog;
    }

    public int screwsForVindskederCalculated(){
        //Vi antager der bruges en skrue for hvert 50cm
        vindskedeLængde = (int)(Math.hypot((construction.getWidth()/2.0), roofSizeCalculator.roofHeight(construction.getWidth(),construction.getDegree())));
        for (int i = 50; i < vindskedeLængde-50 ; i = i + 50) {
            screwsForVindskeder++;
        }
        return screwsForVindskeder;
    }

    public int screwsForVandbrætCalculated(){
        //Vi antager der skal bruges til hver 300 mm en skrue, med mindst 10 mm fra sidste kant
        vandBrætsLength = construction.getLength();
        for (int i = 300; i < vandBrætsLength -10 ; i = i + 300) {
            screwsForVandbræt++;
        }
        return screwsForVandbræt;
    }

    /*public int AmountOfScrewPackages(){
        int screwsTotal = screwForTaglægterCalculated() + screwsForVindskederCalculated()
                + screwsForVandbrætCalculated();
        screwPackage = Math.round(screwsTotal/200);
        return screwPackage;
    }*/

    //todo: fix amountOfBeslagScrewsForToplægteCalculated() line 620
    public int amountOfBeslagScrewsForToplægteCalculated(){
        beslagForToplægte = 9*2* amountOfT1_RygstenLength();
        return beslagForToplægte;
    }


    public int spærPlankLengthPerSpær(){
        int spærfodLength = carport.getLength();
        int spærArm = (int) (spærfodLength/(Math.cos(Math.toRadians(roof.getDegree()))))*2;
        spærFullPlankLength = (spærArm*2)+spærfodLength;
        return spærFullPlankLength;
    }
    //TODO beregning af ekstra spær (til sidst)
    public int spærQuantity(){
        int carportLength = carport.getLength();
        int distanceBestweenSpær = 89;
        for (int i = 0; i < carportLength; i = 1 + distanceBestweenSpær) {
            spærAmount++;
        }
        if (construction.getShedOrNo()==0) {
            spærAmount = spærAmount+ 2;
        }
        return spærAmount;
    }

    public int spærFullQuantityOfPlanksTotal(){
        spærFullQuatityOfPlanks = spærPlankLengthPerSpær()* spærQuantity();
        return spærFullQuatityOfPlanks;
    }

    public int gavlOverlayQuantity(int overlayPlankWidthKonstant, int overlayPlankLenghtAvailable){
        int gavlOverlayPlanksQuantity = 0;
        int lengthOfTriangleGavl = carport.getLength();
        int lenghtOfTriangleGavlShorter = carport.getWidth();
        int restTotal;
        int restUseable = 1;
        int roofHeight = (int) roofSizeCalculator.roofHeight(construction.getWidth(),construction.getDegree());
        double newHeight = roofSizeCalculator.roofHeight(construction.getWidth(),construction.getDegree());
        int roofAngleInTop = (int)(roof.getDegree())*2;
        int lengthOfHalfRoofWidthSurface = roofSizeCalculator.roofWidthSurface(construction.roofChoiceConverter
                (construction.getRoofChoice()),construction.getWidth(),roof.getDegree());
        int overlayPlankWidth;
        double kFactor;
        double tempHeigth;

        for (int i = 0; i < roofHeight-1; i = i + overlayPlankWidth) {
            overlayPlankWidth = overlayPlankWidthKonstant;
            gavlOverlayPlanksQuantity++;
            restTotal = overlayPlankLenghtAvailable % lenghtOfTriangleGavlShorter;
            if ( restTotal != 0){
                restUseable = overlayPlankLenghtAvailable/restTotal;
                gavlOverlayPlanksQuantity ++;
                overlayPlankWidth = overlayPlankWidth * restUseable;
            }
            tempHeigth = newHeight;
            if (overlayPlankWidth<newHeight)
                newHeight = newHeight - overlayPlankWidth;
            else
                newHeight = overlayPlankWidth - newHeight;
            kFactor = newHeight/tempHeigth;

            lenghtOfTriangleGavlShorter = (int) (kFactor * lenghtOfTriangleGavlShorter);
        }
        if (lenghtOfTriangleGavlShorter !=0 )
            return (int) gavlOverlayPlanksQuantity + 1;

        return (int) gavlOverlayPlanksQuantity;
    }


    public int quantityRygsten() {
        amountOfRygsten = construction.getLength() / RYGSTENCOVERS;
        if (amountOfRygsten% RYGSTENCOVERS != 0 )
            amountOfRygsten++;
        return amountOfRygsten;
    }

    //Vi antager at der herfra og ned er et slags materiale og derfor disse beregninger:

    //** Beregning af antal taglægter i forhold til tagets bredde - Remember: tilpas med t1_SpaerLength!? **
    private int amountOfT1_Spaer_Taglaegter()
    {
        int roofWidth = roof.getWidth();
        int T1_SpaerDistance = 307; // 307 mm mellem hvert lægte - dog ikke den første
        int topDistance = 30; // 30 mm på hver side dvs * 2

        numberOfTaglaegter = roofWidth - (topDistance * 2)/T1_SpaerDistance + 2; // 2 = 350mm bræt
        return numberOfTaglaegter;
    }

    // ** Beregning af antal sternbrædder i forhold til tagets længde - stern skal have samme længde som taget + 300 mm**
    public int amountOfStern()
    {
        int roofLength = roof.getLength();
        int sternLength = roofLength + 300; //tag længde + 300mm lægte udhæng

        if (roofLength <= 600 ) //600 mm = 1 stern længde - if roofLength equal/smaller than 600
        {
            numberOfStern = 4; // 2 on each side
        }
        else if (roofLength > 600) //if bigger than 600
        {
            numberOfStern = 6; // 4 on each side
        }
        else
        {
            numberOfStern = 8;
        }

        return numberOfStern;
    }

    //** Beregning af T1 toplægte (til rygsten) i forhold til tag længde **
    public int amountOfT1_RygstenLength()
    {
        int roofLength = roof.getLength();

        //int toplaegteLength = 420; // 420 mm = 1 toplægte længde

        if(roofLength <= 840) // stk af 420 dvs *2= 840
        {
            numberOfToplaegte = 2;
        }
        else if (roofLength > 840)
        {
            numberOfToplaegte = 4;
        }
        else
        {
            numberOfToplaegte = 6;
        }
        return numberOfToplaegte;
    }

    //** Beregning af antal tagfodslægte i forhold til taget længde **
    public int amountOfTagfodsLaegte ()
    {
        int roofLength = roof.getLength();

        if (roofLength <= 1620 ) //340 * 3stk ---> 340 mm = længde af 1 tagfodslægte
        {
            numberOfTagfodsLaegte = 3;
        }
        else if (roofLength > 1620)
        {
            numberOfTagfodsLaegte = 6;
        }
        else {
            numberOfTagfodsLaegte = 9;
        }
        return numberOfTagfodsLaegte;
    }

    //** Beregning af antal vindskeder i forhold til tagets længde**
    public int amountOfVindskeder ()
    {
        int  roofLength = roof.getLength();
        if (roofLength <= 480 ) //if roofLength equal/smaller than 480 - længde af 1 vindskede bræt
        {
            numberOfVindskeder = 2;
        }
        else if (roofLength > 480 )
        {
            numberOfVindskeder = 4;
        }
        return numberOfVindskeder;
    }

    //** Beregning af antal vandbræt i forhold til antal vindskider ** - skal monteres på vindskider
    //Note: antal vandbræt = antal vindskeder.
    public  int amountOfVandbraet()
    {
        int roofLength = roof.getLength();

        if (roofLength <= 480 ) //if roofLength equal/smaller than 480 - længde af 1 vandbræt
        {
            numberOfVandbraet = 2;
        }
        else if (roofLength > 480 )
        {
            numberOfVandbraet= 4;
        }
        return numberOfVandbraet;
    }

    public int getRoofTilesWidth() {
        return roofTilesWidth;
    }
}
