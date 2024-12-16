package HollowKnight.sound;

import HollowKnight.model.menu.Menu;

import javax.sound.sampled.Clip;

public class MenuSoundPlayer implements SoundPlayer{
    private Clip sound;

    public MenuSoundPlayer(Clip sound){
        this.sound = sound;
    }
    @Override
    public void start() {
        sound.setMicrosecondPosition(0);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void stop() {
        sound.stop();
    }

    @Override
    public void setSound(Clip sound) {
        this.sound = sound;
    }

    @Override
    public Clip getSound() {
        return sound;
    }
}
