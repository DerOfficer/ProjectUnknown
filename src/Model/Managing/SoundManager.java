package Model.Managing;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Paths;

public class SoundManager {

    private Mixer mixer;
    private Clip[] clips; // 0 Hintergrundmusik_Menü, 1 EasterEggButtonSound, 2 Settings_changeKeySound, 3 notificationSound, 4 DefileButtonSound, 5 StartButton
    private FloatControl[] control;
    private int volume;
    int runningTitle;

    public SoundManager(){

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        mixer = AudioSystem.getMixer(mixerInfos[0]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        clips = new Clip[6];
        control = new FloatControl[6];

        for(int i = 0; i < clips.length; i++) {
            try
            {
                clips[i] = (Clip) mixer.getLine(dataInfo);
            }
            catch (LineUnavailableException lue) {
                lue.printStackTrace();
            }
        }

        addSound(Paths.get("Sound/space2.wav").toFile(),0);
        addSound(Paths.get("Sound/plop2.wav").toFile(),1);
        addSound(Paths.get("Sound/buttonclickon.wav").toFile(),2);
        addSound(Paths.get("Sound/notification.wav").toFile(),3);
        addSound(Paths.get("Sound/plop.wav").toFile(),4);
        addSound(Paths.get("Sound/startbuttonspace.wav").toFile(),5);

        startSound(0);
    }

    private void addSound(File soundUrl, int pos){
        try
        {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            clips[pos].open(audioStream);
        }
        catch(LineUnavailableException lue) { lue.printStackTrace();}
        catch(UnsupportedAudioFileException uafe) { uafe.printStackTrace();}
        catch(IOException ioe){ ioe.printStackTrace();}
        control[pos] = (FloatControl)clips[pos].getControl(FloatControl.Type.MASTER_GAIN);
    }
    
    public void startSound(int pos){
        if (!clips[pos].isRunning()){
            resetClip(pos);
            clips[pos].start();
            runningTitle = pos;
        }
    }

    public void setVolume(int newVolume){
        for(int i = 0; i < control.length; i++){
            float kot = (control[i].getMaximum() - control[i].getMinimum()) / 10;
            control[i].setValue(control[i].getMinimum() + newVolume*kot);
        }
        volume = newVolume;
    }

    public void increase(){
        if(volume < 10){
            setVolume(volume+1);
        }
    }

    public void decrease(){
        if(volume > 0){
            setVolume(volume-1);
        }
    }

    public int getVolume(){
        return this.volume;
    }

    public void changeMusic(int newMusic){
        if(!clips[newMusic].isRunning()){
            clips[newMusic].start();
            clips[runningTitle].stop();
            runningTitle = newMusic;
        }
    }

    public void resetClip(int pos){
        clips[pos].stop();
        clips[pos].flush();
        clips[pos].setFramePosition(0);
    }

    public Clip getClip(int pos){
        return clips[pos];
    }
}
