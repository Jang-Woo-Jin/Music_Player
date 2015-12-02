package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Music.MusicFile;

public class GUI_MusicList implements ListSelectionListener {
    private JPanel musicListPanel = new JPanel(new GridLayout());
    private JList<MusicFile> musicList;
    private DefaultListModel<MusicFile> listModel = new DefaultListModel<MusicFile>();

    public JPanel createListPanel() {
        //TEST
        for (int i = 0; i < 100; i++) {
            listModel.addElement(new MusicFile("A"));
            listModel.get(i).setName(Integer.toString(i));
        }
        //
        musicList = new JList<MusicFile>(listModel);
        musicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        musicList.addListSelectionListener(this);

        musicList.setVisible(true);

        musicListPanel.add(musicList);
        musicListPanel.setVisible(true);
        return musicListPanel;
    }


    public void valueChanged(ListSelectionEvent e) {

        @SuppressWarnings("unchecked")
        JList<MusicFile> list = (JList<MusicFile>) e.getSource();
        System.out.println(list.getSelectedValue().getName());

    }


}


