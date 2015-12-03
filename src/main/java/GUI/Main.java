package GUI;
import Music.MusicFileManager;

import java.awt.*;

import javax.swing.*;

import FileIO.FileIO;
public class Main {
    private JFrame mainFrame = new JFrame();

    //private JPanel listPanel = new JPanel();

    public void init() {

        GUI_MusicList gui_musicList = new GUI_MusicList();
        JPanel tabPanel             = new GUI_Tab().createTab(gui_musicList);
        JPanel playerPanel          = new GUI_PlayerTab();

        JPanel listPanel            = gui_musicList.createListPanel();

        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(800, 450);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(tabPanel, BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(listPanel), BorderLayout.CENTER);
        this.mainFrame.add(playerPanel, BorderLayout.EAST);

        this.mainFrame.setJMenuBar(new GUI_Toolbar());
        this.mainFrame.setVisible(true);
    }

    public Main() {
        init();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main main= new Main();
    	}
}
