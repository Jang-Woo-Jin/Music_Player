package GUI;

import Music.MusicFile;
import Music.MusicFileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI_MusicList {

    private final JButton refreshButton = new JButton("refresh list");
    private final JPanel musicListPanel = new JPanel(new BorderLayout());
    private final JList<MusicFile> musicList;
    private DefaultListModel<MusicFile> listModel = new DefaultListModel<>();

    public GUI_MusicList() {
        musicList = new JList<>(listModel);
        musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        musicList.setVisible(true);


        // Action Listeners Below

        musicList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<MusicFile> list = (JList<MusicFile>) evt.getSource();
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());

                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });

        refreshButton.addActionListener(e -> {
            arrayListToListModel(MusicFileManager.getInstance().getMusicFileList());
            musicListPanel.updateUI();
        });

    }


    //Functions
    public JPanel createListPanel() {
        if (musicListPanel.getComponentCount() == 0) {
            musicListPanel.add(musicList, BorderLayout.NORTH);
            musicListPanel.add(refreshButton, BorderLayout.SOUTH);
            musicListPanel.setVisible(true);
        }
        return musicListPanel;
    }

    public void arrayListToListModel(ArrayList<MusicFile> list) {
        listModel = new DefaultListModel<>();
        for (MusicFile iter : list) {
            listModel.addElement(iter);
        }
        musicList.setModel(listModel);
        musicList.setSelectedIndex(0);

    }

}


