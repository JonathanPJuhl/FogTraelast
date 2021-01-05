package domain.construction.SVG;

public class Tag implements Draw {




    @Override
    public String draw(int length, int width) {

        return ("<rect x=\"40\" y=\"40\" width=\""+length+"\" " +
                "height=\""+width+"\" " +
                "style=\"stroke:black;" +
                " stroke-width:2; " +
                "fill:white;" +
                "fill-opacity: 0.0;\" >" +
                " </rect>" +
                "\n");
    }


}

