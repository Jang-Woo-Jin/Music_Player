package GUI;

import Music.MusicFileManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class GUI_Toolbar extends JMenuBar {

    private final JMenu fileMenu = new JMenu("File Path");

    private final JMenuItem setMenuItem = new JMenuItem("Set");

    public GUI_Toolbar() {
        onCreate();
    }

    private void onCreate() {

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
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this Directory: " +
                            chooser.getSelectedFile().getName());
                    MusicFileManager.getInstance().addMusicFile(chooser.getSelectedFile().getPath());
                }
            }
        });
    }


}
