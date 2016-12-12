package Model;

import Control.ProjectUnknownProperties;
import View.DrawingPanel;

import java.awt.*;

/**
 * Created by 204g03 on 12.12.2016.
 */
public class Settings extends DrawingPanel{

    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (screenWidth/2)-(300/2);


    private Button jump = new Button(x,);
    private Button crouch = new Button();
    private Button left = new Button();
    private Button right = new Button();

    private Button minus = new Button();
    private Button plus = new Button();

    private Button back = new Button();

    public Settings(ProjectUnknownProperties properties){
        super(properties);

    }


}
