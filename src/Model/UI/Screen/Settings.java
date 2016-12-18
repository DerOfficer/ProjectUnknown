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
    private boolean[] setting;

    private Button[] controlButtons;
    private Button[] volumeButtons;

    private Label[] controlLabels;

    private String[] labels;
    private String[] controls;

    private Label headline;

    private Button minus;
    private Button plus;
    private Button doTheFlop;
    private Button back;

    private boolean turned;


    public Settings(ProjectUnknownProperties properties){
        super(properties);


        int x = screenWidth/2;
        back = new Button(x/7,screenHeight/10*9,100,50,"← Back");
        doTheFlop = new Button(screenWidth-125, screenHeight/10*5,80,50,"Easter Egg");
        headline = new Label(x,screenHeight/10*2,"SETTINGS",150);
        controlButtons = new Button[5];
        controlLabels = new Label[controlButtons.length];
        setting = new boolean[controlButtons.length];
        labels = new String[]{"Jump","Left","Right","Interact","Shoot"};
        controls = new String[]{"jump","left","right","interact","shoot"};


        try {
            settingsParser = new SettingsParser(Paths.get("game.settings"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createVolButtons();
        createConSettings();

        addObject(minus);
        addObject(plus);
        addObject(back);
        addObject(headline);
        addObject(doTheFlop);

        if(!setting()) {
            back.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
            });

            back.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
                if (event.getSrcKey() == KeyEvent.VK_ESCAPE)
                    properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
            });

            turned = false;
            doTheFlop.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                SwingUtilities.invokeLater(() -> {
                    if (!turned) {
                        removeObject(headline);
                        headline = new Label(x, screenHeight / 10 * 2, "IF-SCHLEIFE", 150);
                        addObject(headline);
                    } else {
                        removeObject(headline);
                        headline = new Label(x, screenHeight / 10 * 2, "SETTINGS", 150);
                        addObject(headline);
                    }
                    turned = !turned;
                });
            });

            for (int i = 0; i < volumeButtons.length; i++) {
                volHandlers(i);
            }

            minus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                properties.getVolumeManager().decrease();
            });

            plus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                properties.getVolumeManager().increase();
            });
        }
        changeListener(0);
        changeListener(1);
        changeListener(2);
        changeListener(3);
        changeListener(4);
    }

    public String getSetting(String key){
        return settingsParser.getSetting(key);
    }

    private void createVolButtons(){
        minus = new Button(screenWidth/8*6-screenWidth/25,screenHeight/10*9,screenWidth/25,screenWidth/25,"-");
        plus = new Button(screenWidth/8*7+(screenWidth/8/20),screenHeight/10*9,screenWidth/25,screenWidth/25,"+");
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
        for(int i = 0; i < controlButtons.length; i++){
            controlButtons[i] = new Button(x,y,side,side,settingsParser.getSetting(controls[i]));
            controlLabels[i] = new Label(x+screenWidth/3,y+(side/2),labels[i],20);
            y = y+side+screenHeight/30;
            addObject(controlButtons[i]);
            addObject(controlLabels[i]);
        }
    }

    private void changeListener(int button){
        controlButtons[button].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if(!setting()) {
                setting[button] = true;
                controlButtons[button].addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (e) -> {
                    String temp = String.valueOf((char) e.getSrcKey());
                    if (setting[button] && !temp.equals(getSetting("left")) && !temp.equals(getSetting("right")) && !temp.equals(getSetting("interact")) && !temp.equals(getSetting("shoot")) && !temp.equals(getSetting("jump"))) {
                        settingsParser.overrideSetting(controls[button], temp);
                        controlButtons[button].setText(settingsParser.getSetting(controls[button]));
                        setting[button] = false;
                    }
                });

            }
        });

    }
    private boolean setting(){
        for (int i = 0; i < setting.length; i++){
           if(setting[i] == true) {
               return true;
           }
        }
        return false;
    }
}