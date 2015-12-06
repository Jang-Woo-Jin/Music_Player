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
	private Image musicImage;
	private Image playButtonImage;
	private Image seekNextButtonImage;
	private Image seekPrevioutButtonImage;
	private Image stopButtonImage;
	private Image starButtonImage;
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
        playButton = new JButton();
        /* buttons setting */
        playButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            if (currentMusic.isPlayable()) {
                CurrentMusic.getInstance().play();
                playButton.setText("||");
                MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
            } else {
                CurrentMusic.getInstance().pause();
                reset();
            }
        });
        buttonPanel.add(playButton);
        try{
            playButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_play_arrow_black_18dp.png"));
            playButtonImage = playButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		playButton.setIcon(new ImageIcon(playButtonImage));
		}catch(IOException e){e.printStackTrace();}
    }

	private void addSeekNextButton() {
        seekNextButton = new JButton(">>");

        seekNextButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekNext();
        });

        buttonPanel.add(seekNextButton);
        
        try{
            seekNextButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_fast_forward_black_18dp.png"));
            seekNextButtonImage = seekNextButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		playButton.setIcon(new ImageIcon(seekNextButtonImage));
		}catch(IOException e){e.printStackTrace();}
    }

	private void addSeekPreviousButton() {
        seekPreviousButton = new JButton();

        seekPreviousButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekPrevious();
        });

        buttonPanel.add(seekPreviousButton);
        
        try{
            seekPrevioutButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_fast_rewind_black_18dp.png"));
            seekPrevioutButtonImage = seekPrevioutButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		playButton.setIcon(new ImageIcon(seekPrevioutButtonImage));
		}catch(IOException e){e.printStackTrace();}
    }

	private void addStopButton() {
        stopButton = new JButton();

        stopButton.addActionListener(e -> {

            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.stop();
            reset();
        });
        buttonPanel.add(stopButton);
        
        try{
            stopButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_fast_rewind_black_18dp.png"));
            stopButtonImage = stopButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		playButton.setIcon(new ImageIcon(seekPrevioutButtonImage));
		}catch(IOException e){e.printStackTrace();}
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
            if(!temp.getFavorite()) {
               if(MusicListManager.getInstance().addToFavoriteMusicList(temp))
                    temp.setFavorite();
                    starButton.setText("☆");

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
        starButton.setText("★");
        playButton.setText("▶");
    }
}