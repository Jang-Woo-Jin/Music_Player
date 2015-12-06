package Music;

import java.util.ArrayList;
import java.util.Collections;


public class FavoriteMusicList extends ArrayList<Music> {

    @Override
    public boolean add(Music music) {
        if(!isExist(music))
            return super.add(music);
        else return false;
    }

    public boolean delete(Music music) {
        if(isExist(music)) {
            this.remove(MusicListManager.getInstance().find(music.getFilename()));
            return true;
        }
        else return false;
    }

    public void sort() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (super.get(i).getPlayCount() > super.get(j).getPlayCount())
                    Collections.swap(this, i, j);
            }
        }
    }

    public boolean isExist(Music music) {
        if (MusicListManager.getInstance().findIndex(music.getFilename()) != -1) return true;
        else return false;
    }

}
