package Model.Managing;

import Control.ProjectUnknownProperties;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class SoundManager {

    private int volume;
    private int runningTitle;

    private Mixer mixer;
    private Clip[] clips; // 0 Hintergrundmusik_Menü, 1 EasterEggButtonSound, 2 Settings_changeKeySound, 3 notificationSound, 4 DefileButtonSound, 5 StartButton
    private FloatControl[] controls;

    public SoundManager() {
        this.clips = new Clip[6];
        this.controls = new FloatControl[6];

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        this.mixer = AudioSystem.getMixer(mixerInfos[0]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        for (int i = 0; i < clips.length; i++) {
            try {
                clips[i] = (Clip) mixer.getLine(dataInfo);
            } catch (LineUnavailableException lue) {
                lue.printStackTrace();
            }
        }

        addSound(Paths.get("Sound/space2.wav").toFile(), 0);
        addSound(Paths.get("Sound/plop2.wav").toFile(), 1);
        addSound(Paths.get("Sound/buttonclickon.wav").toFile(), 2);
        addSound(Paths.get("Sound/notification.wav").toFile(), 3);
        addSound(Paths.get("Sound/plop.wav").toFile(), 4);
        addSound(Paths.get("Sound/startbuttonspace.wav").toFile(), 5);

        startSound(0);
    }

    private void addSound(File soundUrl, int pos) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            clips[pos].open(audioStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            ProjectUnknownProperties.raiseException(e);
        }
        controls[pos] = (FloatControl) clips[pos].getControl(FloatControl.Type.MASTER_GAIN);
    }

    public void startSound(int pos) {
        if (!clips[pos].isRunning()) {
            resetClip(pos);
            clips[pos].start();
            runningTitle = pos;
        }
    }

    public void increase() {
        if (volume < 10) {
            setVolume(volume + 1);
        }
    }

    public void decrease() {
        if (volume > 0) {
            setVolume(volume - 1);
        }
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int newVolume) {
        for (int i = 0; i < controls.length; i++) {
            float volumeStep = (controls[i].getMaximum() - controls[i].getMinimum()) / 10;
            controls[i].setValue(controls[i].getMinimum() + newVolume * volumeStep);
        }
        volume = newVolume;
    }

    public void resetClip(int pos) {
        clips[pos].stop();
        clips[pos].flush();
        clips[pos].setFramePosition(0);
    }
}
