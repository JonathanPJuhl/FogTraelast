package domain.construction.Carport;

public class CarportMaterialeBeregner {

    public int antalStolper() {
        //Til denne metode kræves der længde fra databasen.
        int length = 0;

        int userinput = (int) length;
        int AfstandprBjaelke = 180;
        //int N = 180; Udhæng skal det være der eller ej?
        int minimumsøjler = 2;
        int minimumLængdefor4søjler = userinput - 360;
        //N er den aftand der er fra sidste bjælke til taget slutter.
        int AntalBjaelker = (minimumLængdefor4søjler/*-N*/) / AfstandprBjaelke;
        int ExtraBjaelke = (AntalBjaelker + 1);
        int StolpeResultat = (minimumsøjler + ExtraBjaelke * 2);

        return StolpeResultat;
    }

    public class RemBeregner {

        public int remLength() {

        int width = 0;
        int length = 0;
        int remResult = width + length * 2;

            return remResult;
        }
    }
}
