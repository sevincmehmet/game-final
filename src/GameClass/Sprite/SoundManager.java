package GameClass.Sprite;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

//hocam dosya yollarını değiştirmeyi unutmayın. Dosyaların hepsi resources klasöründe. :)

public class SoundManager {

    private Clip clip;
    private Clip winSound;
    private Clip mineHitSound;
    private Clip clickSound;


    public SoundManager() {
        loadSounds();

    }

    private void loadSounds() {
        try {
            File winFile = new File("C:\\Users\\Owsito\\IdeaProjects\\game-final\\src\\GameClass\\Sound\\başarı (online-audio-converter.com) (1).wav");
            AudioInputStream winStream = AudioSystem.getAudioInputStream(winFile);
            winSound = AudioSystem.getClip();
            winSound.open(winStream);


        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void playWinSound() {
        if (winSound != null) {
            winSound.stop();
            winSound.setFramePosition(0);
            winSound.start();
        }
    }


    public void playBackgroundMusic() {
        try {
            File soundFile = new File("C:\\Users\\Owsito\\IdeaProjects\\game-final\\src\\GameClass\\Sound\\Background.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}