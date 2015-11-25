import java.util.ArrayList;
import java.io.*;

public class MusicFile {
	private String author, singer, name, genre, nation, 
						playTime, informationFileName, musicFileId;
	
	private int play_count;
	
	private String fileAddress, fileInformationAddress;
	
	private Lyric lyrics;
	private ArrayList<String> recentPlay;
	
	private static int musicFileNum = 0;
	
	MusicFile(String musicFileId){
		this.musicFileId = musicFileId;
		musicFileNum++;
	}
	MusicFile(String musicFileId, String fileInformationAddress){
		this.fileInformationAddress = fileInformationAddress;
		this.musicFileId = musicFileId;
		
		String[] information = getMusicFileInformation();

		setMusicInformation(information[0],information[1],information[2],
				information[3],information[4],information[5],information[6]);
		
		musicFileNum++;
	}
	MusicFile(String musicFileId, String author, String singer, String name,int playTime,
			String file_address, String nation, String genre){
		this.musicFileId = musicFileId;
		setMusicInformation(author, singer, name,
				playTime, file_address, nation, genre);
	
		musicFileNum++;
	}
	
	public String[] getMusicFileInformation(){
		FileIO fileReader = new FileIO();
		
		String informationFileName = musicFileId;
		String[] tempInformation = fileReader.readTextFile(fileInformationAddress, informationFileName);
		String[] information = tempInformation[0].split("/");
		return information;
	}
	public void setMusicFileInformation(){
		FileIO fileReader = new FileIO();
		String[] writeInformation = {};
		if(this.informationFileName == null){
			this.informationFileName = this.name + "_" + this.singer;
		}
		fileReader.writeTextFile(this.fileInformationAddress, this.informationFileName, writeInformation);
	}

	public void setMusicInformation(Object author, Object singer, Object name,Object playTime,
			Object file_address, Object nation, Object genre){
		this.author = (String)author;
		this.singer = (String)singer;
		this.name = (String)name;
		this.playTime = (String)playTime;
		this.fileAddress = (String)file_address;
		this.nation = (String)nation;
		this.genre = (String)genre;
		
		setMusicFileInformation();
	}
	
	public void addRecentPlay(String information){
		recentPlay.add(information);
	}
	public void deleteRecentPlay(int index){
		recentPlay.remove(index);
	}
	public void deleteRecentPlay(String information){
		recentPlay.remove(information);
	}
	public void deleteAllRecentPlay(){
		recentPlay.clear();
	}
	
	public static int getMusicFileNum(){
		return musicFileNum;
	}
	public String getMusicFileId(){
		return musicFileId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlay_time() {
		return playTime;
	}
	public void setPlay_time(String play_time) {
		this.playTime = play_time;
	}
	public int getPlay_count() {
		return play_count;
	}
	public void setPlay_count(int play_count) {
		this.play_count = play_count;
	}
	public String getFile_address() {
		return fileAddress;
	}
	public void setFile_address(String file_address) {
		this.fileAddress = file_address;
	}
	public Lyric getLyrics() {
		return lyrics;
	}
	public void setLyrics(Lyric lyrics) {
		this.lyrics = lyrics;
	}
	public ArrayList<String> getRecent_play() {
		return recentPlay;
	}
	public void setRecent_play(ArrayList<String> recent_play) {
		this.recentPlay = recent_play;
	}

}
