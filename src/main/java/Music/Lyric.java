package Music;

class Lyric {
    private int[][] time;
    private String[] lrc;

    public Lyric(int[][] time, String[] lrc) {
        this.time = time;
        this.lrc = lrc;
    }

    private int timeCal(int min, int sec, int msec) {
        int total;
        total = min * 60 * 100 + sec * 100 + msec;    //0.01초 단위로 표현?
        return total;
    }


    public String lyricShow(int min, int sec, int msec) {
        int j = 1, start = 0;
        while (lrc[j] != null) {    //텍스트 파일의 총 줄수
            j++;
        }
        //출력 알고리즘
        for (int i = 0; i < j; i++) {
            if (timeCal(min, sec, msec) >= timeCal(time[i][0], time[i][1], time[i][2])) {
                start = i;
            }
        }

        for (int k = 0; k < start; k++) {
            if (timeCal(time[start][0], time[start][1], time[start][2]) == timeCal(time[k][0], time[k][1], time[k][2]))
                start = k;
            break;
        }

        return lrc[start];

    }
}
