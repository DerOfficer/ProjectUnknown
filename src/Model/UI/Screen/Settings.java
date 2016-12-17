package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.SettingsParser;
import Model.UI.Button;
import Model.UI.Label;
import View.DrawingPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Paths;

public class Settings extends DrawingPanel{
    private SettingsParser settingsParser;

    private Button[] controlButtons;
    private Button[] volumeButtons;

    private Label[] controlLabels;

    private String[] kot;

    private Label headline;

    private Button minus;
    private Button plus;
    private Button doTheFlop;
    private Button back;

    private boolean turned = false;


    public Settings(ProjectUnknownProperties properties){
        super(properties);

        controlButtons = new Button[5];
        controlLabels = new Label[controlButtons.length];
        kot = new String[]{"Forward","Left","Right","Interact","Shoot"};

        createVolButtons();
        createConSettings();

        int x = screenWidth/2;

        minus = new Button(screenWidth/8*6-screenWidth/25,screenHeight/10*9,screenWidth/25,screenWidth/25,"-");
        plus = new Button(screenWidth/8*7+(screenWidth/8/20),screenHeight/10*9,screenWidth/25,screenWidth/25,"+");
        back = new Button(x/7,screenHeight/10*9,100,50,"← Back");
        doTheFlop = new Button(screenWidth-125, screenHeight/10*5,80,50,"Easter Egg");

        headline = new Label(x,screenHeight/10*2,"SETTINGS",150);

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
                    headline = new Label(x, screenHeight / 10 * 2, "IF-SCHLEIFE", 150);
                    addObject(headline);
                }else{
                    removeObject(headline);
                    headline = new Label(x, screenHeight / 10 * 2, "SETTINGS", 150);
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
        volumeButtons = new Button[10];
        int x = screenWidth/160;
        int height = screenWidth/250;
        for(int i = 0; i < volumeButtons.length; i++) {
            volumeButtons[i] = new Button(screenWidth/8*6+x, screenHeight/10*9+screenWidth/25-height, screenWidth/160, height, "");
            x = x+screenWidth/80;
            height = height+screenWidth/250;
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
        int x = screenWidth/5;
        int y = screenHeight/10*3;
        int side = screenWidth/30;
        int letter;
        for(int i = 0; i < controlButtons.length; i++){
            controlButtons[i] = new Button(x,y,side,side,"kot");
            controlLabels[i] = new Label(x+screenWidth/3,y+(side/2),kot[i],20);
            y = y+side+screenHeight/30;
            addObject(controlButtons[i]);
            addObject(controlLabels[i]);
        }

    }

    private void conHandlers(int i){
        controlButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {

        });
    }
}
