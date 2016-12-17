package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;
import Model.VolumeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Paths;

public class Settings extends DrawingPanel{

    private int sHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int x = (sWidth/2);

    private Button[] controlButtons = new Button[5];
    private String[] kot = new String[]{"Forward","Left","Right","Interact","Shoot"};

    private Button minus = new Button(sWidth/8*6-sWidth/25,sHeight/10*9,sWidth/25,sWidth/25,"-");
    private Button plus = new Button(sWidth/8*7+(sWidth/8/20),sHeight/10*9,sWidth/25,sWidth/25,"+");

    private Button doTheFlop = new Button(sWidth-125, sHeight/10*5,80,50,"Easter Egg");

    boolean turned = false;

    private SettingsParser settingsParser;

    private Button back = new Button(x/7,sHeight/10*9,100,50,"← Back");
    private Label headline = new Label(x,sHeight/10*2,"SETTINGS",150);
    private Label[] controlLabels = new Label[controlButtons.length];
    private Button[] volumeButtons = new Button[10];

    public Settings(ProjectUnknownProperties properties){
        super(properties);
        createVolButtons();
        createConSettings();
       /* addObject(jump);
        addObject(crouch);
        addObject(left);
        addObject(right);*/
        addObject(minus);
        addObject(plus);
        addObject(back);
        addObject(headline);
        addObject(doTheFlop);
        try {
            settingsParser = new SettingsParser(Paths.get("game.settings"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private void createVolButtons(){
        int x = sWidth/160;
        int height = sWidth/250;
        for(int i = 0; i < volumeButtons.length; i++) {
            volumeButtons[i] = new Button(sWidth/8*6+x, sHeight/10*9+sWidth/25-height, sWidth/160, height, "");
            x = x+sWidth/80;
            height = height+sWidth/250;
            addObject(volumeButtons[i]);
        }
    }

    private void volHandlers(int i){
        volumeButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().setVolume((double) (i+1));
            System.out.println(properties.getVolumeManager().getVolume());
        });
    }

    private void createConSettings(){
        int x = sWidth/5;
        int y = sHeight/10*3;
        int side = sWidth/30;
        int letter;
        for(int i = 0; i < controlButtons.length; i++){
            controlButtons[i] = new Button(x,y,side,side,"kot");
            controlLabels[i] = new Label(x+sWidth/3,y+(side/2),kot[i],20);
            y = y+side+sHeight/30;
            addObject(controlButtons[i]);
            addObject(controlLabels[i]);
        }

    }

    private void conHandlers(int i){
        controlButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {

        });
    }
}
