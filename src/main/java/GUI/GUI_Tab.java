package GUI;

import Music.MusicFileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Tab extends JPanel {
    private JButton allMusic = new JButton("전체 음악");
    private JButton favoriteMusic = new JButton("즐겨찾기");
    private JButton recentMusic = new JButton("최근 재생한 곡");
    private JButton chooseMusic = new JButton("가장 많이 재생한 곡");

    private GUI_MusicList musicList;

    public JPanel createTab(GUI_MusicList musicList) {
        setActionListeners();
        this.musicList = musicList;
        this.setLayout(new GridLayout(4, 1, 0, 0));

        this.add(allMusic);
        this.add(favoriteMusic);
        this.add(recentMusic);
        this.add(chooseMusic);

        return this;
    }


    /* Button Actions Here */
    private void setActionListeners() {
        this.allMusic.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                musicList.arrayListToListModel(MusicFileManager.getInstance().getMusicFileList());
                musicList.createListPanel().updateUI();
            }
        });

        this.favoriteMusic.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                musicList.arrayListToListModel(MusicFileManager.getInstance().getFavoriteFileList());
                musicList.createListPanel().updateUI();
            }
        });

        this.recentMusic.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                //musicList.arrayListToListModel(MusicFileManager.getInstance().getRecentPlayList());
                musicList.createListPanel().updateUI();
            }
        });

        this.chooseMusic.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                musicList.arrayListToListModel(MusicFileManager.getInstance().getChoosePlayList());
                musicList.createListPanel().updateUI();
            }
        });
    }


}
