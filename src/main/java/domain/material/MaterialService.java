package domain.material;

import com.sun.source.tree.Tree;
import domain.bom.BOM;
import domain.bom.BOMItem;
import domain.construction.Category;
import domain.construction.Construction;
import domain.orders.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public abstract interface MaterialService {

    public void insertMaterialIntoDB(Material material);

    public Material findMaterial(String nametype);


    public Material findMaterialByID(int id);

    public List<Material> findMaterialsByCategory(Category category);

    public ArrayList<Material> roofMaterials(Category category);

    public List<Material>allMaterialsInDB();

    public TreeSet<Integer> allWidthsForMaterials();

    public TreeSet<Integer>allLenghtsForMaterials();

    void storeBOM(BOMItem bomItem, Order order, int materialByCategoryID);// TODO burder nok ligger i orders

    public int findMaterialByCategoryID(Material material, Category category);

    public double findBOMPriceByOrderID(int orderID);
}
