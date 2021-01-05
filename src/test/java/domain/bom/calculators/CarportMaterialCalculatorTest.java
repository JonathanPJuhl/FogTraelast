package domain.bom.calculators;

import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.material.Material;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarportMaterialCalculatorTest {

    UsersChoice usersChoice;
    Roof roof;
    Carport carport;
    Construction construction;
    RoofSizeCalculator roofSizeCalculator;
    CarportMaterialCalculator carportMaterialCalculator;


    @Before
    public void setUp() throws Exception {
        //Arrange
        usersChoice = new UsersChoice(6000, 7800, "Flat", 0, 0, null, null, null, 0, 0, 0, null);
        roofSizeCalculator = new RoofSizeCalculator();
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        carportMaterialCalculator = new CarportMaterialCalculator();
    }

    @Test
    public void remLengthTotalLength() {
        //Arrange
        int remLength;

        //Act
        remLength = carportMaterialCalculator.carportFrameLengthFullLength(construction.getCarport());
        int actual = remLength;
        int excepted = 26880;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void remQnty() {
        //Arrange
        int remQnty;
        remQnty = carportMaterialCalculator.carportFrameBoardQnty();

        //Act
        int actual = remQnty;
        int excepted = 4;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void sidePostAmountLength() {
        //Arrange
        int qntySidePost;
        qntySidePost = carportMaterialCalculator.sidePostAmount(carport.getLength(), carportMaterialCalculator.getMAXDISTANCEPOST());
        //Act
        int actual = qntySidePost;
        int excepted = 4;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void qntyPost() {
        //Arrange
        int qntySidePost;
        qntySidePost = carportMaterialCalculator.qntyPost(carport.getWidth(),carport.getLength());

        //Act
        int actual = qntySidePost;
        int excepted = 12;

        //Assert
        assertEquals(excepted, actual);
    }
}