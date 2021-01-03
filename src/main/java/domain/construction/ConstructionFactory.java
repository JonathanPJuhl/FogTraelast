package domain.construction;

import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.PitchedRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.construction.shed.TooLargeException;

public class ConstructionFactory implements ConstructionRepository {

private RoofSizeCalculator roofSizeCalculator;

    @Override
    public Roof createRoof(UsersChoice usersChoice) {
        roofSizeCalculator = new RoofSizeCalculator();
        int width = usersChoice.getWidth();
        int length = usersChoice.getLength();
        Roof roof;
        if (usersChoice.roofChoiceConverter(usersChoice.getRoofChoice())){
            roof = new FlatRoof(usersChoice, roofSizeCalculator);
        }else{
            roof = new PitchedRoof(usersChoice, roofSizeCalculator);
        }
        return roof;
    }

    @Override
    public Carport createCarport(UsersChoice usersChoice) {
        return new Carport(usersChoice);
    }

    @Override
    public Shed createShed(UsersChoice usersChoice, Construction construction, Carport carport)  throws TooLargeException {
        Shed shed = null;
        if(usersChoice.getShedLength() < construction.getCarport().getLength() || usersChoice.getShedwidth() < construction.getCarport().getLength()) {
             shed = new Shed(usersChoice, construction, usersChoice.getShedAndCarportCladding());
            shed.addCladdingToShed(usersChoice.getShedAndCarportCladding(), carport);
        }else{
            throw new TooLargeException("User chosed too big messures for shed - length: " + usersChoice.getShedwidth()
                    + " and width: " + usersChoice.getShedLength() +", where carport - length:" + construction.getCarport().getLength() + " and width: " + construction.getCarport().getWidth() );
        }
        return shed;
    }

    @Override
    public Construction createConstruction(Roof roof, Carport carport) {
        return new Construction(roof, carport);
    }


}
