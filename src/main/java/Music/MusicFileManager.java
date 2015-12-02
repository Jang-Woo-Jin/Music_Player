package Music;

import java.util.ArrayList;

import FileIO.FileIO;


public class MusicFileManager {

    // Singleton Pattern
    private static MusicFileManager uniqueInstance;

    private MusicFileManager() {

    }

    public static MusicFileManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MusicFileManager();
        }
        return uniqueInstance;
    }
    // Singleton Pattern Applied

    private ArrayList<MusicFile> musicFileList = new ArrayList<MusicFile>();
    private RecentPlayList recentPlayList = new RecentPlayList();
    private ArrayList<Integer> musicFileIDList = new ArrayList<Integer>();

    private FavoriteMusicList favoriteMusicList;
    private ArrayList<MusicFile> choosePlayList = new ArrayList<MusicFile>();

    private final int MAX_MUSIC_FILE_NUM = 100000;

    MusicFileManager(String fileAddress) {
        addMusicFile(fileAddress);
        favoriteMusicList = new FavoriteMusicList(musicFileList);
        favoriteMusicList.FSort();
    }

    public void addMusicFile(String fileAddress) {
        FileIO fileio = new FileIO();
        ArrayList<String> musicFileNameList = fileio.readAllFIleInPath(fileAddress, "mp3");
        for (int i = 0; i < musicFileNameList.size(); i++) {
            int musicFileId = generateMusicID();
            String fileName = (String) musicFileNameList.get(i);
            String[] information = fileio.readMusicFile(fileAddress, fileName);

            musicFileList.add(new MusicFile(Integer.toString(musicFileId),
                    information, fileName, fileAddress));
        }
        for (int i = 0; i < musicFileList.size(); i++) {
            System.out.println(((MusicFile) musicFileList.get(i)).getName());
        }
    }

    private int generateMusicID() {
        int random;
        boolean checkExist = false;
        do {
            random = (int) (Math.random() * MAX_MUSIC_FILE_NUM);
            for (int i = 0; i < musicFileIDList.size(); i++) {
                if (random == musicFileIDList.get(i)) {
                    checkExist = true;
                }
            }

        } while (checkExist);

        return random;
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