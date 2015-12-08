package Music;

import FileIO.FileIO;

import GUI.ErrorDetector;
import GUI.MusicList;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MusicListManager {
    private static MusicListManager uniqueInstance;

    private final String FILE_INFO_ADDRESS = System.getProperty("user.home") + "/Desktop/"+"music-info";
    private final String FILE_INFO_NAME = "abc";//"MusicInfoFile";
    private final ArrayList<Music> musicList = new ArrayList<>();
    private final ArrayList<Music> recentPlayList = new ArrayList<>();
    private final ArrayList<Music> favoriteMusicList = new ArrayList<>();

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
        ArrayList<String> informationString = FileIO.readTextFile(FILE_INFO_ADDRESS, FILE_INFO_NAME, ".txt");
        String[] information = new String[5];

        assert informationString != null;
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
            } catch (Exception e) {
                new ErrorDetector();
            }
        }
        ArrayList<String> infoFileInfo = musicList.stream().map(iter -> iter.getSaveInfo()).collect(Collectors.toCollection(ArrayList::new));

        FileIO.writeTextFile(FILE_INFO_ADDRESS, FILE_INFO_NAME, infoFileInfo);

    }
    
    public Music find(String filePath) {
        try {
            Music temp = nowList().get(findIndex(filePath));
            if (temp != null) return temp;
            else return null;
        } catch (Exception e) {
            new ErrorDetector();
            return null;
        }
    }


    public int findIndex(String filePath){
        for(Music iter : nowList()){
            if(iter.getFilename().replaceAll("[+]", " ").equals(filePath)){
                return nowList().indexOf(iter);
            }
        }
        return -1;
    }

    public Music at(int i) {
       return nowList().get(i);
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void addToRecentPlayList(Music music) {
        int temp = MusicList.listNum;
        MusicList.listNum = 2;
        if (isExist(music)) recentPlayList.remove(find(music.getFilename()));
        MusicList.listNum = temp;
        recentPlayList.add(music);
    }

    public void addToFavoriteMusicList(Music music) {
        int temp = MusicList.listNum;
        MusicList.listNum = 1;
        if(!isExist(music)) {
            MusicList.listNum = temp;
            favoriteMusicList.add(music.clone());
        }
        else {
            MusicList.listNum = temp;
        }
    }

    public boolean deleteToFavoriteMusicList(Music music) {
        if(isExist(music)) {
            for(int i = 0 ; i < nowList().size() ; i++) {
                if(nowList().get(i).getFilename().equals(music.getFilename())) {
                    nowList().remove(i);
                    break;
                }
            }
            return true;
        }
        else return false;

    }

    private boolean isExist(Music music) {
        return MusicListManager.getInstance().findIndex(music.getFilename()) != -1;
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