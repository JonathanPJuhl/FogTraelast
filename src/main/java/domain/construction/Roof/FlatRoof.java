package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.UsersChoice;

public class FlatRoof extends Roof {

    public static final double TILTTODEGREE = Math.round(Math.toDegrees(Math.atan(3.0 / 100.0))); //TODO kunne ændre?

    public FlatRoof(UsersChoice usersChoice, RoofSizeCalculator roofSizeCalculator) {
        super(TILTTODEGREE,
                usersChoice.getLength(),
                usersChoice.getWidth(),
                true,
                usersChoice.getRoofCladding(),
                roofSizeCalculator.roofHeight(usersChoice.getLength(), TILTTODEGREE),
                Category.Flat,
                usersChoice);
    }


}
