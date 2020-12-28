package domain.construction.Roof;

public class RoofSizeCalculator {

    //TODO Refactorer!
    private final int MINPITCHDEGREEOPTION = 15;
    private final int MAXPITCHDEGREEOPTION = 45 - 1; //TODO Admin skal kunne indsætte andre tal

    public RoofSizeCalculator() {
    }

    //Hjælpemetode for bredde af tag afhægnig af type
    public int roofWidthSurface(boolean roofChoiceIsFlat, int constructionWidth, double degree) {
        int roofwidth;
        if (!(roofChoiceIsFlat)) {
            roofwidth = pitchedRoofCalcutatedHalfSurfaceWidth(constructionWidth, degree);
        } else {
            roofwidth = constructionWidth;
        }
        return roofwidth;
    }

    //Hjælpemetode for længde af tag afhængnig af type
    public int roofLengthSurface(boolean roofChoiceIsFlat, int construcitonLength, double degree) {
        int roofLength;
        if (!(roofChoiceIsFlat)) {
            roofLength = construcitonLength;
        } else {
            roofLength = flatRoofCalcutatedSurfaceLength(construcitonLength, degree);
        }
        return roofLength;
    }

    //Beregning af tagets højde fra top rem og til øverst på tag eller tagryg
    public double roofHeight(int size, double degree) {
        double roofHeigth = (Math.tan(Math.toRadians(degree)) * size);
        return roofHeigth;
    }


    //Areal hjælpeberegning af længde af fladt tags overflade
    public int flatRoofCalcutatedSurfaceLength(int constructionLength, double degree) {
        int roofSurfaceLength;
        double roofHeigthFlatRoof = roofHeight(constructionLength, degree);
        roofSurfaceLength = (int) Math.hypot((double) constructionLength, roofHeigthFlatRoof);
        return roofSurfaceLength;
    }

    //Areal hjælpeberegning af halv bredde af tagoverflade altså den ene side af fra tagfod til tagryg
    public int pitchedRoofCalcutatedHalfSurfaceWidth(int constructionWidth, double degree) {
        double roofHeigth = roofHeight(constructionWidth, degree);
        int halfRaftWidth = constructionWidth / 2;
        int roofSurfaceHalfWidth = (int) (halfRaftWidth / (Math.cos(Math.toRadians(degree))));
        return roofSurfaceHalfWidth;
    }

}

