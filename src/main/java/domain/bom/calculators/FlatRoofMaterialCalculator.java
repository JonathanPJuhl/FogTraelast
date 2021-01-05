package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.RoofSizeCalculator;

public class FlatRoofMaterialCalculator {
    private RoofSizeCalculator roofSizeCalculator;
    private final Raft raft;
    private final Stern overSternFront;
    private final Stern overSternSide;
    private final Stern underSternLength;
    private final Stern underSternWidth;
    private NoWasteHelper noWasteHelper;

    public FlatRoofMaterialCalculator(FlatRoof roof, RoofSizeCalculator roofSizeCalculator, NoWasteHelper noWasteHelper) {
        this.noWasteHelper = noWasteHelper;
        this.roofSizeCalculator = roofSizeCalculator;
        this.raft = new Raft(roof.getWidth(),roof.getLength());
        this.overSternFront = new Stern(roof.getWidth());
        this.overSternSide = new Stern(roof.getLength());
        this.underSternLength = new Stern(roof.getLength());
        this.underSternWidth = new Stern(roof.getWidth());

    }

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
