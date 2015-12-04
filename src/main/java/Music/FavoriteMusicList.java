package Music;

import java.util.ArrayList;
import java.util.Collections;


public class FavoriteMusicList extends ArrayList<Music> {
    FavoriteMusicList() {
        super();
    }

    FavoriteMusicList(ArrayList<Music> list) {
        super(list);
    }

    public void delete(Music music) {//delete favorite PlayList
        music.setPlayCount();
        FSort();
    }

    public void FSort() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.get(i).getPlayCount() > this.get(j).getPlayCount())
                    Collections.swap(this, i, j);
            }
        }
    }

}
