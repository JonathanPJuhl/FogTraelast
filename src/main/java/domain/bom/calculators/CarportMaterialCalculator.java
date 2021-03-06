package domain.bom.calculators;

import domain.construction.Category;
import domain.construction.ConstructionPart;
import domain.construction.carport.Carport;

import java.util.HashMap;

public class CarportMaterialCalculator {

    public int carportFrameLengthFullLength(Carport carport) {

        int remResult = (carport.getWidth() + carport.getLength()) * 2;

        return remResult;
    }

    public int carportFrameBoardQnty() {

        int remResult = 4;

        return remResult;
    }

    final private int POSTWIDTH = 100;
    final private int MAXROWSISTANCE = 6000;
    final private int MAXDISTANCEPOST = 3000;

    //Taeller hvor mange stolper der er på en side af carport (eller skur)
    public int sidePostAmount(int size, int distanceMax) {
        int numberOfPost;
        if (size % distanceMax == 0) { //
            numberOfPost = (size / distanceMax)+ 1;
        } else {
            numberOfPost = (size / distanceMax) + 2;
        }
        if (size < distanceMax){
            numberOfPost = 2;
        }
        return numberOfPost;
    }

    public int qntyPost(int carportWidth, int carportLength){
        int qntyPosts = 0;
        for (int i = 0; i < carportLength ; i = i+MAXDISTANCEPOST) {
            qntyPosts += sidePostAmount(carportWidth,MAXDISTANCEPOST);
        }
        qntyPosts = qntyPosts + sidePostAmount(carportWidth,MAXDISTANCEPOST);

        return qntyPosts;
    }

    public int getPOSTWIDTH() {
        return POSTWIDTH;
    }

    public int getMAXROWSISTANCE() {
        return MAXROWSISTANCE;
    }

    public int getMAXDISTANCEPOST() {
        return MAXDISTANCEPOST;
    }
}
