package Music;

import FileIO.FileIO;
import FileIO.FilePathParser;

import com.mpatric.mp3agic.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Music extends Mp3File {	// extends MP3File - it is in Mp3agic library
    private final String FILE_INFO_ADDRESS = System.getProperty("user.home")
            + File.separatorChar
            + "music-info"
            + File.separatorChar;	// music info file's address
    private final String FILE_INFO_NAME = "MusicInfoFile";	// music info file's name
    private int playCount;	// play count - add 1 when it played
    private String fileName, fileAddress;	// save music file's name and address
    private Lyric lyrics;	// lyric object
    private String lyricsFileAddress, lyricsFileName;	// save lyric file's address and name
    private String artist, composer, name, album;	// music info
    private byte[] image; // save music image	
    private boolean favorite;	// set when music selected to favorite
    private ID3v1 id3v1Tag;	// save id3v1tag
    private ID3v2 id3v2Tag;	// save id3v2tag
    private boolean isV1Tag = false, isV2Tag = false;	// check tag is existing

    private String[] musicInfo;	// use saving music information

    //make an object with file's name and address. infoInfo has musicfile's address when it read musicinfofile.txt's information
    public Music(String musicFileName, String musicFileAddress, String[] infoInfo) throws UnsupportedTagException, InvalidDataException, IOException {
        super(musicFileAddress
                + File.separatorChar
                + musicFileName
                + ".mp3");	// make an object
        favorite = false;	// default setting - favorite is false
        if (infoInfo != null) {	// when information exist
            if (infoInfo[0] == null) infoInfo[0] = "0";
            this.playCount = Integer.parseInt(infoInfo[0]);
            this.fileName = infoInfo[1];
            this.fileAddress = infoInfo[2];
            this.lyricsFileName = infoInfo[3];
            this.lyricsFileAddress = infoInfo[4];
        }
        if (this.hasId3v1Tag()) {	// check id3v1tag exist
            isV1Tag = true;
            id3v1Tag = this.getId3v1Tag();
        }
        if (this.hasId3v2Tag()) {	// check id3v1tag exist
            isV2Tag = true;
            id3v2Tag = this.getId3v2Tag();
        }
        this.fileAddress = musicFileAddress;
        this.fileName = musicFileName;
        this.musicInfo = infoInfo;
        setMusicInformation();

    }
    
    //make an object with file
    public Music(File file) throws UnsupportedTagException, InvalidDataException, IOException{
    	super(file.getAbsolutePath());
    	boolean check = true;
    	String path = file.getAbsolutePath();
    	this.fileName = FilePathParser.getFileName(path);
    	this.fileAddress = FilePathParser.getPath(path);
    	ArrayList<String> informationString = FileIO.readTextFile(FILE_INFO_ADDRESS, FILE_INFO_NAME, ".txt");

    	//save information when info exist
        assert informationString != null;
        for (String iter : informationString) {
            musicInfo = iter.split("/");
            if (musicInfo[1].equals(fileName)) {
            	 this.playCount = Integer.parseInt(musicInfo[0]);
                 this.fileName = musicInfo[1];
                 this.fileAddress = musicInfo[2];
                 this.lyricsFileName = musicInfo[3];
                 this.lyricsFileAddress = musicInfo[4];
            	break;
            }
        }
        this.playCount = 0;
        this.lyricsFileName = "null";
        this.lyricsFileAddress = "null";


    }
    
    //set music information - 2 case
    private void setMusicInformation() {
        if (isV1Tag) {
            this.artist = id3v1Tag.getArtist();
            this.composer = null;
            this.name = id3v1Tag.getTitle();
            this.album = id3v1Tag.getAlbum();
        }
        if (isV2Tag) {
            this.artist = id3v2Tag.getArtist();
            this.composer = id3v2Tag.getComposer();
            this.name = id3v2Tag.getTitle();
            this.album = id3v2Tag.getAlbum();
            this.image = id3v2Tag.getAlbumImage();
        }
    }

    //return play count, file name, file address, lyricfile name, lyricfile address to save music information
    public String getSaveInfo() {
        return Integer.toString(this.playCount) + "/" + this.fileName + "/" +
                this.fileAddress + "/" + this.lyricsFileName + "/" + this.lyricsFileAddress + "\n";
    }
    
    public String toString() {     //return file name
        return this.fileName;
    }

    public void setLyrics(Lyric lyrics, String lyricsFileName, String lyricsFileAddress) { //set lyric
        this.lyricsFileName = lyricsFileName;
        this.lyricsFileAddress = lyricsFileAddress;
    }
    
    public byte[] getAlbumArt(){	//return album art
    	return this.image;
    }
    public boolean getFavorite() { return this.favorite; }	// return favorite status

    public void setFavorite() { this.favorite = !this.favorite; }	// toggle favorite status

    public Music clone() {	// make clone object
        try {
            return new Music(fileName, fileAddress, musicInfo);
        } catch (UnsupportedTagException | InvalidDataException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}