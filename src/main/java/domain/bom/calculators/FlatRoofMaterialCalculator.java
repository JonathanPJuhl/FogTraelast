package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.material.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FlatRoofMaterialCalculator {
    private RoofSizeCalculator roofSizeCalculator;
    private final Raft raft;
    private final Stern overSternFront;
    private final Stern overSternSide;
    private final Stern underSternLength;
    private final Stern underSternWidth;

    //private int quantityOfT300temp;
    private NoWaistHelper noWaistHelper;

    public FlatRoofMaterialCalculator(FlatRoof roof, RoofSizeCalculator roofSizeCalculator, NoWaistHelper noWaistHelper) {
        this.noWaistHelper = noWaistHelper;
        this.roofSizeCalculator = roofSizeCalculator;
        this.raft = new Raft(roof.getWidth(),roof.getLength());
        this.overSternFront = new Stern(roof.getWidth());
        this.overSternSide = new Stern(roof.getLength());
        this.underSternLength = new Stern(roof.getLength());
        this.underSternWidth = new Stern(roof.getWidth());

    }


   /* //Denne implementerer ikke BOMItemsSpecifications, da der er tale om flere længder og undgå af spilmateriale, så pladerne har ikke kun en længde pr. slags materiale objekt
    public class TrapezPlates implements BOMItemSpecifications {

        private final NoWaistHelper noWaistHelper;

        public TrapezPlates(NoWaistHelper noWaistHelper) {
            this.noWaistHelper = noWaistHelper;
        }


        @Override
        public int length() {
            return 0;
        }

        @Override
        public int width(int widthFromDB) {
            return widthFromDB;
        }

        @Override
        public int quantity() {
            return 0;
        }

        @Override
        public String description(String adminDescription) {
            return null;
        }
        private final int T300ROOFPLADELENGTH = 3000; //TODO Dette skulle rigtig beregnes ud fra 360 cm istedet
        private final int T600ROOFPLADELENGTH = 6000;
        private final int OVERLAP = 200;
        private int numberOfT600Trapezplates;
        private int numberOfT300Trapezplates;
        private int square1numberOfT600Trapezplates = 0;
        private int square2numberOfT600Trapezplates = 0;
        private int square3numberOfT600Trapezplates = 0;
        private Construction construction;
        public RoofSizeCalculator roofSizeCalculator;
        private final int roofWidthSurfaceCalc;
        private final int roofLength;

        public TrapezPlates(RoofSizeCalculator roofSizeCalculator, Construction construction) {
            this.construction = construction;
            this.roofSizeCalculator = roofSizeCalculator;
            roofWidthSurfaceCalc = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(),construction.getRoof().getWidth(), construction.getRoof().getDegree());
            roofLength =  roofSizeCalculator.flatRoofCalcutatedSurfaceWidth(construction.getRoof().getLength(), construction.getRoof().getDegree());
        }

        public HashMap whichTrapezShouldBeUsed(int trapezPladeWidth){
            HashMap quantityPladeMap = new HashMap();

            if((roofLength<=T300ROOFPLADELENGTH)) {
                quantityPladeMap.put("T300", quantityOfT300ForRoof(trapezPladeWidth));
            }else
                quantityPladeMap.put("T600", quantityOfT600ForRoof(trapezPladeWidth));
            int leftOverRoofLength = roofLength % quantityOfT600ForRoof(trapezPladeWidth);
            int leftOverRoofWidth = roofWidthSurfaceCalc % (trapezPladeWidth-OVERLAP);
            if(leftOverRoofLength<T300ROOFPLADELENGTH && roofLength%quantityOfT600ForRoof(trapezPladeWidth) >20) {
                quantityPladeMap.put("T300", quantityOfT300ForRoof(trapezPladeWidth));
            }
            return quantityPladeMap;
        }

        //quantity T600 Trapezplates
        //TODO Cath skal se det igennem in case of forandringer i pladebredde
        public int quantityOfT600ForRoof(int trapezPladeWidth) {
            ///////////////Beregning af første del af tag (hvor mange HELE T600 plader kan der være)
            int tempTrapezPladeWidth = trapezPladeWidth;
            int tempTrapezPladeLength = T600ROOFPLADELENGTH;


                for (int i = 0; i < (roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP); i = i + tempTrapezPladeWidth) {
                    for (int j = 0; j < (roofLength); j = j + tempTrapezPladeLength) {
                        square1numberOfT600Trapezplates++;
                        tempTrapezPladeWidth = trapezPladeWidth - OVERLAP;
                        tempTrapezPladeLength = T600ROOFPLADELENGTH - OVERLAP;
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

            if (restWidth != 0) {
                restPart = tempTrapezPladeWidth / restWidth;
                temp2 = Math.round((double) square2numberOfT600Trapezplates / restPart);
                square2numberOfT600Trapezplates = (int) temp2;
            }


            /////////////////////////////////////////////////////

            ///////////////Beregning af tredje del af tag (om hvor mange antal T600 plader der er (delt i længden))
            quantityOfT300temp = quantityOfT300ForRoof(trapezPladeWidth);

            tempTrapezPladeWidth = trapezPladeWidth;

            if (quantityOfT300temp == 0) {
                for (int i = 0; i < (roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP); i = i + tempTrapezPladeWidth) {
                    square3numberOfT600Trapezplates++;

                }
            }
            if (leftOverRoofLength != 0) {
                restPart = tempTrapezPladeWidth / restWidth;
                temp2 = Math.round((double) square2numberOfT600Trapezplates / restPart);
                square2numberOfT600Trapezplates = (int) temp2;
            }
            /////////////////////////////////////////////////////

            //Mellemregning til samlet antal plader
            numberOfT600Trapezplates = square1numberOfT600Trapezplates + square2numberOfT600Trapezplates +
                    square3numberOfT600Trapezplates;

            /////////////Beregning af fjerde og sidste del af tag (om den sidste plade skal være en T600 eller T300)

            if (quantityOfT300temp == 0)
                numberOfT600Trapezplates++;



            /////////////////////////////////////////////////////

            return numberOfT600Trapezplates;
        }

        //Antal T300 Trapezplader
        public int quantityOfT300ForRoof(int trapezPladeWidth) {
            int tempTrapezPladeWidth = (trapezPladeWidth);
            int restOfLength = roofLength % T600ROOFPLADELENGTH;
            int quantityOfT300 = 0;
            if (restOfLength > 0 && restOfLength <= T300ROOFPLADELENGTH) {
                for (int i = 0; i < roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP; i = i + tempTrapezPladeWidth) {
                    quantityOfT300++;
                    tempTrapezPladeWidth = trapezPladeWidth - OVERLAP;
                }
            }
            numberOfT300Trapezplates = quantityOfT300;
            if (quantityOfT300 != 0)
                numberOfT300Trapezplates = quantityOfT300 + 1;
            //(Beregning af fjerde og sidste del
            // af taget betyder det når jeg skriver +1)


            return numberOfT300Trapezplates;
        }

        public int getT300ROOFPLADELENGTH() {
            return T300ROOFPLADELENGTH;
        }

        public int getT600ROOFPLADELENGTH() {
            return T600ROOFPLADELENGTH;
        }

        public int quantity() {
            return numberOfT300Trapezplates + numberOfT600Trapezplates;
        }

    }*/

    public class Raft implements BOMItemSpecifications {
        private int roofWidth;
        private int roofLength;
        private final int WOODDIFFERENCES = 3000;

        public Raft(int roofwidth, int roofLength) {
            this.roofWidth = roofwidth;
            this.roofLength = roofLength;
        }

        @Override
        public int length() {
            int realMeasures = 0;
            int ideelMeasures = roofWidth - 50;

            realMeasures = ideelMeasures / WOODDIFFERENCES;

            if (ideelMeasures % WOODDIFFERENCES != 0) {
                realMeasures++;
            }
            return realMeasures;
        }

        @Override
        public int width(int widthFromDB) {
            return widthFromDB;
        }

        @Override
        public int quantity() {
            int quantityRafters = 0;
            int constructionLength = roofLength;
            for (int i = 0; i < constructionLength; i = (i + 60)) {
                quantityRafters++;
            }
            return quantityRafters;
        }

        @Override
        public String description(String adminDescription) {
            return adminDescription;
        }

    }

    public class Stern implements BOMItemSpecifications {
        private final int size;
        private final int perSide = 2;

        public Stern(int size) {
            this.size = size;
        }

        @Override
        public int length() {
            return size;
        }

        @Override
        public int width(int widthFromDB) {
            return widthFromDB;
        }

        @Override
        public int quantity() {
            return perSide;
        }

        @Override
        public String description(String adminDescription) {
            return adminDescription;
        }

    }

    public Raft getRaft() {
        return raft;
    }

    public Stern getOverSternFront() {
        return overSternFront;
    }

    public Stern getUnderSternLength() {
        return underSternLength;
    }

    public Stern getUnderSternWidth() {
        return underSternWidth;
    }

    public Stern getOverSternSide() {
        return overSternSide;
    }

}
