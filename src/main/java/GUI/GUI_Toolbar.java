package GUI;

import Music.MusicFileManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI_Toolbar extends JMenuBar {

    JMenu fileMenu = new JMenu("File Path");

    JMenuItem setMenuItem = new JMenuItem("Set");

    GUI_MusicList musicList;

    public GUI_Toolbar(GUI_MusicList musicList) {
        onCreate(musicList);
    }

    private void onCreate(GUI_MusicList musicList) {

        //connect musicList
        this.musicList = musicList;

        //create menu items

        setMenuItem.setMnemonic(KeyEvent.VK_N);
        setMenuItem.setActionCommand("Set");


        //add menu items to menus
        fileMenu.add(setMenuItem);

        //add menu to this
        this.add(fileMenu);

        //add this to the frame
        this.setVisible(true);


        setMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this Directory: " +
                            chooser.getSelectedFile().getName());
                    MusicFileManager.getInstance().addMusicFileInDirectory(chooser.getSelectedFile().getPath());
                    musicList.arrayListToListModel(MusicFileManager.getInstance().getMusicFileList());
                    musicList.createListPanel().updateUI();
                }
            }
        });
    }


}
