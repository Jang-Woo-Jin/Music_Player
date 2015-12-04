package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUI_PlayerTab extends JPanel {

    private final JFXPanel fxPanel = new JFXPanel();
    File bip;
    Media hit;
    private MediaPlayer mediaPlayer;
    private JButton playButton;

    public GUI_PlayerTab() {
        this.initialize();
    }

    private void initialize() {

        this.add(fxPanel);

        this.setPreferredSize(new Dimension(400, 300));
        this.setBackground(Color.BLACK);

        setMusic();
        setButtons();

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

    private void setButtons() {

        playButton = new JButton("▶");
        /* buttons setting */
        playButton.addActionListener((e) -> {
            Status status = mediaPlayer.getStatus();

            if (status == Status.PAUSED
                    || status == Status.READY
                    || status == Status.STOPPED) {

                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText("▶");
            }
        });

        this.add(playButton);

        // TODO add event playButton with get and set
    }

    private void setMusic() {
//        bip = new File(System.getProperty("user.home") + "/Downloads/[와타아메]+모노크로스로드.mp3");
//        hit = new Media(bip.toURI().toString());
//        mediaPlayer = new MediaPlayer(hit);
    }


}