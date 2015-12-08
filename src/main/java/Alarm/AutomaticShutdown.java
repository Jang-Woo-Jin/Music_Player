package Alarm;

import java.io.IOException;
import java.util.Calendar;

import javax.swing.JFrame;

import Music.MusicListManager;

public class AutomaticShutdown extends Thread {

	private static AutomaticShutdown uniqueInstance;
    private long ringring;
    private boolean flag = true;
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
    	flag = false;
        try {
            Thread.sleep(ringring);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        
    	}
    }

    public void setShutdown(String time) {

        int setTime = Integer.parseInt(time);

        ringring = setTime * 1000 * 60;
    }
    
    public boolean getStatus(){
    	return flag;
    }
    
}