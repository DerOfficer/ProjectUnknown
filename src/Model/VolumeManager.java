package Model;


public class VolumeManager{
    private double volume;

    public VolumeManager(){
        volume = 0;
    }

    public void increase(){
        if(volume < 1) {
            volume = volume + 0.1;
        }
    }
    public void decrease(){
        if(volume > 0) {
            volume = volume - 0.1;
        }
    }

    public void setVolume(double newVolume){
        volume = newVolume/10;
        if(volume > 1){
            volume = 1;
        }
        if(volume < 0){
            volume = 0;
        }
    }

    public double getVolume(){
        return volume;
    }
}
