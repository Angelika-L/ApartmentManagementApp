package ALAppGUI1;

import java.util.HashMap;
import java.util.Map;

public class Blok {

    HashMap<Mieszkanie,Boolean> listaMieszkan;
    int numerBloku;
    String Osiedle;
    private static int counter=1;

    public Blok() {
        this.numerBloku = counter++;
        this.listaMieszkan=new HashMap<>();
    }

    public void addMierszkanie(Mieszkanie mieszkanie) {
        if(this.listaMieszkan.size()>50){
            System.out.println("Nie ma już mieszkań w bloku.");
        }
        else {
            this.listaMieszkan.put(mieszkanie, false);
            mieszkanie.setNumerBlokuToM(this.numerBloku);
            mieszkanie.setNazwaOsiedlaToM(this.Osiedle);
        }
    }

    public void setOsiedleToBlok(String osiedle) {
        Osiedle = osiedle;
    }

    public Boolean getStatus(Mieszkanie mieszkanie){
        return this.listaMieszkan.get(mieszkanie);
    }

    public void setStatus(Mieszkanie mieszkanie, Boolean bool){
        this.listaMieszkan.replace(mieszkanie,bool);
    }



    public String getLiczbaPomieszczenMieszkania(){
        String data = "Blok: "+this.numerBloku +": \n";
        for (Map.Entry<Mieszkanie,Boolean> lista: this.listaMieszkan.entrySet()) {
            data+="   * nr. mieszkania: "+lista.getKey().numerMieszkania+", liczba pomieszczeń="+lista.getKey().liczbaPomieszczen+", powierzchnia="+lista.getKey().powierzchniaM3+"\n";
        }
        return data;
    }

    public String getWolnePomieszczenMieszkania(){
        String data = "Blok: "+this.numerBloku +": \n";
        for (Map.Entry<Mieszkanie,Boolean> lista: this.listaMieszkan.entrySet()) {
            Boolean wolne = lista.getValue();
            if(!wolne) data += "   * mieszkanie: "+lista.getKey().numerMieszkania+" = jest wolne. Liczba pomieszczeń = "+lista.getKey().liczbaWolnychPomieszczen+". (tylko wynajem, nie można dokonać podnajmu pomieszczeń) \n";
            else if(lista.getKey().liczbaWolnychPomieszczen>0) data += "   * mieszkanie: "+lista.getKey().numerMieszkania+" = jest wynajęte. Liczba wolnych pomieszczeń pod wynajem = "+lista.getKey().liczbaWolnychPomieszczen+"\n";
        }
        return data;
    }


}
