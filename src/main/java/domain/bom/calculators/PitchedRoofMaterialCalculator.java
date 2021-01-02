package domain.bom.calculators;

import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.ConstructionPart;
import domain.construction.Roof.PitchedRoof;
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
    private int ROOFTILESCOVERS = 330;
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
    private final PitchedRoof roof;
    private final HashMap<Category, ConstructionPart> constructionpart;

    private final RoofSizeCalculator roofSizeCalculator;

    public PitchedRoofMaterialCalculator(HashMap<Category, ConstructionPart> constructionparts) {
        this.constructionpart = constructionparts;
        this.carport = (Carport) constructionparts.get(Category.Carport);
        this.roof = (PitchedRoof) constructionparts.get(Category.Pitched);
        roofSizeCalculator = new RoofSizeCalculator();
    }

    public int amoutOfRygstenBeslagCalculated() {
        rygstenBeslag = quantityRygsten();
        return rygstenBeslag;
    }


    public int amountOfRoofTiles() {
        int tagstenHalfePitchedRoof = 0;
        //Vi trækker ikke en tagstenbredde fra i tagets længde i for-loopet fordi vi vil have det hele antal + en hvis
        // der er en rest
        for (int i = 0; i < roofSizeCalculator.roofWidthSurface(roof.isFlat(), roof.getWidth(), roof.getDegree()) - LÆGTESTENDISTANCE; i = i + LÆGTESTENDISTANCE) {
            for (int j = 0; j < roofSizeCalculator.roofLengthSurface(roof.isFlat(), roof.getLength(), roof.getDegree()); j = j + roofTilesWidth) {
                tagstenHalfePitchedRoof++;
            }
        }
        tagstenEntirePitchedRoof = tagstenHalfePitchedRoof * 2;
        return tagstenEntirePitchedRoof;
    }

    public int tagstenBindereCalculated() {
        int tagstenBinder = amountOfRoofTiles();
        for (int i = 0; i < tagstenBinder; i = i + 2) {
            tagstenBinder++;
        }
        return tagstenBinder;
    }

    public int tagstenNakkekrogeCalculated() {
        //Vi formoder der er en tagstensnakkekrog pr. tagsten
        tagstenNakkekrog = amountOfRoofTiles();
        return tagstenNakkekrog;
    }

    public int screwsForVindskederCalculated() {
        //Vi antager der bruges en skrue for hvert 50cm
        vindskedeLængde = (int) (Math.hypot((roof.getWidth() / 2.0), roofSizeCalculator.roofHeight(roof.getWidth(), roof.getDegree())));
        for (int i = 50; i < vindskedeLængde - 50; i = i + 50) {
            screwsForVindskeder++;
        }
        return screwsForVindskeder;
    }

    public int screwsForVandbrætCalculated() {
        //Vi antager der skal bruges til hver 300 mm en skrue, med mindst 10 mm fra sidste kant
        vandBrætsLength = roofSizeCalculator.roofWidthSurface(roof.isFlat(), roof.getWidth(), roof.getDegree());
        for (int i = 0; i < vandBrætsLength - 10; i = i + 300) {
            screwsForVandbræt++;
        }
        screwsForVandbræt = screwsForVandbræt * 2;
        return screwsForVandbræt;
    }

    public int amountOfBeslagScrewsForToplægteCalculated() {
        beslagForToplægte = 2 * quantityRygsten();
        return beslagForToplægte;
    }


    public int spærPlankLengthPerSpær() {
        int spærfodLength = carport.getLength();
        int spærArm = (int) (spærfodLength / (Math.cos(Math.toRadians(roof.getDegree())))) * 2;
        spærFullPlankLength = (spærArm * 2) + spærfodLength;
        return spærFullPlankLength;
    }

    public int spærQuantity() {
        int carportLength = carport.getLength();
        int distanceBestweenSpær = 890;
        for (int i = 0; i < carportLength; i = i + distanceBestweenSpær) {
            spærAmount++;
        }
        if (constructionpart.get(Category.Shed) == null) {
            spærAmount = spærAmount + 2;
        }
        return spærAmount;
    }

    public int spærFullQuantityOfPlanksTotal() {
        spærFullQuatityOfPlanks = spærPlankLengthPerSpær() * spærQuantity();
        return spærFullQuatityOfPlanks;
    }

    public int gavlOverlayQuantity(int overlayPlankWidthKonstant, int overlayPlankLenghtAvailable) {
        int gavlOverlayPlanksQuantity = 0;
        int lengthOfTriangleGavl = carport.getLength();
        int lenghtOfTriangleGavlShorter = carport.getWidth();
        int restTotal;
        int restUseable = 1;
        int roofHeight = (int) roofSizeCalculator.roofHeight(roof.getWidth(), roof.getDegree());
        double newHeight = roofSizeCalculator.roofHeight(roof.getWidth(), roof.getDegree());
        int roofAngleInTop = (int) (roof.getDegree()) * 2;
        int lengthOfHalfRoofWidthSurface = roofSizeCalculator.roofWidthSurface(roof.isFlat(), roof.getWidth(), roof.getDegree());
        int overlayPlankWidth;
        double kFactor;
        double tempHeigth;

        for (int i = 0; i < roofHeight - 1; i = i + overlayPlankWidth) {
            overlayPlankWidth = overlayPlankWidthKonstant;
            gavlOverlayPlanksQuantity++;
            restTotal = overlayPlankLenghtAvailable % lenghtOfTriangleGavlShorter;
            if (restTotal != 0) {
                restUseable = overlayPlankLenghtAvailable / restTotal;
                gavlOverlayPlanksQuantity++;
                overlayPlankWidth = overlayPlankWidth * restUseable;
            }
            tempHeigth = newHeight;
            if (overlayPlankWidth < newHeight)
                newHeight = newHeight - overlayPlankWidth;
            else
                newHeight = overlayPlankWidth - newHeight;
            kFactor = newHeight / tempHeigth;

            lenghtOfTriangleGavlShorter = (int) (kFactor * lenghtOfTriangleGavlShorter);
        }
        if (lenghtOfTriangleGavlShorter != 0)
            return (int) gavlOverlayPlanksQuantity + 1;

        return (int) gavlOverlayPlanksQuantity;
    }


    public int quantityRygsten() {
        amountOfRygsten = roof.getLength() / ROOFTILESCOVERS;
        if (amountOfRygsten % ROOFTILESCOVERS != 0)
            amountOfRygsten++;
        return amountOfRygsten;
    }

    // ** Beregning af antal sternbrædder i forhold til tagets længde - stern skal have samme længde som taget + 300 mm**
    public int amountOfStern() {
        int roofLength = roof.getLength();
        int sternLength = roofLength + 300; //tag længde + 300mm lægte udhæng

        if (roofLength <= 600) //600 mm = 1 stern længde - if roofLength equal/smaller than 600
        {
            numberOfStern = 4; // 2 on each side
        } else if (roofLength > 600) //if bigger than 600
        {
            numberOfStern = 6; // 4 on each side
        } else {
            numberOfStern = 8;
        }

        return numberOfStern;
    }

    //** Beregning af antal tagfodslægte i forhold til taget længde **
    public int qntyOfRoofFirstLathBothSides() {
        numberOfTagfodsLaegte = 2;
        return numberOfTagfodsLaegte;
    }

    public int qntyOfRoofLathBothSides() {
        int roofSurfaceWidthOneSide = roofSizeCalculator.roofWidthSurface(roof.isFlat(), roof.getWidth(), roof.getDegree());
        int numberOfRoofLathsHalfRoof = roofSurfaceWidthOneSide / ROOFTILESCOVERS;
        int numberOfRoofLath = numberOfRoofLathsHalfRoof * 2;
        return numberOfRoofLath;
    }

    //** Beregning af antal vindskeder i forhold til tagets længde**
    public int amountOfVindskeder() {
        numberOfVindskeder = 4;
        return numberOfVindskeder;
    }

    //** Beregning af antal vandbræt i forhold til antal vindskider ** - skal monteres på vindskider
    public int amountOfVandbraet() {
        return numberOfVandbraet = 2;
    }

    public int getRoofTilesWidth() {
        return roofTilesWidth;
    }
}
