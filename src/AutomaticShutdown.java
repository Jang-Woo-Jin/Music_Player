//예약종료 - 핸드폰의 취침예약처럼
//15분 30분 45분 60분으로 나눔.

import java.util.*;

public class AutomaticShutdown extends Thread {

	public boolean letShutdown(String time){

		int setTime = Integer.parseInt(time);
		long setMilliTime = setTime * 1000 * 60;
		
		Calendar currentTime = Calendar.getInstance();
		Calendar shutTime = Calendar.getInstance();
		
		shutTime.setTimeInMillis(currentTime.getTimeInMillis() + setMilliTime);
	
		try {
			Thread.sleep(shutTime.getTimeInMillis() - currentTime.getTimeInMillis());
		} 
		catch (InterruptedException e) {
			return false;
		}
		
		
		return true;
	}
}
