//�˶���� - �޴��� �˶�ó�� ����/���� �ð� �� �Է� ����
//�� �ð��� ��ƾƲ��ϰ� �˶� ���� .

import java.util.*;
import java.text.*;

public class AlarmSystem extends Thread{
	
	public boolean letAlarm(String ampm, String hour, String min){
		
		int setHour = Integer.parseInt(hour);
		int setMin = Integer.parseInt(min);
		
		Calendar currentTime = Calendar.getInstance();
		Calendar alarmTime = Calendar.getInstance();
		Calendar laterTime = Calendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = currentTime.getTime();
		String Today = new SimpleDateFormat("yyyyMMdd").format(date);
		try {
		    currentTime.setTime(formatter.parse(Today));
		} 
		catch (ParseException e1) {
		    e1.printStackTrace();
		} 
		
		alarmTime.set(currentTime.SECOND, + 0);
		alarmTime.set(currentTime.MINUTE, + setMin);
		
		if(ampm.equals("����")){
			if(setHour != 12) 
				alarmTime.set(currentTime.HOUR, + setHour);
			else 
				alarmTime.set(currentTime.HOUR, + 0);
		}
		else{
			if(setHour != 12) 
				alarmTime.set(currentTime.HOUR, + setHour + 12);
			else 
				alarmTime.set(currentTime.HOUR, + 12);
		}
				
		try {
			if(alarmTime.getTimeInMillis() - laterTime.getTimeInMillis() < 0)
				throw new InterruptedException();
			else {
				Thread.sleep(alarmTime.getTimeInMillis() - laterTime.getTimeInMillis());
			}
		} 
		catch (InterruptedException e) {
			return false;
		}

		return true;
	}
}

