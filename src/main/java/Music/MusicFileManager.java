package Music;

import java.io.IOException;
import java.util.ArrayList;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import FileIO.FileIO;

public class MusicFileManager {

    // Singleton Pattern
    private static MusicFileManager uniqueInstance;

    private ArrayList<MusicFile> musicFileList = new ArrayList<MusicFile>();
    private RecentPlayList recentPlayList = new RecentPlayList();
    private FavoriteMusicList favoriteMusicList = new FavoriteMusicList();
    private ArrayList<MusicFile> choosePlayList = new ArrayList<MusicFile>();

    private MusicFileManager() {
        // TEST CODE
        musicFileList.add((new MusicFile("a")));
        favoriteMusicList.add((new MusicFile("b")));
        // TEST CODE END
    }

    public static MusicFileManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MusicFileManager();
        }

        return uniqueInstance;
    }
    // Singleton Pattern Applied

    public void addMusicFile(String fileAddress) {
        ArrayList<String> musicFileNameList = FileIO.readAllFileInPath(fileAddress, "mp3");
        for (int i = 0; i < musicFileNameList.size(); i++) {
            String fileName = (String) musicFileNameList.get(i);

            try {
                musicFileList.add(new MusicFile(fileAddress, fileName));
            } catch (UnsupportedTagException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidDataException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        favoriteMusicList.FSort();
        for (int i = 0; i < musicFileList.size(); i++) {
            System.out.println(((MusicFile) musicFileList.get(i)).getName());
        }
    }

    //~~~~~~~~~~~~~~ Getter & Setter
    public ArrayList<MusicFile> getMusicFileList() {
        return musicFileList;
    }

    public void setMusicFileList(ArrayList<MusicFile> musicFileList) {
        this.musicFileList = musicFileList;
    }

    public RecentPlayList getRecentPlayList() {
        return recentPlayList;
    }

    public void setRecentPlayList(RecentPlayList recentPlayList) {
        this.recentPlayList = recentPlayList;
    }

    public FavoriteMusicList getFavoriteFileList() {
        return favoriteMusicList;
    }

    public void setFavoriteFileList(FavoriteMusicList favoriteMusicList) {
        this.favoriteMusicList = favoriteMusicList;
    }

    public ArrayList<MusicFile> getChoosePlayList() {
        return choosePlayList;
    }

    public void setChoosePlayList(ArrayList<MusicFile> choosePlayList) {
        this.choosePlayList = choosePlayList;
    }

    public int getMusicFileNum() {
        return this.musicFileList.size();
    }
}