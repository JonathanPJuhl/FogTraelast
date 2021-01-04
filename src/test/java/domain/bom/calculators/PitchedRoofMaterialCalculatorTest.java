package domain.bom.calculators;

import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.PitchedRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PitchedRoofMaterialCalculatorTest {

    UsersChoice usersChoice;
    Roof roof;
    Carport carport;
    Construction construction;
    RoofSizeCalculator roofSizeCalculator;
    PitchedRoofMaterialCalculator pitchedRoofMaterialCalculator;

    @Before
    public void setUp() throws Exception {
        //Arrange
        usersChoice = new UsersChoice(6000, 7800, "Pitched", 0, 0, null, null, null, 20, 0, 0, null);
        roofSizeCalculator = new RoofSizeCalculator();
        roof = new PitchedRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        pitchedRoofMaterialCalculator = new PitchedRoofMaterialCalculator(construction.getPartForConstruction());
    }

    @Test
    public void amountOfRoofTiles() {
        //Arrange
        int tilesQnty;

        //Act
        tilesQnty = pitchedRoofMaterialCalculator.amountOfRoofTiles();
        int actual = tilesQnty;
        int excepted = 780;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void screwsForVindskederCalculated() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.screwsForVindskederCalculated();
        int actual = qnty;
        int excepted = 24;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void screwsForVandbrætCalculated() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.screwsForVandbrætCalculated();
        int actual = qnty;
        int excepted = 44;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void amountOfBeslagScrewsForToplægteCalculated() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.amountOfBeslagScrewsForToplægteCalculated();
        int actual = qnty;
        int excepted = 48;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void spærPlankLengthPerSpær() {
        //Arrange
        int lenght;

        //Act
        lenght = pitchedRoofMaterialCalculator.spærPlankLengthPerSpær();
        int actual = lenght;
        int excepted = 15728;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void spærQuantity() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.spærQuantity();
        int actual = qnty;
        int excepted = 9;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void spærFullQuantityOfPlanksTotal() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.spærFullQuantityOfPlanksTotal();
        int actual = qnty;
        int excepted = 141552;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void gavlOverlayQuantity() {
        //Arrange
        int qnty;
        int gavlOverlayWidth = 200;
        int plankLengthAvailable = 2000;

        //Act
        qnty = pitchedRoofMaterialCalculator.gavlOverlayQuantity(gavlOverlayWidth,plankLengthAvailable);
        int actual = qnty;
        int excepted = 19;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void quantityRygsten() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.quantityRygsten();
        int actual = qnty;
        int excepted = 24;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void amountOfStern() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.amountOfStern();
        int actual = qnty;
        int excepted = 4;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void qntyOfRoofFirstLathBothSides() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.qntyOfRoofFirstLathBothSides();
        int actual = qnty;
        int excepted = 2;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void qntyOfRoofLathBothSides() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.qntyOfRoofLathBothSides();
        int actual = qnty;
        int excepted = 18;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void amountOfVindskeder() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.amountOfVindskeder();
        int actual = qnty;
        int excepted = 4;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void amountOfVandbraet() {
        //Arrange
        int qnty;

        //Act
        qnty = pitchedRoofMaterialCalculator.amountOfVandbraet();
        int actual = qnty;
        int excepted = 2;

        //Assert
        assertEquals(excepted, actual);
    }
}