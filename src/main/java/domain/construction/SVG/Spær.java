package domain.construction.SVG;

public class Spær implements Draw  {


    int spærTykkelse = 50;
    int x = 40;
    int y1 = 40;


    @Override
    public String draw(int length, int width) {
        StringBuilder svgSpærText = new StringBuilder();
        int inderBredde = width - 60;
        int y2 = inderBredde + spærTykkelse;

        svgSpærText.append("<rect x=\"" +x+ "\" y=\""+y1+"\" width=\""+length+"\" " +
                "height=\""+spærTykkelse+"\" " +
                "style=\"stroke:black; stroke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
        );

        svgSpærText.append("<rect x=\""+x+"\" y=\""+y2+"\" width=\""+length+"\"" +
                " height=\""+spærTykkelse+"\" " +
                "style=\"stroke:black; stroke-width:1;  fill:rgb(255,255,255);\" > </rect>" + "\n"
        );

        svgSpærText.append("<rect x=\"" +40+ "\" y=\""+40+"\" width=\""+spærTykkelse+"\" " +
                "height=\""+width+"\" " +
                "style=\"stroke:black; stroke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
        );

        svgSpærText.append("<rect x=\""+(length)+"\" y=\"40\" width=\""+spærTykkelse+"\"" +
                " height=\""+width+"\" " +
                "style=\"stroke:black; stroke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
        );


        int mellemRum = 840 + spærTykkelse;


        int antalSpær = 0;

            int length2 = length;

            for(int j=mellemRum; j<=length2; j+=mellemRum) {
                antalSpær++;
            }


        int location = 40 + mellemRum;
        if(mellemRum<width-location) {
            for (int i = 0; i < antalSpær; i++) {

                svgSpærText.append("<rect x=\"" + location + "\" y=\"40\" width=\"" + spærTykkelse + "\" " +
                        "height=\"" + width + "\" " +
                        "style=\"stroke:black; stroke-width:1; fill:rgb(255,255,255);\" > </rect>" + "\n"
                );
                location += (840 + spærTykkelse);
            }
        }

        return svgSpærText.toString();
    }
}