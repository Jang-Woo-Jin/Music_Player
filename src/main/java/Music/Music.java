package Music;

import FileIO.FilePathParser;

import com.googlecode.mp4parser.util.Path;
import com.mpatric.mp3agic.*;

import java.io.File;
import java.io.IOException;

public class Music extends Mp3File {
    private final String FILE_INFO_ADDRESS = System.getProperty("user.home")
            + File.separatorChar
            + "music-info"
            + File.separatorChar;
    private final String FILE_INFO_NAME = "MusicInfoFile";
    private int playCount;
    private String fileName, fileAddress;
    private Lyric lyrics;
    private String lyricsFileAddress, lyricsFileName;
    private String artist, composer, name, album;
    private byte[] image;

    private ID3v1 id3v1Tag;
    private ID3v2 id3v2Tag;
    private boolean isV1Tag = false, isV2Tag = false;

    public Music(String musicFileName, String musicFileAddress, String[] infoInfo) throws UnsupportedTagException, InvalidDataException, IOException {
        super(musicFileAddress
                + File.separatorChar
                + musicFileName
                + ".mp3");
        if (infoInfo != null) {
            if (infoInfo[0] == null) infoInfo[0] = "0";
            this.playCount = Integer.parseInt(infoInfo[0]);
            this.fileName = infoInfo[1];
            this.fileAddress = infoInfo[2];
            this.lyricsFileName = infoInfo[3];
            this.lyricsFileAddress = infoInfo[4];
        }
        if (this.hasId3v1Tag()) {
            isV1Tag = true;
            id3v1Tag = this.getId3v1Tag();
        }
        if (this.hasId3v2Tag()) {
            isV2Tag = true;
            id3v2Tag = this.getId3v2Tag();
        }
        this.fileAddress = musicFileAddress;
        this.fileName = musicFileName;
        setMusicInformation();

    }

    public Music(File file) throws UnsupportedTagException, InvalidDataException, IOException{
    	super(file.getAbsolutePath());
    	String path = file.getAbsolutePath();
    	this.fileName = FilePathParser.getFileName(path);
    	this.fileAddress = FilePathParser.getPath(path);
    	
    }
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
        for(int i=0;i<image.length;i++)
        	System.out.println(this.image[i]);
    }

    public String getSaveInfo() {
        return Integer.toString(this.playCount) + "/" + this.fileName + "/" +
                this.fileAddress + "/" + this.lyricsFileName + "/" + this.lyricsFileAddress + "\n";

    }

    public String toString() {
        return this.fileName;
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

    public void setPlayCount() {
        this.playCount = 0;
    }
}