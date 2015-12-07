package GUI;

import Alarm.AlarmSystem;
import FileIO.FileIO;
import Music.CurrentMusic;
import sun.util.resources.cldr.da.CurrencyNames_da;

import javax.swing.*;

import java.awt.*;

public class Main {
    private final JFrame mainFrame = new JFrame();

    public static void main(String[] args) {
        Main main = new Main();
        FileIO.makeDirectory(System.getProperty("user.home") + "/Desktop/" + "music-info");
        main.init();
    }

    private void init() {
        PlayerTab playerPanel = new PlayerTab();

        AlarmSystem gui_alarmSystem = new AlarmSystem();

        MusicList musicList = new MusicList(playerPanel);
        Tab tabPanel = new Tab();

        playerPanel.connectPanels(musicList, tabPanel);
        tabPanel.connectPanels(playerPanel);

        CurrentMusic.getInstance().setPlayerTab(playerPanel);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(800, 450);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(tabPanel.createTab(musicList), BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(musicList.getPanel()), BorderLayout.CENTER);
        this.mainFrame.add(playerPanel, BorderLayout.EAST);

        this.mainFrame.setJMenuBar(new Toolbar(musicList));
        this.mainFrame.getJMenuBar().add(new Toolbar(gui_alarmSystem));
        
        this.mainFrame.setVisible(true);
    }
}
