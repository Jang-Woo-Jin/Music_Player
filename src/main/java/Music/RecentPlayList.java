package Music;

import java.util.ArrayList;

class RecentPlayList<T> extends ArrayList<T> {
    @Override
    public boolean add(T t) {
        if (this.contains(t)) this.remove(t);
        return super.add(t);
    }
}