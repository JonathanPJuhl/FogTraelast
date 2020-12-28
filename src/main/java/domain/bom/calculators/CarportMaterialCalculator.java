package domain.bom.calculators;

import domain.construction.Construction;

public class CarportMaterialCalculator {

    private final Construction construction;

    public CarportMaterialCalculator(Construction construction) {
        this.construction = construction;
    }

    //Lars/////////////////////////////////
    public int antalStolper() {
        //Til denne metode kræves der længde fra databasen.
        int length = 0;

        int userinput = (int) length;
        int AfstandprBjaelke = 180;
        //int N = 180; Udhæng skal det være der eller ej?
        int minimumsøjler = 2;
        int minimumLængdefor4søjler = userinput - 360;
        //N er den aftand der er fra sidste bjælke til taget slutter.
        int AntalBjaelker = (minimumLængdefor4søjler/*-N*/) / AfstandprBjaelke;
        int ExtraBjaelke = (AntalBjaelker + 1);
        int StolpeResultat = (minimumsøjler + ExtraBjaelke * 2);

        return StolpeResultat;
    }


    public int remLength() {

        int width = 0;
        int length = 0;
        int remResult = width + length * 2;

        return remResult;
    }

    /////////////////////////////////////////

    final private int POSTWIDTH = 100;
    final private int MAXROWSISTANCE = 6000;
    final private int MAXDISTANCEPOST = 3000;

    //Taeller hvor mange stolper der er på en side af carport (og skur)
    public int sidePostAmount(int size, int distanceMax) {
        int numberOfPost;
        if (size % distanceMax == 0) { //
            numberOfPost = (size / distanceMax)+ 1;
        } else {
            numberOfPost = (size / distanceMax) + 2;
        }
        if (size == 1){
            numberOfPost = 2;
        }
        return numberOfPost;
    }


    //counts distance between posts on the side
    public int postColumns(int length) {
        return (length - POSTWIDTH) / (sidePostAmount(length, MAXDISTANCEPOST) - 1);
    }

    //counts how many rows of post should there be because max distance between posts
    public int postRows(int width) {
        int rows;
        if (width % MAXROWSISTANCE == 0) {
            rows = width / MAXROWSISTANCE + 1;
        } else {
            rows = (width - width % MAXROWSISTANCE) / MAXROWSISTANCE + 2;
        }
        return rows;
    }


    public int getPOSTWIDTH() {
        return POSTWIDTH;
    }
}
