package ALAppGUI1;

import java.sql.Date;

public interface Najmowanie {

    void najem(Osiedle osiedle, Osoba osoba, Date start, Date end);

    void rezygnacjaNajmu(Osiedle osiedle, Osoba osoba);

    void odnowienieNajmu(Osoba osoba, Date newDate);

    void eksmisja(Osiedle osiedle, File file, Osoba osoba);

    void zadłużenie(Osoba osoba, File file, Date data);

}
