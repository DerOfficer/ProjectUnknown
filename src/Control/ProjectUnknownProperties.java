package Control;

import Model.Start;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import Model.VolumeManager;
/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class ProjectUnknownProperties {
    private VolumeManager volumeManager;
    private MainFrame frame;//

    public ProjectUnknownProperties(){
        this.frame = new MainFrame("ProjectUnknown",0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height, this);

        DrawingPanel start = new Start(this);
        volumeManager = new VolumeManager();
        frame.addNewDrawingPanel(start);
    }

    public MainFrame getFrame() {
        return frame;
    }

    public VolumeManager getVolumeManager(){
        return  volumeManager;
    }

}
