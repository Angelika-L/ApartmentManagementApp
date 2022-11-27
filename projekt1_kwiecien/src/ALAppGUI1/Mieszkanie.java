package ALAppGUI1;

import ALAppGUI1.exception.ProblematicTenantException;
import java.sql.Date;
import java.util.Map;

public class Mieszkanie implements Najmowanie, Comparable<Mieszkanie>{

    Double powierzchniaM3;
    int liczbaPomieszczen, numerMieszkania, liczbaLokatorow, liczbaWolnychPomieszczen,numerBloku;
    String nazwaOsiedla;
    File nadrzednyDokumentNajmu;
    private static int counter = 1;

    public Mieszkanie(Double powierzchniaM3, int liczbaPomieszczen) {
        this.powierzchniaM3 = powierzchniaM3;
        this.liczbaPomieszczen = liczbaPomieszczen;
        this.liczbaWolnychPomieszczen = liczbaPomieszczen;
        this.liczbaLokatorow = 0;
        this.numerMieszkania=counter++;
    }

    public void setNumerBlokuToM(int numerBloku) {
        this.numerBloku = numerBloku;
    }

    public void setNazwaOsiedlaToM(String nazwaOsiedla) {
        this.nazwaOsiedla = nazwaOsiedla;
    }

    public Double getPowierzchniaM3(){return powierzchniaM3;}

    @Override
    public void najem(Osiedle osiedle, Osoba osoba, Date start, Date end) {
        if(osoba.listaAktaZadluzenDoc.size()>3 ) {
            try {
                Integer sum = osoba.listaAktaZadluzenDoc.values().stream().mapToInt(p-> p.liczbaPomieszczeń).sum();
                 throw new ProblematicTenantException(" ERROR --> Osoba "+osoba.NameOsoba+" posiadała już najem pomieszczeń: "+sum);
            }
            catch (ProblematicTenantException e) {
                e.printStackTrace();
            }
        }
        else {
            if (osoba.listaNajmowanychMieszkan.size() < 5) {
                osiedle.listaBlok.stream().forEach(blok -> {
                    if (blok.numerBloku == this.numerBloku) {
                        if (blok.getStatus(this))
                            System.out.println("ERROR --> Mieszkanie nr " + this.numerMieszkania + " jest już wynajęte!!");
                        else {
                            blok.setStatus(this, true);
                            osoba.addMieszkanie(this, new File(osoba, start, end, File.TypDoc.NAJEMCA, osiedle));
                            System.out.println(" SUCCESS --> Utworzenie dokumentu najmu mieszkania. Data: "+start+" - "+end);
                            this.liczbaLokatorow = 0;
                            this.nadrzednyDokumentNajmu = osoba.listaNajmowanychMieszkan.get(this);
                            System.out.println(" SUCCESS --> Mieszkanie nr " + this.numerMieszkania + " zostało wynajete. ");
                        }
                    }
                });
            } else System.out.println("ERROR --> Nie można wynająć wiecej niż 5 całych mieszkań.");
        }
    }

    @Override
    public void rezygnacjaNajmu(Osiedle osiedle, Osoba osoba) {
        if(osoba.listaNajmowanychMieszkan.get(this).status.equals(File.StatusNajmu.ZADŁUŻENIE)){
            osoba.listaAktaZadluzenDoc.remove(this);
            System.out.println("Usunieto dokument o zadłużeniu z akt "+ osoba.NameOsoba+".");
        }
        osiedle.listaBlok.stream().forEach( blok  -> {
            if(blok.numerBloku == this.numerBloku){
                blok.setStatus(this,false);

                osoba.listaNajmowanychMieszkan.get(this).updateStatus(File.StatusNajmu.ZAKOŃCZNEONE);
                System.out.println(" SUCCESS --> Zamkniecie umowy najmu.");
                for (Map.Entry<Osoba,File> lista: this.nadrzednyDokumentNajmu.listaLokatorow.entrySet()) {
                    this.wymeldowanie(lista.getKey());
                    System.out.println(" SUCCESS --> Zakończenie wynajmu dla:"+lista.getKey().NameOsoba+", status umowy: "+lista.getValue().status);
                }

                this.liczbaLokatorow = 0;
                this.liczbaWolnychPomieszczen = this.liczbaPomieszczen;
                System.out.println(" SUCCESS --> Mieszkanie nr "+this.numerMieszkania+" zostało zwolnione z najmu.");
                this.nadrzednyDokumentNajmu = null;
            }
        });
    }

    @Override
    public void odnowienieNajmu(Osoba osoba, Date newDate){
        if(!(osoba==osoba.listaNajmowanychMieszkan.get(this).najemca) && newDate.after(osoba.listaNajmowanychMieszkan.get(this).najemca.listaNajmowanychMieszkan.get(this).dateEnd))
        {
            System.out.println("ERROR --> Data najmu nie może byc póżniejsza niż data końca najmu właściciela mieszkania, tj:" + osoba.listaNajmowanychMieszkan.get(this).najemca.listaNajmowanychMieszkan.get(this).dateEnd);
        }
        else {
            if (osoba.listaNajmowanychMieszkan.get(this).status.equals(File.StatusNajmu.ZADŁUŻENIE)) {
                osoba.listaAktaZadluzenDoc.remove(this);
                System.out.println("Usunieto dokument o zadłużeniu z akt " + osoba.NameOsoba + ".");
            }
            osoba.listaNajmowanychMieszkan.get(this).updateStatus(File.StatusNajmu.TRWA);
            osoba.listaNajmowanychMieszkan.get(this).updateDateEnd(newDate);
            System.out.println(" SUCCESS --> Odnowiono umowę najmu. Nowa data końcowa najmu to: " + newDate);
        };
}

    public void meldowanie(Osiedle osiedle, Osoba osoba, Date start, Date end, int liczbaPomieszczen) {
        if( (start.after(this.nadrzednyDokumentNajmu.dateStart) || start.equals(this.nadrzednyDokumentNajmu.dateStart)) &&
                (end.before(this.nadrzednyDokumentNajmu.dateEnd) || end.equals(this.nadrzednyDokumentNajmu.dateEnd)) ) {
            osiedle.listaBlok.stream().forEach(blok -> {
                if (blok.numerBloku == this.numerBloku) {
                    if (!blok.getStatus(this))
                        System.out.println("ERROR --> Mieszkanie nr " + this.numerMieszkania + " nie jest wynajęte i udostepnione do podnajmu!! Nie można wynając pojedyńczego pokoju");
                    else if (this.liczbaWolnychPomieszczen < liczbaPomieszczen) {
                        System.out.println("ERROR --> Brak wolnych pokoji. Obecnie w tym mieszkaniu jest dostepnych pod wynajem: " + this.liczbaWolnychPomieszczen + " pomieszczęń.");
                    } else {
                        osoba.addMieszkanie(this, new File(osoba, start, end, File.TypDoc.LOKATOR, osiedle)); //lokator wynajmuje pomieszczenia
                        this.nadrzednyDokumentNajmu.updateListaLokatorow(osoba, osoba.listaNajmowanychMieszkan.get(this));
                        osoba.listaNajmowanychMieszkan.get(this).updateLiczbaPomieszczen(liczbaPomieszczen); //dodanie lumowy lokatora do umowy nadrzednej umowy najmu
                        System.out.println(" SUCCESS --> Utworzenie dokumentu najmu dla " + osoba.NameOsoba + " na pomieszczenia w mieszkaniu: " + this.numerMieszkania + ".");
                        this.liczbaLokatorow += 1;
                        this.liczbaWolnychPomieszczen -= liczbaPomieszczen;
                    }
                }
            });
        }
        else {
            System.out.println("ERROR --> Zmień daty wynajmu. Możliwy termin w przedziale od: "+this.nadrzednyDokumentNajmu.dateStart+" do: "+this.nadrzednyDokumentNajmu.dateEnd+".");
            System.out.print("ERROR --> Termin niedostepny od: "+start+" do: "+end+".\n");
        }
    }

    public void wymeldowanie(Osoba osoba) {
        osoba.listaNajmowanychMieszkan.get(this).updateStatus(File.StatusNajmu.ZAKOŃCZNEONE);
         this.liczbaLokatorow -= 1;
        this.liczbaWolnychPomieszczen += osoba.listaNajmowanychMieszkan.get(this).liczbaPomieszczeń;
    }

    @Override
    public void zadłużenie(Osoba osoba, File file, Date data) {
        boolean exists = true;
        for (Map.Entry<Mieszkanie, File> existFile : osoba.listaAktaZadluzenDoc.entrySet()) {
            if(existFile.getValue() == file) exists = false;
        }
        if (exists) {
            file.updateStatus(File.StatusNajmu.ZADŁUŻENIE);
            osoba.addZadluzenie(this, file);
          }
    }

    @Override
    public void eksmisja(Osiedle osiedle, File file, Osoba osoba) {
        if(file.typDokumentu==File.TypDoc.LOKATOR){
            this.wymeldowanie(osoba);
        } else {
            osiedle.listaBlok.stream().forEach( blok  -> {
                if(blok.numerBloku == this.numerBloku){
                    blok.setStatus(this,false);
                    file.updateStatus(File.StatusNajmu.ZAKOŃCZNEONE);
                    if(!(file.listaLokatorow.isEmpty() || file.listaLokatorow==null ) ) {
                        for (Map.Entry<Osoba, File> lista : file.listaLokatorow.entrySet()) {
                            this.wymeldowanie(lista.getKey());
                        }
                    }
                    this.liczbaLokatorow = 0;
                    this.liczbaWolnychPomieszczen = this.liczbaPomieszczen;
                    this.nadrzednyDokumentNajmu = null;
                }
            });
        }
    }



    @Override
    public int compareTo(Mieszkanie mieszkanie) {
        return (int) (this.getPowierzchniaM3() - mieszkanie.getPowierzchniaM3());
    }
}
