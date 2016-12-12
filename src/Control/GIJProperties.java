package Control;

import Model.Start;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class GIJProperties {

    private MainFrame frame;//

    public GIJProperties(){
        this.frame = new MainFrame("Fische sind dezent",0,0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);

        DrawingPanel start = new Start();

        frame.addNewDrawingPanel(start);
    }

    public MainFrame getFrame(){
        return frame;
    }
}
