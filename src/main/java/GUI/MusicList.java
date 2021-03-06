package GUI;

import Music.CurrentMusic;
import Music.Lyric_Parser;
import Music.Music;
import Music.MusicListManager;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MusicList {

    private final JPanel musicListPanel = new JPanel(new BorderLayout());           // Panel
    private final JList<Music> musicList;                                           // Store Music List
    private DefaultListModel<Music> listModel = new DefaultListModel<>();
    public static int listNum = 0;                                                  // Static Variable ; that can recognize list that you are playing now

    public MusicList(PlayerTab playerTab) {
    	musicList = new JList<>(listModel);
        musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        musicList.setVisible(true);

        // Action Listeners Below

        musicList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {                                          // Play when double clicked
                if (evt.getClickCount() == 2) {
                    JList<Music> list = (JList<Music>) evt.getSource();
                    playerTab.doStop();
                    CurrentMusic.getInstance().setMedia(list.getSelectedValue().getFilename());
                    MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
                    playerTab.doPlay();
                    listNum = Tab.listNum;
                }
            }
        });

    }                                     // Constructor ; connect panels and actionListeners


    //Functions
    public JPanel getPanel() {
        if (musicListPanel.getComponentCount() == 0) {
            musicListPanel.add(musicList, BorderLayout.NORTH);
            musicListPanel.setVisible(true);
        }
        return musicListPanel;
    }                                                  // can give Music List Panel

    public void arrayListToListModel(ArrayList<Music> list) {
        listModel = new DefaultListModel<>();
        for (Music iter : list) {
            listModel.addElement(iter);
        }
        musicList.setModel(listModel);
        musicList.setSelectedIndex(0);
    }                   // can apply input Array List to List Model

}


