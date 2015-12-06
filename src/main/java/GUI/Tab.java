package GUI;

import Music.MusicListManager;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    private final JButton allMusic = new JButton("전체 음악");
    private final JButton favoriteMusic = new JButton("즐겨찾기");
    private final JButton recentMusic = new JButton("최근 재생한 곡");

    private MusicList musicList;

    public static int listNum = 0;

    public JPanel createTab(MusicList musicList) {
        setActionListeners();
        this.musicList = musicList;
        this.setLayout(new GridLayout(3, 1, 0, 0));

        this.add(allMusic);
        this.add(favoriteMusic);
        this.add(recentMusic);

        return this;
    }

    private void setActionListeners() {
        this.allMusic.addActionListener(e -> {
            musicList.arrayListToListModel(MusicListManager.getInstance().getMusicList());
            musicList.getPanel().updateUI();
            listNum = 0;
        });

        this.favoriteMusic.addActionListener(e -> {
            musicList.arrayListToListModel(MusicListManager.getInstance().getFavoriteFileList());
            musicList.getPanel().updateUI();
            listNum = 1;
        });

        this.recentMusic.addActionListener(e -> {
            musicList.arrayListToListModel(MusicListManager.getInstance().getRecentPlayList());
            musicList.getPanel().updateUI();
            listNum = 2;
        });

    }

}
