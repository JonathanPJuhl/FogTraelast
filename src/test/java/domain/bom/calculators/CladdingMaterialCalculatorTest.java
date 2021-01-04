package domain.bom.calculators;

import domain.construction.Category;
import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.material.Material;
import domain.material.MaterialType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CladdingMaterialCalculatorTest {

    Material cladding;
    UsersChoice usersChoice;
    Roof roof;
    Carport carport;
    Construction construction;
    RoofSizeCalculator roofSizeCalculator;
    CladdingMaterialCalculator claddingMaterialCalculator;

    @Before
    public void setUp() throws Exception {
        //Arrange
        usersChoice = new UsersChoice(6000, 7800, "Flat", 0, 0, null, null, null, 0, 0, 0, null);
        roofSizeCalculator = new RoofSizeCalculator();
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);

    }

    @Test
    public void quantityPicesOfMaterialPerConstrucitonLength() {
        //Arrange
        int qnty;
        cladding = new Material(0, "lyst egetræ","blue",0.0, "Beklædning", "træ",200, 10);
        claddingMaterialCalculator = new CladdingMaterialCalculator(cladding.getHeight(), carport.getLength(),construction.getCarport().getHeight());
        //Act
        qnty = claddingMaterialCalculator.quantity();
        int actual = qnty;
        int excepted = 13;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void quantityPicesOfMaterialPerConstrucitonWidth() {
        //Arrange
        int qnty;
        cladding = new Material(0, "lyst egetræ","blue",0.0, "Beklædning", "træ",200, 10);
        claddingMaterialCalculator = new CladdingMaterialCalculator(cladding.getHeight(), carport.getWidth(),construction.getCarport().getHeight());
        //Act
        qnty = claddingMaterialCalculator.quantity();
        int actual = qnty;
        int excepted = 13;

        //Assert
        assertEquals(excepted, actual);
    }
}