package Music;

import java.util.ArrayList;
import java.io.*;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import FileIO.FileIO;

public class MusicFile extends Mp3File {
	private int playCount;

<<<<<<< HEAD
	private String fileName, fileAddress, fileInformationAddress;
=======
	private String fileName, fileAddress;
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887

	private Lyric lyrics;
	private String lyricsFileAddress, lyricsFileName;

<<<<<<< HEAD
	private ArrayList<String> recentPlay;

	private final String MUSICINFODELIMITER = "/";
	private final String INFOFILEADDRESS = "";

	private String singer, composer, name, album, genre, comment;
	private byte[] imagedata;
	
	
=======
	private final String FILEINFOADDRESS = System.getProperty("user.home") + "/Document/";
    private final String FILEINFONAME = "MusicInfoFile";
    
	private String singer, composer, name, album;
	private byte[] imagedata;
	
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
	private ID3v1 id3v1Tag;
	private ID3v2 id3v2Tag;
	private boolean isV1Tag = false, isV2Tag = false;

<<<<<<< HEAD
	public MusicFile(String musicFileName, String musicFileAddress) throws UnsupportedTagException, InvalidDataException, IOException {
		super(musicFileAddress+"//"+musicFileName+".mp3");
=======
	public MusicFile(String musicFileName, String musicFileAddress, String[] infoInfo) throws UnsupportedTagException, InvalidDataException, IOException {
		super(musicFileAddress+"//"+musicFileName+".mp3");
		if(infoInfo[0] == null) infoInfo[0] = "0";
		this.playCount = Integer.parseInt(infoInfo[0]);
		this.fileName = infoInfo[1];
		this.fileAddress = infoInfo[2];
		this.lyricsFileName = infoInfo[3];
		this.lyricsFileAddress = infoInfo[4];
		
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
		if (this.hasId3v1Tag()){
			isV1Tag = true;
			id3v1Tag = this.getId3v1Tag();
		}
		if (this.hasId3v2Tag()){
			isV2Tag = true;
			id3v2Tag = this.getId3v2Tag();
		}
		this.fileAddress = musicFileAddress;
		this.fileName = musicFileName;
		setMusicInformation();
		
	}
<<<<<<< HEAD
	public MusicFile(String a){
		this.fileName = fileName;
	}
	public String[] getMusicFileInformation() {
		String[] tempInformation = FileIO.readTextFile(fileInformationAddress, getName());
		String[] information = tempInformation[0].split("/");
		return information;
	}

	public void setMusicFileInformation() {
		String[] writeInformation = null;
		FileIO.writeTextFile(this.fileInformationAddress, fileName,
				writeInformation, MUSICINFODELIMITER);
	}

	public void setMusicInformation() {
		if(isV1Tag){
			id3v1Tag.getArtist();
		}
		if(isV2Tag){
			
		}
		setMusicFileInformation();
	}

	// ~~~~~~~~~~~~~~~ relation with recent play time
	public void addRecentPlay(String information) {
		recentPlay.add(information);
	}
	public void deleteRecentPlay(int index) {
		recentPlay.remove(index);
	}
	public void deleteRecentPlay(String information) {
		recentPlay.remove(information);
	}
	public void deleteAllRecentPlay() {
		recentPlay.clear();
	}
	
	// ~~~~~~~~~~~~~~~~ Getter & Setter for private value
=======

	public void setMusicInformation() {
		if(isV1Tag){
			this.singer = id3v1Tag.getArtist();
			this.composer = null;
			this.name = id3v1Tag.getTitle();
			this.album = id3v1Tag.getAlbum();
		}
		if(isV2Tag){
			this.singer = id3v2Tag.getArtist();
			this.composer = id3v2Tag.getComposer();
			this.name = id3v2Tag.getTitle();
			this.album = id3v2Tag.getAlbum();
			this.imagedata = id3v2Tag.getAlbumImage();
		}
	}
	
	public String getSaveInfo(){
		return Integer.toString(this.playCount)+"/"+this.fileName+"/"+
				this.fileAddress+"/"+this.lyricsFileName+"/"+this.lyricsFileAddress+"\n";
		
	}
	// ~~~~~~~~~~~~~~~~ Getter & Setter for private value
	
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
	public Lyric getLyrics() {
		return lyrics;
	}
	public String getLyricsFileAddress() {
		return lyricsFileAddress;
	}
	public String getLyricsFileName() {
		return lyricsFileName;
	}
	public void setLyrics(Lyric lyrics, String lyricsFileName,
			String lyricsFileAddress) {
		this.lyrics = lyrics;
		this.lyricsFileName = lyricsFileName;
		this.lyricsFileAddress = lyricsFileAddress;
	}
<<<<<<< HEAD
	public ArrayList<String> getRecentPlay() {
		return recentPlay;
	}
	public void setRecentPlay(ArrayList<String> recentPlay) {
		this.recentPlay = recentPlay;
	}
=======
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
	public int getPlayCount() {
		return this.playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
<<<<<<< HEAD

	public String getName(){
		return this.fileName;
    }

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getImagedata() {
		return imagedata;
	}

=======
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public byte[] getImagedata() {
		return imagedata;
	}
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}

}
