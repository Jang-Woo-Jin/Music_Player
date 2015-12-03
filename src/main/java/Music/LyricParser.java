package Music;

import FileIO.FileIO;

import java.util.ArrayList;

public class LyricParser {

    private int[][] time = new int[1000][3];// 0:Min  1:Sec   2:0.Sec
    private String[] lrctime = new String[1000];
    private String[] lrc = new String[1000];
    private String[] index = new String[10];

    public LyricParser() {
        splitLrc();
    }

    public int[][] getTime() {
        return time;
    }

    public void setTime(int[][] time) {
        this.time = time;
    }

    public void splitLrc() {
        FileIO a = new FileIO();
        ArrayList<String> b = a.readTextFile("C:", "kimagurestadamu");
        for (int i = 0; i < b.size(); i++) {
            index = b.get(i).split("]");
            lrctime[i] = index[0];
            lrctime[i] = lrctime[i].replaceAll("\\[", "");

            try {
                lrc[i] = index[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {

            }

            time[i][0] = (lrctime[i].charAt(0) - 48) * 10
                    + (lrctime[i].charAt(1) - 48);
            time[i][1] = (lrctime[i].charAt(3) - 48) * 10
                    + (lrctime[i].charAt(4) - 48);
            time[i][2] = (lrctime[i].charAt(6) - 48) * 10
                    + (lrctime[i].charAt(7) - 48);

        }

    }

    public String[] getLrctime() {
        return lrctime;
    }

    public void setLrctime(String[] lrctime) {
        this.lrctime = lrctime;
    }

    public String[] getLrc() {
        return lrc;
    }

    public void setLrc(String[] lrc) {
        this.lrc = lrc;
    }

    public String[] getIndex() {
        return index;
    }

    public void setIndex(String[] index) {
        this.index = index;
    }
}