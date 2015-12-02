package GUI;
import java.awt.*;

import javax.swing.*;


public class Main {
    private JFrame mainFrame = new JFrame();

    //private JPanel listPanel = new JPanel();

    public void init() {

        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setSize(800, 450);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainFrame.add(new GUI_Tab().createTabList(), BorderLayout.WEST);
        this.mainFrame.add(new JScrollPane(new GUI_MusicList().createListPanel()), BorderLayout.CENTER);
        this.mainFrame.add(new GUI_PlayerTab(), BorderLayout.EAST);

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
