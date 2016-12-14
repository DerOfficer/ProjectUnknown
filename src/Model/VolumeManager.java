package Model;

/**
 * Created by 204g03 on 12.12.2016.
 */
public class VolumeManager {
    double volume;

    public VolumeManager(){
        volume = 1;
    }

    public void increase(){
        if(checkBounds()) {
            volume = volume + 0.01;
        }
    }
    public void decrease(){
        if(checkBounds()) {
            volume = volume - 0.01;
        }
    }

    public boolean checkBounds(){
        return (volume > 0 && volume < 1);
    }
}
