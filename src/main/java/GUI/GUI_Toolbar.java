package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GUI_Toolbar extends JMenuBar {

    JMenu fileMenu                      = new JMenu("File");

    JMenuItem newMenuItem               = new JMenuItem("New");
    JMenuItem openMenuItem              = new JMenuItem("Open");
    JMenuItem saveMenuItem              = new JMenuItem("Save");
    JMenuItem exitMenuItem              = new JMenuItem("Exit");

    MenuItemListener menuItemListener   = new MenuItemListener();

    public GUI_Toolbar() {
        onCreate();
    }

    private void onCreate() {

        //create menu items

        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setActionCommand("New");
        openMenuItem.setActionCommand("Open");
        saveMenuItem.setActionCommand("Save");
        exitMenuItem.setActionCommand("Exit");

        newMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);


        //add menu items to menus
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        //add menu to this
        this.add(fileMenu);

        //add this to the frame
        this.setVisible(true);
    }

    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }

}
