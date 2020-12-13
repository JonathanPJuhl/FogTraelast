package domain.bom.calculators;

import domain.construction.Construction;

public class CarportMaterialCalculator {



    final private int POSTWIDTH =100;
    final private int MAXPOSTDISTANCE=3000; //TODO er det 3 m fra side til side?
    final private int MAXROWSISTANCE=6000; //TODO er det 6 m fra side til side?

    //counts how many posts should there be on one side of a carport or a shed
    public int sidePostAmount(int size){
        int numberOfPost;
        size=size- POSTWIDTH;
        if(size%MAXPOSTDISTANCE==0){ //
            numberOfPost=size/MAXPOSTDISTANCE+1;
        } else {
            numberOfPost= (( size - size%MAXPOSTDISTANCE)/MAXPOSTDISTANCE)+2;
        }
        return numberOfPost;
    }


    //counts distance between posts on the side
    public int postColumns(int length) {
        return (length- POSTWIDTH)/(sidePostAmount(length)-1);
    }

    //counts how many rows of post should there be because max distance between posts
    public int postRows (int width){
        int rows;
        if(width%MAXROWSISTANCE==0){
            rows=width/MAXROWSISTANCE+1;
        } else {
            rows=(width - width%MAXROWSISTANCE)/MAXROWSISTANCE +2;
        }
        return  rows;
    }

    public static int roofSpaerAmount (Construction construction){
        //todo return number of spaer needed for whole construction length
        //todo in ConstrucionMaterialCalculator implement method that will return  2 Materials of beslag
        // - one for left and one for right with amount of number of spaer and one Material that is the beslag skruer where the amount is roofSpaernumber x2x3x3

        // Der skal være max 550 mm mellem spærne
        int constructionLength = construction.getLength();
        double almostSpaerAmount = constructionLength/550.0 +1;
        int spaerAmount = (int) Math.round(almostSpaerAmount);
        return spaerAmount;
    }

    public int getPOSTWIDTH() {
        return POSTWIDTH;
    }
}
