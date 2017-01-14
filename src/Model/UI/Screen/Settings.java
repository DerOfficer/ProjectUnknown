package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.SettingsParser;
import Model.UI.Button;
import Model.UI.Label;
import View.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Paths;

public class Settings extends DrawingPanel {

    private boolean turned;
    private boolean[] setting;

    private Label lblHeadline;
    private Label[] controlLabels;

    private String[] controlKeyLabels;

    private Button btnVolumeMinus;
    private Button btnVolumePlus;
    private Button btnEasterEgg;
    private Button btnBack;
    private Button[] controlButtons;
    private Button[] volumeButtons;

    private SettingsParser settingsParser;

    private Color buttonBackgroundColor;

    public Settings(ProjectUnknownProperties properties) {
        super(properties);

        this.buttonBackgroundColor = new Color(109, 115, 255);

        initGenericButtons();
        initGenericLabels();

        initGenericEventHandlers();

        controlButtons = new Button[5];
        controlLabels = new Label[controlButtons.length];

        setting = new boolean[controlButtons.length];

        controlKeyLabels = new String[]{ "Jump", "Left", "Right", "Interact", "Shoot" };

        settingsParser = new SettingsParser(Paths.get("game.settings"));

        createVolButtons();
        createConSettings();

        btnEasterEgg.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                properties.getSoundManager().startSound(1);
                SwingUtilities.invokeLater(() -> {
                    if (!turned) {
                        removeObject(lblHeadline);
                        lblHeadline = new Label(screenWidth / 2, screenHeight / 10 * 2, "IF-SCHLEIFE", properties.getGameFont().deriveFont(50f));
                        addObject(lblHeadline);
                    } else {
                        removeObject(lblHeadline);
                        lblHeadline = new Label(screenWidth / 2, screenHeight / 10 * 2, "SETTINGS", properties.getGameFont().deriveFont(50f));
                        addObject(lblHeadline);
                    }
                    turned = !turned;
                });
            }
        });

        for (int i = 0; i < volumeButtons.length; i++) {
            volHandlers(i);
        }

        changeListener(0);
        changeListener(1);
        changeListener(2);
        changeListener(3);
        changeListener(4);
        properties.getSoundManager().setVolume(Integer.parseInt(settingsParser.getSetting("volume")));
        updateVolButtons();
    }

    private void initGenericButtons() {
        btnBack = new Button(screenWidth / 14, screenHeight / 10 * 9, "← Back", properties.getGameFont());
        btnEasterEgg = new Button(screenWidth - 125, screenHeight / 10 * 5, "Easter Egg", properties.getGameFont());
        btnVolumeMinus = new Button(screenWidth / 8 * 6 - screenWidth / 25, screenHeight / 10 * 9, screenWidth / 25, screenWidth / 25, "-", properties.getGameFont());
        btnVolumePlus = new Button(screenWidth / 8 * 7 + (screenWidth / 8 / 20), screenHeight / 10 * 9, screenWidth / 25, screenWidth / 25, "+", properties.getGameFont());

        btnBack.setForegroundColor(Color.white);
        btnEasterEgg.setForegroundColor(Color.white);
        btnVolumeMinus.setForegroundColor(Color.white);
        btnVolumePlus.setForegroundColor(Color.white);
        btnVolumePlus.setBackgroundColor(buttonBackgroundColor);
        btnVolumeMinus.setBackgroundColor(buttonBackgroundColor);

        addObject(btnBack);
        addObject(btnEasterEgg);
        addObject(btnVolumeMinus);
        addObject(btnVolumePlus);
    }

    private void initGenericLabels() {
        lblHeadline = new Label(screenWidth / 2, screenHeight / 10 * 2, "SETTINGS", properties.getGameFont().deriveFont(50f));

        addObject(lblHeadline);
    }

    private void initGenericEventHandlers() {
        btnBack.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                properties.getSoundManager().startSound(4);
                properties.getFrame().setContentPanel(properties.getFrame().getStart());
            }
        });

        btnBack.addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (event) -> {
            if (event.getSrcKey() == KeyEvent.VK_ESCAPE && !isExpectingUserInput()) {
                properties.getSoundManager().startSound(4);
                properties.getFrame().setContentPanel(properties.getFrame().getStart());
            }
        });

        btnVolumeMinus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                properties.getSoundManager().decrease();
                updateVolButtons();
            }
        });

        btnVolumePlus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                properties.getSoundManager().increase();
                updateVolButtons();
            }
        });
    }

    private void createVolButtons() {
        volumeButtons = new Button[10];
        int x = screenWidth / 160;
        int height = screenWidth / 250;
        for (int i = 0; i < volumeButtons.length; i++) {
            volumeButtons[i] = new Button(screenWidth / 8 * 6 + x, screenHeight / 10 * 9 + screenWidth / 25 - height, screenWidth / 160, height, Color.WHITE);
            x = x + screenWidth / 80;
            height = height + screenWidth / 250;
            addObject(volumeButtons[i]);
        }
        updateVolButtons();
    }

    private void volHandlers(int i) {
        volumeButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getSoundManager().setVolume(i);
            settingsParser.overrideSetting("volume", Integer.toString(i));
            updateVolButtons();
        });
    }

    private void createConSettings() {
        int x = screenWidth / 5;
        int y = screenHeight / 10 * 3;
        int side = screenWidth / 30;
        for (int i = 0; i < controlButtons.length; i++) {
            controlButtons[i] = new Button(x, y, screenWidth / 25, screenWidth / 25, settingsParser.getSetting(controlKeyLabels[i].toLowerCase()), properties.getGameFont());
            controlButtons[i].setForegroundColor(Color.white);
            controlButtons[i].setBackgroundColor(buttonBackgroundColor);
            controlLabels[i] = new Label(x + screenWidth / 3, y + (side / 2), controlKeyLabels[i], properties.getGameFont().deriveFont(20F));
            y = y + side + screenHeight / 30;
            addObject(controlButtons[i]);
            addObject(controlLabels[i]);
        }
    }

    private void changeListener(int button) {
        controlButtons[button].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                setting[button] = true;
                controlButtons[button].addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (e) -> {
                    String temp = String.valueOf((char) e.getSrcKey());
                    if (setting[button] && !temp.equals(getSetting("left")) && !temp.equals(getSetting("right")) && !temp.equals(getSetting("interact")) && !temp.equals(getSetting("shoot")) && !temp.equals(getSetting("jump"))) {
                        settingsParser.overrideSetting(controlKeyLabels[button].toLowerCase(), temp);
                        controlButtons[button].setText(settingsParser.getSetting(controlKeyLabels[button].toLowerCase()));
                        properties.getSoundManager().startSound(2);
                        setting[button] = false;
                    }
                });
            }
        });
    }

    private boolean isExpectingUserInput() {
        for (int i = 0; i < setting.length; i++) {
            if (setting[i]) {
                return true;
            }
        }
        return false;
    }

    public void updateVolButtons() {
        for (int i = 0; i < volumeButtons.length; i++) {
            if (properties.getSoundManager().getVolume() < i) {
                volumeButtons[i].setBackgroundColor(new Color(255, 255, 255));
            } else {
                volumeButtons[i].setBackgroundColor(new Color(0, 255, 0));
            }
        }
    }

    public String getSetting(String key) {
        return settingsParser.getSetting(key);
    }
}
