package domain.bom.calculators;

import domain.bom.BOMItemSpecifications;
import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.ConstructionPart;
import domain.construction.Roof.Roof;
import domain.construction.shed.Shed;

import java.util.HashMap;

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

    public ShedMaterialCalculator(HashMap<Category, ConstructionPart> construction){
        this.carportMaterialCalculator = new CarportMaterialCalculator();
        this.shed = (Shed) construction.get(Category.Shed);
        this.stern = new Stern(this.shed);
        this.postAdded = new PostsWithShed(carportMaterialCalculator, this.shed);
        this.rim = new Rim(this.stern); //TODO RET NAVN VIGTIGT
        this.loesHolterSide = new LoesHolter(this.shed.getLength(), this.postAdded);
        this.loesHolterBack = new LoesHolter(this.shed.getWidth(), this.getPostAdded());
        this.lengthLoesHolterFront = (int) Math.hypot(postAdded.sidePostFront(this.shed.getWidth()), this.shed.getHeigth());
        this.quanityLoesHolterFront = postAdded.sidePostFront(this.shed.getWidth())-1;
    }

    public class LoesHolter implements BOMItemSpecifications{

        private final int side;
        private final PostsWithShed postAdded;

        public LoesHolter(int side, PostsWithShed postAdded) {
            this.side = side;
            this.postAdded = postAdded;
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
        public String description(String adminDescription){
            return adminDescription;
        }
    }

    public class Stern implements BOMItemSpecifications{

        private final Shed shed;

        public Stern(Shed shed) {
            this.shed = shed;
        }

        @Override
        public int length() {
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

        private final Stern stern;

        public Rim(Stern stern) {
            this.stern = stern;
        }

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

        private final Shed shed;
        private final CarportMaterialCalculator carportMaterialCalculator;

        public PostsWithShed(CarportMaterialCalculator carportMaterialCalculator, Shed shed) {
            this.carportMaterialCalculator = carportMaterialCalculator;
            this.shed = shed;
        }

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
