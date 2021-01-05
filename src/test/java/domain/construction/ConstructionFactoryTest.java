package domain.construction;

import domain.bom.calculators.CarportMaterialCalculator;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.carport.Carport;
import domain.construction.shed.Shed;
import domain.construction.shed.TooLargeException;
import domain.material.Material;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConstructionFactoryTest {

    Material cladding;
    UsersChoice usersChoice;
    Roof roof;
    Carport carport;
    Construction construction;
    RoofSizeCalculator roofSizeCalculator;
    ConstructionFactory constructionFactory;

    @Before
    public void setUp() throws Exception {
        //Arrange
        roofSizeCalculator = new RoofSizeCalculator();
        cladding = new Material(0, "lyst egetræ","blue",0.0, "Beklædning", "træ",200, 10);
        constructionFactory = new ConstructionFactory();
    }

    //Negativ Test
    @Test (expected = TooLargeException.class)
    public void createTooBigShed() throws TooLargeException {
        //Arrange
        int shedLength = 90000;
        int shedWidth = 60000;
        usersChoice = new UsersChoice(6000, 7800, "Flat", 1, 0, null, null, null, 0, shedLength , shedWidth, cladding);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);

        //Act
        Shed result = constructionFactory.createShed(usersChoice,construction,carport);

    }

    //Negativ Test
    @Test (expected = TooLargeException.class)
    public void createTooBigButSameRoofWidthShed() throws TooLargeException {
        //Arrange
        int shedLength = 2000;
        int shedWidth = 6000;
        usersChoice = new UsersChoice(6000, 7800, "Flat", 1, 0, null, null, null, 0, shedLength , shedWidth, cladding);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);

        //Act
        Shed result = constructionFactory.createShed(usersChoice,construction,carport);

    }

}