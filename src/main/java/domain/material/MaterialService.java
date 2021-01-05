package domain.material;

import domain.bom.BOMFromDB;
import domain.bom.BOMItem;
import domain.construction.Category;
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

    void storeBOM(BOMItem bomItem, Order order, int materialByCategoryID);

    public int findMaterialByCategoryID(Material material, Category category);

    public double findBOMPriceByOrderID(int orderID);

    ArrayList<BOMFromDB> findBOMByOrderID(int orderID);

    BOMFromDB parseBOMList(ResultSet set) throws SQLException;
}
