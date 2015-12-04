package GUI;

import FileIO.FileIO;

import javax.swing.*;
import java.awt.*;

public class Main {
    private final JFrame mainFrame = new JFrame();

    public static void main(String[] args) {
        Main main = new Main();
        FileIO.makeDirectory(System.getProperty("user.home") + "/Desktop/"+"music-info");
        main.init();
    }

    private void init() {
        MusicList gui_musicList = new MusicList();
        JPanel tabPanel = new Tab().createTab(gui_musicList);
        JPanel playerPanel = new PlayerTab();

        JPanel listPanel = gui_musicList.createListPanel();

        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(800, 450);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(tabPanel, BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(listPanel), BorderLayout.CENTER);
        this.mainFrame.add(playerPanel, BorderLayout.EAST);

        this.mainFrame.setJMenuBar(new Toolbar(gui_musicList));
        this.mainFrame.setVisible(true);
    }
}
