package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.material.Material;

public interface ConstructionRepository {

    public Roof createRoof(UsersChoice usersChoice);

    public Carport createCarport(UsersChoice usersChoice);

    public Shed createShed(UsersChoice usersChoice, Construction construction, Carport carport);

    public Construction createConstruction(Roof roof, Carport carport);

}
