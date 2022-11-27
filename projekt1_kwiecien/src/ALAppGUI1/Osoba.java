package ALAppGUI1;

import java.text.SimpleDateFormat;
import java.util.*;

public class Osoba {

    String NameOsoba, pesel, adres;
    Date dataUrodzenia;
    Integer numerOsoby;
    HashMap<Mieszkanie, File> listaNajmowanychMieszkan, listaAktaZadluzenDoc;
    HashMap<MiejsceParkingowe, File> listaNajmowanychParkingow, listaAktaZadluzenDocPark;
    boolean ranga;

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");


    public Osoba(String nameOsoba, String pesel, String adres, Date dataUrodzenia, boolean ranga, Integer number) {
        if (nameOsoba.isEmpty() || (pesel.length() < 11)) {
            System.out.println("Źle podane dane osobowe.");
        } else {
            this.NameOsoba = nameOsoba;
            this.pesel = pesel;
            this.adres = adres;
            this.dataUrodzenia = dataUrodzenia;
            this.numerOsoby = number;
            this.listaNajmowanychMieszkan = new HashMap<>();
            this.listaAktaZadluzenDoc = new HashMap<>();
            this.listaNajmowanychParkingow = new HashMap<>();
            this.listaAktaZadluzenDocPark = new HashMap<>();
            this.ranga = ranga;
        }
    }

    public void addMieszkanie(Mieszkanie numerMieszkania, File file) {
        this.listaNajmowanychMieszkan.put(numerMieszkania, file);
    }

    public void addZadluzenie(Mieszkanie numerMieszkania, File file) {
        this.listaAktaZadluzenDoc.put(numerMieszkania, file);
    }

    public void addParking(MiejsceParkingowe miejsceParkingowe, File file) {
        this.listaNajmowanychParkingow.put(miejsceParkingowe, file);
    }

    public void addZadlizenieParking(MiejsceParkingowe miejsceParkingowe, File file) {
        this.listaAktaZadluzenDocPark.put(miejsceParkingowe, file);
    }

    @Override
    public String toString() {
        return "["+this.numerOsoby + "]-" + this.NameOsoba;
    }

    public String getDataAboutMe() {
        return "| Numer id: " + this.numerOsoby +
                "\n| Nazwa: " + this.NameOsoba + "   ,Pesel: " + this.pesel + "   ,Data Urodzenia: " + this.dataUrodzenia +
                "\n| Adres: " + this.adres + "\n";
    }

    public String getListMieszkania(String listWithDataM,String listMyRM, String mM) {
        String listWithData = listWithDataM;
        String listMyR = listMyRM;
        int liczba = 0;
        int liczbaR = 0;

        for (Map.Entry<Mieszkanie, File> lista : this.listaNajmowanychMieszkan.entrySet()) {
            if (!(lista.getValue().status == File.StatusNajmu.ZAKOŃCZNEONE)) {
                if (this.listaAktaZadluzenDoc.containsKey(lista.getKey())) {
                    if (lista.getValue().typDokumentu == File.TypDoc.NAJEMCA) {
                        listWithData += "   *ZADŁUŻENIE*> Mieszkanie numer: " + lista.getKey().numerMieszkania + " . Najem na okres: [" + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]\n";
                        liczba++;
                    } else {
                        listMyR += "   *ZADŁUŻENIE*> Mieszkanie numer: " + lista.getKey().numerMieszkania + ". Liczba wynajetych pomieszczeń: " + lista.getValue().liczbaPomieszczeń + ". Najem na okres: [" + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]\n";
                        liczbaR++;
                    }
                } else {
                    if (lista.getValue().typDokumentu == File.TypDoc.NAJEMCA) {
                        listWithData += "   *> Mieszkanie numer: " + lista.getKey().numerMieszkania + " . Najem na okres: [" + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]\n";
                        liczba++;
                    } else {
                        listMyR += "   *> Mieszkanie numer:  " + lista.getKey().numerMieszkania + ". Liczba wynajetych pomieszczeń: " + lista.getValue().liczbaPomieszczeń + ". Najem na okres: [" + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]\n";
                        liczbaR++;
                    }
                }
            }
        }
        String m = mM;
        if (liczba > 0) m += listWithData;
        if (liczbaR > 0) m += listMyR;
        return m;
    }

    public String getListParkingow(String listWithDataM, String mM, boolean zawartość)  {
        String listWithData = listWithDataM;
        int liczba = 0;
        for (Map.Entry<MiejsceParkingowe, File> lista : this.listaNajmowanychParkingow.entrySet()) {
            if (!(lista.getValue().status == File.StatusNajmu.ZAKOŃCZNEONE)) {
                if (this.listaAktaZadluzenDocPark.containsKey(lista.getKey())) {
                    if (lista.getKey().wolnaPowierzchnia == lista.getKey().powierzchniaP3) {
                        listWithData += "   *ZADŁUŻENIE*> Parking numer: " + lista.getKey().numerParkingu + ". Pomieszczenie parkingowe jest calkiem puste. Najem na okres: ["
                                + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]\n" ;
                        if(zawartość)listWithData += getItemParking(lista.getKey());
                        liczba++;
                    } else {
                        listWithData += "   *ZADŁUŻENIE*> Parking numer: " + lista.getKey().numerParkingu + ".  Najem na okres: [" + lista.getValue().dateStart + "]-["
                                + lista.getValue().dateEnd + "]. Pozostale miejsce wolne: " + lista.getKey().wolnaPowierzchnia + ", z :"+ lista.getKey().powierzchniaP3+"\n";
                        if(zawartość)listWithData += getItemParking(lista.getKey());
                        liczba++;
                    }
                } else {
                    if (lista.getKey().wolnaPowierzchnia == lista.getKey().powierzchniaP3) {
                        listWithData += "   > Parking numer: " + lista.getKey().numerParkingu + ". Pomieszczenie parkingowe jest calkiem puste. Najem na okres: [" + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]\n";
                        if(zawartość)listWithData += getItemParking(lista.getKey());
                        liczba++;
                    } else {
                        listWithData += "   > Parking numer: " + lista.getKey().numerParkingu + ". Najem na okres: [" + lista.getValue().dateStart + "]-[" + lista.getValue().dateEnd + "]. Pozostale miejsce wolne: " + lista.getKey().wolnaPowierzchnia + ", z :"+ lista.getKey().powierzchniaP3+"\n";
                        if(zawartość)listWithData += getItemParking(lista.getKey());
                        liczba++;
                    }
                }
            }
        }
        String m = mM;
        if(liczba>0) {
            m = mM + listWithData;
        }
        else m = "Brak wynajętych parkingów.";
        return m;
    }

    public String getItemParking(MiejsceParkingowe miejsceParkingowe) {
        String str = "";
            if(!miejsceParkingowe.listaZawartosciPojazdów.isEmpty()){
                str += "        Lista pojazdow: \n";
                for (Map.Entry<Integer, Pojazdy> mapPojazd: miejsceParkingowe.listaZawartosciPojazdów.entrySet()){
                    str +=   "           * nr.: " + mapPojazd.getValue().number + ", > nazwa: " + mapPojazd.getValue().nazwaPojazdu + ", zajmowana powierzchnia: " + mapPojazd.getValue().powierzchniam3
                            +", cechy pojazdu: "+ mapPojazd.getValue().getCechyString()+"\n";
                }
            }
            if(!miejsceParkingowe.listaZawartosciPrzedmiotów.isEmpty()){
                str += "        Lista przedmiotow: \n";
                for (Map.Entry<Integer, Przedmiot> mapPrzedmiotow: miejsceParkingowe.listaZawartosciPrzedmiotów.entrySet()){
                    str +=   "           * nr.: " + mapPrzedmiotow.getValue().number + ", > nazwa: " + mapPrzedmiotow.getValue().nazwaPrzedmiotu + ", zajmowana powierzchnia: " + mapPrzedmiotow.getValue().powierzchniam3+"\n";
                }
            }
        return str;
      }


    public void verificationFileM(Date dateNow) {
        for (Map.Entry<Mieszkanie, File> personVerificationList : this.listaNajmowanychMieszkan.entrySet()) {
            File f = personVerificationList.getValue();
            Calendar date30 = Calendar.getInstance();
            date30.setTime(f.dateEnd);
            date30.add(Calendar.DATE, 30);
            if (dateNow.after(f.dateEnd)) {
                if (!date30.getTime().after(dateNow)) {
                    personVerificationList.getKey().eksmisja(f.osiedle, f,this);
                } else {
                    personVerificationList.getKey().zadłużenie(this, f, java.sql.Date.valueOf(format1.format(dateNow)));
                }
            }
        }
    }

        public void verificationFileP(Date dateNow) {
            for (Map.Entry<MiejsceParkingowe, File> personVerificationList: this.listaNajmowanychParkingow.entrySet()) {
                File f = personVerificationList.getValue();
                Calendar date301 = Calendar.getInstance();
                date301.setTime(f.dateEnd);
                date301.add(Calendar.DATE, 30);
                if(dateNow.after(f.dateEnd)){
                    if(!date301.getTime().after(dateNow)) {
                        personVerificationList.getKey().eksmisja(f.osiedle,f,this);
                    }else{
                        personVerificationList.getKey().zadłużenie(this,f, java.sql.Date.valueOf(format1.format(dateNow)));
                    }
                }
            }
    }


}
