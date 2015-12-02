package GUI;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Music.MusicFile;

public class GUI_MusicList implements ListSelectionListener {
    private JPanel musicListPanel = new JPanel(new GridLayout());
    private JList<MusicFile> musicList;
    private DefaultListModel<MusicFile> listModel = new DefaultListModel<MusicFile>();


    public GUI_MusicList() {
        musicList = new JList<MusicFile>(listModel);
        musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        musicList.addListSelectionListener(this);

        musicList.setVisible(true);
    }

    public JPanel createListPanel() {
       
        musicListPanel.add(musicList);
        musicListPanel.setVisible(true);
        return musicListPanel;
    }


    public void valueChanged(ListSelectionEvent e) {

        if (!e.getValueIsAdjusting()) {
            JList<MusicFile> list = (JList<MusicFile>) e.getSource();
            System.out.println(list.getSelectedValue().getName());
        }
    }

    public void arrayListToListModel(ArrayList<MusicFile> list) {
        for (MusicFile iter : list) {
            listModel.addElement(iter);
        }
        musicList.setModel(listModel);
        musicList.setSelectedIndex(0);

    }

    public DefaultListModel<MusicFile> getListModel() {
        return this.listModel;
    }
}


