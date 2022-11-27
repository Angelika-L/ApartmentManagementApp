package ALAppGUI1;

import java.sql.Date;
import java.util.HashMap;

public class Developer extends Osoba{

    HashMap<Integer,Osiedle> listaOsiedli;

    public Developer(String nameOsoba, String pesel, String adres, Date dataUrodzenia, Integer number) {
        super(nameOsoba,pesel,adres,dataUrodzenia,true, number);
        this.listaOsiedli=new HashMap();
    }

    public void addOsiedle(Osiedle osiedle, Integer number) {
      this.listaOsiedli.put(number,osiedle);
    }



}
