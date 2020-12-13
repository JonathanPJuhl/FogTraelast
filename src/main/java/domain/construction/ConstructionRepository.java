package domain.construction;

import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.material.Material;

public interface ConstructionRepository {

    public Roof createRoof(String roofTypChoice, int roofHeight, Construction construction, Material cladding, int degree);
}
