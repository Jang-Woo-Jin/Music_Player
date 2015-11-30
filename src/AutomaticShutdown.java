//�������� - �ڵ����� ��ħ����ó��
//15�� 30�� 45�� 60������ ����.

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
