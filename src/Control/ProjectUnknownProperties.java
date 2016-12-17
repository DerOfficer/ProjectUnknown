package Control;

import View.MainFrame;
import java.awt.*;
import Model.VolumeManager;

public class ProjectUnknownProperties {

    private VolumeManager volumeManager;
    private MainFrame frame;

    public ProjectUnknownProperties(){
        this.frame = new MainFrame("ProjectUnknown",0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height, this);

        volumeManager = new VolumeManager(1);
        frame.setDrawingPanel(frame.getStart());
    }

    public MainFrame getFrame() {
        return frame;
    }

    public VolumeManager getVolumeManager(){
        return  volumeManager;
    }
}
