package domain.bom.calculators;

import domain.construction.Category;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
import domain.construction.carport.Carport;
import domain.material.Material;
import domain.material.MaterialType;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NoWaistHelperTest extends TestCase {

    Material roofTrapezPlates;
    RoofSizeCalculator roofSizeCalculator;
    UsersChoice usersChoice;
    Roof roof;
    NoWaistHelper waistHelper;
    TreeSet<Integer> lengths;
    TreeSet<Integer> widths;
    Carport carport;
    Material post;

    public void setUp() throws Exception { //TODO Specificér exception
        //Arrange
        super.setUp();
        this.roofSizeCalculator = new RoofSizeCalculator();
        this.waistHelper = new NoWaistHelper();
        this.lengths = new TreeSet<>();
        this.widths = new TreeSet<>();
    }

    //TODO IntegrationTest?
    public void testQuantitiesT600PlatesAreaForFlatRoof1090Width() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 1090;
        usersChoice = new UsersChoice(4000, 9000, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        roofTrapezPlates = new Material(1, "Trapez", "pink", 20.00, MaterialType.roofPlades.getDanishName(), Category.Flat.getDanishName(), 10, 100);
        lengths.add(6000);
        lengths.add(3000);
        widths.add(1090);
        widths.add(2090);
        int qnty = 0;


        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roof, roofTrapezPlates, widths, lengths);
        for (int i = 1; i <= testMethod.size(); i++) {
            for (Map.Entry materialAndMeassures : testMethod.get(i).entrySet()) {
                int[] messauresQnty = (int[]) materialAndMeassures.getValue();
                int lengthOption = messauresQnty[0];
                int widthOption = messauresQnty[1];
                if (widthOption == widthT600 && lengthOption == lengthT600) {
                    qnty = messauresQnty[2];
                    break;
                }
            }
            if (qnty != 0) {
                break;
            }
        }
        int actual = qnty;
        int excepted = 3;

        //Assert
        assertEquals(excepted, actual);
    }

    public void testQuantitiesT300PlatesAreaForFlatRoof1090Width() throws Exception {
        //Arrange
        setUp();
        int lengthT300 = 3000;
        int widthT300 = 1090;
        usersChoice = new UsersChoice(4000, 9000, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        roofTrapezPlates = new Material(1, "Trapez", "pink", 20.00, MaterialType.roofPlades.getDanishName(), Category.Flat.getDanishName(), 10, 100);
        lengths.add(6000);
        lengths.add(3000);
        widths.add(1090);
        widths.add(2090);
        int qnty = 0;


        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roof, roofTrapezPlates, widths, lengths);
        for (int i = 1; i <= testMethod.size(); i++) {
            for (Map.Entry materialAndMeassures : testMethod.get(i).entrySet()) {
                int[] messauresQnty = (int[]) materialAndMeassures.getValue();
                int lengthOption = messauresQnty[0];
                int widthOption = messauresQnty[1];
                if (widthOption == widthT300 && lengthOption == lengthT300) {
                    qnty = messauresQnty[2];
                    break;
                }
            }
            if (qnty != 0) {
                break;
            }
            if (qnty != 0) {
                break;
            }
        }
        int actual = qnty;
        int excepted = 4;

        //Assert
        assertEquals(excepted, actual);

    }

    public void testQuantitiesAtSideCalculatedCarport2000Post() throws Exception {
        //Arrange
        setUp();
        int postLength = 2000;
        usersChoice = new UsersChoice(4000, 9000, "Carport", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        carport = new Carport(usersChoice);
        post = new Material(4, "Stolpe", "lys træ", 30.00, MaterialType.wood.getDanishName(), Category.Carport.getDanishName(), 95, 0);
        lengths.add(2000);
        lengths.add(1000);
        widths.add(97);
        int[] lenghtAndQnty = new int[2];
        int lengthOption;
        int qnty = 0;

        //Act
        HashMap<Integer, HashMap<Material, Integer>> testMethod = waistHelper.quantitiesAtSideCalculated(carport.getHeight(), lengths, post);
        for (int i = 1; i <= testMethod.size(); i++) {
            for (Map.Entry materialAndMeassures : testMethod.get(i).entrySet()) {
                lenghtAndQnty = (int[]) materialAndMeassures.getValue();
                lengthOption = lenghtAndQnty[0];
                if (lengthOption == postLength) {
                    qnty = lenghtAndQnty[1];
                    break;
                }
            }
            if (qnty != 0) {
                break;
            }
            if (qnty != 0) {
                break;
            }
        }

        int actual = qnty;
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);
    }

    public void testRestConstructionPartSide() {
    }

    public void testValidationOfSideRest() {
    }

    public void testFromLargeToSmallOrder() {
    }

    public void testSizeOptionsArray() {
    }

    public void testLengthOptionsListed() {
    }

    public void testMeassuresFromMaterialBefore() {
    }

    public void testAddQuantityForSquare() {
    }

    public void testAddOneMoreToSide() {
    }

    public void testStartValueForQntyLength() {
    }

    public void testStartValueForQntySideArea() {
    }

    public void testQuantitySideCounter() {
    }
}