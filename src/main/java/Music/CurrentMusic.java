package Music;

import FileIO.FilePathParser;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

import java.io.File;
import java.util.Optional;

public class CurrentMusic {
    private static CurrentMusic uniqueInstance;
    private Optional<MediaPlayer> mediaPlayerOptional;

    private CurrentMusic() {
        this.mediaPlayerOptional = Optional.empty();
    }

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
        if (mediaPlayerOptional.isPresent()) {
            Status status = mediaPlayerOptional.get().getStatus();
            return (status == Status.READY
                    || status == Status.PAUSED
                    || status == Status.STOPPED);
        }
        
        return false;
    }

    public void play() {
        mediaPlayerOptional.ifPresent(mediaPlayer -> {
            if (isPlayable()) {
                mediaPlayer.play();
            }
        });
    }

    public void pause() {
        mediaPlayerOptional.ifPresent(MediaPlayer::pause);
    }

    public void stop() {
        mediaPlayerOptional.ifPresent(MediaPlayer::stop);
    }

    public boolean setMedia(File file) {
        if (file.isFile()) {
            stop();
            mediaPlayerOptional = Optional.of(
                    new MediaPlayer(
                            new Media(file.toURI().toString())));
            return true;
        }
        return false;
    }

    public boolean setVolume(float volume) {
        if (volume >= 0.0 && volume <= 1.0) {
            mediaPlayerOptional.ifPresent(mediaPlayer -> setVolume(volume));
            return true;
        }
        return false;

    }

    public boolean setMedia(String filePath) {
        return setMedia(new File(filePath));
    }

    public Optional<Duration> getCurrentTime() {
        return mediaPlayerOptional.map(MediaPlayer::getCurrentTime);
    }

    private void seek(int second) {
        if (mediaPlayerOptional.isPresent()) {
            MediaPlayer mediaPlayer = mediaPlayerOptional.get();
            Duration duration = mediaPlayer.getCurrentTime();
            Duration delta = new Duration(second * 1000);
            mediaPlayer.seek(duration.add(delta));
        }
    }

    public void seekNext() {
        seek(5);
    }

    public void seekPrevious() {
        seek(-5);
    }

    public Music toMusic() {
    	String filePath = mediaPlayerOptional.get().getMedia().getSource();
    	Music music = MusicListManager.getInstance().find(filePath);
    	return music;
    }
}
