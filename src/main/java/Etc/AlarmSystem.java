package Etc;
import java.util.*;
import java.text.*;

public class AlarmSystem extends Thread{
   
   public boolean letAlarm(String ampm, String hour, String min){
      
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
      } 
      catch (ParseException e1) {
          e1.printStackTrace();
      } 
      
      alarmTime.set(today.SECOND, + 0);
      alarmTime.set(today.MINUTE, + setMin);
      
      if(ampm.equals("오전")){
         if(setHour != 12) 
            alarmTime.set(today.HOUR_OF_DAY, + setHour);
         else 
            alarmTime.set(today.HOUR_OF_DAY, + 0);
      }
      else{
         if(setHour != 12) 
            alarmTime.set(today.HOUR_OF_DAY, + setHour + 12);
         else 
            alarmTime.set(today.HOUR_OF_DAY, + 12);
      }
            
      try {
         if(alarmTime.getTimeInMillis() - currentTime.getTimeInMillis() < 0)
            throw new InterruptedException();
         else 
            Thread.sleep(alarmTime.getTimeInMillis() - currentTime.getTimeInMillis());
      } 
      catch (InterruptedException e) {
         return false;
      }

      return true;
   }
}
