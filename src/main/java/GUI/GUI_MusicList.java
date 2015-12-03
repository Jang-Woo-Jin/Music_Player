package GUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Music.MusicFile;

public class GUI_MusicList {
    private JPanel musicListPanel = new JPanel(new GridLayout());
    private JList<MusicFile> musicList;
    private DefaultListModel<MusicFile> listModel = new DefaultListModel<MusicFile>();


    public GUI_MusicList() {
        musicList = new JList<MusicFile>(listModel);
        musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        musicList.setVisible(true);

        musicList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<MusicFile> list = (JList<MusicFile>)evt.getSource();
                System.out.println(list.getSelectedValue().getName());
                if (evt.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                } else if (evt.getClickCount() == 3) {

                    // Triple-click detected
                    int index = list.locationToIndex(evt.getPoint());
                }
            }
        });
    }

    public JPanel createListPanel() {
        System.out.println(musicListPanel.getComponentCount());
        if(musicListPanel.getComponentCount() == 0) {
            musicListPanel.add(musicList);
            musicListPanel.setVisible(true);
        }
        return musicListPanel;
    }


    public void arrayListToListModel(ArrayList<MusicFile> list) {
        listModel = new DefaultListModel<MusicFile>();
        for (MusicFile iter : list) {
            listModel.addElement(iter);
        }
        musicList.setModel(listModel);
        musicList.setSelectedIndex(0);

    }

}


