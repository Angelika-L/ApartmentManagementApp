package ALAppGUI1;

import java.util.HashMap;
import java.util.Map;

public class Pojazdy {

    String nazwaPojazdu;
    TypyPojazdow typPojazdu;
    Double powierzchniam3;
    Integer number;
    HashMap<CechyChaktarestyczne,String> listaCechCharakterystycznych;

    private static int countre = 1;

    public Pojazdy(String nazwaPojazdu, TypyPojazdow typPojazdu, Double powierzchniam3,HashMap<CechyChaktarestyczne,String> mapa) {
        this.nazwaPojazdu = nazwaPojazdu;
        this.typPojazdu = typPojazdu;
        this.powierzchniam3 = powierzchniam3;
        this.listaCechCharakterystycznych = new HashMap<>();
        this.listaCechCharakterystycznych = mapa;
        this.number = countre++;
    }

    public void addCechy(String string){
        String[] splitString = string.split(",");
        for (int i = 0; i < splitString.length ; i++) {
            CechyChaktarestyczne cechyChaktarestyczne = null;
            String opis = null;
            if (i % 2 != 0) {
                cechyChaktarestyczne = CechyChaktarestyczne.valueOf(splitString[i]);
            } else {
                opis = splitString[i];
            }
            this.listaCechCharakterystycznych.put(cechyChaktarestyczne,opis);
        }
    }

    public String getCechyString(){
        if(!this.listaCechCharakterystycznych.isEmpty()){
            String message = "";
            for (Map.Entry<CechyChaktarestyczne, String> lista : this.listaCechCharakterystycznych.entrySet()) {
                message += " ["+lista.getKey().strreprezentacjaEnum +"="+ lista.getValue()+"].";
            }
            return message;
        } else
            return "pojazd nie ma dodanych cech.";

    }




    public enum CechyChaktarestyczne {
        TYP_SILNIKA("TYP_SILNIKA"), ROCZNIK("ROCZNIK"),
        POJEMNOSC_PRZYCZEPY("POJEMNOSC_PRZYCZEPY"), SKRZYNIA_BIEGOW("SKRZYNIA_BIEGOW"),
        MAX_ZANURZENIE("MAX_ZANURZENIE"), DLUGOSC_LODZI("DLUGOSC_LODZI"),
        RODZAJ_MOTOCYKLA("RODZAJ_MOTOCYKLA"),
        RODZAJ_KOL_g_k("RODZAJ_KOL_g_k");

        private String strreprezentacjaEnum;
        CechyChaktarestyczne(String strreprezentacjaEnum) {this.strreprezentacjaEnum = strreprezentacjaEnum;}

        @Override
        public String toString() {return strreprezentacjaEnum;}

        public static String getCechy(String string) {
            String wynik = null;
            if (string.contains("samochód_terenowy") || string.contains("SAMOCHÓD_TERENOWY"))
                wynik = " TYP_SILNIKA , ROCZNIK";
            else if (string.contains("samochód_miejski") || string.contains("SAMOCHÓD_MIEJSKI"))
                wynik = " TYP_SILNIKA , SKRZYNIA_BIEGOW";
            else if (string.contains("AMFIBIA") || string.contains("amfibia"))
                wynik = " RODZAJ_KOL_g_k , ROCZNIK";
            else if (string.contains("łódź") || string.contains("ŁÓDŹ"))
                wynik = " MAX_ZANURZENIE , DLUGOSC_LODZI ";
            else if (string.contains("MOTOCYKL") || string.contains("motocykl"))
                wynik = " RODZAJ_MOTOCYKLA , ROCZNIK";
            return wynik;
        }

        //źródło pomocy przy pisaniu kodu:
        // https://www.java67.com/2014/12/2-ways-to-print-custom-string-value-of.html
        //https://stackoverflow.com/questions/14413581/how-to-print-all-enum-value-in-java
    }

}
