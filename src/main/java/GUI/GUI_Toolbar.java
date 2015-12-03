package GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GUI_Toolbar extends JMenuBar {

    JMenu fileMenu = new JMenu("File Path");

    JMenuItem setMenuItem = new JMenuItem("Set");

    MenuItemListener menuItemListener = new MenuItemListener();

    public GUI_Toolbar() {
        onCreate();
    }

    private void onCreate() {

        //create menu items

        setMenuItem.setMnemonic(KeyEvent.VK_N);
        setMenuItem.setActionCommand("Set");

        setMenuItem.addActionListener(menuItemListener);


        //add menu items to menus
        fileMenu.add(setMenuItem);

        //add menu to this
        this.add(fileMenu);

        //add this to the frame
        this.setVisible(true);
    }


}
