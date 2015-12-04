package Music;

public class Lyric {
	private int[][] time;
	private String[] lrc;
	public Lyric() {
		
	}
	public Lyric(int[][]time, String[]lrc){
		this.time = time;
		this.lrc = lrc;
		int msec=0, sec=0, min=0;
	}
	int timeCal(int min, int sec, int msec){
		int total=0;
		total = min * 60 * 100 + sec * 100 + msec;	//0.01초단위로 현재시간을 계산(?)
		return total;
	}
	
	
	public void lyricShow(int min, int sec, int msec){
		System.out.println(min+" : "+sec+" : "+msec+" 상태의 가사를 실행합니다");
		int j=1, start=0, finish=0;
		while(lrc[j]!=null){	//텍스트파일 줄 수
			j++;
		}
		//밑부분: 같은 시간에 여러개의 가사가 있을때 처리
		for(int i = 0; i < j; i++){
			if(timeCal(min, sec, msec) >= timeCal(time[i][0], time[i][1], time[i][2])){
				start = i;
			}
		}
		
		for(int k = 0; k < start; k++){
			if(timeCal(time[start][0], time[start][1], time[start][2]) == timeCal(time[k][0], time[k][1], time[k][2]))
				start=k;
				break;
		}
		
		for(int i = 0; i < j; i++){
			if(timeCal(min, sec, msec) < timeCal(time[i][0], time[i][1], time[i][2])){
				finish = i;
				break;
			}
		}
		
		for(int i = start; i < finish; i++){
			System.out.println(lrc[i]);
		}
		if(timeCal(min, sec, msec) > timeCal(time[j-1][0], time[j-1][1], time[j-1][2])){
			System.out.println(lrc[j-1]);
		}
		
	}
}
