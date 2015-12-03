package Music;

import FileIO.FileIO;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.util.ArrayList;

public class MusicFileManager {

    // Singleton Pattern
    private static MusicFileManager uniqueInstance;

    private final String FILEINFOADDRESS = System.getProperty("user.home") + "/Desktop/";
    private final String FILEINFONAME = "abc";//"MusicInfoFile";
    private ArrayList<MusicFile> musicFileList = new ArrayList<MusicFile>();
    private RecentPlayList recentPlayList = new RecentPlayList();
    private FavoriteMusicList favoriteMusicList = new FavoriteMusicList();
    private ArrayList<MusicFile> choosePlayList = new ArrayList<MusicFile>();

    public static MusicFileManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MusicFileManager();
        }

        return uniqueInstance;
    }
    // Singleton Pattern Applied

    public void addMusicFile(final String fileAddress) {
        FileIO.mkdir(FILEINFOADDRESS);
        ArrayList<String> musicFileNameList = FileIO.readAllFileInPath(fileAddress);
        for (String iter : musicFileNameList) {
            String fileName = iter;
            System.out.println(FILEINFOADDRESS);
            System.out.println(fileName);
            try {
                musicFileList.add(new MusicFile(fileName, fileAddress, getMusicInfoFile(fileName, fileAddress)));
            } catch (UnsupportedTagException e) {
                e.printStackTrace();
            } catch (InvalidDataException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> infoFileInfo = new ArrayList<String>();

        for (MusicFile iter : musicFileList) {
            infoFileInfo.add(iter.getSaveInfo());

        }

        FileIO.writeTextFile(FILEINFOADDRESS, FILEINFONAME, infoFileInfo, "");
        favoriteMusicList.FSort();

    }

    /*
        private HashMap<String,Integer> checkTextFileAndMusicFile(final String fileAddress){
            HashMap<String, Integer> information;

            ArrayList<String> mp3files = FileIO.readAllFileInPath(fileAddress, ".mp3");
            ArrayList<String> saveInfo = FileIO.readTextFile(FILEINFOADDRESS,FILEINFONAME);
            ArrayList<String> saveNameInfo = new ArrayList<String>();

            for(String iter1 : mp3files){
                boolean findFlag = false;
                for(String iter2 : saveNameInfo){
                    if(iter1 == iter2) {
                        findFlag = true;

                    }
                }

            }


            return information;
        }
      */
    public String[] getMusicInfoFile(final String fileName, final String fileAddress) {
        ArrayList<String> informationString = FileIO.readTextFile(FILEINFOADDRESS, FILEINFONAME);
        String[] information = new String[5];

        for (String iter : informationString) {
            information = iter.split("/");
            if (information[0].equals(fileName)) {
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

    public void setMusicFileInformation() {
        String[] writeInformation = null;
        //FileIO.writeTextFile(this.fileInformationAddress, fileName,
        //		writeInformation, MUSICINFODELIMITER);
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