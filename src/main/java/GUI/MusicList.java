package GUI;

import Music.CurrentMusic;
import Music.Music;
import Music.MusicListManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MusicList {

    private final JButton refreshButton = new JButton("refresh list");
    private final JPanel musicListPanel = new JPanel(new BorderLayout());
    private final JList<Music> musicList;
    private DefaultListModel<Music> listModel = new DefaultListModel<>();

    public static int listNum = 0;

    public MusicList(PlayerTab playerTab) {
        musicList = new JList<>(listModel);
        musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        musicList.setVisible(true);


        // Action Listeners Below

        musicList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    JList<Music> list = (JList<Music>) evt.getSource();
                    playerTab.doStop();
                    CurrentMusic.getInstance().setMedia(list.getSelectedValue().getFilename());
                    MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
                    playerTab.doPlay();
                    listNum = Tab.listNum;
                    playerTab.updateUI();
                }
            }
        });

        refreshButton.addActionListener(e -> {
            arrayListToListModel(MusicListManager.getInstance().getMusicList());
            musicListPanel.updateUI();
        });

    }


    //Functions
    public JPanel getPanel() {
        if (musicListPanel.getComponentCount() == 0) {
            musicListPanel.add(musicList, BorderLayout.NORTH);
            musicListPanel.add(refreshButton, BorderLayout.SOUTH);
            musicListPanel.setVisible(true);
        }
        return musicListPanel;
    }

    public void arrayListToListModel(ArrayList<Music> list) {
        listModel = new DefaultListModel<>();
        for (Music iter : list) {
            listModel.addElement(iter);
        }
        musicList.setModel(listModel);
        musicList.setSelectedIndex(0);

    }

}


