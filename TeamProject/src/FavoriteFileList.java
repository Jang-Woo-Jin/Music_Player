import java.util.ArrayList;
import java.util.Collections;


public class FavoriteFileList extends ArrayList<MusicFile>{
	FavoriteFileList(){
		super();
	}
	
	FavoriteFileList(ArrayList<MusicFile> list){
		super(list);
	}
	public void delete(MusicFile musicFile){//delete favorite PlayList
	      musicFile.setPlayCount(0);//선택한 뮤직파일의 play_coount를 0으로한다 
	      FSort();
	}
	
	public void FSort() {
		for (int i = 0; i < this.size(); i++)// 뮤직 파일의 수 만큼 1차 for구문(.이 안되서 일단
												// 뮤직파일넘 그대로 가져다 씀)
		{
			for (int j = 0; j < this.size(); j++)// 뮤직 파일의 수 만큼 2차 for구문
			{
				if (((MusicFile) this.get(i)).getPlayCount() > ((MusicFile) this.get(j)).getPlayCount())
					Collections.swap(this, i, j);// 크기가 조사하려는 것보다 작을 경우 해당 것과
													// 위치를 변경한다.
			}
		}
	}

}
