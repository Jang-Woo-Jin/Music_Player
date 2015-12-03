package Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import java.io.File;

public class CurrentMusic {
    private static CurrentMusic uniqueInstance;
    private MediaPlayer mediaPlayer;

    public static CurrentMusic getInstance() {
        if (uniqueInstance == null) {
            synchronized (CurrentMusic.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new CurrentMusic();
                }
            }
        }
        return uniqueInstance;
    }

    public boolean isPlayable() {
        Status status = mediaPlayer.getStatus();
        return (status == Status.READY
                || status == Status.PAUSED
                || status == Status.STOPPED);
    }

    public void play() {
        if (isPlayable()) {
            mediaPlayer.play();
        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void set(File file) {
        if (file.exists()) {
            mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
        }
    }

    public void set(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
        }
    }
}
