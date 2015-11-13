import java.util.ArrayList;

public class Music {
	private String author;
	private String singer;
	private String name;
	private int play_time;
	private int play_count;
	private String file_address;
	private String file_information_address;
	private Lyric lyrics;
	private ArrayList<Integer> recent_play;
	private Category category;
	
	private static int music_file_num = 0;
	
	Music(){
		music_file_num++;
	}
	Music(String name, String file_address){
		//파일 읽기
		this.name = name;
		this.file_address = file_address;
		music_file_num++;
	}
	Music(String author, String singer, String name,int play_time,
			String file_address, String nation, String[] genre){
		this.author = author;
		this.singer = singer;
		this.name = name;
		this.play_time = play_time;
		this.file_address = file_address;
		//category에 nation, genre 넣기
		
		music_file_num++;
	}
	
	private void getMusicFile(){
		// 주소를 통해 파일 가져오기
	}
	private void getMusicFileInformation(){
		// 정보가 저장된 곳에 가서 정보를 가져옴
	}
	private void reNameMusicFile(){
		// 음악파일 이름 바꾸기
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
	public int getPlay_time() {
		return play_time;
	}
	public void setPlay_time(int play_time) {
		this.play_time = play_time;
	}
	public int getPlay_count() {
		return play_count;
	}
	public void setPlay_count(int play_count) {
		this.play_count = play_count;
	}
	public String getFile_address() {
		return file_address;
	}
	public void setFile_address(String file_address) {
		this.file_address = file_address;
	}
	public Lyric getLyrics() {
		return lyrics;
	}
	public void setLyrics(Lyric lyrics) {
		this.lyrics = lyrics;
	}
	public ArrayList<Integer> getRecent_play() {
		return recent_play;
	}
	public void setRecent_play(ArrayList<Integer> recent_play) {
		this.recent_play = recent_play;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
