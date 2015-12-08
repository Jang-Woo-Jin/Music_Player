package GUI;

import FileIO.FilePathParser;
import Music.CurrentMusic;
import Music.Lyric_Repeat;
import Music.Music;
import Music.MusicListManager;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import org.omg.CORBA.Current;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class PlayerTab extends JPanel {

    private final JFXPanel fxPanel = new JFXPanel();

    private JPanel musicInfoPanel;
    private JLabel musicName;
    private Image musicImage;
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

    public static JLabel text;

    private final JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 30, 10));
    private Tab tabPanel;

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

        //TODO!!!!!
        addLyric();

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

    private void addButtonImage(JButton button, String imageFileName) throws IOException {

        Image buttonImage = ImageIO.read(new File(System.getProperty("user.home")
                + "/Desktop/" + imageFileName));
        buttonImage = buttonImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        button.setIcon(new ImageIcon(buttonImage));

    }

    private void addPlayButton() {
        playButton = new JButton();
        
        /* buttons setting */
        try {
            addButtonImage(playButton, "play.jpg");
        } catch (IOException e) {
            playButton.setText("▶");
        }

        playButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            if (currentMusic.isPlayable()) {
                CurrentMusic.getInstance().play();
                try {
                    addButtonImage(playButton, "pause.png");
                } catch (Exception k) {
                    playButton.setText("||");
                }
                MusicListManager.getInstance().addToRecentPlayList(CurrentMusic.getInstance().toMusic());
            } else {
                CurrentMusic.getInstance().pause();
                reset();
            }
        });
        buttonPanel.add(playButton);
    }

    private void addSeekNextButton() {
        seekNextButton = new JButton();
        try {
            addButtonImage(seekNextButton, "seek-next.png");
        } catch (Exception e) {
            seekNextButton.setText(">>");
        }

        seekNextButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekNext();
        });
        buttonPanel.add(seekNextButton);
    }

    private void addSeekPreviousButton() {
        seekPreviousButton = new JButton();

        try {
            addButtonImage(seekPreviousButton, "seek-Previous.png");

        } catch (IOException e) {
            seekPreviousButton.setText("<<");
        }

        seekPreviousButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.seekPrevious();
        });

        buttonPanel.add(seekPreviousButton);

    }

    private void addStopButton() {
        stopButton = new JButton();
        try {
            addButtonImage(stopButton, "stop.png");
        } catch (IOException e) {
            stopButton.setText("■");
        }

        stopButton.addActionListener(e -> {
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.stop();
            reset();
        });
        buttonPanel.add(stopButton);


    }

    private void addPlayModeButton() {
        playModeButton = new JButton();
        try {
            addButtonImage(playModeButton, "loop.png");
        } catch (Exception e) {
            playModeButton.setText("A/R");
        }

        playModeButton.addActionListener(e -> {
            if (CurrentMusic.playMode == 0) {
                CurrentMusic.playMode++;
                try {
                    addButtonImage(playModeButton, "loop.png");
                } catch (Exception e1) {
                    playModeButton.setText("A/N");
                }
            } else if (CurrentMusic.playMode == 1) {
                CurrentMusic.playMode++;
                try {
                    addButtonImage(playModeButton, "loop.png");
                } catch (Exception e2) {
                    playModeButton.setText("O/R");
                }
            } else {
                CurrentMusic.playMode = 0;
                try {
                    addButtonImage(playModeButton, "loop.png");
                } catch (Exception e3) {
                    playModeButton.setText("A/R");
                }
            }
        });
        buttonPanel.add(playModeButton);
    }

    private void addVolumeSlider() {
        //vertical plz
        volumeSlider = new JSlider();

        // TODO add graphic option
        volumeSlider.addPropertyChangeListener(evt -> {
            float volume = volumeSlider.getValue() / (volumeSlider.getMaximum() - volumeSlider.getMinimum());
            CurrentMusic currentMusic = CurrentMusic.getInstance();
            currentMusic.setVolume(volume);
        });
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
            if (MusicList.listNum != 1) {
                MusicListManager.getInstance().addToFavoriteMusicList(temp);

            } else {
                MusicListManager.getInstance().deleteToFavoriteMusicList(temp);
                tabPanel.getFavoriteButton().doClick();
            }
        });
        buttonPanel.add(starButton);
    }

    private void addImageLabel() {

        try {
            if (musicImage == null) {
                musicImage = ImageIO.read(new File(System.getProperty("user.home")
                        + "/Desktop/" + "defaultImage.jpg"));
            }
            musicImage = musicImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT);

            musicImageLabel = new JLabel(new ImageIcon(musicImage));

            musicInfoPanel = new JPanel();
            musicInfoPanel.setLayout(new BorderLayout());
            musicInfoPanel.add(musicImageLabel, BorderLayout.CENTER);

            musicName = new JLabel();
            musicName.setText("Ready");
            musicName.setForeground(Color.WHITE);
            musicName.setBackground(Color.darkGray);
            musicName.setOpaque(true);

            musicInfoPanel.setSize(30, 30);
            musicInfoPanel.add(musicName, BorderLayout.SOUTH);

            this.add(musicInfoPanel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void replaceMusicInfo() {
        try {
            Music music = CurrentMusic.getInstance().toMusic();
            if (music.getAlbumArt() != null) {
                musicImage = ImageIO.read(new ByteArrayInputStream(music.getAlbumArt()));
                musicImage = musicImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                musicImageLabel.removeAll();
                musicImageLabel.setIcon(new ImageIcon(musicImage));
            }
            musicName.setText(FilePathParser.getFileName(music.getFilename()));
        } catch (Exception e) {
        }
    }

    public void doStop() {
        stopButton.doClick();
    }

    public void doPlay() {
        replaceMusicInfo();
        playButton.doClick();
    }

    public void reset() {
        starButton.setText("★");
        try {
            addButtonImage(playButton, "play.jpg");
        } catch (IOException e) {
            playButton.setText("▶");
        }
    }

    public void connectPanels(Tab tabPanel) {
        this.tabPanel = tabPanel;
    }

    public void addLyric(){
    	Lyric_Repeat rp = new Lyric_Repeat();
  
    	text = new JLabel();
		
		text.setOpaque(true);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setSize(30, 30);
		add(text);

    	
    	//buttonPanel.add(text1);
    }
}