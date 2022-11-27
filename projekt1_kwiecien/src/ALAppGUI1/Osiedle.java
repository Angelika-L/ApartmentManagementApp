package ALAppGUI1;

import java.util.*;

public class Osiedle {

    ArrayList<Blok> listaBlok;
    HashMap<MiejsceParkingowe, Boolean> listaParking;
    String nazwaOsiedla;
    Integer numerOsiedla;

    public Osiedle(String nazwaOsiedla, Integer number) {
        this.nazwaOsiedla = nazwaOsiedla;
        this.numerOsiedla = number;
        this.listaBlok = new ArrayList<>();
        this.listaParking = new HashMap<>();
    }

    public void addBlok(Blok blok) {
        this.listaBlok.add(blok);
        blok.setOsiedleToBlok(this.nazwaOsiedla);
    }

    public void addParking(MiejsceParkingowe miejsceParkingowe) {
        this.listaParking.put(miejsceParkingowe, false);
        miejsceParkingowe.setOsiedleToParking(this);
    }

    public void setListaParking(MiejsceParkingowe miejsceParkingowe, Boolean b) {
        this.listaParking.replace(miejsceParkingowe, b);
    }

    @Override
    public String toString() {
        return "["+this.numerOsiedla + "]-" + this.nazwaOsiedla;
    }

    public Mieszkanie getM(Integer numberM) {
        final Mieszkanie[] mieszkanie = {null};
        this.listaBlok.stream().forEach(blok -> {
            for (Map.Entry<Mieszkanie, Boolean> lista : blok.listaMieszkan.entrySet()) {
                Mieszkanie m = lista.getKey();
                if (m.numerMieszkania == numberM) {
                    mieszkanie[0] = m;
                }
            }
        });
        return mieszkanie[0];
    }

    public Blok getB(Integer numberM) {
        try {
            final Blok[] blok = {null};
            this.listaBlok.stream().filter(p->p.numerBloku==numberM).forEach(b->blok[0]=b);
            return blok[0];
        }catch (NullPointerException e){
            return null;
        }

    }

    public MiejsceParkingowe getP(Integer numberP) {
        final MiejsceParkingowe[] parking = {null};
        this.listaParking.entrySet().stream().forEach(parkingoweBooleanEntry -> {
            if (parkingoweBooleanEntry.getKey().numerParkingu == numberP) {
                parking[0] = parkingoweBooleanEntry.getKey();
            }
        });
        return parking[0];
    }

    public List<String> getCurrentStatus() {
        List<String> listWithStatus = new ArrayList<>();

        listWithStatus.add("Osiedle: " + this.nazwaOsiedla +": ");
        for (Blok b : this.listaBlok) {
            listWithStatus.add("Blok: " + b.numerBloku +": ");

            Map<Mieszkanie, Boolean> sortedMieszkania = this.sortListM(b.listaMieszkan); //.entrySet().stream().forEach(p-> System.out.print(p.getKey().powierzchniaM3+" "));
            for (Map.Entry<Mieszkanie, Boolean> lista : sortedMieszkania.entrySet()) {
                Boolean wolne = lista.getValue();
                if (wolne) {
                    listWithStatus.add("   * mieszkanie: " + lista.getKey().numerMieszkania + "= jest niewynajęte. Powierzchnia = " + lista.getKey().powierzchniaM3+ ". Liczba pomieszczeń = " + lista.getKey().liczbaWolnychPomieszczen);
                } else if (lista.getKey().liczbaWolnychPomieszczen == 0) {
                    listWithStatus.add("   * mieszkanie: " + lista.getKey().numerMieszkania + " = jest wynajete. Powierzchnia = " + lista.getKey().powierzchniaM3+ ". Brak wolnych pokoji.");
                } else {
                    listWithStatus.add("   * mieszkanie: " + lista.getKey().numerMieszkania + " = jest wynajęte. Powierzchnia = " + lista.getKey().powierzchniaM3+ ". Liczba wolnych pomieszczeń pod wynajem = " + lista.getKey().liczbaWolnychPomieszczen);
                }
            }
        }
        Map<MiejsceParkingowe, Boolean> sortedParking = this.sortListP(this.listaParking);
        listWithStatus.add("__________________________________________________________________________________________________________________________________________");
        listWithStatus.add("Pomieszczenia parkingowe:");
            for (Map.Entry<MiejsceParkingowe, Boolean> lista : sortedParking.entrySet()) {
                Boolean wolne = lista.getValue();
                if (!wolne) {
                    listWithStatus.add("   * parking nr: " + lista.getKey().numerParkingu + " - jest do niewynajęty. Powierzchnia m^3 = " + lista.getKey().powierzchniaP3);
                }
                else if (lista.getValue() && lista.getKey().wolnaPowierzchnia != lista.getKey().powierzchniaP3) {
                    listWithStatus.add("   * parking nr: " + lista.getKey().numerParkingu + " - jest do wynajety. Powierzchnia m^3 = " + lista.getKey().powierzchniaP3
                            + ". Obecnie wypełniony. Zajęta przestrzeń :" + (lista.getKey().powierzchniaP3 - lista.getKey().wolnaPowierzchnia) + "m^3");

                    if (!lista.getKey().listaZawartosciPojazdów.isEmpty()) {
                        Map<Integer, Pojazdy> sortedZawartosc1 = this.sortListZ1(lista.getKey().listaZawartosciPojazdów);
                        listWithStatus.add("        Lista pojazdów: ");
                        for (Map.Entry<Integer, Pojazdy> mapPojazd : sortedZawartosc1.entrySet()) {
                            listWithStatus.add("           * nr.: " + mapPojazd.getValue().number + ", > nazwa: " + mapPojazd.getValue().nazwaPojazdu + ", zajmowana powierzchnia: " + mapPojazd.getValue().powierzchniam3 + ", cechy pojazdu: " + mapPojazd.getValue().getCechyString());
                        }
                    }
                    if (!lista.getKey().listaZawartosciPrzedmiotów.isEmpty()) {
                        Map<Integer, Przedmiot> sortedZawartosc2 = this.sortListZ2(lista.getKey().listaZawartosciPrzedmiotów);
                        listWithStatus.add("        Lista przedmiotow: ");
                        for (Map.Entry<Integer, Przedmiot> mapPrzedmiotow : sortedZawartosc2.entrySet()) {
                            listWithStatus.add("           * nr.: " + mapPrzedmiotow.getValue().number + ", > nazwa: " + mapPrzedmiotow.getValue().nazwaPrzedmiotu + ", zajmowana powierzchnia: " + mapPrzedmiotow.getValue().powierzchniam3);
                        }
                    }
                }
                else
                    listWithStatus.add("   * parking nr: " + lista.getKey().numerParkingu + " - jest do wynajety. Powierzchnia m^3 = " + lista.getKey().powierzchniaP3);
            }
        return listWithStatus;
    }

    public String getFreeParking() {
        String data = "lista wolnych parkingów: \n";
        try {
            for (Map.Entry<MiejsceParkingowe, Boolean> lista : this.listaParking.entrySet()) {
                Boolean wolne = lista.getValue();
                if (!wolne)
                    data += "* parking nr: " + lista.getKey().numerParkingu + " - jest do wynajęcia. Powierzchnia m^3 = " + lista.getKey().powierzchniaP3 + "\n";
            }
            return data;
        } catch (NullPointerException e) {
            System.out.print("Brak parkingów w bazie programu.");
            return null;
        }
    }

    public String getAllM(){
        try {
            String message = "";
            for (Blok blok : this.listaBlok) {  message += blok.getLiczbaPomieszczenMieszkania();
            }
            return message;
        } catch (NullPointerException e) {
            return "Osiedle nie ma dodanych żadnych blokow. Najpierw utwórz blok aby dodać nowe mieszkanie w programie.";
        }
    }

    public String getAllP(){
        String message = "";
        for (Map.Entry<MiejsceParkingowe, Boolean> lista : this.listaParking.entrySet()) {
            message += "   * nr. pomieszczenia parkingowego: " + lista.getKey().numerParkingu + ", powierzchnia=" + lista.getKey().powierzchniaP3 + "\n";
        }
        return message;
    }


    private  Map<Mieszkanie, Boolean> sortListM(Map<Mieszkanie, Boolean> unsortMap) {
        List<Map.Entry<Mieszkanie, Boolean>> list = new LinkedList<Map.Entry<Mieszkanie, Boolean>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Mieszkanie, Boolean>>() {
            public int compare(Map.Entry<Mieszkanie, Boolean> o1,Map.Entry<Mieszkanie, Boolean> o2) {
                return (o1.getKey().powierzchniaM3).compareTo(o2.getKey().powierzchniaM3);
            }
        });
        Map<Mieszkanie, Boolean> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Mieszkanie, Boolean> entry : list) {sortedMap.put(entry.getKey(), entry.getValue());}
        return sortedMap;
    }


    private  Map<MiejsceParkingowe, Boolean> sortListP(Map<MiejsceParkingowe, Boolean> unsortMap) {
        List<Map.Entry<MiejsceParkingowe, Boolean>> list = new LinkedList<Map.Entry<MiejsceParkingowe, Boolean>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<MiejsceParkingowe, Boolean>>() {
            public int compare(Map.Entry<MiejsceParkingowe, Boolean> o1,Map.Entry<MiejsceParkingowe, Boolean> o2) {
                return (o1.getKey().powierzchniaP3).compareTo(o2.getKey().powierzchniaP3);
            }
        });
        Map<MiejsceParkingowe, Boolean> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<MiejsceParkingowe, Boolean> entry : list) {sortedMap.put(entry.getKey(), entry.getValue());}
        return sortedMap;
    }



    private  Map<Integer, Przedmiot> sortListZ2(Map<Integer, Przedmiot> unsortMap) {
        List<Map.Entry<Integer, Przedmiot>> list = new LinkedList<Map.Entry<Integer, Przedmiot>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Przedmiot>>() {
            public int compare(Map.Entry<Integer, Przedmiot> o1,Map.Entry<Integer, Przedmiot> o2) {
                return (o1.getValue().powierzchniam3).compareTo(o2.getValue().powierzchniam3);
            }
        });
        Map<Integer, Przedmiot> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Przedmiot> entry : list) {sortedMap.put(entry.getKey(), entry.getValue());}
        return sortedMap;
    }



    private Map<Integer, Pojazdy> sortListZ1(Map<Integer, Pojazdy> unsortMap) {
        List<Map.Entry<Integer, Pojazdy>> list = new LinkedList<Map.Entry<Integer, Pojazdy>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Pojazdy>>() {
            public int compare(Map.Entry<Integer, Pojazdy> o1,Map.Entry<Integer, Pojazdy> o2) {
                return (o2.getValue().powierzchniam3).compareTo(o1.getValue().powierzchniam3);
            }
        });
        Map<Integer, Pojazdy> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Pojazdy> entry : list) {sortedMap.put(entry.getKey(), entry.getValue());}
        return sortedMap;
    }
}


//źródło inspiracji :
//https://www.baeldung.com/java-hashmap-sort
//http://devfoundry.pl/sortowanie-kolekcji-w-javie/
//https://stackoverflow.com/questions/47897994/sort-hashmap-values-compareto-method