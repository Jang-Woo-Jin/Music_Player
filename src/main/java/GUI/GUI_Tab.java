package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI_Tab extends JPanel{
    JButton allMusic        = new JButton("전체 음악");
    JButton favoriteMusic   = new JButton("즐겨찾기");
    JButton recentMusic     = new JButton("최근 재생한 곡");
    JButton chooseMusic     = new JButton("가장 많이 재생한 곡");

    public JPanel createTabList() {

        this.setLayout(new GridLayout(4, 1, 0, 0));

        this.add(allMusic);
        this.add(favoriteMusic);
        this.add(recentMusic);
        this.add(chooseMusic);

        return this;
    }


    /* Button Actions Here */
    private void setActionListeners() {
        this.allMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
