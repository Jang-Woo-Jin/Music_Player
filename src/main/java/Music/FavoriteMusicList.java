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

    public void delete(Music music) {
        music.setPlayCount();
        sort();
    }

    public void sort() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.get(i).getPlayCount() > this.get(j).getPlayCount())
                    Collections.swap(this, i, j);
            }
        }
    }

}
