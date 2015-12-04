package Music;

import  java.lang.Thread;//for test
public class timetest extends Thread {
	static int timeget=0;
    public void run() {
        timeget=0;
        
        while(true){//테스트용으로 시간 흘러가는절 만들어줌
        	try {
				this.sleep(10);	//0.01초 단위로 흘러감
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
