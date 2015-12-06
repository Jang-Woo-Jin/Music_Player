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
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MusicListManager {
    private static MusicListManager uniqueInstance;

    private final String FILE_INFO_ADDRESS = System.getProperty("user.home") + "/Desktop/"+"music-info";
    private final String FILE_INFO_NAME = "abc";//"MusicInfoFile";
    private ArrayList<Music> musicList = new ArrayList<Music>();
    private RecentPlayList recentPlayList = new RecentPlayList();
    private FavoriteMusicList favoriteMusicList = new FavoriteMusicList();
    private ArrayList<Music> choosePlayList = new ArrayList<Music>();

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

    public void addMusicFileInDirectory(final String fileAddress) {
        ArrayList<String> musicFileNameList = FileIO.readAllFileInPath(fileAddress);

        for (String iter : musicFileNameList) {
            try {
                musicList.add(new Music(iter, fileAddress, getMusicInfoFile(iter, fileAddress)));
            } catch (UnsupportedTagException | IOException | InvalidDataException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> infoFileInfo = musicList.stream()
                .map(iter -> iter.getSaveInfo())
                .collect(Collectors.toCollection(ArrayList::new));

        FileIO.writeTextFile(FILE_INFO_ADDRESS, FILE_INFO_NAME, infoFileInfo, "");
        favoriteMusicList.sort();
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
    	for(Music iter : musicList){
            if(iter.getFilename().equals(filePath)){
    			return iter;
    		}
    	}
    	return null;
    }

    public int findIndex(String filePath){
        filePath = FilePathParser.parseSeparator(filePath);
        switch(MusicList.listNum) {
            case 0 :
                for(Music iter : musicList){
                    if(iter.getFilename().equals(filePath)){
                        if(musicList.indexOf(iter) == musicList.size() - 1) return -1;
                        return musicList.indexOf(iter);
                    }
                }
                break;
            case 1 :
                for(Music iter : favoriteMusicList){
                    if(iter.getFilename().equals(filePath)){
                        if(musicList.indexOf(iter) == favoriteMusicList.size() - 1) return -1;
                        return musicList.indexOf(iter);
                    }
                }
                break;
            case 2 :
                for(Object iter : recentPlayList){
                    if(((Music)iter).getFilename().equals(filePath)){
                        if(musicList.indexOf(iter) == recentPlayList.size() - 1) return -1;
                        return musicList.indexOf(iter);
                    }
                }
                break;
            case 3:
                for(Music iter : choosePlayList){
                    if(iter.getFilename().equals(filePath)){
                        if(musicList.indexOf(iter) == choosePlayList.size() - 1) return -1;
                        return musicList.indexOf(iter);
                    }
                }
                break;
        }

        return -1;
    }

    public Music at(int i) {
        switch(MusicList.listNum) {
            case -1 :
            case 0 :
                return musicList.get(i);
            case 1 :
                return favoriteMusicList.get(i);
            case 2 :
                return (Music)recentPlayList.get(i);
            case 3:
                return choosePlayList.get(i);
        }
        return null;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public FavoriteMusicList getFavoriteFileList() {
        return favoriteMusicList;
    }

    public ArrayList<Music> getChoosePlayList() {
        return choosePlayList;
    }

    public RecentPlayList<Music> getRecentPlayList() { return recentPlayList; }
}