package domain.construction.Roof;

import domain.construction.Cladding;
import domain.construction.Construction;
import domain.construction.ConstructionRepository;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;

public class ConstructionFactory implements ConstructionRepository { /*TODO Ændres til ConstructionFactory og flyttes*/

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
    public Shed createShed(UsersChoice usersChoice, Construction construction, Carport carport) {
        Shed shed = new Shed(usersChoice, construction, usersChoice.getShedAndCarportCladding());
        shed.addCladdingToShed(usersChoice.getShedAndCarportCladding(),carport);
        return shed;
    }

    @Override
    public Construction createConstruction(Roof roof, Carport carport) {
        return new Construction(roof, carport);
    }


 /*public boolean roofChoiceConverter(String roofTypeChoice){
        if(roofTypeChoice.equals("Flat")){
            return true;
        } else {
            return false;
        }
    }*/

}
