package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.UsersChoice;

public class FlatRoof extends Roof {

    public static final double TILTTODEGREE = Math.toDegrees(Math.atan(3.0 / 100.0));

    public FlatRoof(UsersChoice usersChoice, RoofSizeCalculator roofSizeCalculator) {
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
