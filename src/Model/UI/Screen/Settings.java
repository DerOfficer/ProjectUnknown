package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.SettingsParser;
import Model.UI.TextButton;
import Model.UI.Label;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Paths;

public class Settings extends ScreenPanel{
    private SettingsParser settingsParser;
    private boolean[] setting;

    private TextButton[] controlTextButtons;
    private TextButton[] volumeTextButtons;

    private Label[] controlLabels;

    private String[] labels;
    private String[] controls;

    private Label headline;

    private TextButton minus;
    private TextButton plus;
    private TextButton doTheFlop;
    private TextButton back;

    private boolean turned;


    public Settings(ProjectUnknownProperties properties){
        super(properties);

        int x = screenWidth/2;
        back = new TextButton(x/7,screenHeight/10*9,100,50,15f,"← Back");
        doTheFlop = new TextButton(screenWidth-125, screenHeight/10*5,80,50,15f,"Easter Egg");
        headline = new Label(x,screenHeight/10*2,"SETTINGS",150);
        controlTextButtons = new TextButton[5];
        controlLabels = new Label[controlTextButtons.length];
        setting = new boolean[controlTextButtons.length];
        labels = new String[]{"Jump","Left","Right","Interact","Shoot"};
        controls = new String[]{"forward","left","right","interact","shoot"};


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

            for (int i = 0; i < volumeTextButtons.length; i++) {
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
        minus = new TextButton(screenWidth/8*6-screenWidth/25,screenHeight/10*9,screenWidth/25,screenWidth/25,20f,"-");
        plus = new TextButton(screenWidth/8*7+(screenWidth/8/20),screenHeight/10*9,screenWidth/25,screenWidth/25,20f,"+");
        volumeTextButtons = new TextButton[10];
        int x = screenWidth/160;
        int height = screenWidth/250;
        for(int i = 0; i < volumeTextButtons.length; i++) {
            volumeTextButtons[i] = new TextButton(screenWidth/8*6+x, screenHeight/10*9+screenWidth/25-height, screenWidth/160, height,20f, "");
            x = x+screenWidth/80;
            height = height+screenWidth/250;
            addObject(volumeTextButtons[i]);
        }
    }

    private void volHandlers(int i){
        volumeTextButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getVolumeManager().setVolume((double) (i+1));
            System.out.println(properties.getVolumeManager().getVolume());
        });
    }

    private void createConSettings(){
        int x = screenWidth/5;
        int y = screenHeight/10*3;
        int side = screenWidth/30;
        for(int i = 0; i < controlTextButtons.length; i++){
            controlTextButtons[i] = new TextButton(x,y,side,side,20f,settingsParser.getSetting(controls[i]));
            controlLabels[i] = new Label(x+screenWidth/3,y+(side/2),labels[i],20);
            y = y+side+screenHeight/30;
            addObject(controlTextButtons[i]);
            addObject(controlLabels[i]);
        }
    }

    private void changeListener(int button){
        controlTextButtons[button].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            setting[button] = true;
            controlTextButtons[button].addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (e) -> {
                if(setting[button]) {
                    settingsParser.overrideSetting(controls[button], String.valueOf((char) e.getSrcKey()));
                    controlTextButtons[button].setText(settingsParser.getSetting(controls[button]));
                    setting[button] = false;
                }
            });
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