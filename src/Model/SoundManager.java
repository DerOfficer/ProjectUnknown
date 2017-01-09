package Model;

import sun.applet.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class SoundManager {

    private Mixer mixer;
    private Clip[] clips;

    public SoundManager(){

        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        mixer = AudioSystem.getMixer(mixerInfos[0]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        clips = new Clip[1];

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
}
