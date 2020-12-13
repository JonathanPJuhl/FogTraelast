package domain.construction;

import domain.construction.Roof.Roof;

public interface ConstructionRepository {

    public Roof createRoof(Category category, Construction construction);
}
