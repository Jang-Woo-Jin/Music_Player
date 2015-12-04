package GUI;

import Music.CurrentMusic;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class PlayerTab extends JPanel {

    private final JFXPanel fxPanel = new JFXPanel();
    private JButton playButton;
    private JButton seekNextButton;
    private JButton seekPreviousButton;
    private JButton stopButton;
    private JButton playModeButton; // this button can change play mode ( all music play, all music play repeatly, one music repeatly )
    private JSlider volumeSlider;
    private JSlider currentTimeSlider;


    public PlayerTab() {
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

        return (new Scene(root, javafx.scene.paint.Color.GREENYELLOW));
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

    private void addStopButton() {
        stopButton = new JButton("■");

        stopButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            //TODO
        });
        this.add(stopButton);
    }

    private void addPlayModeButton() {
        playModeButton = new JButton("all");

        playModeButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            //TODO
        });
        this.add(playModeButton);
    }

    private void addVolumeSlider() {
        //TODO
        //vertical plz
        volumeSlider = new JSlider();

        this.add(volumeSlider);
    }

    private void addCurrentTimeSlider() {
        //TODO
        //horizontal plz
        currentTimeSlider = new JSlider();

        this.add(currentTimeSlider);
    }
}