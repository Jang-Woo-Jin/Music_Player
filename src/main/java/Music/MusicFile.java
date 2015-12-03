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

	private String fileName, fileAddress;

	private Lyric lyrics;
	private String lyricsFileAddress, lyricsFileName;

	private final String FILEINFOADDRESS = System.getProperty("user.home") + "/Document/";
	private final String FILEINFONAME = "MusicInfoFile";

	private String singer, composer, name, album;
	private byte[] imagedata;

	private ID3v1 id3v1Tag;
	private ID3v2 id3v2Tag;
	private boolean isV1Tag = false, isV2Tag = false;

	public MusicFile(String musicFileName, String musicFileAddress, String[] infoInfo) throws UnsupportedTagException, InvalidDataException, IOException {
		super(musicFileAddress+"//"+musicFileName+".mp3");
		if(infoInfo[0] == null) infoInfo[0] = "0";
		this.playCount = Integer.parseInt(infoInfo[0]);
		this.fileName = infoInfo[1];
		this.fileAddress = infoInfo[2];
		this.lyricsFileName = infoInfo[3];
		this.lyricsFileAddress = infoInfo[4];

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
	public int getPlayCount() {
		return this.playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
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
	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}

}