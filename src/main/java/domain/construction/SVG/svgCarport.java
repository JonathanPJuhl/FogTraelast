package domain.construction.SVG;

public class svgCarport implements Draw {
    private int length;
    private int width;
    /*
    private int StolpeResultat;
    private int remResult;

    int antalStolper = StolpeResultat;
    int remLength = remResult;
*/

    @Override
    public String draw(int length, int width) {
        StringBuilder stolpeText = new StringBuilder();

        int stolpeBredde = 12;
        int ystolpeBredde1 = 29 + 40;

        int ystolpeBredde2 = width - 60 + stolpeBredde - 3 + 40;

        int Resultat = 0;
        int AfstandprBjælke = 0;

        int overskud = (length - (AfstandprBjælke * (Resultat - 1)));
        int xstolpeBredde = 35 + (overskud / 2);
        for (int i = 0; i < Resultat; i++){



        }

        stolpeText.append("<rect x=\""+xstolpeBredde+"\" y=\""+ystolpeBredde1+"\" width=\"12\" height=\"12\" " +
                "style=\"stroke:black; storke-width:1; fill-opacity:0.0;\" > </rect>" + "\n"
        );
        stolpeText.append("<rect x=\""+xstolpeBredde+"\" y=\""+ ystolpeBredde2 +"\" width=\"12\" height=\"12\" " +
                "style=\"stroke:black; storke-width:1; fill-opacity:0.0;\" > </rect>" + "\n"
        );
        xstolpeBredde += 180;


        StringBuilder svgSpærText = new StringBuilder();

        int spærTykkelse = 10;
        int xSpær = 0 + 40;
        int ySpær = 30 + 40;

        int inderBredde = width - 60;
        int ySpær2 = inderBredde + spærTykkelse + 40;

        svgSpærText.append("<rect x=\"" +xSpær+ "\" y=\""+ySpær+"\" width=\""+length+"\" " +
                "height=\""+spærTykkelse+"\" " +
                "style=\"stroke:black; storke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
        );

        svgSpærText.append("<rect x=\""+xSpær+"\" y=\""+ySpær2+"\" width=\""+length+"\"" +
                " height=\""+spærTykkelse+"\" " +
                "style=\"stroke:black; storke-width:1;  fill:rgb(255,255,255);\" > </rect>" + "\n"
        );

        svgSpærText.append("<rect x=\"" +40+ "\" y=\""+40+"\" width=\""+spærTykkelse+"\" " +
                "height=\""+width+"\" " +
                "style=\"stroke:black; storke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
        );

        svgSpærText.append("<rect x=\""+(length - 10 + 40)+"\" y=\""+40+"\" width=\""+spærTykkelse+"\"" +
                " height=\""+width+"\" " +
                "style=\"stroke:black; storke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
        );


        int mellemRum = 56 + spærTykkelse;
        int antalSpær = (length / mellemRum);

        int location = 40 + mellemRum;
        for (int i = 0; i < antalSpær; i++){

            svgSpærText.append("<rect x=\"" +location+ "\" y=\""+40+"\" width=\""+spærTykkelse+"\" " +
                    "height=\""+width+"\" " +
                    "style=\"stroke:black; storke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
            );
            location += (56 + spærTykkelse);
        }

        StringBuilder remText = new StringBuilder();
        int xRem1 = (int) (length / 4) + 40;
        int xRem2 = (int) (length / 1.4) + 40;
        remText.append(" <line x1=\""+xRem1+"\" y1=\"80\" x2=\""+xRem2+"\" y2=\""+(width -  50 + 40)+"\" style=\"stroke:black;stroke-width:2;stroke-dasharray:5,5\" />\n" +
                "\n");
        remText.append(" <line x1=\""+xRem2+"\" y1=\"80\" x2=\""+xRem1+"\" y2=\""+(width - 50 + 40)+"\" style=\"stroke:black;stroke-width:2;stroke-dasharray:5,5\" />\n" +
                "\n");



        return svgSpærText.toString() + remText.toString() + stolpeText.toString();
    }

}
