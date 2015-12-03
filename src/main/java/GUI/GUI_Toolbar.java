package GUI;
<<<<<<< HEAD
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GUI_Toolbar extends JMenuBar {

<<<<<<< HEAD
    JMenu fileMenu                      = new JMenu("File Path");

    JMenuItem setMenuItem               = new JMenuItem("Set");
=======
    JMenu fileMenu                      = new JMenu("File");

    JMenuItem newMenuItem               = new JMenuItem("New");
    JMenuItem openMenuItem              = new JMenuItem("Open");
    JMenuItem saveMenuItem              = new JMenuItem("Save");
    JMenuItem exitMenuItem              = new JMenuItem("Exit");
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887

    MenuItemListener menuItemListener   = new MenuItemListener();

    public GUI_Toolbar() {
        onCreate();
    }

    private void onCreate() {

        //create menu items

<<<<<<< HEAD
        setMenuItem.setMnemonic(KeyEvent.VK_N);
        setMenuItem.setActionCommand("Set");

        setMenuItem.addActionListener(menuItemListener);


        //add menu items to menus
        fileMenu.add(setMenuItem);
=======
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
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887

        //add menu to this
        this.add(fileMenu);

        //add this to the frame
        this.setVisible(true);
    }

<<<<<<< HEAD
=======
    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //TODO
        }
    }
>>>>>>> c6c7a654b9d29db9793671750a3ada88b50e1887

}
