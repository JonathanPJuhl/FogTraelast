package domain.construction.Roof;

import domain.construction.UsersChoice;

public class RoofSizeCalculator{

    private final int MINPITCHDEGREEOPTION = 15;
    private final int MAXPITCHDEGREEOPTION = 45 - 1; //TODO Admin skal kunne indsætte andre tal

    public RoofSizeCalculator() {
    }

    //Hjælpemetode for bredde af tag afhægnig af type
    public int roofWidthSurface(boolean roofChoiceIsFlat, int width, double degree) {
        int roofwidth;
        if (!(roofChoiceIsFlat))
            roofwidth = pitchedRoofCalcutatedSurfaceWidth(width,degree);
        else
            roofwidth = width;
        return roofwidth;
    }

    //Hjælpemetode for længde af tag afhængnig af type
    public int roofLengthSurface(boolean roofChoiceIsFlat, int length, double degree) {
        int roofLength;
        if (!(roofChoiceIsFlat))
            roofLength = length;
        else
            roofLength = flatRoofCalcutatedSurfaceLength(length, degree);

        return roofLength;
    }

    //Beregning af tagets højde fra top rem og til øverst på tag eller tagryg
    public double roofHeight(int size, double degree) {
            double roofHeigth = (Math.tan(Math.toRadians(degree)) * size);
        return roofHeigth;
    }


    //Areal hjælpeberegning af længde af fladt tags overflade
    public int flatRoofCalcutatedSurfaceLength(int length, double degree) {
        int roofSurfaceLength;
        double roofHeigthFlatRoof = roofHeight(length, degree);
        roofSurfaceLength = (int) Math.hypot((double) length, roofHeigthFlatRoof);
        return roofSurfaceLength;
    }

    //Areal hjælpeberegning af halv bredde af tagoverflade altså den ene side af fra tagfod til tagryg
    public int pitchedRoofCalcutatedSurfaceWidth(int width, double degree) {
        double roofHeigth = roofHeight(width, degree);
        int halfRaftWidth = width / 2;
        int roofSurfaceHalfWidth = (int) (halfRaftWidth / (Math.sin(Math.toRadians(degree))));
        return roofSurfaceHalfWidth;
    }

}

