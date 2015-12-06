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
	private Image pauseButtonImage;
	private Image playModeButtonImage;
	private Image seekNextButtonImage;
	private Image seekPreviousButtonImage;
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
        try{
            playButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_play_arrow_black_18dp.png"));
            pauseButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_pause_black_18dp.png"));
            playButtonImage = playButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            pauseButtonImage = pauseButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		
		}catch(IOException e){e.printStackTrace();}
        
        playButton.setIcon(new ImageIcon(playButtonImage));
        playButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            if (currentMusic.isPlayable()) {
                CurrentMusic.getInstance().play();
                playButton.setIcon(new ImageIcon(pauseButtonImage));
                MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
            } else {
                CurrentMusic.getInstance().pause();       
                reset();
            }
        });
        buttonPanel.add(playButton);
        playButton.setIcon(new ImageIcon(playButtonImage));
    }

	private void addSeekNextButton() {
        seekNextButton = new JButton();

        seekNextButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekNext();
        });

        buttonPanel.add(seekNextButton);
        
        try{
            seekNextButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_fast_forward_black_18dp.png"));
            seekNextButtonImage = seekNextButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		seekNextButton.setIcon(new ImageIcon(seekNextButtonImage));
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
            seekPreviousButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_fast_rewind_black_18dp.png"));
            seekPreviousButtonImage = seekPreviousButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		seekPreviousButton.setIcon(new ImageIcon(seekPreviousButtonImage));
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
    				+ "/Desktop/" + "ic_stop_black_18dp.png"));
            stopButtonImage = stopButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		stopButton.setIcon(new ImageIcon(stopButtonImage));
		}catch(IOException e){e.printStackTrace();}
    }

	private void addPlayModeButton() {
        playModeButton = new JButton();

        playModeButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            //TODO
        });
        buttonPanel.add(playModeButton);
        
        try{
            playModeButtonImage = ImageIO.read(new File(System.getProperty("user.home")
    				+ "/Desktop/" + "ic_loop_black_18dp.png"));
            playModeButtonImage = playModeButtonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            playModeButton.setIcon(new ImageIcon(playModeButtonImage));
		}catch(IOException e){e.printStackTrace();}
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
        playButton.setIcon(new ImageIcon(playButtonImage));
    }
}