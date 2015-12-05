package GUI;

import Music.MusicListManager;
import OS.RecursiveFinder;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;

public class Toolbar extends JMenuBar {
    private final JMenu fileMenu = new JMenu("File Path");
    private final JMenuItem setMenuItem = new JMenuItem("Set");
    private MusicList musicList;

    public Toolbar(MusicList musicList) {
        onCreate(musicList);
    }

    private void onCreate(MusicList musicList) {

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

        setMenuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    RecursiveFinder finder = new RecursiveFinder(
                            chooser.getSelectedFile().getPath(), "*.mp3");
                    String[] paths = finder.find()
                            .stream()
                            .map(Path::toAbsolutePath)
                            .map(Path::toString)
                            .toArray(String[]::new);

                    for (String path : paths) {
                        MusicListManager.getInstance().addMusic(path);
                    }
                    musicList.arrayListToListModel(MusicListManager.getInstance().getMusicList());
                    musicList.getPanel().updateUI();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }


}
