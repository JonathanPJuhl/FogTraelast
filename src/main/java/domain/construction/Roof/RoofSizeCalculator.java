package domain.construction.Roof;

import domain.construction.Construction;
import domain.construction.ConstructionRepository;

import java.util.spi.CalendarDataProvider;

public class RoofSizeCalculator{

    private double roofHeigth;
    private final int MINPITCHDEGREEOPTION = 15;
    private final int MAXPITCHDEGREEOPTION = 45 - 1; //TODO Admin skal kunne indsætte andre tal

    //Hjælpemetode for bredde af tag afhægnig af type
    public int roofWidthSurface(Construction construction) {
        int roofwidth;
        if (!(construction.getRoof().isFlat()))
            roofwidth = pitchedRoofCalcutatedSurfaceWidth(construction);
        else
            roofwidth = construction.getWidth();
        return roofwidth;
    }

    //Hjælpemetode for længde af tag afhængnig af type
    public int roofLengthSurface(Construction construction) {
        int roofLength;
        if (!(construction.getRoof().isFlat()))
            roofLength = construction.getLength();
        else
            roofLength = flatRoofCalcutatedSurfaceLength(construction);

        return roofLength;
    }

    //Beregning af tagets højde fra top rem og til øverst på tag eller tagryg
    public int roofHeight(Construction construction, boolean flatRoof, int length, int width) {
        if (!(flatRoof))
            roofHeigth = (Math.tan(Math.toRadians(construction.getRoof().getDegree())) * width / 2);
        else
            roofHeigth = (Math.tan(Math.toRadians(construction.getRoof().getDegree())) * length);
        return (int) roofHeigth;
    }


    //Areal hjælpeberegning af længde af fladt tags overflade
    public int flatRoofCalcutatedSurfaceLength(Construction construction) {
        int roofLength;
        roofHeigth = roofHeight(construction,false, construction.getLength(), construction.getWidth());
        roofLength = (int) Math.hypot((double) construction.getLength(), (double) roofHeigth);
        return roofLength;
    }

    //Areal hjælpeberegning af halv bredde af tagoverflade altså den ene side af fra tagfod til tagryg
    public int pitchedRoofCalcutatedSurfaceWidth(Construction construction) {
        int halfRaftWidthForPitchedRoof = construction.getWidth() / 2;
        int roofHalfWidth = (int) (halfRaftWidthForPitchedRoof / (Math.cos(
                Math.toRadians(construction.getRoof().getDegree()))));
        return roofHalfWidth;
    }

}

