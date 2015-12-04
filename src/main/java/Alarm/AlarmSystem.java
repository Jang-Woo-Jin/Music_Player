package Alarm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmSystem extends Thread {

    long ringring;

    @Override
    public void run() {

        try {
            if (ringring < 0)
                throw new InterruptedException();
            else
                Thread.sleep(ringring);
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

        alarmTime.set(today.SECOND, +0);
        alarmTime.set(today.MINUTE, +setMin);

        if (ampm.equals("오전")) {
            if (setHour != 12)
                alarmTime.set(today.HOUR_OF_DAY, +setHour);
            else
                alarmTime.set(today.HOUR_OF_DAY, +0);
        } else if (ampm.equals("오후")) {
            if (setHour != 12)
                alarmTime.set(today.HOUR_OF_DAY, +setHour + 12);
            else
                alarmTime.set(today.HOUR_OF_DAY, +12);
        }

        ringring = alarmTime.getTimeInMillis() - currentTime.getTimeInMillis();

    }
}



