package GUI;

import Music.CurrentMusic;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class GUI_PlayerTab extends JPanel {

    private final JFXPanel fxPanel = new JFXPanel();
    private JButton playButton;
    private JButton seekNextButton;
    private JButton seekPreviousButton;

    public GUI_PlayerTab() {
        this.add(fxPanel);

        this.setPreferredSize(new Dimension(400, 300));
        this.setBackground(Color.BLACK);

        addSeekPreviousButton();
        addPlayButton();
        addSeekNextButton();

        Platform.runLater(() -> initFX(fxPanel));

        this.setVisible(true);
    }

    private void initFX(JFXPanel fxPanel) {
        Scene scene = initScene();
        fxPanel.setScene(scene);
    }

    private Scene initScene() {
        Group root = new Group();
        Scene scene = new Scene(root, javafx.scene.paint.Color.GREENYELLOW);

        return (scene);
    }

    private void addPlayButton() {
        playButton = new JButton("▶");
        /* buttons setting */
        playButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            if (currentMusic.isPlayable()) {
                CurrentMusic.getInstance().play();
                playButton.setText("||");
            } else {
                CurrentMusic.getInstance().pause();
                playButton.setText("▶");
            }
        });
        this.add(playButton);
    }

    private void addSeekNextButton() {
        seekNextButton = new JButton(">>");

        seekNextButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekNext();
            currentMusic.getCurrentTime().ifPresent(System.out::println);
        });

        this.add(seekNextButton);
    }

    private void addSeekPreviousButton() {
        seekPreviousButton = new JButton("<<");

        seekPreviousButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekNext();

        });

        this.add(seekPreviousButton);
    }
}