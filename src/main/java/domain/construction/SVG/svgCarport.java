package domain.construction.SVG;

public class svgCarport implements Draw {
    /*
    private int StolpeResultat;
    private int remResult;

    int antalStolper = StolpeResultat;
    int remLength = remResult;
*/
    int stolpeBredde = 12;
    int y1 = 29 + 40;

    @Override
    public String draw(int length, int width) {
        StringBuilder stolpeText = new StringBuilder();

        int y2 = width - 60 + stolpeBredde - 3 + 40;

        int Resultat = 0;
        int AfstandprBjælke = 0;

        int overskud = (length - (AfstandprBjælke * (Resultat - 1)));
        int x = 35 + (overskud / 2);
        for (int i = 0; i < Resultat; i++){

            stolpeText.append("<rect x=\""+x+"\" y=\""+y1+"\" width=\"12\" height=\"12\" " +
                    "style=\"stroke:black; storke-width:1; fill-opacity:0.0;\" > </rect>" + "\n"
            );
            stolpeText.append("<rect x=\""+x+"\" y=\""+ y2 +"\" width=\"12\" height=\"12\" " +
                    "style=\"stroke:black; storke-width:1; fill-opacity:0.0;\" > </rect>" + "\n"
            );
            x += 180;
        }


        return stolpeText.toString();
    }
}
