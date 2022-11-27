package ALAppGUI1.thread;


import ALAppGUI1.Osoba;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContractRenewal extends Thread {

    private boolean exit;
    Calendar calendar;
    HashMap<Integer, Osoba> listaOsob;

    public ContractRenewal(Calendar calendar, HashMap<Integer, Osoba>  map){
        this.exit = false;
        this.calendar = calendar;
        this.listaOsob = map;
    }

    @Override
    public void run() {
        System.out.println("weryfikacja odnawiania umowy:");
        while (!exit){
            checkFile(listaOsob,calendar.getTime());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<PROGRAM ZOSTAŁ ZAKOŃCZONY>~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    public void stopContractRenewal() {
            exit = true;
        }

    private void  checkFile(HashMap<Integer, Osoba> listaOsob, Date dateNow){
        if(!listaOsob.isEmpty()){
            for (Map.Entry<Integer, Osoba> personVerificationList: listaOsob.entrySet()) {
                personVerificationList.getValue().verificationFileM(dateNow);
                personVerificationList.getValue().verificationFileP(dateNow);
            }
        }
    }

    public void setNewOsobaToCheck(Integer i, Osoba osoba) {
        this.listaOsob.put(i,osoba);
    }




}


//źródło pomocy i inspiracji:
//https://www.geeksforgeeks.org/killing-threads-in-java/
//https://www.baeldung.com/java-enum-iteration