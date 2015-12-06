package GUI;

import Music.CurrentMusic;
import Music.Music;
import Music.MusicListManager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PlayerTab extends JPanel {

	private final JFXPanel fxPanel = new JFXPanel();

	private JPanel musicInfoPanel;
	private JLabel musicName;
	private Image  musicImage;
	private JLabel musicImageLabel;

	private JButton playButton;
	private JButton seekNextButton;
	private JButton seekPreviousButton;
	private JButton stopButton;
	private JButton playModeButton; // this button can change play mode ( all
									// music play, all music play repeatly, one
									// music repeatly )
	private JButton starButton; // for favorite
	private JSlider volumeSlider;
	private JSlider currentTimeSlider;

    private JPanel listPanel;
	private JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 30, 10));

	public PlayerTab() {

        this.setPreferredSize(new Dimension(240, 300));
        this.setBackground(Color.BLACK);

        addImageLabel();

        addCurrentTimeSlider();

        addSeekPreviousButton();
        addPlayButton();
        addSeekNextButton();

        addPlayModeButton();
        addStopButton();
        addStarButton();
        buttonPanel.setBackground(Color.black);
        this.add(buttonPanel);

        Platform.runLater(() -> initFX(fxPanel));
        fxPanel.setSize(0, 0);

        this.add(fxPanel);
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
                if(MusicList.listNum == 1) starButton.setText("☆");
                MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
            } else {
                CurrentMusic.getInstance().pause();
                reset();
            }
        });
        buttonPanel.add(playButton);
    }

	private void addSeekNextButton() {
        seekNextButton = new JButton(">>");

        seekNextButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekNext();
        });

        buttonPanel.add(seekNextButton);
    }

	private void addSeekPreviousButton() {
        seekPreviousButton = new JButton("<<");

        seekPreviousButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekPrevious();
        });

        buttonPanel.add(seekPreviousButton);
    }
//
	private void addStopButton() {
        stopButton = new JButton("■");

        stopButton.addActionListener(e -> {

            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.stop();
            reset();
        });
        buttonPanel.add(stopButton);
    }

	private void addPlayModeButton() {
        playModeButton = new JButton("all");

        playModeButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            //TODO
        });
        buttonPanel.add(playModeButton);
    }

	private void addVolumeSlider() {
		// TODO
		// vertical plz
		volumeSlider = new JSlider();

		this.add(volumeSlider);
	}

	private void addCurrentTimeSlider() {
		// TODO
		// horizontal plz
		currentTimeSlider = new JSlider();
		currentTimeSlider.setEnabled(false);

		this.add(currentTimeSlider);
	}

	private void addStarButton() {
        //TODO
        starButton = new JButton("★");

        starButton.addActionListener(e -> {
            Music temp = CurrentMusic.getInstance().toMusic();
            if(!starButton.getText().equals("☆")) {
               if(MusicListManager.getInstance().addToFavoriteMusicList(temp)) {
                   temp.setFavorite();
                   starButton.setText("☆");
               }
            }
            else {
                if(MusicListManager.getInstance().deleteToFavoriteMusicList(temp))
                    reset();
            }
        });

        buttonPanel.add(starButton);
    }

	private void addImageLabel() {
		try {
			musicImage = ImageIO.read(new File(System.getProperty("user.home")
					+ "/Desktop/" + "defaultImage.jpg"));
			musicImage = musicImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			musicImageLabel = new JLabel(new ImageIcon(musicImage));
			musicInfoPanel = new JPanel();
			musicInfoPanel.setLayout(new BorderLayout());
			musicInfoPanel.add(musicImageLabel,BorderLayout.CENTER);

			musicName = new JLabel();

			musicName.setText("Ready");
			musicName.setBackground(Color.darkGray);
			musicName.setOpaque(true);
			musicInfoPanel.setSize(30, 30);
			musicInfoPanel.add(musicName,BorderLayout.SOUTH);

			this.add(musicInfoPanel);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public void doStop() {
		stopButton.doClick();
	}

	public void doPlay() {
		playButton.doClick();
	}

	public void doStar() {
		starButton.doClick();
	}
    public void reset() {
        if(MusicList.listNum == 1)
            starButton.setText("☆");
        else
            starButton.setText("★");
        playButton.setText("▶");
    }
}