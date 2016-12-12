package Control;

import Model.Start;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class ProjectUnknownProperties {

    private MainFrame frame;//

    public ProjectUnknownProperties(){
        this.frame = new MainFrame("ProjectUnknown",0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height, this);

        DrawingPanel start = new Start(this);

        frame.addNewDrawingPanel(start);
    }

    public MainFrame getFrame() {
        return frame;
    }
}
