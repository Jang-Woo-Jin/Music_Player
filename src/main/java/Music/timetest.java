package Music;

import  java.lang.Thread;//실험용입니다***for test
public class timetest extends Thread {
	static int timeget=0;
    public void run() {
        timeget=0;
        
        while(true){//원준이가 아직 시간보내주는걸 덜완성해서 임시로 시간돌아가는거 만들어놈
        	try {
				this.sleep(10);	//0.01초마다 업데이트되는 시계
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	timeget++;
        }
    }

    public static int getTG() {
		return timeget;
	}

	public static void main(String[] args) {
    
    }

}
