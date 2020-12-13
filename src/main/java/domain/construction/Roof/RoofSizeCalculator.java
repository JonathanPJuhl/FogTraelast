package domain.construction.Roof;

import domain.construction.Construction;

public class RoofSizeCalculator {

    private final Construction construction;
    private double roofHeigth;
    private final int MINPITCHDEGREEOPTION = 15;
    private final int MAXPITCHDEGREEOPTION = 45 - 1; //TODO Admin skal kunne indsætte andre tal


    public RoofSizeCalculator(Construction construction) {
        this.construction = construction;
    }

    //Hjælpemetode for bredde af tag afhægnig af type
    public int roofWidthSurface() {
        int roofwidth;
        if (!(construction.getRoof().isFlat()))
            roofwidth = pitchedRoofCalcutatedSurfaceWidth();
        else
            roofwidth = construction.getWidth();
        return roofwidth;
    }

    //Hjælpemetode for længde af tag afhængnig af type
    public int roofLengthSurface() {
        int roofLength;
        if (!(construction.getRoof().isFlat()))
            roofLength = construction.getLength();
        else
            roofLength = flatRoofCalcutatedSurfaceLength();

        return roofLength;
    }

    //Beregning af tagets højde fra top rem og til øverst på tag eller tagryg
    public int roofHeight(boolean flatRoof, int length, int width) {
        if (!(flatRoof))
            roofHeigth = (Math.tan(Math.toRadians(construction.getRoof().getDegree())) * width / 2);
        else
            roofHeigth = (Math.tan(Math.toRadians(construction.getRoof().getDegree())) * length);
        return (int) roofHeigth;
    }


    //Areal hjælpeberegning af længde af fladt tags overflade
    public int flatRoofCalcutatedSurfaceLength() {
        int roofLength;
        roofHeigth = roofHeight(false, construction.getLength(),
                construction.getWidth());
        roofLength = (int) Math.hypot((double) construction.getLength(), (double) roofHeigth);
        return roofLength;
    }

    //Areal hjælpeberegning af halv bredde af tagoverflade altså den ene side af fra tagfod til tagryg
    public int pitchedRoofCalcutatedSurfaceWidth() {
        int halfRaftWidthForPitchedRoof = construction.getWidth() / 2;
        int roofHalfWidth = (int) (halfRaftWidthForPitchedRoof / (Math.cos(
                Math.toRadians(construction.getRoof().getDegree()))));
        return roofHalfWidth;
    }
}

