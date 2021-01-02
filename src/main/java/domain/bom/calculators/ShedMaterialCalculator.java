package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.construction.Construction;
import domain.construction.shed.Shed;

public class ShedMaterialCalculator {

    private Construction construction;
    private final CarportMaterialCalculator carportMaterialCalculator;
    private final Shed shed;
    private final Stern stern;
    private final PostsWithShed postAdded;
    private final Rim rim;
    private final LoesHolter loesHolterSide;
    private final LoesHolter loesHolterBack;
    private final int lengthLoesHolterFront;
    private final int quanityLoesHolterFront;

    public ShedMaterialCalculator(Construction construction){
        this.construction = construction;
        this.carportMaterialCalculator = new CarportMaterialCalculator(construction);
        this.shed = (Shed) construction.getPartForConstruction().get("shed");
        this.stern = new Stern();
        this.postAdded = new PostsWithShed();
        this.rim = new Rim(); //TODO RET NAVN VIGTIGT
        this.loesHolterSide = new LoesHolter(shed.getLength()); // TODO d. 18 december Kig på Her omkring shed = null;
        this.loesHolterBack = new LoesHolter(shed.getWidth());
        this.lengthLoesHolterFront = (int) Math.hypot(postAdded.sidePostFront(shed.getWidth()), shed.getHeigth());
        this.quanityLoesHolterFront = postAdded.sidePostFront(shed.getWidth())-1;
    }

    public class LoesHolter implements BOMItemSpecifications{

        private final int side;

        public LoesHolter(int side) {
            this.side = side;
        }

        @Override
        public int length() {
            int distanceBetweenPostsSide = postAdded.sidePostAmount(side);
            double length = Math.hypot(distanceBetweenPostsSide, shed.getHeigth());
            return (int) length;
        }

        @Override
        public int width(int widthFromDB) {
            return widthFromDB;
        }

        @Override
        public int quantity() {
            int quantity = postAdded.sidePostAmount(side)-1;
            return quantity;
        }

        @Override
        public String description(String adminDescription) {
            return null;
        }
    }

    public class Stern implements BOMItemSpecifications{

        @Override
        public int length() {
            Shed shed = (Shed) construction.getPartForConstruction().get("shed");
            return shed.getLength()*2; //TODO Ændre beregning
        }

        @Override
        public int width(int widthFromDB) {
            return widthFromDB;
        }

        @Override
        public int quantity() {
            return 1;
        }

        @Override
        public String description(String adminDescription) {
            return adminDescription;
        }
    }

    public class Rim implements BOMItemSpecifications{

        Stern stern = getStern();

        @Override
        public int length() {
            return stern.length();
        }

        @Override
        public int width(int widthFromDB) {
            return 0;
        }

        @Override
        public int quantity() {
            return stern.quantity();
        }

        @Override
        public String description(String adminDescription) {
            return adminDescription;
        }
    }

    public class PostsWithShed implements BOMItemSpecifications {

        private final int maxDistancePost = 3000;
        private final int DOORWIDTH = 1000;

        public int sidePostAmount(int size) {
            return carportMaterialCalculator.sidePostAmount(size, maxDistancePost);
        }

        public int sidePostFront(int sideSize){
            int postsForSide = sidePostAmount(sideSize - DOORWIDTH);
            //Dør giver plus en da vi genbruger en af de stolper der er i den ene side
            postsForSide++;
            return postsForSide;
        }

        @Override
        public int length() {
            return shed.getHeigth();
        }

        @Override
        public int width(int widthFromDB) {
            return widthFromDB;
        }

        @Override
        public int quantity() {
            int postQuantity = sidePostAmount(shed.getLength());
            postQuantity =+ sidePostFront(shed.getWidth());
            postQuantity =+ sidePostAmount(shed.getWidth());
            return postQuantity;
        }

        @Override
        public String description(String adminDescription) {
            return adminDescription;
        }
    }


    public Stern getStern() {
        return stern;
    }

    public PostsWithShed getPostAdded() {
        return postAdded;
    }

    public Rim getRim() {
        return rim;
    }

    public LoesHolter getLoesHolterSide() {
        return loesHolterSide;
    }

    public LoesHolter getLoesHolterBack() {
        return loesHolterBack;
    }

    public int getLengthLoesHolterFront() {
        return lengthLoesHolterFront;
    }

    public int getQuanityLoesHolterFront() {
        return quanityLoesHolterFront;
    }
}
