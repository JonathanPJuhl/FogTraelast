package domain.construction.SVG;

public class svgCarportSide {

    StringBuilder SvgCarportText = new StringBuilder();



    public String Build(int length, int width, int ShedWidth, int ShedLength, double gableHeight) {

        int xShed = 600 - ShedLength;
        int xStolpeStart = 60;
        int stolpeAfstand = 180;
        int xStolpe = xStolpeStart + stolpeAfstand;

        //billede størrelse
        SvgCarportText.append("<svg width=\""+width+"\" height=\""+length+"\">");

        //skur
        SvgCarportText.append("<rect x=\""+xShed+"\" y=\"155\" rx=\"0\" ry=\"0\" width=\""+ShedLength+"\" height=\"230\" style=\"fill:blue;stroke:black;stroke-width:3;opacity:1\" />");

        SvgCarportText.append("<rect x=\""+xShed+"\" y=\"155\" rx=\"0\" ry=\"0\" width=\"15\" height=\"230\" style=\"fill:blue;stroke:black;stroke-width:3;opacity:1\" />");

        //Stolpe

        SvgCarportText.append("<rect x=\""+xStolpeStart+"\" y=\"155\" rx=\"0\" ry=\"0\" width=\"15\" height=\"230\" style=\"fill:white;stroke:black;stroke-width:3;opacity:1\" />");

        SvgCarportText.append("<rect x=\""+xStolpe+"\" y=\"155\" rx=\"0\" ry=\"0\" width=\"15\" height=\"230\" style=\"fill:white;stroke:black;stroke-width:3;opacity:1\" />");

        //tag
        SvgCarportText.append("<rect x=\"10\" y=\"60\" rx=\"0\" ry=\"0\" width=\""+length+"\" height=\""+gableHeight+"\" style=\"fill:grey;stroke:black;stroke-width:3;opacity:1\" />");
        //Rem
        SvgCarportText.append("<rect x=\"10\" y=\"140\" rx=\"0\" ry=\"0\" width=\""+length+"\" height=\"15\" style=\"fill:white;stroke:black;stroke-width:3;opacity:1\" />");

        //Målestokke
        SvgCarportText.append("<text x=\""+((length/2)-30)+"\" y=\"440\" fill=\"red\" transform=\"rotate(0 20,40)\">total længde 7m</text>");
        SvgCarportText.append("<line x1=\"10\" y1=\"400\" x2=\""+length+"\" y2=\"400\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");

        SvgCarportText.append("<text x=\"10\" y=\"415\" fill=\"red\" transform=\"rotate(0 20,40)\">95cm</text>");
        SvgCarportText.append("<line x1=\"60\" y1=\"390\" x2=\"60\" y2=\"410\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");

        SvgCarportText.append("<text x=\"130\" y=\"415\" fill=\"red\" transform=\"rotate(0 20,40)\">180cm</text>");
        SvgCarportText.append("<line x1=\"240\" y1=\"390\" x2=\"240\" y2=\"410\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");

        SvgCarportText.append("<text x=\"320\" y=\"415\" fill=\"red\" transform=\"rotate(0 20,40)\">180cm</text>");
        SvgCarportText.append("<line x1=\"420\" y1=\"390\" x2=\"420\" y2=\"410\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");

        SvgCarportText.append("<text x=\"490\" y=\"415\" fill=\"red\" transform=\"rotate(0 20,40)\">180cm</text>");
        SvgCarportText.append("<line x1=\"600\" y1=\"390\" x2=\"600\" y2=\"410\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");

        SvgCarportText.append("<text x=\"602\" y=\"415\" fill=\"red\" transform=\"rotate(0 20,40)\">65cm</text>");
        SvgCarportText.append("<line x1=\"640\" y1=\"390\" x2=\"640\" y2=\"410\" style=\"stroke:rgb(0,0,0);stroke-width:1\" />");



        SvgCarportText.append("</svg>");
        return SvgCarportText.toString();
    }
    public double gavlBeregner(double hældning, double totalLængde){
        double lilleb = totalLængde/2;
        lilleb = Math.toRadians(lilleb);
        double storeA = hældning;
        storeA = Math.toRadians(storeA);
        double storeC = 90;
        storeC = Math.toRadians(storeC);

        double storeB = Math.toRadians(180)-storeA-storeC;

        double lillea = (Math.sin(storeA)*lilleb)/Math.sin(storeB);

        return Math.toDegrees(lillea);
    }
}
