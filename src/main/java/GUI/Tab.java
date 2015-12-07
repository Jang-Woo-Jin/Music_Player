package GUI;

import Music.MusicListManager;

import javax.swing.*;
import java.awt.*;

class Tab extends JPanel {
    private final JButton allMusic = new JButton("전체 음악");
    private final JButton favoriteMusic = new JButton("즐겨찾기");
    private final JButton recentMusic = new JButton("최근 재생한 곡");

    private MusicList musicList;
    private PlayerTab playerTab;

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
            listNum = 0;
            MusicList.listNum = 0;
            tabClicked();
        });

        this.favoriteMusic.addActionListener(e -> {
            listNum = 1;
            MusicList.listNum = 1;
            tabClicked();
        });

        this.recentMusic.addActionListener(e -> {
            listNum = 2;
            MusicList.listNum = 2;
            tabClicked();
        });

    }

    public void connectPanels(PlayerTab playerTab) {
        this.playerTab = playerTab;
    }

    private void tabClicked() {
        musicList.arrayListToListModel(MusicListManager.getInstance().nowList());
        musicList.getPanel().updateUI();
        playerTab.reset();
        playerTab.updateUI();
    }

    public JButton getFavoriteButton() {
        return this.favoriteMusic;
    }

}
