package Alarm;

import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFrame;

import Music.MusicListManager;

public class AutomaticShutdown extends Thread {

	private static AutomaticShutdown uniqueInstance;
    private long ringring;
    
    public static AutomaticShutdown getInstance() {
        if (uniqueInstance == null) {
            synchronized (MusicListManager.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new AutomaticShutdown();
                }
            }
        }
        return uniqueInstance;
    }
    
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

        ringring = 5 * 10* 60;
    }
    
}