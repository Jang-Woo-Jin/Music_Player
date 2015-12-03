package GUI;

import Music.CurrentMusic;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;

class GUI_PlayerTab extends JPanel {

    private final JFXPanel fxPanel = new JFXPanel();
    private JButton playButton;

    public GUI_PlayerTab() {
        this.initialize();
    }

    private void initialize() {
        this.add(fxPanel);

        this.setPreferredSize(new Dimension(400, 300));
        this.setBackground(Color.BLACK);

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
}