package Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class SoundManager {

    private Mixer mixer;
    private Clip[] clips;
    private FloatControl[] control;
    private int volume;

    public SoundManager(){

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        mixer = AudioSystem.getMixer(mixerInfos[0]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        clips = new Clip[3];
        control = new FloatControl[3];

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
        addSound(Paths.get("Sound/button_1.wav").toFile(),1);
        addSound(Paths.get("Sound/button_2.wav").toFile(),2);
        control[0] = (FloatControl)clips[0].getControl(FloatControl.Type.MASTER_GAIN);
        control[1] = (FloatControl)clips[1].getControl(FloatControl.Type.MASTER_GAIN);
        control[2] = (FloatControl)clips[2].getControl(FloatControl.Type.MASTER_GAIN);
        setVolume(10);
        volume = 10;

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
    }
    
    public void startSound(int pos){
        if (!clips[pos].isActive()){
            clips[pos].start();
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
}
