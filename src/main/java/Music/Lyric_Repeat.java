package Music;

public class Lyric_Repeat extends Thread {
    public void run() {
        int min = 0;
        int sec = 0;
        int msec = 0;

        while (true) {
            Lyric_Parser b = new Lyric_Parser();
            Lyric c = new Lyric(b.getTime(), b.getLrc());

//            msec = timetest.getTG() % 100;    //0.01초
//            sec = ((timetest.getTG() - msec) / 100) % 60;    //1초
//            min = (timetest.getTG() - sec * 100 - msec) / 60 / 100; //1분.
            //�ð��� 00:00:00 �������� �߶�
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            c.lyricShow(min, sec, msec);//�ð� �޾ƿͼ� �����. �ð��κ� �ϼ��ϸ� ��ģ��~
        }
    }
}