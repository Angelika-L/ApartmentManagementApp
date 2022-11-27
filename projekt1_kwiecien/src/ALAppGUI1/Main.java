package ALAppGUI1;

import ALAppGUI1.Pojazdy.CechyChaktarestyczne;
import ALAppGUI1.thread.ContractRenewal;
import ALAppGUI1.thread.DateCalender;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Main {

    private static final String PATH_FILE = "src/fileSave/";
    private static Integer counterDev = 101;
    private static Integer counterUser = 14;
    private static Integer counterO = 1;


    public static  void main(String[] args) {


        HashMap<Integer, Osiedle> listaOsiedli = new HashMap<>();
        HashMap<Integer, Osoba> listaOsob = new HashMap<>();


//zainicjownaie kilka obiektów do programu: osoba, mieszkania , osiedle,..
        Developer developerOsiedla1 = new Developer("Pablo Escabar", "12363830054", "ul. Rionegro, Colombia", java.sql.Date.valueOf("1949-1-19"),100);
        Developer developerOsiedla2 = new Developer("Arnold Schwarzenegger", "12365550054", "ul. OneStreet, Waszyngton", java.sql.Date.valueOf("1987-1-19"),101);
        listaOsob.put(developerOsiedla1.numerOsoby, developerOsiedla1);
        listaOsob.put(developerOsiedla2.numerOsoby, developerOsiedla2);

        Osoba osoba1 = new Osoba("Ron Wesley", "12345678911", "ul. Pokątna, Hogward", java.sql.Date.valueOf("1983-1-7"),false,10);
        Osoba osoba2 = new Osoba("Hermiona Gringer", "12345670032", "ul. Cross Rode, UK", java.sql.Date.valueOf("1978-11-10"),false,11);
        Osoba osoba3 = new Osoba("Harry Styles", "12345698100", "ul. Beverly Hills, Los Angeles", java.sql.Date.valueOf("1999-6-19"),false,12);
        Osoba osoba4 = new Osoba("Keylie Kardaschian", "12345678913", "ul. Beverly Hills, Los Angeleg", java.sql.Date.valueOf("1997-4-23"),false,13);
        Osoba osoba5 = new Osoba("Jessica Mercedez", "12536287910", "ul. Złota 44, WAWA", java.sql.Date.valueOf("1995-7-30"),false,14);
        listaOsob.put(osoba1.numerOsoby, osoba1);
        listaOsob.put(osoba2.numerOsoby, osoba2);
        listaOsob.put(osoba3.numerOsoby, osoba3);
        listaOsob.put(osoba4.numerOsoby, osoba4);
        listaOsob.put(osoba5.numerOsoby, osoba5);

        Osiedle osiedle1 = new Osiedle("Tymbark",1);
        developerOsiedla1.addOsiedle(osiedle1, 1);
        listaOsiedli.put(osiedle1.numerOsiedla, osiedle1);

        Blok blok1 = new Blok();
        osiedle1.addBlok(blok1);

        Mieszkanie mieszkanie1 = new Mieszkanie(84.00, 4);
        Mieszkanie mieszkanie2 = new Mieszkanie(62.50, 2);
        Mieszkanie mieszkanie3 = new Mieszkanie(68.00, 3);
        Mieszkanie mieszkanie4 = new Mieszkanie(50.00, 1);
        Mieszkanie mieszkanie5 = new Mieszkanie(79.00, 4);
        Mieszkanie mieszkanie6 = new Mieszkanie(54.00, 2);
        Mieszkanie mieszkanie7 = new Mieszkanie(103.20, 4);
        Mieszkanie mieszkanie8 = new Mieszkanie(50.00, 1);
        Mieszkanie mieszkanie9 = new Mieszkanie(90.25, 4);
        Mieszkanie mieszkanie10 = new Mieszkanie(62.50, 2);

        blok1.addMierszkanie(mieszkanie1);
        blok1.addMierszkanie(mieszkanie2);
        blok1.addMierszkanie(mieszkanie3);
        blok1.addMierszkanie(mieszkanie4);
        blok1.addMierszkanie(mieszkanie5);
        blok1.addMierszkanie(mieszkanie6);
        blok1.addMierszkanie(mieszkanie7);
        blok1.addMierszkanie(mieszkanie8);
        blok1.addMierszkanie(mieszkanie9);
        blok1.addMierszkanie(mieszkanie10);

        MiejsceParkingowe parkingowe1 = new MiejsceParkingowe(20.00, osiedle1);
        osiedle1.listaParking.put(parkingowe1, false);
        MiejsceParkingowe parkingowe2 = new MiejsceParkingowe(40.00, osiedle1);
        osiedle1.listaParking.put(parkingowe2, false);
        MiejsceParkingowe parkingowe3 = new MiejsceParkingowe(50.00, osiedle1);
        osiedle1.listaParking.put(parkingowe3, false);
        MiejsceParkingowe parkingowe4 = new MiejsceParkingowe(15.00, osiedle1);
        osiedle1.listaParking.put(parkingowe4, false);
        MiejsceParkingowe parkingowe6 = new MiejsceParkingowe(20.00, osiedle1);
        osiedle1.listaParking.put(parkingowe6, false);
        MiejsceParkingowe parkingowe7 = new MiejsceParkingowe(24.00, osiedle1);
        osiedle1.listaParking.put(parkingowe7, false);
        MiejsceParkingowe parkingowe8 = new MiejsceParkingowe(30.00, osiedle1);
        osiedle1.listaParking.put(parkingowe8, false);
        MiejsceParkingowe parkingowe9 = new MiejsceParkingowe(16.00, osiedle1);
        osiedle1.listaParking.put(parkingowe9, false);


        HashMap<CechyChaktarestyczne, String> mapaPojazd = new HashMap<>();
        mapaPojazd.put(CechyChaktarestyczne.TYP_SILNIKA, "hybryda");
        mapaPojazd.put(CechyChaktarestyczne.ROCZNIK, "2016");
        mapaPojazd.put(CechyChaktarestyczne.SKRZYNIA_BIEGOW, "ręczna");
        Pojazdy pojazd1 = new Pojazdy("Toyota", TypyPojazdow.SAMOCHÓD_MIEJSKI, 12.50, mapaPojazd);
        HashMap<CechyChaktarestyczne, String> mapaPojazd2 = new HashMap<>();
        mapaPojazd2.put(CechyChaktarestyczne.TYP_SILNIKA, "disel");
        mapaPojazd2.put(CechyChaktarestyczne.ROCZNIK, "2020");
        mapaPojazd2.put(CechyChaktarestyczne.SKRZYNIA_BIEGOW, "ręczna");
        mapaPojazd2.put(CechyChaktarestyczne.POJEMNOSC_PRZYCZEPY, "10");
        Pojazdy pojazd2 = new Pojazdy("Jeep", TypyPojazdow.SAMOCHÓD_TERENOWY, 17.90, mapaPojazd2);
        HashMap<CechyChaktarestyczne, String> mapaPojazd3 = new HashMap<>();
        mapaPojazd3.put(CechyChaktarestyczne.ROCZNIK, "2019");
        mapaPojazd3.put(CechyChaktarestyczne.MAX_ZANURZENIE, "0.65");
        mapaPojazd3.put(CechyChaktarestyczne.DLUGOSC_LODZI, "5.70 metrów");
        Pojazdy pojazd3 = new Pojazdy("Sofia", TypyPojazdow.ŁÓDŹ, 25.00, mapaPojazd3);

//uruchomienie wątków:
        Calendar calendar = Calendar.getInstance();
        Date dateFromCalendar = calendar.getTime();
        System.out.println("data z kalendarza" + dateFromCalendar);

        calendar.add(Calendar.DAY_OF_WEEK, 1);
        System.out.println("data" + calendar.getTime());

        DateCalender threadDateCalender = new DateCalender(calendar);
        threadDateCalender.start();
        ContractRenewal threadCheckContractRenewal = new ContractRenewal(calendar, listaOsob);
        threadCheckContractRenewal.start();


//inicjowanie umów najmu:
        Calendar calendarUmowyStart = Calendar.getInstance();
        Calendar calendarUmowyEnd = Calendar.getInstance();
        Calendar calendarUmowyEnd3 = Calendar.getInstance();
        Calendar calendarUmowyEnd2 = Calendar.getInstance();
        calendarUmowyStart.add(Calendar.DAY_OF_WEEK, 1);
        calendarUmowyEnd.add(Calendar.DAY_OF_WEEK, 15);
        calendarUmowyEnd3.add(Calendar.DAY_OF_WEEK, 10);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        mieszkanie1.najem(osiedle1, osoba1, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd.getTime())));
        mieszkanie1.meldowanie(osiedle1, osoba2, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd3.getTime())), 1);
        mieszkanie1.meldowanie(osiedle1, osoba3, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd3.getTime())), 2);
        mieszkanie2.najem(osiedle1, osoba2, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf("2024-09-07"));
        parkingowe1.najem(osiedle1, osoba1, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf("2021-08-08"));
        parkingowe4.najem(osiedle1, osoba4, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf("2021-08-08"));
        parkingowe2.najem(osiedle1, osoba2, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd.getTime())));
        parkingowe8.najem(osiedle1, osoba1, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd.getTime())));
        mieszkanie10.najem(osiedle1, osoba5, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf("2024-09-07"));
        parkingowe7.najem(osiedle1, osoba5, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf("2024-09-07"));

        parkingowe1.addPrzedmiotToParking(new Przedmiot("Rower", 12.00));
        parkingowe1.addPrzedmiotToParking(new Przedmiot("kosz", 2.00));
        parkingowe7.addPrzedmiotToParking(new Przedmiot("Rower", 5.00));
        parkingowe7.addPrzedmiotToParking(new Przedmiot("sprzet sportowy", 3.00));
        parkingowe2.addPojazdToParking(pojazd1);
        parkingowe4.addPrzedmiotToParking(new Przedmiot("taczki", 12.00));
        parkingowe8.addPojazdToParking(pojazd3);

        calendarUmowyEnd.add(Calendar.DAY_OF_WEEK, 4);
        mieszkanie3.najem(osiedle1, osoba3, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd.getTime())));
        calendarUmowyEnd.add(Calendar.MONTH, 3);
        calendarUmowyEnd2.add(Calendar.DAY_OF_WEEK, 20);
        mieszkanie5.najem(osiedle1, osoba4, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf("2023-09-30"));
        mieszkanie5.meldowanie(osiedle1, osoba2, java.sql.Date.valueOf(format1.format(calendarUmowyStart.getTime())), java.sql.Date.valueOf(format1.format(calendarUmowyEnd2.getTime())), 2);
        mieszkanie5.meldowanie(osiedle1, osoba3, java.sql.Date.valueOf("2020-09-09"), java.sql.Date.valueOf("2023-09-09"), 2);






        System.out.println("\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<PROGRAM  DO ZRZĄDZANIA OSIEDLAMI DEWELOPERSKIMI : S21401>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Boolean exite = true;
        Osoba osobaScanner;
        Osiedle osiedleScanner = null;

        outerloop:
        {
            while (exite) {
                Boolean powrot1 = true;
                System.out.println(".__________________________________________________________________________________________________.");
                System.out.println("| LOGOWANIE                                                                                        | obecna data:"+threadDateCalender.getDate());
                System.out.println("| Podaj numer osoby lub wpisz 'X' jeśli chcesz utworzyć nową osobe:                                |" +
                                 "\n|__________________________________________________________________________________________________|");
                listaOsob.values().forEach(o -> System.out.print(o.toString() + " . "));
                System.out.println("\n.");
                Scanner textScanner = new Scanner(System.in);
                String numerOsoby = textScanner.nextLine();
                try {
                    if (numerOsoby.contains("x") || numerOsoby.contains("X")) {
                        osobaScanner = createNewPerson(listaOsob);
                        threadCheckContractRenewal.setNewOsobaToCheck(osobaScanner.numerOsoby,osobaScanner);
                        System.out.println("| Jesteś zalogowany jako:  " + osobaScanner.NameOsoba);
                    } else {
                        osobaScanner = listaOsob.get(Integer.valueOf(numerOsoby));
                        System.out.println("| Jesteś zalogowany jako:  " + osobaScanner.NameOsoba);
                    }
                } catch (NullPointerException e) {
                    System.out.println("Niepoprawny numer id użytkownika. Dodaj nowego user'a:");
                    osobaScanner = createNewPerson(listaOsob);
                    threadCheckContractRenewal.setNewOsobaToCheck(osobaScanner.numerOsoby,osobaScanner);
                    System.out.println("\n| Jesteś zalogowany jako:  " + osobaScanner.NameOsoba);
                }
                System.out.println(".__________________________________________________________________________________________________.");
                System.out.println("| Wybierz numer osiedla z listy  lub wpisz 'X' jeśli chcesz utworzyć nowe:                         | obecna data:"+threadDateCalender.getDate());
                System.out.println("|__________________________________________________________________________________________________|");
                listaOsiedli.values().forEach(o -> System.out.print(o.toString() + " . "));
                System.out.println("\n.");
                String textOsiedle = textScanner.nextLine();
                try {
                    if (textOsiedle.contains("X") || textOsiedle.contains("x")) {
                        osiedleScanner = createNewOsiedle(listaOsiedli,listaOsob);
                    } else {
                        osiedleScanner = getO(Integer.valueOf(textOsiedle),listaOsiedli);
                    }
                } catch (NullPointerException e) {
                    System.out.println("Niepoprawny numer id osiedla.");
                }


                innerloop:
                while (powrot1) {
                    Boolean powrot2 = true;
                    Scanner textScanner2 = new Scanner(System.in);
                    System.out.println(".__________________________________________________________________________________________________.");
                    System.out.println("| user: "+ osobaScanner.toString()+ ",   osiedle: "+ osiedleScanner.toString()+"                                         |");
                    System.out.println("| MENU - wybierz numer funkcji:                                                                    | obecna data:"+threadDateCalender.getDate());
                    System.out.println("| 1-Mieszkania | 2-Parking | 3-Moje_dane |  4-Zapisz_stan_osiedla_w_pliku |  5-wyloguj |  6-exite  |  " +
                                     "\n|..................................................................................................|");
                    String text1 = textScanner2.nextLine();
                    if (text1.contains("6") || text1.contains("exit")) {
                        exit(threadDateCalender, threadCheckContractRenewal);
                        break outerloop;
                    } else if (text1.contains("wyloguj") || text1.contains("5")) {
                        System.out.println("|................................Zostałeś wylogowany..........................................");
                        break innerloop;
                    } else if (text1.equals("Mieszkania") || text1.contains("1")) {
                        mieszkanieloop:
                        while (powrot2) {
                            System.out.println(".....................................................................................................");
                            System.out.println("| 1-MIESZKANIA - wybierz numer funkcji:                                                             | obecna data:"+ threadDateCalender.getDate()+
                                             "\n|  1-wolne .  2-wynajete .  3-dodaj_mieszkanie  . 4-dodaj_blok  .  5-powrót . 6-wyloguj  . 7-exite  |" +
                                             "\n|...................................................................................................|");
                            Scanner textScanner3 = new Scanner(System.in);
                            String text2 = textScanner3.nextLine();
                            if (text2.contains("wolne") || text2.contains("1")) {
                                System.out.println("...................................................................");
                                System.out.println("| mieszkania > 1-WOLNE - wybierz numer funkcji:   "  +
                                                 "\n|  1-wynajem  .  2-zapisz_dane_w_pliku  .  3-powrot  .  4-wyloguj   " +
                                                 "\n|_                 ");
                                System.out.println("| Lista wolnych mieszkań: ");
                                List<String> data = new ArrayList<>();
                                osiedleScanner.listaBlok.forEach(b -> data.add(b.getWolnePomieszczenMieszkania()));
                                data.forEach(p -> System.out.println(p));
                                System.out.println("...................................................................");
                                String text3 = textScanner3.nextLine();
                                if (text3.contains("wynajem") || text3.contains("1")) {
                                    System.out.println("| mieszkania > wolne > WYNAJEM " +
                                            "\n| Chcesz wynająć całe mieszkanie?  Y/N                         >lub jesli chcesz przerwać wpisz 'X'");
                                    String textCaleM = textScanner3.nextLine();
                                    if (textCaleM.contains("Y") || textCaleM.contains("y")) {
                                        System.out.println("| Podaj numer mieszkania:");
                                        Scanner textScanner4 = new Scanner(System.in);
                                        Integer textWynajemM = textScanner4.nextInt();
                                        Mieszkanie m = osiedleScanner.getM(textWynajemM);
                                        System.out.println("| Podaj date rozpoczęcia najmu. (format:yyyy-mm-dd)             obecna data:"+ threadDateCalender.getDate() );
                                        Scanner textScanner9 = new Scanner(System.in);
                                        String dataStartWynajemM = textScanner9.nextLine();
                                        System.out.println("| Podaj date zakończenia najmu. (format:yyyy-mm-dd)            obecna data:"+threadDateCalender.getDate() );
                                        String dataEndWynajemM = textScanner9.nextLine();
                                        m.najem(osiedleScanner, osobaScanner, java.sql.Date.valueOf(dataStartWynajemM), java.sql.Date.valueOf(dataEndWynajemM));
                                        System.out.println(".__________________________________________________________________________________________________.");
                                    } else if (textCaleM.contains("N") || textCaleM.contains("n")) {
                                        try {
                                            System.out.println("| Podaj numer mieszknaie:");
                                            Scanner textScanner5 = new Scanner(System.in);
                                            Integer textWynajemM = textScanner5.nextInt();
                                            Mieszkanie m = osiedleScanner.getM(textWynajemM);
                                            System.out.println("| ~~~możliwy termin wynajmu pomiedzy datami: [" + m.nadrzednyDokumentNajmu.dateStart + "] - [" + m.nadrzednyDokumentNajmu.dateEnd+"]");
                                            System.out.println("| ~~~liczba wolnych pomieszczeń: " + m.liczbaWolnychPomieszczen);
                                            System.out.println("| Podaj liczbę pokoi do najmu:");
                                            Integer numberRoomWynajemM = textScanner5.nextInt();
                                            Scanner textScanner6 = new Scanner(System.in);
                                            System.out.println("| Podaj date rozpoczęcia najmu. (format:yyyy-mm-dd)           obecna data:"+threadDateCalender.getDate() );
                                            String dataStartWynajemM = textScanner6.nextLine();
                                            System.out.println("| Podaj date zakończenia najmu. (format:yyyy-mm-dd)           obecna data:"+ threadDateCalender.getDate() );
                                            String dataEndWynajemM = textScanner6.nextLine();
                                            m.meldowanie(osiedleScanner, osobaScanner, java.sql.Date.valueOf(dataStartWynajemM), java.sql.Date.valueOf(dataEndWynajemM), numberRoomWynajemM);
                                            System.out.println(".__________________________________________________________________________________________________.");
                                        } catch (NullPointerException e) {
                                            System.out.println("Błąd: mieszkanie nie jest jeszcze wynajęte, dlatego nie jest dostepny wynajem pojedyńczych pokoi.  ");
                                        }
                                    }
                                } else if (text3.contains("zapisz_dane_w_pliku") || text3.contains("2")) {
                                    System.out.println("| Podaj nazwę dla pliku:");
                                    String fileName = textScanner3.nextLine();
                                    createFileAndSave(fileName, data);
                                } else if (text3.contains("powrot") || text3.contains("powrót") || text3.contains("3")) {
                                    break mieszkanieloop;
                                } else if (text3.contains("4")) {
                                    break innerloop;
                                } else {
                                    System.out.println("Nie rozpoznano funkcji.");
                                }
                            } else if (text2.contains("wynajete") || text2.contains("2")) {
                                System.out.println("...................................................................");
                                System.out.println("| mieszkania > 2-WYNAJETE - wybierz numer funkcji: "+
                                                 "\n|  1-zapisz_dane_w_pliku  .   2-powrot  .  3-wyloguj    " +
                                                 "\n|_  " +
                                                 "\n| Lista wynajętych mieszkań: \n");
                                List<String> data = new ArrayList<>();
                                listaOsob.values().stream().filter(f-> !f.listaNajmowanychMieszkan.isEmpty()).forEach(b-> data.add(b.getListMieszkania("\n Jest właścicielem mieszkania numer : \n", " Jest najemcą pomieszczeń : \n","OSOBA: "+ b.toString()+": ")));
                                data.forEach(p -> System.out.print(p));
                                System.out.println("...................................................................");
                                String text3 = textScanner2.nextLine();
                                if (text3.contains("zapisz_dane_w_pliku") || text3.contains("1")) {
                                    System.out.println("| Podaj nazwę dla pliku:");
                                    String fileName = textScanner2.nextLine();
                                    createFileAndSave(fileName, data);
                                } else if (text3.contains("powrot") || text3.contains("2")) {
                                    break mieszkanieloop;
                                } else if (text3.contains("3")) {
                                    break innerloop;
                                } else {
                                    System.out.println("Nie rozpoznano funkcji");
                                }
                            } else if (text2.contains("dodaj_mieszkanie") || text2.contains("3")) {
                                System.out.println("...................................................................");
                                System.out.println("| mieszkanie > 3-DODAJ_MIESZKANIE - wybierz numer funkcji:"+
                                                 "\n|  1-wprowadz_nowe_mieszkanie  .   2-powrot  " +
                                                 "\n|_" +
                                                " \n| Lista obecnych mieszkań znajdujących się w systemie: ");
                                List<String> data = new ArrayList<>();
                                data.add(osiedleScanner.getAllM());
                                data.forEach(p -> System.out.println(p));
                                System.out.println("...................................................................\n");
                                String text3 = textScanner2.nextLine();
                                if (text3.contains("wprowadz_nowe_mieszkanie") || text3.contains("1")) {
                                    System.out.println("| Podaj blok w którym sie znajduje mieszkanie:");
                                    String bloknumber = textScanner2.nextLine();
                                    Blok blok = osiedleScanner.getB(Integer.valueOf(bloknumber.trim()));
                                    if(blok==null){
                                        System.out.println(" !! Podany blok nie istnieje w systemie dla osiedla :"+osiedleScanner.nazwaOsiedla+".  Możesz go dodać w opcji: '4-dodaj_blok'");
                                    }else {
                                        System.out.println("| Podaj Powierzchnie mieszknaia w metrach sześciennych:");
                                        Double powierzchnia = textScanner2.nextDouble();
                                        System.out.println("| Podaj liczbę pomieszczeń mieszknaia:");
                                        Integer liczba_pomieszczen = textScanner2.nextInt();
                                        blok.addMierszkanie(new Mieszkanie(powierzchnia, liczba_pomieszczen));
                                        System.out.println(" SUCCESS --> Nowe mieszkanie zostało dodane do systemu.");
                                    }
                                } else if (text3.contains("powrot") || text3.contains("2")) {
                                    break mieszkanieloop;
                                }else{
                                    System.out.println("Nie rozpoznano funkcji.");
                                }
                            } else if (text2.contains("dodaj_blok") || text2.contains("4")) {
                                System.out.println("...................................................................");
                                System.out.println("| mieszkanie > 2-DODAJ_NOWEGO_BLOKU - wybierz numer funkcji: "+
                                                 "\n|  1-wprowadz_nowe_bloku  .   2-powrot  " +
                                                 "\n|_   " +
                                                 "\n| Lista obecnych bloków znajdujących się w systemie: ");
                                List<String> data = new ArrayList<>();
                                osiedleScanner.listaBlok.forEach(b -> data.add("  * nr. bloku: " + b.numerBloku));
                                data.forEach(p -> System.out.println(p));
                                System.out.println("...................................................................");
                                String text3 = textScanner2.nextLine();
                                if (text3.contains("wprowadz_nowe_bloku") || text3.contains("1")) {
                                    osiedleScanner.addBlok(new Blok());
                                    System.out.println("| Utworzono nowy blok.");
                                } else if (text3.contains("powrot") || text3.contains("2")) {
                                    break mieszkanieloop;
                                } else{
                                    System.out.println("Nie rozpoznano funkcji.");
                                }
                            } else if (text2.contains("powrot") || text2.contains("5")) {
                                break mieszkanieloop;
                            } else if (text2.contains("6")) {
                                System.out.println("|................................Zostałeś wylogowany..........................................");
                                break innerloop;
                            } else if (text2.contains("7")) {
                                exit(threadDateCalender, threadCheckContractRenewal);
                                break outerloop;
                            }
                        }
                    } else if (text1.contains("Parking") || text1.contains("2")) {
                        parkingLoop:
                        while (powrot2) {
                            System.out.println(".....................................................................................................");
                            System.out.println("| 1-PARKING - wybierz numer funkcji:                                                                | obecna data:"+ threadDateCalender.getDate()+
                                             "\n|  1-wolne  .   2-wynajete  .  3-dodaj_parking .  4-powrót  .  5-wyloguj  .  6-exite                |" +
                                             "\n|...................................................................................................|");
                            Scanner text = new Scanner(System.in);
                            String text2 = text.nextLine();
                            if (text2.contains("wolne") || text2.contains("1")) {
                                System.out.println("...................................................................");
                                System.out.println("| parking > 1-WOLNE - wybierz numer funkcji:  " +
                                                 "\n|  1-wynajem  .  2-zapisz_dane_w_pliku  .  3-powrot  .  4-wyloguj    " +
                                                 "\n|_");
                                System.out.println("| Lista wolnych pomieszczeń parkingowych: ");
                                System.out.println(osiedleScanner.getFreeParking());

                                System.out.println("...................................................................");
                                String text3 = textScanner2.nextLine();
                                if (text3.contains("wynajem") || text3.contains("1")) {
                                    System.out.println("| parking > wolne > WYNAJEM ");
                                    System.out.println("| Podaj numer pomieszczenia parkingowego:");
                                    Scanner textScanner4 = new Scanner(System.in);
                                    Integer textWynajemM = textScanner2.nextInt();
                                    MiejsceParkingowe parking = osiedleScanner.getP(textWynajemM);
                                    System.out.println("| Podaj datę rozpoczęcia najmu. (format:yyyy-mm-dd)");
                                    String dataStartWynajemM = textScanner4.nextLine();
                                    System.out.println("| Podaj datę zakończenia najmu. (format:yyyy-mm-dd)");
                                    String dataEndWynajemM = textScanner4.nextLine();
                                    parking.najem(osiedleScanner, osobaScanner, java.sql.Date.valueOf(dataStartWynajemM), java.sql.Date.valueOf(dataEndWynajemM));
                                    System.out.println(".__________________________________________________________________________________________________.");
                                } else if (text3.contains("zapisz_dane_w_pliku") || text3.contains("2")) {
                                    System.out.println("| Podaj nazwę dla pliku:");
                                    String fileName = textScanner2.nextLine();
                                    ArrayList<String> data = new ArrayList<>();
                                    data.add(osiedleScanner.getFreeParking());
                                    createFileAndSave(fileName, data);
                                } else if (text3.contains("powrot") || text3.contains("powrót") || text3.contains("3")) {
                                    break parkingLoop;
                                } else if (text3.contains("4")) {
                                    System.out.println("|................................Zostałeś wylogowany..........................................");
                                    break innerloop;
                                } else {
                                    System.out.println("Nie rozpoznano funkcji.");
                                }
                            } else if (text2.contains("wynajete") || text2.contains("2")) {
                                System.out.println("...................................................................");
                                System.out.println("| parking > 2-WYNAJETE - wybierz numer funkcji:  "+
                                                 "\n|  1-Wyświetl_zawartość_wybranej_osoby  .  2-zapisz_dane_w_pliku  .  2-powrot  .  3-wyloguj    " +
                                                 "\n|_");
                                System.out.println("| Lista wynajętych pomieszczeń parkingowych: ");
                                ArrayList<String> data = new ArrayList<>();
                                listaOsob.values().stream().filter(f-> !f.listaNajmowanychParkingow.isEmpty()).forEach(b-> data.add(b.getListParkingow("\n  Jest właścicielem parkingów numer : \n","OSOBA: "+ b.toString()+": ",false)));
                                data.forEach(p -> System.out.println(p));

                                System.out.println("...................................................................");
                                String text3 = textScanner2.nextLine();
                                if (text3.contains("Wyświetl_zawartość_wybranej_osoby") || text3.contains("1")) {
                                    System.out.println("| Podaj numer osoby:          >lub jesli chcesz przerwać wpisz 'X'");
                                    String personNumber = textScanner2.nextLine();
                                    if(personNumber.contains("x")||personNumber.contains("X")){
                                        System.out.print("");
                                    }else {
                                        System.out.println("| Podaj pomieszczenia, którego chcesz sprawdzić zawartosć: ");
                                        String parkingNumber = textScanner2.nextLine();
                                        MiejsceParkingowe parking = osiedleScanner.getP(Integer.valueOf(parkingNumber.trim()));
                                        System.out.println("Lista zawartości :");
                                        parking.listaZawartosciPrzedmiotów.entrySet().stream().forEach(z -> System.out.print("  * nr.: " + z.getKey() + " = nazwa: " + z.getValue().nazwaPrzedmiotu + ", zajmowana powierzchnia: " + z.getValue().powierzchniam3 + "\n"));
                                        parking.listaZawartosciPojazdów.entrySet().stream().forEach(z -> System.out.print("  * nr.: " + z.getKey() + " = typ: " + z.getValue().typPojazdu.name() + ", nazwa: " + z.getValue().nazwaPojazdu + ", zajmowana powierzchnia: " + z.getValue().powierzchniam3 + "\n"));
                                        System.out.println("\n| Jeśli zakonczyłeś przeglądać zawartość pomieszczenia wpisz: 'x' \n");
                                        String endOverview = textScanner2.nextLine();
                                    }
                                }
                                else if (text3.contains("zapisz_dane_w_pliku") || text3.contains("2")) {
                                    System.out.println("| Podaj nazwę dla pliku:");
                                    String fileName = textScanner2.nextLine();
                                    createFileAndSave(fileName, data);
                                } else if (text3.contains("powrot") || text3.contains("powrót") || text3.contains("2")) {
                                    break parkingLoop;
                                } else if (text3.contains("3")) {
                                    System.out.println("|................................Zostałeś wylogowany..........................................");
                                    break innerloop;
                                } else {
                                    System.out.println("Nie rozpoznano funkcji.");
                                }
                            } else if(text2.contains("3") || text2.contains("dadaj_parking")) {
                                System.out.println("...................................................................");
                                System.out.println("| parking > 3-DODAJ_NOWY_PARKING - wybierz numer funkcji: "+
                                        "\n|  1-wprowadz_nowy_parking  .   2-powrot  " +
                                        "\n|_" +
                                        " \n| Lista obecnych parkingów znajdujących się w systemie: ");
                                List<String> data = new ArrayList<>();
                                data.add(osiedleScanner.getAllP());
                                data.forEach(p -> System.out.println(p));
                                System.out.println("...................................................................\n");
                                String text3 = textScanner2.nextLine();
                                if (text3.contains("wprowadz_nowy_parking") || text3.contains("1")) {
                                        System.out.println("| Numer parkingu jest nadawany automatycznie. " +
                                                         "\n| Podaj powierzchnie pomieszczenia parkingowego w metrach sześciennych:");
                                        Double powierzchnia = textScanner2.nextDouble();
                                        osiedleScanner.addParking(new MiejsceParkingowe(powierzchnia, osiedleScanner));
                                        System.out.println(" SUCCESS --> Nowy parking został dodany do systemu.");
                                } else if (text3.contains("powrot") || text3.contains("2")) {
                                    break parkingLoop;
                                }else{
                                    System.out.println("Nie rozpoznano funkcji.");
                                }
                            } else if (text2.contains("powrot") || text2.contains("4")) {
                                break parkingLoop;
                            } else if (text2.contains("5")) {
                                System.out.println("|................................Zostałeś wylogowany..........................................");
                                break innerloop;
                            } else if (text2.contains("6")) {
                                exit(threadDateCalender, threadCheckContractRenewal);
                                break outerloop;
                            }
                        }
                    } else if (text1.contains("Moje_dane") || text1.contains("3")) {
                        mojeDaneLoop:
                        while (powrot2) {
                            System.out.println(".....................................................................................................");
                            System.out.println("| 1-MOJE_DANE - wybierz numer funkcji:                                                              | obecna data:"+ threadDateCalender.getDate()+
                                             "\n|  1-MOJE_MIESZKANIA  .  2-MOJE_POMIESZCZENIA_PARKINGOWE  .  3-powrót  .  4-wyloguj  .  5-exite     | " +
                                             "\n|...................................................................................................| ");
                            System.out.println(osobaScanner.getDataAboutMe());
                            String text3 = textScanner2.nextLine();
                            boolean powrot3 = true;
                            ostatnie:
                            while (powrot3) {
                                if (text3.contains("1") || text3.contains("MOJE_MIESZKANIA")) {
                                    System.out.println("...............................................................................................");
                                    System.out.println("| moje_dane -> 1-MOJE_MIESZKANIA - wybierz numer funkcji:                          obecna data:"+ threadDateCalender.getDate()+
                                                     "\n|  1-odnownienie_najmu  .   2-rezygnacja_najmu  .  3-powrót  .  4-wyloguj    " +
                                                     "\n|_");
                                    System.out.println(osobaScanner.getDataAboutMe());
                                    System.out.println(osobaScanner.getListMieszkania(" Jestem właściciele mmieszkania numer : \n", " Jestem najemcą pomieszczeń : \n","| Lista wynajmowanych mieszkań: \n"));
                                    System.out.println("................................................................................................");
                                    String textmojedane = textScanner2.nextLine();
                                    if (textmojedane.contains("1") || textmojedane.contains("odnownienie_najmu")) {
                                        System.out.println("| Podaj numer mieszknaie dla którego przedłużasz najem:          >lub jesli chcesz przerwać wpisz 'X'");
                                        String textmuner = textScanner2.nextLine();
                                        if(textmuner.contains("x")||textmojedane.contains("X")){
                                            System.out.println("Operacja 'odnownienie_najmu' została przerwana.");
                                        } else {
                                            Mieszkanie m = osiedleScanner.getM(Integer.valueOf(textmuner));
                                            System.out.println("| Podaj nową datę zakończenia najmu. (format:yyyy-mm-dd)           obecna data:"+ threadDateCalender.getDate());
                                            String newDateEnd = textScanner2.nextLine();
                                            m.odnowienieNajmu(osobaScanner, java.sql.Date.valueOf(newDateEnd));
                                        }
                                        System.out.println(".__________________________________________________________________________________________________.");
                                    } else if (textmojedane.contains("2") || textmojedane.contains("rezygnacja_najmu")) {
                                        System.out.println("| Podaj numer mieszknaie z którego rezygnujesz:          >lub jesli chcesz przerwać wpisz 'X'");
                                        String textmuner = textScanner2.nextLine();
                                        if(textmuner.contains("x")||textmojedane.contains("X")){
                                            System.out.println("Operacja 'rezygnacja_najmu' została przerwana.");
                                        } else {
                                            Mieszkanie m = osiedleScanner.getM(Integer.valueOf(textmuner));
                                            if (osobaScanner.listaNajmowanychMieszkan.get(m).najemca == osobaScanner) {
                                                m.rezygnacjaNajmu(osiedleScanner, osobaScanner);
                                            } else {
                                                m.wymeldowanie(osobaScanner);
                                                System.out.println("Zamkniecie umowy "+osobaScanner.listaNajmowanychMieszkan.get(m).numerDokumentu+" wynajmu pomieszczenia w mieszkaniu: "+m.numerMieszkania+".");
                                                System.out.println("W mieszkaniu nr "+m.numerMieszkania+" zostało zwolnione "+osobaScanner.listaNajmowanychMieszkan.get(m).liczbaPomieszczeń+" pomieszczenie.");
                                            }
                                        }
                                        System.out.println(".__________________________________________________________________________________________________.");
                                    } else if (textmojedane.contains("powrót") || textmojedane.contains("3")) {
                                        break ostatnie;
                                    } else if (textmojedane.contains("4")) {
                                        System.out.println("|................................Zostałeś wylogowany..........................................");
                                        break innerloop;
                                    } else {
                                        System.out.println("Nie rozpoznano funkcji");
                                    }
                                } else if (text3.contains("2") || text3.contains("MOJE_POMIESZCZENIA_PARKINGOWE")) {
                                    System.out.println(".....................................................................................................................................");
                                    System.out.println("| moje_dane -> 2-MOJE_POMIESZCZENIA_PARKINGOWE - wybierz numer funkcji:                                                  obecna data:"+ threadDateCalender.getDate()+
                                                     "\n|  1-odnownienie_najmu  .   2-rezygnacja_najmu  .  3-Włóż_do_park  .  4-Wyciągnij_z_park  .  5-powrót  .  6-wyloguj    " +
                                                     "\n|_");
                                    System.out.println(osobaScanner.getListParkingow("\n  Jestem właściciele parkingów numer : \n","| Lista wynajmowanych parkingów: \n", true));
                                    System.out.println("................................................................................................");
                                    String textmojedane = textScanner2.nextLine();
                                    if (textmojedane.contains("1") || textmojedane.contains("odnownienie_najmu")) {
                                        System.out.println("| Podaj numer parkingu dla którego przedłużasz najem:           >lub jesli chcesz przerwać wpisz 'X'");
                                        String textnumer = textScanner2.nextLine();
                                        if(textnumer.contains("x")||textnumer.contains("X")){
                                            System.out.println("Operacja 'odnownienie_najmu' została przerwana.");
                                        }else {
                                            MiejsceParkingowe parking = osiedleScanner.getP(Integer.valueOf(textnumer));
                                            System.out.println("| Podaj nową datę zakończenia najmu. (format:yyyy-mm-dd)           obecna data:"+ threadDateCalender.getDate());
                                            String newDateEnd = textScanner2.nextLine();
                                            parking.odnowienieNajmu(osobaScanner, java.sql.Date.valueOf(newDateEnd));
                                            System.out.println("Odnowiono umowę najmu. Nowa data końcowa najmu to: " + newDateEnd);
                                        }
                                        System.out.println(".__________________________________________________________________________________________________.");
                                    } else if (textmojedane.contains("2") || textmojedane.contains("rezygnacja_najmu")) {
                                        System.out.println("| Podaj numer parkingu z którego rezygnujesz:           >lub jesli chcesz przerwać wpisz 'X'");
                                        String textnumer = textScanner2.nextLine();
                                        if(textnumer.contains("x")||textnumer.contains("X")) {
                                            System.out.println("Operacja 'rezygnacja_najmu' została przerwana.");
                                        }else {
                                            MiejsceParkingowe parking = osiedleScanner.getP(Integer.valueOf(textnumer));
                                            parking.rezygnacjaNajmu(osiedleScanner, osobaScanner);
                                        }
                                        System.out.println(".__________________________________________________________________________________________________.");
                                    } else if (textmojedane.contains("Włóż_do_park") || textmojedane.contains("3")) {
                                        System.out.println("| Podaj numer parkingu do którego chcesz włożyć pojazd/przedmiot:           >lub jesli chcesz przerwać wpisz 'X'");
                                        String textnumer = textScanner2.nextLine();
                                        if(textnumer.contains("x")||textnumer.contains("X")){
                                            System.out.println("Operacja 'Włóż_do_park' została przerwana.");
                                        }else {
                                            MiejsceParkingowe parking = osiedleScanner.getP(Integer.valueOf(textnumer));
                                            System.out.println("| Czy to jest pojazd? Y/N           >lub jesli chcesz przerwać wpisz 'X' ");
                                            String question = textScanner2.nextLine();
                                            if (question.contains("Y") || question.contains("y")) {
                                                System.out.print("| Podaj typ pojazdu:       -- lista typow pojazdów: ");
                                                Arrays.stream(TypyPojazdow.values()).forEach(p -> System.out.print(p + ", "));
                                                System.out.println();
                                                String typPojazduS = textScanner2.nextLine();
                                                System.out.println("| Podaj nazwę własną pojazdu: ");
                                                String nazwaPojazdu = textScanner2.nextLine();
                                                System.out.println("| Podaj powierzchnie pojazdu w m3: ");
                                                String powierzchniaP = textScanner2.nextLine();
                                                System.out.print("| Wypisz specyfikę pojazu podając wg wzoru: cecha,specyfikacja,cecha,sprecyfikacja,...   --> lista dostepnych cech: " + CechyChaktarestyczne.getCechy(typPojazduS));
                                                System.out.println();
                                                String cecha = textScanner2.nextLine();
                                                parking.addPojazdToParking(new Pojazdy(nazwaPojazdu, getTyp(typPojazduS), Double.valueOf(powierzchniaP), createListCech(cecha)));
                                            } else if (question.contains("X") || question.contains("x")) {
                                                System.out.println("Operacja 'Włóż_do_park' została przerwana.");
                                            }else {
                                                System.out.println("Podaj nazwę przedmiotu:");
                                                String namePojazd = textScanner2.nextLine();
                                                System.out.println("| Podaj powierzchnie pojazdu w m3: ");
                                                String powierzchniaP = textScanner2.nextLine();
                                                parking.addPrzedmiotToParking(new Przedmiot(namePojazd, Double.valueOf(powierzchniaP)));
                                            }
                                        }
                                        System.out.println(".______________________________________________________________________________________________.");
                                    } else if (textmojedane.contains("Wyciągnij_z_park") || textmojedane.contains("4")) {
                                        System.out.println("| Podaj numer parkingu z którego chcesz wyjąć pojazd/przedmiot:           >lub jesli chcesz przerwać wpisz 'X'");
                                        String textnumer = textScanner2.nextLine();
                                        if(textnumer.contains("x")||textnumer.contains("X")){
                                            System.out.println("Operacja 'Wyciągnij_z_park' została przerwana.");
                                        }
                                        else {
                                            MiejsceParkingowe parking = osiedleScanner.getP(Integer.valueOf(textnumer));
                                            System.out.println("");
                                            try {
                                                System.out.println("Lista zawartości : \n) lista przedmiotow:");
                                                parking.listaZawartosciPrzedmiotów.entrySet().stream().forEach(z -> System.out.print("  * nr.: " + z.getKey() + " = nazwa: " + z.getValue().nazwaPrzedmiotu + ", zajmowana powierzchnia: " + z.getValue().powierzchniam3 + "\n"));
                                                System.out.println(") lista pojazdów:");
                                                parking.listaZawartosciPojazdów.entrySet().stream().forEach(z -> System.out.print("  * nr.: " + z.getKey() + " = typ: " + z.getValue().typPojazdu.name() + ", nazwa: " + z.getValue().nazwaPojazdu + ", zajmowana powierzchnia: " + z.getValue().powierzchniam3 + "\n"));
                                                System.out.println("\n| Chcesz wyjąć pojazd?  Y/N          >lub jesli chcesz przerwać wpisz 'X'");
                                                String wybor = textScanner2.nextLine();
                                                if(wybor.contains("x")||wybor.contains("X")){
                                                    System.out.println("Operacja 'Wyciągnij_z_park' została przerwana.");
                                                } else if (wybor.contains("y") || wybor.contains("Y")) {
                                                    System.out.println("| Podaj numer pojazdy który chcesz wyciągnąć: ");
                                                    String wybor1 = textScanner2.nextLine();
                                                    parking.getPojazdFromParking(Integer.valueOf(wybor1.trim()));
                                                } else if (wybor.contains("n") || wybor.contains("N")) {
                                                    System.out.println("| Podaj numer przedmiotu który chcesz wyciągnąć: ");
                                                    String wybor1 = textScanner2.nextLine();
                                                    parking.getPrzedmiotFromParking(Integer.valueOf(wybor1.trim()));
                                                }
                                            } catch (NullPointerException e) {
                                                System.out.println("Brak przedmiotów w wybranym pomieszczeniu parkingowym.");
                                            }
                                        }
                                        System.out.println(".______________________________________________________________________________________________.");
                                    } else if (textmojedane.contains("powrót") || textmojedane.contains("5")) {
                                        break ostatnie;
                                    } else if (textmojedane.contains("6")) {
                                        System.out.println("|................................Zostałeś wylogowany..........................................");
                                        break innerloop;
                                    } else {
                                        System.out.println("Nie rozpoznano funkcji.");
                                    }
                                } else if (text3.contains("powrót") || text3.contains("3")) {
                                    break mojeDaneLoop;
                                } else if (text3.contains("4")) {
                                    System.out.println("|................................Zostałeś wylogowany..........................................");
                                    break innerloop;
                                } else if (text3.contains("5")) {
                                    exit(threadDateCalender, threadCheckContractRenewal);
                                    break outerloop;

                                }
                            }
                        }
                    } else if (text1.contains("Zapisz_stan") || text1.contains("4")) {
                        System.out.println("| Podaj nazwę dla pliku:");
                        String fileName = textScanner2.nextLine();
                        List<String> data = osiedleScanner.getCurrentStatus();
                        data.add(" obecna data:"+ format1.format(calendar.getTime())+"\n__________________________________________________________________________________________________________________________________________");
                        List<String> finalData = data;
                        listaOsob.values().stream().filter(f-> !f.listaNajmowanychParkingow.isEmpty()).forEach(b-> finalData.add(b.getListParkingow("\n  Jest właścicielem parkingów numer : \n","OSOBA: "+ b.toString()+": ",false)));
                        finalData.add("__________________________________________________________________________________________________________________________________________");
                        listaOsob.values().stream().filter(f-> !f.listaNajmowanychMieszkan.isEmpty()).forEach(b-> finalData.add(b.getListMieszkania("\n Jest właścicielem mmieszkania numer : \n", " Jest najemcą pomieszczeń : \n","OSOBA: "+ b.toString()+": ")));
                        createFileAndSave(fileName, finalData);
                    }
                }
            }

        }


    }




    private static Osoba createNewPerson(HashMap<Integer, Osoba> listaOsob) {
        Scanner textScanner1 = new Scanner(System.in);
        System.out.println("...............................................................");
        System.out.println("| DODAWNIE NOWEJ OSOBY: \n| Wybierz numer uprawnienia nowego użytkownika -->  1-zwykły_user  lub  2-deweloper :");
        String user = textScanner1.nextLine();
        System.out.println("| Podaj pelne imię i nazwisko nowej osoby:");
        String fulName = textScanner1.nextLine();
        System.out.println("| Podaj numer pesel nowej osoby:");
        String pesel = textScanner1.nextLine();
        if (pesel.length() == 11 ) {
            System.out.println("| Błąd: pesel powinien zawierać 11 liczb! \n| Podaj jeszcze raz:");
            pesel = textScanner1.nextLine();
        }
        System.out.println("| Podaj adres nowej osoby:");
        String adres = textScanner1.nextLine();
        System.out.println("| Podaj datę urodzenia nowej osoby:  (format:yyyy-mm-dd)");
        String dateBorn = textScanner1.nextLine();
        Integer id_personDev = counterDev++;
        Integer id_personUser = counterUser++;
        if(user.contains("1")){
            listaOsob.put(id_personUser, new Osoba(fulName, pesel, adres, java.sql.Date.valueOf(dateBorn),false, id_personUser));

            return listaOsob.get(id_personUser);
        }
        else {
            listaOsob.put(id_personDev, new Developer(fulName, pesel, adres, java.sql.Date.valueOf(dateBorn),id_personDev));
            return listaOsob.get(id_personDev);
        }

    }

    private static void createFileAndSave(String nameFile, List<String> data) {
        String pathEnd = "" + PATH_FILE + nameFile+".txt";
        java.io.File file = new File(pathEnd);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Writer writer;
        try {
            writer = new FileWriter(pathEnd, true);
            for (String a : data) writer.write(a + "\n");
            writer.close();
            System.out.println("| Dane zapisane do pliku: " + pathEnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static HashMap<CechyChaktarestyczne, String> createListCech(String string) {
        HashMap<CechyChaktarestyczne, String> mapaCech = new HashMap<>();
        String[] splitString = string.split(",");
        for (int i = 0; i < splitString.length; i+=2) {
            CechyChaktarestyczne cechyChaktarestyczne = null;
            String opis = null;
            if (i % 2 == 0) {
                cechyChaktarestyczne = CechyChaktarestyczne.valueOf(splitString[i]);
                opis = splitString[i+1];
                mapaCech.put(cechyChaktarestyczne, opis);
            }
        }
        return mapaCech;
    }

    private static Osiedle createNewOsiedle(HashMap<Integer, Osiedle> listaOsiedli, HashMap<Integer, Osoba> listaOsob) {
        Scanner textScanner2 = new Scanner(System.in);
        System.out.println("...............................................................");
        System.out.println("| DODAWANIE NOWEGO OSIEDLA");
        System.out.println("| Podaj nazwę osiedla: ");
        String nazwaOsiedlaNew = textScanner2.nextLine();
        System.out.println("| Podaj numer osoby, która zostaje deweloperem nowego osiedla: ");
        listaOsob.values().stream().filter(p->p.ranga==true).forEach(o -> System.out.print(o.toString() + " . "));
        System.out.println("");
        String developerOsiedla = textScanner2.nextLine();
        Developer dew = getPerson(Integer.valueOf(developerOsiedla), listaOsob);
        Integer id_osiedla = counterO++;
        listaOsiedli.put(id_osiedla, new Osiedle(nazwaOsiedlaNew,id_osiedla));
        dew.addOsiedle(listaOsiedli.get(id_osiedla), id_osiedla);
        return listaOsiedli.get(id_osiedla);

    }

    private static Developer getPerson(Integer numberO, HashMap<Integer, Osoba> listaOsob) {
        final Developer[] person = {null};
        for (Map.Entry<Integer, Osoba> personlist : listaOsob.entrySet()) {
            if (personlist.getValue().numerOsoby == numberO)
                person[0] = (Developer) personlist.getValue();
        }
        return person[0];
    }

    private static Osiedle getO(Integer numberO, HashMap<Integer, Osiedle> listaOsiedli) {
        final Osiedle[] o = {null};
        for (Map.Entry<Integer, Osiedle> olist : listaOsiedli.entrySet()) {
            if (olist.getValue().numerOsiedla == numberO)
                o[0] = olist.getValue();
        }
        return o[0];
    }

    private static TypyPojazdow getTyp(String s){
        if(s.contains("SAMOCHÓD_MIEJSKI")||s.contains("samochod miejski"))
            return TypyPojazdow.SAMOCHÓD_MIEJSKI;
        else if(s.contains("SAMOCHÓD_TERENOWY")||s.contains("samochod terenowy"))
            return TypyPojazdow.SAMOCHÓD_TERENOWY;
        else if(s.contains("ŁÓDŹ")||s.contains("łódź"))
            return TypyPojazdow.ŁÓDŹ;
        else if(s.contains("AMFIBIA")||s.contains("amfabia"))
            return TypyPojazdow.AMFIBIA;
        else if(s.contains("MOTOCYKL")||s.contains("motocykl"))
            return TypyPojazdow.MOTOCYKL;
        else
            return null;
    }

    private static void exit(DateCalender threadDateCalender, ContractRenewal threadCheckContractRenewal){
        threadDateCalender.stopDateCalender();
        threadCheckContractRenewal.stopContractRenewal();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<Trwa zamykanie systemu.>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}