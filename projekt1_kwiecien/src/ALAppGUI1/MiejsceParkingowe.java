package ALAppGUI1;

import ALAppGUI1.exception.ProblematicTenantException;
import ALAppGUI1.exception.TooManyThingsException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class MiejsceParkingowe implements Najmowanie, Comparable<MiejsceParkingowe>{

    HashMap<Integer, Pojazdy> listaZawartosciPojazdów;
    HashMap<Integer, Przedmiot> listaZawartosciPrzedmiotów;
    Osiedle osiedle;
    Double powierzchniaP3;
    Double wolnaPowierzchnia;
    int numerParkingu;
    private static int counter=13;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    public MiejsceParkingowe(Double powierzchniaP3, Osiedle nazwaOsiedla) {
        this.powierzchniaP3 = powierzchniaP3;
        this.osiedle = nazwaOsiedla;
        this.numerParkingu= counter++;
        this.wolnaPowierzchnia = powierzchniaP3;
        this.listaZawartosciPojazdów = new HashMap<>();
        this.listaZawartosciPrzedmiotów = new HashMap<>();
    }

    public void setOsiedleToParking(Osiedle osiedle) {
        this.osiedle = osiedle;
    }

    public Double getPowierzchniaP3() {return powierzchniaP3;}


    @Override
    public void najem(Osiedle osiedle, Osoba osoba, Date start, Date end) {
        if(osoba.listaAktaZadluzenDoc.size()>3 ) {
            try {
                Integer sum = osoba.listaAktaZadluzenDoc.values().stream().mapToInt(p-> p.liczbaPomieszczeń).sum();
                throw new ProblematicTenantException("Osoba "+osoba.NameOsoba+" posiadała już najem pomieszczeń: " +sum);
            }
            catch (ProblematicTenantException e) {
                e.printStackTrace();
            }
        }
        else {
            if (osoba.listaNajmowanychParkingow.size() < 5) {
                for (Map.Entry<MiejsceParkingowe, Boolean> lista : osiedle.listaParking.entrySet()) {
                    if (lista.getKey() == this) {
                        if(lista.getValue()){
                            System.out.println("ERROR --> Miejsce parkingowe nr " + this.numerParkingu + " jest już wynajęte!!");
                        }else {
                            osiedle.setListaParking(this,true);
                            osoba.addParking(this, new File(osoba, start, end, File.TypDoc.PARKING,osiedle));
                            System.out.println("Utworzenie dokumentu najmu parkingu.");
                            System.out.println("Parking nr " + this.numerParkingu + " zostało wynajete.");
                        }
                    }
                }
            } else System.out.println("ERROR --> Nie można wynająć wiecej niż 5 ponieszczeń parkingowych w ramach jednego osiedla.");
        }
    }


    @Override
    public void rezygnacjaNajmu(Osiedle osiedle, Osoba osoba) {
        if (osoba.listaNajmowanychParkingow.get(this).status.equals(File.StatusNajmu.ZADŁUŻENIE)) {
            osoba.listaAktaZadluzenDocPark.remove(this);
            System.out.println("Usunieto dokument o zadłużeniu z akt " + osoba.NameOsoba + ".");
        }
        for (Map.Entry<MiejsceParkingowe, Boolean> lista : osiedle.listaParking.entrySet()) {
            if (lista.getValue()) {
                osiedle.setListaParking(this, false);
                osoba.listaNajmowanychParkingow.get(this).updateStatus(File.StatusNajmu.ZAKOŃCZNEONE);
                System.out.println("Zamkniecie umowy najmu.");
                this.listaZawartosciPojazdów.clear();
                this.wolnaPowierzchnia = this.powierzchniaP3;
                System.out.println("Parking nr " + this.numerParkingu + " zostało oczyszczony przez najemce.");
            }
        }
    }

    @Override
    public void odnowienieNajmu(Osoba osoba, Date newDate) {
            if (osoba.listaNajmowanychParkingow.get(this).status.equals(File.StatusNajmu.ZADŁUŻENIE)) {
                osoba.listaAktaZadluzenDocPark.remove(this);
            }
            osoba.listaNajmowanychParkingow.get(this).updateStatus(File.StatusNajmu.TRWA);
            osoba.listaNajmowanychParkingow.get(this).updateDateEnd(newDate);
    }

    @Override
    public void eksmisja(Osiedle osiedle,File file, Osoba osoba) {
        if(this.listaZawartosciPojazdów.isEmpty()) {
            osiedle.setListaParking(this, false);
            osoba.listaNajmowanychParkingow.get(this).updateStatus(File.StatusNajmu.ZAKOŃCZNEONE);
            this.listaZawartosciPojazdów.clear();
            this.wolnaPowierzchnia = this.powierzchniaP3;
        } else {
            file.updateStatus(File.StatusNajmu.TRWA);
            osoba.listaAktaZadluzenDocPark.remove(this,file);
            konkwistataPojazdu(osoba);
        }
    }

    @Override
    public void zadłużenie(Osoba osoba, File file, Date data) {
        boolean exists = true;
        for (Map.Entry<MiejsceParkingowe, File> existFile : osoba.listaAktaZadluzenDocPark.entrySet()) {
            if (existFile.getValue() == file) exists = false;
        }
        if (exists) {
            file.updateStatus(File.StatusNajmu.ZADŁUŻENIE);
            osoba.addZadlizenieParking(this, file);
        }
    }

    public void konkwistataPojazdu(Osoba osoba){
        if(!this.listaZawartosciPojazdów.isEmpty()){
            this.listaZawartosciPojazdów.clear();
            Date newdata = osoba.listaNajmowanychParkingow.get(this).dateEnd;
            Calendar c = Calendar.getInstance();
            c.setTime(newdata);
            c.add(Calendar.MONTH,2);
            this.wolnaPowierzchnia=this.powierzchniaP3;
            osoba.listaNajmowanychParkingow.get(this).updateDateEnd(java.sql.Date.valueOf(format1.format(c.getTime())));
        }
    }

    public void addPojazdToParking(Pojazdy pojazdy){
        if(!(pojazdy==null)) {
            if (pojazdy.powierzchniam3 > this.wolnaPowierzchnia) {
                try {
                    throw new TooManyThingsException("Remove some old items to insert a new item");
                } catch (TooManyThingsException e) {
                    e.printStackTrace();
                }
            } else {
                    listaZawartosciPojazdów.put(pojazdy.number, pojazdy);
                    this.wolnaPowierzchnia -= pojazdy.powierzchniam3;
                    System.out.println(" SUCCESS --> Pojazd typu:" + pojazdy.typPojazdu + " -> " + pojazdy.nazwaPojazdu + " zostal włożony do pomieszczenia/parkingu.");
                    System.out.println(" SUCCESS --> W pomieszczeniu parkingowym pozostalo : "+this.wolnaPowierzchnia+" m^3 wolnego miejsca.");
            }
        }
    }

    public void addPrzedmiotToParking(Przedmiot przedmiot){
        if(!(przedmiot==null)) {
            if (przedmiot.powierzchniam3 > this.wolnaPowierzchnia) {
                try {
                    throw new TooManyThingsException("Remove some old items to insert a new item");
                } catch (TooManyThingsException e) {
                    e.printStackTrace();
                }
            } else {
                    listaZawartosciPrzedmiotów.put(przedmiot.number, przedmiot);
                    this.wolnaPowierzchnia -= przedmiot.powierzchniam3;
                    System.out.println(" SUCCESS --> Przedmiot zostal włożony do pomieszczenia/parkingu.");
                    System.out.println(" SUCCESS --> W pomieszczeniu parkingowym pozostalo : "+this.wolnaPowierzchnia+" m^3 wolnego miejsca.");
                }
        }
    }


    public void getPojazdFromParking(Integer number){
        try {
            this.wolnaPowierzchnia += listaZawartosciPojazdów.get(number).powierzchniam3;
            System.out.println(" SUCCESS --> Pojazd typu:" + listaZawartosciPojazdów.get(number).typPojazdu + " -> " + listaZawartosciPojazdów.get(number).nazwaPojazdu + " zostal wyciągnięty z pomieszczenia/parkingu.");
            listaZawartosciPojazdów.remove(number);
            System.out.println(" SUCCESS --> W pomieszczeniu parkingowym jest teraz : "+this.wolnaPowierzchnia+" m^3 wolnego miejsca.");
        } catch (NullPointerException e){
            System.out.println("ERROR --> Brak takiego przedmiotu/pojazdu w pomieszczeniu parkingowym.");
        }
    }

    public void getPrzedmiotFromParking(Integer number){
        try {
            this.wolnaPowierzchnia += listaZawartosciPrzedmiotów.get(number).powierzchniam3;
            System.out.println(" SUCCESS --> Przedmiot zostal wyciągnięty z pomieszczenia/parkingu.");
            listaZawartosciPrzedmiotów.remove(number);
            System.out.println(" SUCCESS --> W pomieszczeniu parkingowym jest teraz : "+this.wolnaPowierzchnia+" m^3 wolnego miejsca.");
        } catch (NullPointerException e){
            System.out.println("ERROR --> Brak takiego przedmiotu/pojazdu w pomieszczeniu parkingowym.");
        }
    }


    @Override
    public int compareTo(MiejsceParkingowe miejsceParkingowe) {
        if(this.getPowierzchniaP3() < miejsceParkingowe.getPowierzchniaP3()) return -1;
        if(this.getPowierzchniaP3() > miejsceParkingowe.getPowierzchniaP3()) return 1;
        else return 0;
    }

}
