package domain.construction.Roof;

import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.material.Material;
import junit.framework.TestCase;
import domain.construction.Construction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RoofSizeCalculatorTest extends TestCase {

    RoofSizeCalculator roofSizeCalculator;
    Construction construction;
    Carport carport;
    Roof roof;
    UsersChoice usersChoice;
    int width;
    int length;
    String roofChoice;
    int shedOrNo;
    int claddingChoice;
    double degree;
    Material roofMaterial;

    @Before
    public void setUp() throws Exception {
        //Arrange
        roofSizeCalculator = new RoofSizeCalculator();
        shedOrNo = 0;
        claddingChoice = 0;
        roofMaterial = null;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFlatRoofCalcutatedSurfaceLength() {
        //Arrange
        width = 4000;
        length = 9000;
        roofChoice = "Flat";
        usersChoice = new UsersChoice(width,length,roofChoice,shedOrNo,claddingChoice, null, null);
        carport = new Carport(usersChoice);
        roof = new FlatRoof(usersChoice,roofSizeCalculator);
        construction = new Construction(roof,carport);

        //Act
        int actual = roofSizeCalculator.flatRoofCalcutatedSurfaceLength(construction.getRoof().getLength(), construction.getRoof().getDegree());
        int excpeted = 0;

        //Assert
        assertEquals(excpeted, actual);
    }

    @Test
    public void testPitchedRoofCalcutatedSurfaceWidth() {
        //Arrange
        width = 4000;
        length = 9000;
        roofChoice = "Pitched";
        degree = 40;

        usersChoice = new UsersChoice(width,length,roofChoice,shedOrNo,claddingChoice,null,null,roofMaterial,degree,0,0,null);
        carport = new Carport(usersChoice);
        roof = new PitchedRoof(usersChoice,roofSizeCalculator);
        construction = new Construction(roof,carport);

        //Act
        int actual = roofSizeCalculator.pitchedRoofCalcutatedHalfSurfaceWidth(construction.getRoof().getWidth(), construction.getRoof().getDegree());
        int excpeted = 0;

        //Assert
        assertEquals(excpeted, actual);
    }

    @Test
    public void testSetRoofHeightFlatRoof() {
        //Arrange
        width = 4000;
        length = 9000;
        roofChoice = "Flat";
        usersChoice = new UsersChoice(width,length,roofChoice,shedOrNo,claddingChoice, null,null);
        carport = new Carport(usersChoice);
        Roof roof = new FlatRoof(usersChoice,roofSizeCalculator);
        construction = new Construction(roof,carport);

        //Act
        double actual = roofSizeCalculator.roofHeight(construction.getRoof().getLength(), construction.getRoof().getDegree());
        double excpeted = 0.0;

        //Assert
        assertEquals(excpeted, actual);
    }
}