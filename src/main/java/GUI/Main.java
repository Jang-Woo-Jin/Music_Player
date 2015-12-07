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
        MusicList gui_musicList = new MusicList(playerPanel);
        AlarmSystem gui_alarmSystem = new AlarmSystem();
        JPanel tabPanel = new Tab().createTab(gui_musicList);

        JPanel listPanel = gui_musicList.getPanel();

        CurrentMusic.getInstance().setPlayerTab(playerPanel);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(800, 450);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(tabPanel, BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(listPanel), BorderLayout.CENTER);
        this.mainFrame.add(playerPanel, BorderLayout.EAST);

        this.mainFrame.setJMenuBar(new Toolbar(gui_musicList));
        this.mainFrame.getJMenuBar().add(new Toolbar(gui_alarmSystem));
        this.mainFrame.setVisible(true);
    }
}
