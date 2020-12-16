package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;

public interface ConstructionRepository { //TODO navngiv til andet navn f.eks. -factory

    public Roof createRoof(UsersChoice usersChoice);

    public Carport createCarport(UsersChoice usersChoice);

    public Shed createShed(UsersChoice usersChoice);

    public Construction createConstruction(Roof roof, Carport carport);
}
