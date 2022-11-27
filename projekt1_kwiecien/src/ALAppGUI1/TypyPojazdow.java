package ALAppGUI1;

public enum TypyPojazdow {

    SAMOCHÓD_TERENOWY("SAMOCHÓD_TERENOWY"),
    SAMOCHÓD_MIEJSKI("SAMOCHÓD_MIEJSKI"),
    ŁÓDŹ("ŁÓDŹ"),
    MOTOCYKL("MOTOCYKL"),
    AMFIBIA("AMFIBIA");

    private String strreprezentacjaEnum;

    TypyPojazdow(String strreprezentacjaEnum) {this.strreprezentacjaEnum = strreprezentacjaEnum;}

    @Override
    public String toString() {return strreprezentacjaEnum;}




    //źródło pomocy przy pisaniu kodu:
    // https://www.java67.com/2014/12/2-ways-to-print-custom-string-value-of.html
    //https://stackoverflow.com/questions/14413581/how-to-print-all-enum-value-in-java
}

