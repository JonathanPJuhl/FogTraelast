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
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.ReadOnlyFileSystemException;
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
    Construction construction;

    @Before
    public void setUp() throws Exception { //TODO Specificér exception
        //Arrange
        super.setUp();
        this.roofSizeCalculator = new RoofSizeCalculator();
        this.waistHelper = new NoWaistHelper();
        this.lengths = new TreeSet<>();
        this.widths = new TreeSet<>();
        lengths.add(6000);
        lengths.add(3000);
        widths.add(1900);
        widths.add(2900);
        roofTrapezPlates = new Material(1, "Trapez", "pink", 20.00, MaterialType.roofPlades.getDanishName(), Category.Flat.getDanishName(), 10, 100);

    }

    //TODO IntegrationTest?
    @Test
    public void testQuantitiesT600PlatesAreaForFlatRoof2900Width() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 2900;
        usersChoice = new UsersChoice(4600, 8800, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 0;
        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());


        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void testQntysT600PlatesAreaForFlatRoof1900WidthFromPDF() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 1900;
        usersChoice = new UsersChoice(7300, 3500, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 0;
        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());


        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void testQuantitiesT600PlatesAreaForFlatRoof1900Width() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 1900;
        usersChoice = new UsersChoice(4600, 8800, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);

        int qnty = 0;
        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());

        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void testQuantitiesT300PlatesAreaForFlatRoof1900Width() throws Exception {
        //Arrange
        setUp();
        int lengthT300 = 3000;
        int widthT300 = 1900;
        usersChoice = new UsersChoice(4600, 8800, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 1;

        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());

        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        }
        int actual = qnty;
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);

    }
    @Test
    public void testQuantitiesT300PlatesAreaForFlatRoof2900Width() throws Exception {
        //Arrange
        setUp();
        int lengthT300 = 3000;
        int widthT300 = 2900;
        usersChoice = new UsersChoice(4600, 8800, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 0;

        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());

        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
        for (int i = 1; i < testMethod.size(); i++) {
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
        }
        int actual = qnty;
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);

    }

    @Test
    public void testQuantitiesMoreThanOneForT600PlatesArea2900() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 2900;
        usersChoice = new UsersChoice(8600, 16800, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 0;
        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());

        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        int excepted = 6;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void testQuantitiesMoreThanOneForT600PlatesArea1900WidthRecycling() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 1900;
        usersChoice = new UsersChoice(3500, 17800, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 0;
        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());

        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        int excepted = 2;

        //Assert
        assertEquals(excepted, actual);
    }

    @Test
    public void testQuantitiesMoreThanOneForT300PlatesArea2900WidthRecycling() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 3000;
        int widthT600 = 2900;
        usersChoice = new UsersChoice(6700, 6000, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        carport = new Carport(usersChoice);
        construction = new Construction(roof, carport);
        int qnty = 0;
        int roofLengthSurface = roofSizeCalculator.roofLengthSurface(construction.getRoof().isFlat(), construction.getRoof().getLength(), construction.getRoof().getDegree());
        int roofwidthSurface = roofSizeCalculator.roofWidthSurface(construction.getRoof().isFlat(), construction.getRoof().getWidth(), construction.getRoof().getDegree());

        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod = waistHelper.quantitiesPlatesAreaCalculated(roofLengthSurface, roofwidthSurface, roofTrapezPlates, widths, lengths);
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
        int excepted = 1;

        //Assert
        assertEquals(excepted, actual);
    }

    /*//TODO Lav strategy Pattern
    @Test
    public void testQuantitiesAtSideCalculatedCarport2000Post() throws Exception {
        //Arrange
        setUp();
        int postLength = 2000;
        usersChoice = new UsersChoice(4000, 9000, "Carport", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        carport = new Carport(usersChoice);
        Roof roof = new FlatRoof(usersChoice, roofSizeCalculator);
        construction = new Construction(roof, carport);
        post = new Material(4, "Stolpe", "lys træ", 30.00, MaterialType.wood.getDanishName(), Category.Carport.getDanishName(), 95, 0);
        lengths.removeAll(lengths);
        lengths.add(2000);
        lengths.add(1000);
        widths.removeAll(widths);
        widths.add(97);
        int[] lenghtAndQnty = new int[2];
        int lengthOption;
        int qnty = 0;

        //Act
        HashMap<Integer, HashMap<Material, Integer>> testMethod = waistHelper.quantitiesAtSideCalculated(construction.getCarport().getHeight(), lengths, post);
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
//        assertEquals(excepted, actual);
    }*/

    @Test
    public void testRestConstructionPartSide() {
    }

    @Test
    public void testValidationOfSideRest() {
    }

    @Test
    public void testFromLargeToSmallOrder() {
    }

    @Test
    public void testSizeOptionsArray() {
    }

    @Test
    public void testLengthOptionsListed() {
    }

    @Test
    public void testMeassuresFromMaterialBefore() {
    }

    @Test
    public void testAddQuantityForSquare() {
    }

    @Test
    public void testAddOneMoreToSide() {
    }

    @Test
    public void testStartValueForQntyLength() {
    }

    @Test
    public void testStartValueForQntySideArea() {
    }

    @Test
    public void testQuantitySideCounter() {
    }
}