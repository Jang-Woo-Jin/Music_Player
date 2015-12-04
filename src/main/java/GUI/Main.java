package GUI;

import javax.swing.*;

import Music.MusicFileManager;

import java.awt.*;

public class Main {
    private final JFrame mainFrame = new JFrame();

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    	
    }

    private void init() {

        GUI_MusicList gui_musicList = new GUI_MusicList();
        JPanel tabPanel = new GUI_Tab().createTab(gui_musicList);
        JPanel playerPanel = new GUI_PlayerTab();

        JPanel listPanel = gui_musicList.createListPanel();

        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(800, 450);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(tabPanel, BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(listPanel), BorderLayout.CENTER);
        this.mainFrame.add(playerPanel, BorderLayout.EAST);

        this.mainFrame.setJMenuBar(new GUI_Toolbar(gui_musicList));
        this.mainFrame.setVisible(true);
    }
}
