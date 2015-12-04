package Music;

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

    public boolean set(File file) {
        if (file.exists()) {
            stop();
            mediaPlayerOptional = Optional.of(
                    new MediaPlayer(
                            new Media(file.toURI().toString())));
            return true;
        }
        return false;
    }

    public boolean set(String filePath) {
        return set(new File(filePath));
    }

    public Optional<Duration> getCurrentTime() {
        return mediaPlayerOptional.map(MediaPlayer::getCurrentTime);
    }

    public void seekNext() {
        seek(5);
    }

    private void seek(int second) {
        if (mediaPlayerOptional.isPresent()) {
            MediaPlayer mediaPlayer = mediaPlayerOptional.get();
            Duration duration = mediaPlayer.getCurrentTime();
            Duration duration5s = new Duration(second * 1000);
            duration.add(duration5s);
            mediaPlayer.seek(duration);
        }
    }

    public void seekPrevious() {
        seek(-5);
    }
}
