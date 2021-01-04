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
        int excpeted = 9004;

        //Assert
        assertEquals(excpeted, actual);
    }


    @Test
    public void testPitchedRoofCalcutatedHalfSurfaceWidth() {
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
        int excpeted = 2610;

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
        double excpeted = 269.99999974;

        //Assert
        assertEquals(excpeted, actual, 0.03);
    }

    @Test
    public void testSetRoofHeightPitchedRoof() {
        //Arrange
        width = 4000;
        length = 9000;
        degree = 30;
        roofChoice = "Pitched";
        usersChoice = new UsersChoice(width,length,roofChoice,shedOrNo,claddingChoice,null,null,roofMaterial,degree,0,0,null);
        carport = new Carport(usersChoice);
        Roof roof = new PitchedRoof(usersChoice,roofSizeCalculator);
        construction = new Construction(roof,carport);
        int roofHalfWidth = width/2;

        //Act
        double actual = roofSizeCalculator.roofHeight(roofHalfWidth, construction.getRoof().getDegree());
        double excpeted = 1154.70;

        //Assert
        assertEquals(excpeted, actual, 0.02);
    }

    @Test
    public void testSetRoofHeightPitchedRoofWith20Degrees() {
        //Arrange
        width = 4000;
        length = 9000;
        degree = 20;
        roofChoice = "Pitched";
        usersChoice = new UsersChoice(width,length,roofChoice,shedOrNo,claddingChoice,null,null,roofMaterial,degree,0,0,null);
        carport = new Carport(usersChoice);
        Roof roof = new PitchedRoof(usersChoice,roofSizeCalculator);
        construction = new Construction(roof,carport);
        int roofHalfWidth = width/2;

        //Act
        double actual = roofSizeCalculator.roofHeight(roofHalfWidth, construction.getRoof().getDegree());
        double excpeted = 727.940;

        //Assert
        assertEquals(excpeted, actual, 0.05);
    }

    @Test
    public void getRoofHeight() {
    }
}