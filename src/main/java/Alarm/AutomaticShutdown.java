package Alarm;

import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFrame;

public class AutomaticShutdown extends Thread {


    private long ringring;
    
    @Override
    public void run() {
        try {
            Thread.sleep(ringring);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setShutdown(String time) {

        int setTime = Integer.parseInt(time);
//        long setMilliTime = setTime * 1000 * 60;
          ringring = setTime * 1000* 60;
//        Calendar currentTime = Calendar.getInstance();
//        Calendar shutTime = Calendar.getInstance();
//
//        shutTime.setTimeInMillis(currentTime.getTimeInMillis() + setMilliTime);
//
//        ringring = shutTime.getTimeInMillis() - currentTime.getTimeInMillis();
    }
    
}