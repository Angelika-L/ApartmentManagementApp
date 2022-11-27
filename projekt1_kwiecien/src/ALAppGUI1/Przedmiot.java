package ALAppGUI1;


public class Przedmiot {

    String nazwaPrzedmiotu;
    Double powierzchniam3;
    Integer number;
    private static int countre = 1;

    public Przedmiot(String nazwaPrzedmiotu,  Double powierzchniam3) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.powierzchniam3 = powierzchniam3;
        this.number = countre++;
    }



}
