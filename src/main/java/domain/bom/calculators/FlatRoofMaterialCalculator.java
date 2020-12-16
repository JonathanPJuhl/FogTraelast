package domain.bom.calculators;

import domain.construction.Construction;
import domain.construction.Roof.RoofSizeCalculator;

public class FlatRoofMaterialCalculator {

    public int getT300ROOFPLADELENGTH() {
        return T300ROOFPLADELENGTH;
    }

    public int getT600ROOFPLADELENGTH() {
        return T600ROOFPLADELENGTH;
    }

    //TODO ændre bredde
    private final int T300ROOFPLADELENGTH = 3000; //TODO Dette skulle rigtig beregnes ud fra 360 cm istedet
    private final int T600ROOFPLADELENGTH = 6000;
    private final int OVERLAP = 200;
    private int numberOfT600Trapezplates;
    private int numberOfT300Trapezplates;
    private int square1numberOfT600Trapezplates = 0;
    private int square2numberOfT600Trapezplates = 0;
    private int square3numberOfT600Trapezplates = 0;
    private final int roofWidthSurfaceCalc;
    private final int roofLength;
    private final RoofSizeCalculator roofSizeCalculator = new RoofSizeCalculator();
    private final Construction construction;

    public FlatRoofMaterialCalculator(Construction construction) {
        this.roofWidthSurfaceCalc = roofSizeCalculator.pitchedRoofCalcutatedSurfaceWidth(construction.getWidth(),construction.getRoof().getDegree());
        this.roofLength = roofSizeCalculator.flatRoofCalcutatedSurfaceLength(construction.getLength(),construction.getRoof().getDegree());
        this.construction = construction;
    }

    //TrapezePlader

    //quantity T600 Trapezplates
    //TODO Cath skal se det igennem in case of forandringer i pladebredde
    public int quantityOfT600ForRoof(int trapezPladeWidth) {
        ///////////////Beregning af første del af tag (hvor mange HELE T600 plader kan der være)
        trapezPladeWidth = trapezPladeWidth*10;
        int tempTrapezPladeWidth = trapezPladeWidth;
        for (int i = 0; i < (roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP); i = i+ tempTrapezPladeWidth) {
            for (int j = 0; j < roofLength-1; j = j+ T600ROOFPLADELENGTH) {
                square1numberOfT600Trapezplates++;
                tempTrapezPladeWidth = trapezPladeWidth*10 - OVERLAP;
            }
        }
        tempTrapezPladeWidth = trapezPladeWidth;
        /////////////////////////////////////////////////////

        /////Beregning af anden del af tag (T600 plader inkl. T600 pladerester - hvor pladerne er delt på bredden)
        for (int i = 0; i < roofLength - T600ROOFPLADELENGTH; i = i + T600ROOFPLADELENGTH) {
            square2numberOfT600Trapezplates++;
        }

        int restWidth = roofWidthSurfaceCalc % tempTrapezPladeWidth;

        int restPart;
        double temp2;

        if (restWidth != 0 ) {
            restPart = tempTrapezPladeWidth / restWidth;
            temp2 = Math.round((double)square2numberOfT600Trapezplates / restPart);
            square2numberOfT600Trapezplates = (int) temp2;
        }

        /////////////////////////////////////////////////////

        ///////////////Beregning af tredje del af tag (om hvor mange antal T600 plader der er (delt i længden))
        int quantityOfT300 = quantityOfT300ForRoof(trapezPladeWidth);

        tempTrapezPladeWidth = trapezPladeWidth;

        if (quantityOfT300 == 0) {
            for (int i = 0; i < (roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP) ; i = i + tempTrapezPladeWidth) {
                square3numberOfT600Trapezplates++;

            }
        }

        /////////////////////////////////////////////////////

        //Mellemregning til samlet antal plader
        numberOfT600Trapezplates = square1numberOfT600Trapezplates + square2numberOfT600Trapezplates +
                square3numberOfT600Trapezplates;

        /////////////Beregning af fjerde og sidste del af tag (om den sidste plade skal være en T600 eller T300)

        if (quantityOfT300 == 0)
            numberOfT600Trapezplates++;


        /////////////////////////////////////////////////////

        return numberOfT600Trapezplates;
    }

    //Antal T300 Trapezplader
    public int quantityOfT300ForRoof(int trapezPladeWidth) {
        int tempTrapezPladeWidth = (trapezPladeWidth*10);
        int restOfLength = roofLength % T600ROOFPLADELENGTH;
        int quantityOfT300 = 0;
        if (restOfLength > 0 && restOfLength <= T300ROOFPLADELENGTH){
            for (int i = 0; i < roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP; i=i+ tempTrapezPladeWidth) {
                quantityOfT300++;
                tempTrapezPladeWidth = trapezPladeWidth*10 - OVERLAP;
            }
        }
        numberOfT300Trapezplates = quantityOfT300;
        if (quantityOfT300 != 0)
            numberOfT300Trapezplates = quantityOfT300 +1;
        //(Beregning af fjerde og sidste del
        // af taget betyder det når jeg skriver +1)


        return numberOfT300Trapezplates;
    }

}
