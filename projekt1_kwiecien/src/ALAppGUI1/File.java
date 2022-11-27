package ALAppGUI1;

import java.sql.Date;
import java.util.HashMap;

public class File {

    int numerDokumentu, liczbaPomieszczeń;
    Osiedle osiedle;
    Osoba najemca;
    HashMap<Osoba,File> listaLokatorow;
    Date dateStart, dateEnd;
    TypDoc typDokumentu;
    StatusNajmu status;
    private static int counter = 13333;

    public File(Osoba najemca, Date dateStart, Date dateEnd, TypDoc typDokumentu, Osiedle osiedle){
        this.najemca = najemca;
        this.listaLokatorow = new HashMap<>();
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.osiedle = osiedle;
        this.typDokumentu = typDokumentu;
        this.numerDokumentu = counter++;
        this.status = StatusNajmu.TRWA;
    }

    public void updateStatus(StatusNajmu status){
        this.status = status;
    }

    public void updateDateEnd(Date newdate){
        this.dateEnd = newdate;
    }
    public void updateLiczbaPomieszczen(int liczbaPomieszczeń){
        this.liczbaPomieszczeń = liczbaPomieszczeń;
    }

    public void updateListaLokatorow(Osoba osoba, File file){
        this.listaLokatorow.put(osoba,file);
    }

    public enum TypDoc{
        LOKATOR, NAJEMCA, PARKING;
    }

    public enum StatusNajmu{
        ZAKOŃCZNEONE, TRWA, ZABLOKOWANE, EKSMISJA, ZADŁUŻENIE;
    }
}
