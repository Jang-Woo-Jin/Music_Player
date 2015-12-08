package GUI;

import FileIO.FileIO;
import Music.CurrentMusic;

import javax.swing.*;

import java.awt.*;

class Main {
    private final JFrame mainFrame = new JFrame();

    public static void main(String[] args) {
        Main main = new Main();
        FileIO.makeDirectory(System.getProperty("user.home") + "/Desktop/" + "music-info");
        main.init();
    }

    private void init() {
        PlayerTab playerPanel = new PlayerTab();                        // Player Panel
        MusicList musicList = new MusicList(playerPanel);               // Music List Panel
        Tab tabPanel = new Tab();                                       // Button Tab Panel

        playerPanel.connectPanels(tabPanel);                            // Connect Panels
        tabPanel.connectPanels(playerPanel);                            // Connect Panels

        CurrentMusic.getInstance().setPlayerTab(playerPanel);           // SetUp Panels
        this.mainFrame.setLayout(new BorderLayout());                                           //Setting Layout and add Panels start
        this.mainFrame.setSize(800, 500);

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(tabPanel.createTab(musicList), BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(musicList.getPanel()), BorderLayout.CENTER);
        this.mainFrame.add(playerPanel, BorderLayout.EAST);

        this.mainFrame.setJMenuBar(new Toolbar(musicList));
        this.mainFrame.getJMenuBar().add(new Toolbar());
        
        this.mainFrame.setVisible(true);                                                        //Setting Layout and add Panels end
    }
}
