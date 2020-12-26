package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.UsersChoice;

public class FlatRoof extends Roof {

    public static final double TILTTODEGREE = Math.toDegrees(Math.atan(3.0 / 100.0)); //TODO kunne ændre det til procent?

    public FlatRoof(UsersChoice usersChoice, RoofSizeCalculator roofSizeCalculator) { //TODO Fjern roofSizeCalc
        super(usersChoice,
                TILTTODEGREE,
                usersChoice.getLength(),
                usersChoice.getWidth(),
                true,
                usersChoice.getRoofCladding(),
                Category.Flat
                );
    }


}
