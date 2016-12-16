package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;
import Model.VolumeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Settings extends DrawingPanel{

    private int sHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (sWidth/2);


    private Button jump = new Button(x-75,sHeight/10*4-150,150,150,"W");
    private Button crouch = new Button(x-75,sHeight/10*5,150,150,"S");
    private Button left = new Button(x-225-sHeight/10,sHeight/10*5,150,150,"A");
    private Button right = new Button(x+75+sHeight/10,sHeight/10*5,150,150,"D");

    private Button minus = new Button(sWidth/8*6-sWidth/25,sHeight/10*9,sWidth/25,sWidth/25,"-");
    private Button plus = new Button(sWidth/8*7+(sWidth/8/20),sHeight/10*9,sWidth/25,sWidth/25,"+");

    private Button doTheFlop = new Button(sWidth-125, sHeight/10*5,80,50,"Easter Egg");

    boolean turned = false;

    private Button back = new Button(x/7,sHeight/10*9,100,50,"← Back");
    private Label headline = new Label(x,sHeight/10*2,"SETTINGS",150);
    private Button[] volumeButtons = new Button[10];

    public Settings(ProjectUnknownProperties properties){
        super(properties);
        createVolButtons();
        addObject(jump);
        addObject(crouch);
        addObject(left);
        addObject(right);
        addObject(minus);
        addObject(plus);
        addObject(back);
        addObject(headline);
        addObject(doTheFlop);

        back.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
        });

        back.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE)
                properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
        });

        minus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().decrease();
        });

        plus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().increase();
        });

        doTheFlop.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            SwingUtilities.invokeLater(() -> {
                if(!turned) {
                    removeObject(headline);
                    headline = new Label(x, sHeight / 10 * 2, "IF-SCHLEIFE", 150);
                    addObject(headline);
                }else{
                    removeObject(headline);
                    headline = new Label(x, sHeight / 10 * 2, "SETTINGS", 150);
                    addObject(headline);
                }
                turned = !turned;
            });
        });

        for(int i = 0; i < volumeButtons.length; i++){
            volHandlers(i);
        }
    }

    public void createVolButtons(){
        int x = sWidth/160;
        int height = sWidth/25/10;
        for(int i = 0; i < volumeButtons.length; i++) {
            volumeButtons[i] = new Button(sWidth/8*6+x, sHeight/10*9+sWidth/25-height, sWidth/8/20, height, "");
            x = x+sWidth/8/10;
            height = height+sWidth/25/10;
            addObject(volumeButtons[i]);
        }
    }

    public void volHandlers(int i){
        volumeButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().setVolume((double) (i+1));
            System.out.println(properties.getVolumeManager().getVolume());
        });
    }
}
