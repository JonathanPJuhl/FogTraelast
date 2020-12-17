package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.construction.Construction;
import domain.construction.Roof.RoofSizeCalculator;

import static domain.bom.BOMService.WOODDIFFERENCES;

public class FlatRoofMaterialCalculator {
    private final TrapezPlates trapezPlates;
    private Construction construction;
    private RoofSizeCalculator roofSizeCalculator;
    private final Raft raft;
    private final Stern overSternFront;
    private final Stern overSternSide;
    private final Stern underSternLength;
    private final Stern underSternWidth;

    public FlatRoofMaterialCalculator(Construction construction, RoofSizeCalculator roofSizeCalculator) {
        this.construction = construction;
        this.roofSizeCalculator = roofSizeCalculator;
        this.trapezPlates = new TrapezPlates(roofSizeCalculator, construction);
        this.raft = new Raft(construction);
        this.overSternFront = new Stern(construction.getRoof().getWidth());
        this.overSternSide = new Stern(construction.getRoof().getLength());
        this.underSternLength =  new Stern(construction.getRoof().getLength());
        this.underSternWidth = new Stern(construction.getRoof().getWidth());
    }



    //Denne implementerer ikke BOMItemsSpecifications, da der er tale om flere længder og undgå af spilmateriale, så pladerne har ikke kun en længde pr. slags materiale objekt
    public class TrapezPlates {
        //TODO ændre bredde
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
            roofWidthSurfaceCalc = roofSizeCalculator.pitchedRoofCalcutatedSurfaceWidth(construction.getRoof().getWidth(), construction.getRoof().getDegree());
            roofLength =  roofSizeCalculator.flatRoofCalcutatedSurfaceLength(construction.getRoof().getLength(), construction.getRoof().getDegree());
        }

        //quantity T600 Trapezplates
        //TODO Cath skal se det igennem in case of forandringer i pladebredde
        public int quantityOfT600ForRoof(int trapezPladeWidth) {
            ///////////////Beregning af første del af tag (hvor mange HELE T600 plader kan der være)
            trapezPladeWidth = trapezPladeWidth * 10;
            int tempTrapezPladeWidth = trapezPladeWidth;
            for (int i = 0; i < (roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP); i = i + tempTrapezPladeWidth) {
                for (int j = 0; j < roofLength - 1; j = j + T600ROOFPLADELENGTH) {
                    square1numberOfT600Trapezplates++;
                    tempTrapezPladeWidth = trapezPladeWidth * 10 - OVERLAP;
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
            int quantityOfT300 = quantityOfT300ForRoof(trapezPladeWidth);

            tempTrapezPladeWidth = trapezPladeWidth;

            if (quantityOfT300 == 0) {
                for (int i = 0; i < (roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP); i = i + tempTrapezPladeWidth) {
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
            int tempTrapezPladeWidth = (trapezPladeWidth * 10);
            int restOfLength = roofLength % T600ROOFPLADELENGTH;
            int quantityOfT300 = 0;
            if (restOfLength > 0 && restOfLength <= T300ROOFPLADELENGTH) {
                for (int i = 0; i < roofWidthSurfaceCalc - tempTrapezPladeWidth + OVERLAP; i = i + tempTrapezPladeWidth) {
                    quantityOfT300++;
                    tempTrapezPladeWidth = trapezPladeWidth * 10 - OVERLAP;
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

    }

    public class Raft implements BOMItemSpecifications {
        private Construction construction;

        public Raft(Construction construction) {
        this.construction = construction;
        }

        @Override
        public int length() {
            int realMeasures = 0;
            int ideelMeasures = construction.getRoof().getWidth() - 50;

            realMeasures = ideelMeasures / WOODDIFFERENCES;

            if (ideelMeasures % WOODDIFFERENCES != 0) {
                realMeasures++;
            }
            return realMeasures;
        }

        @Override
        public int quantity() {
            int quantityRafters = 0;
            int constructionLength = construction.getRoof().getLength();
            for (int i = 0; i < constructionLength; i = i + 60) {
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

    public TrapezPlates getTrapezPlates() {
        return trapezPlates;
    }

}
