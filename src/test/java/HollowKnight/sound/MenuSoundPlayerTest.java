package HollowKnight.sound;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuSoundPlayerTest {

    @Test
    public void soundLoaderWorking() throws Exception {
        AudioInputStream audioInput = mock(AudioInputStream.class);
        Clip musicClip = mock(Clip.class);

        Clip sound = new SoundLoader().loadSound(audioInput, musicClip);
        verify(musicClip, Mockito.times(1)).open(audioInput);

        MenuSoundPlayer backgroundSoundPlayer = new MenuSoundPlayer(sound);
        assertEquals(sound, backgroundSoundPlayer.getSound());
    }

    @Test
    public void soundLoaderException() throws Exception {
        AudioInputStream audioInput = mock(AudioInputStream.class);
        Clip musicClip = mock(Clip.class);
        doThrow(new FileNotFoundException()).when(musicClip).open(Mockito.any());

        Exception thrown = Assertions.assertThrows(Exception.class,
                () -> new MenuSoundPlayer(new SoundLoader().loadSound(audioInput, musicClip)),
                "BackgroundSoundPlayer was supposed to throw Exception");

        Assertions.assertEquals("Unable to load sound file!", thrown.getMessage());
    }
    @Test
    public void soundTesting() {
        Clip sound=mock(Clip.class);
        MenuSoundPlayer backgroundSoundPlayer = new MenuSoundPlayer(sound);
        backgroundSoundPlayer.setSound(sound);
        backgroundSoundPlayer.start();
        verify(sound, times(1)).setMicrosecondPosition(0);
        verify(sound, times(1)).start();
        verify(sound, times(1)).loop(Clip.LOOP_CONTINUOUSLY);

        backgroundSoundPlayer.stop();
        verify(sound, times(1)).stop();
    }
}