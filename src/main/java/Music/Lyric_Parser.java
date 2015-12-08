package Music;

import FileIO.FileIO;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Lyric_Parser {

    private int[][] time = new int[1000][3];// 0:Min  1:Sec   2:0.Sec
    private String[] lrcTime = new String[1000];
    private String[] lrc = new String[1000];
    private String[] index = new String[10];

    public Lyric_Parser() {
        splitLrc();
    }

    public int[][] getTime() {
        return time;
    }

    private void splitLrc() {
    	ArrayList<String> b = FileIO.readTextFile(System.getProperty("user.home") + "/Desktop/"+"music-info",
                CurrentMusic.getInstance().toMusic().getFilename(), ".lrc");
        
        for (int i = 0; i < b.size(); i++) {
        	index = b.get(i).split("]");
            lrcTime[i] = index[0];
            lrcTime[i] = lrcTime[i].replaceAll("\\[", "");
            try {
                lrc[i] = index[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {

            }
            time[i][0] = (lrcTime[i].charAt(0) - 48) * 10
                    + (lrcTime[i].charAt(1) - 48);
            time[i][1] = (lrcTime[i].charAt(3) - 48) * 10
                    + (lrcTime[i].charAt(4) - 48);
            time[i][2] = (lrcTime[i].charAt(6) - 48) * 10
                    + (lrcTime[i].charAt(7) - 48);
        }
    }
    public String[] getLrc() {
        return lrc;
    }
}