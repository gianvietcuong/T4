package base;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sound {
    public Sound() {

        try {
            // Open an audio input stream.
            File file = new File("/home/hp/Downloads/ci-begin-master/assets/music/Taketori.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
