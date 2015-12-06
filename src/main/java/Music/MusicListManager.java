package Music;

import FileIO.FileIO;
import FileIO.FilePathParser;

import GUI.MusicList;
import GUI.Tab;
import com.beust.jcommander.internal.Nullable;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class MusicListManager {
    private static MusicListManager uniqueInstance;

    private final String FILE_INFO_ADDRESS = System.getProperty("user.home") + "/Desktop/"+"music-info";
    private final String FILE_INFO_NAME = "abc";//"MusicInfoFile";
    private ArrayList<Music> musicList = new ArrayList<Music>();
    private ArrayList<Music> recentPlayList = new ArrayList<Music>();
    private ArrayList<Music> favoriteMusicList = new ArrayList<Music>();

    public static MusicListManager getInstance() {
        if (uniqueInstance == null) {
            synchronized (MusicListManager.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new MusicListManager();
                }
            }
        }
        return uniqueInstance;
    }

    private String[] getMusicInfoFile(final String fileName, final String fileAddress) {
        ArrayList<String> informationString = FileIO.readTextFile(FILE_INFO_ADDRESS, FILE_INFO_NAME);
        String[] information = new String[5];

        for (String iter : informationString) {
            information = iter.split("/");
            if (information[1].equals(fileName)) {
                return information;
            }
        }
        information[0] = "0";
        information[1] = fileName;
        information[2] = fileAddress;
        information[3] = "null";
        information[4] = "null";
        return information;
    }

    public void addMusic(String filepath) {
        File file = new File(filepath);

        if (file.isFile()) {
            String fileName = filepath.substring(filepath.lastIndexOf(File.separatorChar) + 1,
                    filepath.lastIndexOf("."));
            String fileAddress = filepath.substring(0, filepath.lastIndexOf(File.separatorChar));
            try {
                musicList.add(new Music(fileName, fileAddress, getMusicInfoFile(fileName, fileAddress)));
            } catch (InvalidDataException | IOException | UnsupportedTagException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> infoFileInfo = new ArrayList<String>();

        for (Music iter : musicList) {
            infoFileInfo.add(iter.getSaveInfo());
        }

        FileIO.writeTextFile(FILE_INFO_ADDRESS, FILE_INFO_NAME, infoFileInfo, "");

    }
    
    public Music find(String filePath){

        Music temp = musicList.get(findIndex(filePath));
        if(temp != null) return temp;
        else return null;
    }

    public int findIndex(String filePath){
        switch(MusicList.listNum) {
            case 0 :
                for(Music iter : musicList){

                    System.out.println(filePath);
                    System.out.println(iter.getFilename());

                    if(iter.getFilename().equals(filePath)){
                        return musicList.indexOf(iter);
                    }
                }
                break;
            case 1 :
                for(Music iter : favoriteMusicList){
                    if(iter.getFilename().equals(filePath)){
                        return musicList.indexOf(iter);
                    }
                }
                break;
            case 2 :
                for(Music iter : recentPlayList){
                    if(iter.getFilename().equals(filePath)){
                        return musicList.indexOf(iter);
                    }
                }
                break;
        }

        return -2;
    }

    public Music at(int i) {
        switch(MusicList.listNum) {
            case -1 :
            case 0 :
                return musicList.get(i);
            case 1 :
                return favoriteMusicList.get(i);
            case 2 :
                return recentPlayList.get(i);
        }
        return null;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public ArrayList<Music> getFavoriteFileList() {
        return favoriteMusicList;
    }

    public ArrayList<Music> getRecentPlayList() { return recentPlayList; }

    public boolean addToRecentPlayList(Music music) {
        if (recentPlayList.contains(music)) recentPlayList.remove(music);
        return recentPlayList.add(music);
    }

    public boolean addToFavoriteMusicList(Music music) {
        if(!isExist(music))
            return favoriteMusicList.add(music);
        else return false;
    }

    public boolean deleteToFavoriteMusicList(Music music) {
        if(isExist(music)) {
            favoriteMusicList.remove(MusicListManager.getInstance().find(music.getFilename()));
            return true;
        }
        else return false;
    }

    public boolean isExist(Music music) {
        int temp = MusicList.listNum;
        MusicList.listNum = 1;
        if (MusicListManager.getInstance().findIndex(music.getFilename()) != -1) {
            MusicList.listNum = temp;
            return true;
        }
        else {
            MusicList.listNum = temp;
            return false;
        }
    }

    public ArrayList<Music> nowList() {
        switch(MusicList.listNum) {
            case 0 :
                return musicList;
            case 1 :
                return favoriteMusicList;
            case 2 :
                return recentPlayList;
        }
        return null;
    }
}