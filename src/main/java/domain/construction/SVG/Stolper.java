package domain.construction.SVG;

public class Stolper implements Draw {

    int stolpeBredde = 50;
    int y1 = 29 + 40;

    @Override
    public String draw(int length, int width) {
        StringBuilder stolpeText = new StringBuilder();

        int y2 = width - 60 + stolpeBredde - 3 + 40;


        int userinput = length;
        int AfstandprBjælke = 180;
        //int N = 180; Udhæng skal det være der eller ej?
        int minimumsøjler = 2;
        int minimumLængdefor4søjler = userinput-360;
        int AntalB = (minimumLængdefor4søjler/*-N*/)/AfstandprBjælke;
        int ExtraB = (AntalB);
        int Resultat = (minimumsøjler+ExtraB);

        int overskud = (length - (AfstandprBjælke * (Resultat - 1)));
        int x = 35 + (overskud / 2);

        stolpeText.append("<rect x=\""+40+"\" y=\""+40+"\" width=\""+stolpeBredde+"\" height=\""+stolpeBredde+"\" " +
                "style=\"stroke:black; stroke-width:10; fill-opacity:0.0;\" > </rect>" + "\n"
        );
        stolpeText.append("<rect x=\""+40+"\" y=\""+(y2-40)+"\" width=\""+stolpeBredde+"\" height=\""+stolpeBredde+"\" " +
                "style=\"stroke:black; stroke-width:10; fill-opacity:0.0;\" > </rect>" + "\n"
        );
        stolpeText.append("<rect x=\""+(length)+"\" y=\""+40+"\" width=\""+stolpeBredde+"\" height=\""+stolpeBredde+"\" " +
                "style=\"stroke:black; stroke-width:10; fill-opacity:0.0;\" > </rect>" + "\n"
        );
        stolpeText.append("<rect x=\""+(length)+"\" y=\""+(y2-40)+"\" width=\""+stolpeBredde+"\" height=\""+stolpeBredde+"\" " +
                "style=\"stroke:black; stroke-width:10; fill-opacity:0.0;\" > </rect>" + "\n"
        );



        for (int i = 0; i < Resultat; i++){


            x += 1500;
            stolpeText.append("<rect x=\""+x+"\" y=\""+40+"\" width=\""+stolpeBredde+"\" height=\""+stolpeBredde+"\" " +
                    "style=\"stroke:black; stroke-width:10; fill-opacity:0.0;\" > </rect>" + "\n"
            );
            stolpeText.append("<rect x=\""+x+"\" y=\""+ (y2-40) +"\" width=\""+stolpeBredde+"\" height=\""+stolpeBredde+"\" " +
                    "style=\"stroke:black; stroke-width:10; fill-opacity:0.0;\" > </rect>" + "\n"
            );

        }


        return stolpeText.toString();
    }
}