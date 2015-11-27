
import java.util.ArrayList;
public class MusicFileManager {
	private ArrayList<MusicFile> musicFileList = new ArrayList<MusicFile>();
	private ArrayList<String> recentPlayList = new ArrayList<String>();
	
	public void addMusicFile(String fileAddress, String fileName){
		
	}
	public void addMusicFileWithMusicInforationFile(String fileAddress){
		
	}
	public void appendMusicFile(MusicFile musicFile){
		musicFileList.add(musicFile);
	}
	public void deleteMusicFile(int index){
		musicFileList.remove(index);
	}
	public void deleteMusicFile(MusicFile musicFile){
		musicFileList.remove(musicFile);
	}
	
	public void addRecentPlayList(MusicFile musicFile, String date, String time){
		String information = date + "/" + time;
		musicFile.addRecentPlay(information);
		information = information + "/" + musicFile.getMusicFileId();
		recentPlayList.add(information);
	}
	public void deleteRecentPlayList(int index){
		if(index > MusicFile.getMusicFileNum()){
			System.out.println("Error, Out of Index");
		}
		else{
			String[] deleteRecentPlayMusicInfo = (recentPlayList.get(index)).split("/");
			MusicFile deleteRecentPlayMusic = null;
			for(int i=0;i<MusicFile.getMusicFileNum();i++){
				if(deleteRecentPlayMusicInfo[2] == (musicFileList.get(i)).getMusicFileId()){
					deleteRecentPlayMusic = musicFileList.get(i);
					break;
				}
			}
			deleteRecentPlayMusic.deleteRecentPlay(deleteRecentPlayMusicInfo[0]+"/"+deleteRecentPlayMusicInfo[1]);
		}
	}
	public void deleteRecentPlayList(String data){
		recentPlayList.remove(data);
		
		String[] deleteRecentPlayMusicInfo = data.split("/");
		MusicFile deleteRecentPlayMusic = null;
		for(int i=0;i<MusicFile.getMusicFileNum();i++){
			if(deleteRecentPlayMusicInfo[2] == (musicFileList.get(i)).getMusicFileId()){
				deleteRecentPlayMusic = musicFileList.get(i);
				break;
			}
		}
		deleteRecentPlayMusic.deleteRecentPlay(deleteRecentPlayMusicInfo[0]+"/"+deleteRecentPlayMusicInfo[1]);
	}
	
	
	public ArrayList<MusicFile> getMusicFileList() {
		return musicFileList;
	}
	public void setMusicFileList(ArrayList<MusicFile> musicFileList) {
		this.musicFileList = musicFileList;
	}
	public ArrayList<String> getRecentPlayList() {
		return recentPlayList;
	}
	public void setRecentPlayList(ArrayList<String> recentPlayList) {
		this.recentPlayList = recentPlayList;
	}


}
