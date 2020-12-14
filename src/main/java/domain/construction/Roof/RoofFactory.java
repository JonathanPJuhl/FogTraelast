package domain.construction.Roof;

import domain.construction.Construction;
import domain.construction.ConstructionRepository;
import domain.material.Material;

public class RoofFactory implements ConstructionRepository {


    @Override
    public Roof createRoof(String roofTypChoice, int roofHeight, Construction construction, Material cladding, int degree) {
        int width = construction.getWidth();
        int length = construction.getLength();
        Roof roof;
        if (roofTypChoice.equals("flat")){
            roof = new FlatRoof(roofHeight, length, width, cladding);
        }else{
            roof = new PitchedRoof(roofHeight,length, width,cladding, degree);
        }
        return roof;
    }

}
