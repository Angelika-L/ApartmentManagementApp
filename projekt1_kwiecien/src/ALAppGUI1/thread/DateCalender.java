package ALAppGUI1.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateCalender extends Thread {

    Calendar calendar;
    boolean exit;
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public DateCalender(Calendar calendar){
        this.calendar = calendar;
        this.exit=false;
    }

    @Override
    public void run() {

        while (!exit){
            this.calendar.add(Calendar.DAY_OF_WEEK,1);
            try {
                Thread.sleep(5000);
             } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void stopDateCalender(){
        exit = true;
    }

    public String getDate(){
        return  formatDate.format(calendar.getTime());
    }

}


//źródło pomocy i inspiracji:
//https://www.geeksforgeeks.org/killing-threads-in-java/
//https://www.baeldung.com/java-enum-iteration