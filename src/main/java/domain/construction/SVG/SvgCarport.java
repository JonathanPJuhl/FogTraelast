package domain.construction.SVG;


public class SvgCarport {

    private Tag tag = new Tag();
    private Spær spær = new Spær();
    private Rem rem = new Rem();
    private Stolper stolper = new Stolper();
    private Pile pile = new Pile();
    private Skur skur;



    StringBuilder SvgCarportText = new StringBuilder();



    public String Build(int lenght, int width, int skurBredde, int skurLængde){
        skur = new Skur(skurBredde, skurLængde);
        SvgCarportText.append(tag.draw(lenght,width));
        SvgCarportText.append(spær.draw(lenght,width));
        SvgCarportText.append(rem.draw(lenght,width));
        SvgCarportText.append(stolper.draw(lenght,width));
        SvgCarportText.append(pile.draw(lenght,width));
        SvgCarportText.append(skur.draw(lenght,width));

        return SvgCarportText.toString();
    }

}
