package domain.bom.calculators;

import domain.construction.Category;
import domain.construction.Roof.FlatRoof;
import domain.construction.Roof.Roof;
import domain.construction.Roof.RoofSizeCalculator;
import domain.construction.UsersChoice;
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

    public void setUp() throws Exception { //TODO Specificér exception
        //Arrange
        super.setUp();
        this.roofSizeCalculator = new RoofSizeCalculator();
        this.waistHelper = new NoWaistHelper();
        this.lengths = new TreeSet<>();
        this.widths = new TreeSet<>();
    }

    public void testQuantitiesT600PlatesAreaForFlatRoof2000Width() throws Exception {
        //Arrange
        setUp();
        int lengthT600 = 6000;
        int widthT600 = 1090;
        usersChoice = new UsersChoice(4000, 9000, "Flat", 0, 0, roofTrapezPlates, 0, 0, 0, null);
        roof = new FlatRoof(usersChoice, roofSizeCalculator);
        roofTrapezPlates = new Material(1, "Trapez", "pink", 20.00, MaterialType.roofPlades.getDanishName(), Category.Flat.getDanishName(), 10, 10);
        lengths.add(6000);
        lengths.add(3000);
        widths.add(1090);
        widths.add(2090);
        int qnty = 0;


        //Act
        HashMap<Integer, HashMap<Material, int[]>> testMethod= waistHelper.quantitiesPlatesAreaCalculated(roof,roofTrapezPlates,widths,lengths);
        for (int i = 0; i <= testMethod.size(); i++) {
            for (Map.Entry materialAndMeassures : testMethod.get(i).entrySet()) {
                int[] messauresQnty = (int[]) materialAndMeassures.getKey();
                int lengthOption = messauresQnty[0];
                int widthOption = messauresQnty[1];
                if ( widthOption == widthT600 &&  lengthOption == lengthT600){
                    qnty = messauresQnty[3];
                }
            }
        }
        int actual = qnty;
        int excepted = 3;

        //Assert
        assertEquals(excepted,actual);
    }

}