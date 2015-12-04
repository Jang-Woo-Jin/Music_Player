package GUI;

import Music.MusicFileManager;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    private final JButton allMusic = new JButton("전체 음악");
    private final JButton favoriteMusic = new JButton("즐겨찾기");
    private final JButton recentMusic = new JButton("최근 재생한 곡");
    private final JButton chooseMusic = new JButton("가장 많이 재생한 곡");

    private MusicList musicList;

    public JPanel createTab(MusicList musicList) {
        setActionListeners();
        this.musicList = musicList;
        this.setLayout(new GridLayout(4, 1, 0, 0));

        this.add(allMusic);
        this.add(favoriteMusic);
        this.add(recentMusic);
        this.add(chooseMusic);

        return this;
    }

    private void setActionListeners() {
        this.allMusic.addActionListener(e -> {
            musicList.arrayListToListModel(MusicFileManager.getInstance().getMusicFileList());
            musicList.createListPanel().updateUI();
        });

        this.favoriteMusic.addActionListener(e -> {
            musicList.arrayListToListModel(MusicFileManager.getInstance().getFavoriteFileList());
            musicList.createListPanel().updateUI();
        });

        this.recentMusic.addActionListener(e -> {
            //musicList.arrayListToListModel(MusicFileManager.getInstance().getRecentPlayList());
            musicList.createListPanel().updateUI();
        });

        this.chooseMusic.addActionListener(e -> {
            musicList.arrayListToListModel(MusicFileManager.getInstance().getChoosePlayList());
            musicList.createListPanel().updateUI();
        });
    }
}
