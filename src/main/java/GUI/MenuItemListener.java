package GUI;

import Music.MusicFileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuItemListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        //TODO
        final JFrame newFrame = new JFrame("\t\t\tsettings\t\t\t");
        final JTextField mp3Path = new JTextField();
        final JButton saveButton = new JButton("Save");

        newFrame.setSize(600, 70);
        mp3Path.setSize(500, 70);
        saveButton.setSize(100, 70);

        //PromptSupport.setPrompt("Bunnies", bunnies);


        newFrame.setLayout(new BorderLayout());
        newFrame.add(mp3Path);
        newFrame.add(saveButton, BorderLayout.EAST);

        newFrame.setVisible(true);

        saveButton.addActionListener(e1 -> {
            System.out.println(mp3Path.getText());
            MusicFileManager.getInstance().addMusicFile(mp3Path.getText());
            newFrame.setVisible(false);
        });
    }
}