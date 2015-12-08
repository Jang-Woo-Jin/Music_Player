package GUI;

import Music.MusicListManager;
import OS.RecursiveFinder;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;

class Toolbar extends JMenuBar {
    /* These Menus are create into ToolBar */
    private final JMenu fileMenu = new JMenu("File Path");
    private final JMenuItem setMenuItem = new JMenuItem("Set");

    private final JMenu alarmMenu = new JMenu("Alarm");
    private final JMenuItem setAlarmMenuItem = new JMenuItem("Set");
    
    private final JMenu automaticShutdownMenu = new JMenu("Automatic Shutdown");
    private final JMenuItem setAutomaticShutdownMenuItem = new JMenuItem("Set");

    /* Constructor */
    public Toolbar(MusicList musicList) {
        onCreate(musicList);
    }

    /* Constructor */
    public Toolbar(){
    	onAlarmToolBarCreate();
    	onAutomaticShutdownToolBarCreate();
    }

    private void onCreate(MusicList musicList) {

        //connect musicList

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
                            chooser.getSelectedFile().getPath());
                    String[] paths = finder.find()
                            .stream()
                            .map(Path::toAbsolutePath)
                            .map(Path::toString)
                            .toArray(String[]::new);

                    for (String path : paths) {
                        MusicListManager.getInstance().addMusic(path);
                    }
                    musicList.arrayListToListModel(MusicListManager.getInstance().getMusicList());
                    MusicList.listNum = 0;
                    musicList.getPanel().updateUI();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
    /* Alarm Tools */
    private void onAlarmToolBarCreate(){
    	
    	setAlarmMenuItem.setMnemonic(KeyEvent.VK_N);
        setAlarmMenuItem.setActionCommand("Set");
        
        //add menu items to menus
        alarmMenu.add(setAlarmMenuItem);
        
        //add menu to this
        this.add(alarmMenu);
        
        //add this to the frame
        this.setVisible(true);
        
        setAlarmMenuItem.addActionListener(e -> new AlarmFrame());
    }
    /* Alarm Tools */
    private void onAutomaticShutdownToolBarCreate(){
    	
    	setAutomaticShutdownMenuItem.setMnemonic(KeyEvent.VK_N);
        setAutomaticShutdownMenuItem.setActionCommand("Set");
        
        //add menu items to menus
        automaticShutdownMenu.add(setAutomaticShutdownMenuItem);
        
        //add menu to this
        this.add(automaticShutdownMenu);
        
        //add this to the frame
        this.setVisible(true);
        
        setAutomaticShutdownMenuItem.addActionListener(e -> {
            new ShutdownFrame();
        });
    }
}
