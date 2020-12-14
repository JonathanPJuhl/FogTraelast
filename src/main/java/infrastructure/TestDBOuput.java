package infrastructure;

import domain.construction.Construction;
import domain.construction.Roof.FlatRoof;
import domain.material.Material;

import java.util.List;

public class TestDBOuput {

    public static void main(String[] args) {
        Construction con = new Construction(100, 100,"Fladt tag", 0 , 0);
        con.setRoof(new FlatRoof(20, con.getLength(), con.getWidth(), null));
        Database db = new Database();
        DBMaterialRepository dbMaterialRepository = new DBMaterialRepository(db);
        List<Material> materials = dbMaterialRepository.roofMaterials(con);
        for (Material m: materials) {
            System.out.println(m.toString());
        }

    }
}
