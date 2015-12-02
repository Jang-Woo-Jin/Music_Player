package Music;
import java.util.ArrayList;
import java.io.*;

//import com.mpatric.mp3agic.*;

import FileIO.FileIO;

public class MusicFile {
    private String composer, singer, name, genre, nation,
            album, informationFileName, musicFileId;
    private String duration;
    private int playCount;

    private String fileName, fileAddress, fileInformationAddress;


    private Lyric lyrics;
    private String lyricsFileAddress, lyricsFileName;
    private ArrayList<String> recentPlay;

    private final String musicInformationDelimiter = "/";
    private final int musicDataNum = 12;
	
    public MusicFile() {
    	
    }

    public MusicFile(String musicFileId) {
        this.musicFileId = musicFileId;
    }

    public MusicFile(String musicFileId, String fileInformationAddress) {
        this.fileInformationAddress = fileInformationAddress;
        this.musicFileId = musicFileId;

        String[] information = getMusicFileInformation();

        setMusicInformation(information);
    }

    public MusicFile(String musicFileId, String[] information, String musicFileName, String musicFileAddress) {
        this.musicFileId = musicFileId;
        this.fileAddress = musicFileAddress;
        this.fileName = musicFileName;
        setMusicInformation(information);
    }


    public String[] getMusicFileInformation() {
        FileIO fileReader = new FileIO();

        String informationFileName = musicFileId;
        String[] tempInformation = fileReader.readTextFile(fileInformationAddress, informationFileName);
        String[] information = tempInformation[0].split("/");
        return information;
    }

    public void setMusicFileInformation() {
        FileIO fileReader = new FileIO();
        String[] writeInformation = {this.composer, this.singer, this.name, this.album, this.fileAddress, this.nation,
                this.duration, this.genre, Integer.toString(this.playCount), this.musicFileId,
                this.lyricsFileName, this.lyricsFileAddress};

        if (this.informationFileName == null) {
            this.informationFileName = this.name + "_" + this.singer;
        }

        fileReader.writeTextFile(this.fileInformationAddress, this.informationFileName, writeInformation, musicInformationDelimiter);
    }


    public void setMusicInformation(String[] information) {

        for (int i = 0; i < musicDataNum; i++) {
            if (information[i] == null) {
                information[i] = "null";
            }
        }
        this.composer = information[0];
        this.singer = information[1];
        this.name = information[2];
        this.album = information[3];
        this.nation = information[5];
        this.duration = information[6];
        this.genre = information[7];
        this.fileAddress = "[" + this.fileAddress + "]";
        try {
            this.playCount = Integer.parseInt(information[8]);
        } catch (Exception e) {
            this.playCount = 0;
            information[8] = "0";
        }
        this.lyricsFileName = information[10];
        this.lyricsFileAddress = information[11];
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
    public String getMusicFileId() {
        return musicFileId;
    }

    public String getcomposer() {
        return composer;
    }

    public void setcomposer(String composer) {
        this.composer = composer;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPlayTime() {
        return album;
    }

    public void setPlayTime(String playTime) {
        this.album = playTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String file_address) {
        this.fileAddress = file_address;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation() {
        return nation;
    }

    public Lyric getLyrics() {
        return lyrics;
    }

    public String getLyricsFileAddress() {
        return lyricsFileAddress;
    }

    public String getLyricsFileName() {
        return lyricsFileName;
    }

    public void setLyrics(Lyric lyrics, String lyricsFileName, String lyricsFileAddress) {
        this.lyrics = lyrics;
        this.lyricsFileName = lyricsFileName;
        this.lyricsFileAddress = lyricsFileAddress;
    }

    public ArrayList<String> getRecentPlay() {
        return recentPlay;
    }

    public void setRecent_play(ArrayList<String> recentPlay) {
        this.recentPlay = recentPlay;
    }

}
