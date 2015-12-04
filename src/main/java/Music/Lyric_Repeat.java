package Music;

public class Lyric_Repeat extends Thread {
    public void run() {
    	int min=0;
		int sec=0;
		int msec=0;
		
    	while(true){
    		Lyric_Parser b= new Lyric_Parser();
    		Lyric c = new Lyric(b.getTime(),b.getLrc());
    		
    		msec = timetest.getTG()%100;	//0.01초단위
    		sec = ((timetest.getTG()-msec)/100)%60;	//1초단위
    		min = (timetest.getTG()-sec*100-msec)/60/100; //1분단위.
    		//시간을 00:00:00 형식으로 잘라냄
    		try {
				this.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		c.lyricShow(min, sec, msec);//시간 받아와서 사용함. 시간부분 완성하면 고친다~
    	}
    }

    public static void main(String[] args) {
    	timetest test = new timetest();
    	Lyric_Repeat LR = new Lyric_Repeat();
        LR.start();
        test.start();
    }
}

