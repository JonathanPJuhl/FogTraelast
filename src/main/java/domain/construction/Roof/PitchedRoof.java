package domain.construction.Roof;

import domain.construction.Category;
import domain.construction.UsersChoice;

public class PitchedRoof extends Roof {

    public PitchedRoof(UsersChoice usersChoice, RoofSizeCalculator roofSizeCalculator) {
        super(roofSizeCalculator.roofHeight(usersChoice.getWidth(),usersChoice.getDegree()),
                usersChoice.getLength(), usersChoice.getWidth(), false, usersChoice.getRoofCladding(),
                usersChoice.getDegree(), Category.Pitched, usersChoice);
    }


}
