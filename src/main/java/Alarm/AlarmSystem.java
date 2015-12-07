package Alarm;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AlarmSystem extends Thread {

    private long ringring;
    private final JFrame alarmMessageFrame;
    private final JTextArea alarmMessage;
    
    public AlarmSystem(JTextArea alarmMessage){
        alarmMessageFrame = new JFrame("Alarm Message");
        this.alarmMessage = alarmMessage;
        alarmMessageFrameInit();
    }
    
    @Override
    public void run() {

        try {
            if (ringring < 0)
                throw new InterruptedException();
            else{
            	Thread.sleep(ringring);
                alarmMessageFrame.setVisible(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setAlarm(String ampm, String hour, String min) {

        int setHour = Integer.parseInt(hour);
        int setMin = Integer.parseInt(min);

        Calendar today = Calendar.getInstance();
        Calendar alarmTime = Calendar.getInstance();
        Calendar currentTime = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = today.getTime();
        String Today = new SimpleDateFormat("yyyyMMdd").format(date);
        try {
            today.setTime(formatter.parse(Today));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        alarmTime.set(Calendar.SECOND, +0);
        alarmTime.set(Calendar.MINUTE, +setMin);

        if (ampm.equals("오전")) {
            if (setHour != 12)
                alarmTime.set(Calendar.HOUR_OF_DAY, +setHour);
            else
                alarmTime.set(Calendar.HOUR_OF_DAY, +0);
        } else if (ampm.equals("오후")) {
            if (setHour != 12)
                alarmTime.set(Calendar.HOUR_OF_DAY, +setHour + 12);
            else
                alarmTime.set(Calendar.HOUR_OF_DAY, +12);
        }

        ringring = alarmTime.getTimeInMillis() - currentTime.getTimeInMillis();

    }

    private void alarmMessageFrameInit(){
		alarmMessageFrame.add(alarmMessage);
        alarmMessageFrame.setSize(400, 200);
        alarmMessageFrame.setLocationRelativeTo(null);
        alarmMessage.setEditable(false);
        alarmMessage.setBackground(Color.white);
	}
}



