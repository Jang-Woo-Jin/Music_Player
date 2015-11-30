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
	      musicFile.setPlayCount(0);//������ ���������� play_coount�� 0�����Ѵ� 
	      FSort();
	}
	
	public void FSort() {
		for (int i = 0; i < this.size(); i++)// ���� ������ �� ��ŭ 1�� for����(.�� �ȵǼ� �ϴ�
												// �������ϳ� �״�� ������ ��)
		{
			for (int j = 0; j < this.size(); j++)// ���� ������ �� ��ŭ 2�� for����
			{
				if (((MusicFile) this.get(i)).getPlayCount() > ((MusicFile) this.get(j)).getPlayCount())
					Collections.swap(this, i, j);// ũ�Ⱑ �����Ϸ��� �ͺ��� ���� ��� �ش� �Ͱ�
													// ��ġ�� �����Ѵ�.
			}
		}
	}

}
