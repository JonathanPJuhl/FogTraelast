package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.UsersChoice;

public class PitchedRoof extends Roof {

    public PitchedRoof(UsersChoice usersChoice, RoofSizeCalculator roofSizeCalculator) {
        super(usersChoice,
                usersChoice.getDegree(),
                usersChoice.getLength(),
                usersChoice.getWidth(),
                false,
                usersChoice.getRoofCladding(),
                Category.Pitched);
    }


}
