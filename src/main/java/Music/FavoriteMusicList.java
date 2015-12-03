package Music;

import java.util.ArrayList;
import java.util.Collections;


public class FavoriteMusicList extends ArrayList<MusicFile> {
    FavoriteMusicList() {
        super();
    }

    FavoriteMusicList(ArrayList<MusicFile> list) {
        super(list);
    }

    public void delete(MusicFile musicFile) {//delete favorite PlayList
        musicFile.setPlayCount(0);
        FSort();
    }

    public void FSort() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (((MusicFile) this.get(i)).getPlayCount() > ((MusicFile) this.get(j)).getPlayCount())
                    Collections.swap(this, i, j);
            }
        }
    }

}
