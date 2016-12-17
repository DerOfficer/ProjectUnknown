package Model;

import Control.ProjectUnknownProperties;
import View.DrawingPanel;

import java.awt.*;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class LevelSelect extends DrawingPanel {


    private Planet sun = new Planet(100,400,50,new Color(0xFFDA00));
    private Planet merkur = new Planet(250,400,10,new Color(0x4F4C4C));


    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);
    }
}
