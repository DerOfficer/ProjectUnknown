package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.SettingsParser;
import Model.UI.Button;
import Model.UI.Label;
import View.DrawingPanel;
import org.jetbrains.annotations.NotNull;

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
    private Button settingButton;

    private SettingsParser settingsParser;

    private Color buttonBackgroundColor;
    private Color noColor;

    public Settings(@NotNull ProjectUnknownProperties properties) {
        super(properties);

        buttonBackgroundColor = new Color(109, 115, 255);
        noColor = new Color(0,0,0,0);

        controlButtons = new Button[5];
        controlLabels = new Label[controlButtons.length];
        setting = new boolean[controlButtons.length];
        controlKeyLabels = new String[]{ "Jump", "Left", "Right", "Interact", "Shoot" };
        settingsParser = new SettingsParser(Paths.get("game.settings"));

        createVolButtons();
        for (int i = 0; i < volumeButtons.length; i++) {
            volHandlers(i);
        }

        initGenericLabels();
        createConSettings();
        initGenericButtons();
        initGenericEventHandlers();

        changeListener(0);
        changeListener(1);
        changeListener(2);
        changeListener(3);
        changeListener(4);
        properties.getSoundManager().setVolume(Integer.parseInt(settingsParser.getSetting("volume")));
        updateVolButtons();

    }

    /**
     * Erstellt die Buttons: Back, EasterEgg, settingButton und die Plus-/Minus-Buttons zum Ändern der Lautstärke
     */
    private void initGenericButtons() {
        btnBack = new Button(screenWidth / 14, screenHeight / 10 * 9, "Back", properties.getGameFont());
        btnEasterEgg = new Button(screenWidth - 125, screenHeight / 10 * 5, "Easter Egg", properties.getGameFont());
        btnVolumeMinus = new Button(screenWidth / 8 * 6 - screenWidth / 25, screenHeight / 10 * 9, screenWidth / 25, screenWidth / 25, "-", properties.getGameFont().deriveFont(25f));
        btnVolumePlus = new Button(screenWidth / 8 * 7 + (screenWidth / 8 / 20), screenHeight / 10 * 9, screenWidth / 25, screenWidth / 25, "+", properties.getGameFont().deriveFont(25f));
        settingButton = new Button(0,0,screenWidth,screenHeight," ",properties.getGameFont().deriveFont(25f));

        btnBack.setForegroundColor(Color.white);
        btnEasterEgg.setForegroundColor(Color.white);
        btnVolumeMinus.setForegroundColor(Color.white);
        btnVolumePlus.setForegroundColor(Color.white);
        btnVolumePlus.setBackgroundColor(buttonBackgroundColor);
        btnVolumeMinus.setBackgroundColor(buttonBackgroundColor);
        changeSettingButton(noColor,noColor," ");

        addObject(btnBack);
        addObject(btnEasterEgg);
        addObject(btnVolumeMinus);
        addObject(btnVolumePlus);
        addObject(settingButton);
    }

    /**
     * Erstellt die EventHandler für die Buttons Back, EasterEgg, settingButton und die Plus-/Minus-Buttons
     */
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

        btnEasterEgg.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                properties.getSoundManager().startSound(1);
                SwingUtilities.invokeLater(() -> {
                    if (!turned) {
                        lblHeadline.setText("IF-SCHLEIFE");
                        btnBack.setText("IF-SCHLEIFE");
                        for(int i = 0; i < controlLabels.length; i++){
                            controlLabels[i].setText("IF-SCHLEIFE");
                        }
                    } else {
                        lblHeadline.setText("SETTINGS");
                        btnBack.setText("Back");
                        for(int i = 0; i < controlLabels.length; i++){
                            controlLabels[i].setText(controlKeyLabels[i]);
                        }
                    }
                    turned = !turned;
                });
            }
        });
    }

    /**
     * Erstellt die Überschrift
     */
    private void initGenericLabels() {
        lblHeadline = new Label(screenWidth / 2, screenHeight / 10 * 2, "SETTINGS", properties.getGameFont().deriveFont(50f));

        addObject(lblHeadline);
    }

    /**
     * Erstellt die einzelnen Lautstärkebuttons
     */
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

    /**
     * Erstellt die EventHandler der Lautstärkebuttons
     */
    private void volHandlers(int i) {
        volumeButtons[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if(!isExpectingUserInput()) {
                properties.getSoundManager().setVolume(i);
                settingsParser.overrideSetting("volume", Integer.toString(i));
                updateVolButtons();
            }
        });
    }

    /**
     * Erstellt die Buttons zum Ändern der Steuerung
     */
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

    /**
     * Erstellt die EventHandler für die einzelnen Knöpfe, welche die Steuerung ändern
     */
    private void changeListener(int button) {
        controlButtons[button].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            if (!isExpectingUserInput()) {
                setting[button] = true;

                changeSettingButton(Color.white, new Color(0,0,0,200), "Currently setting Button");

                settingButton.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (e2) -> {
                    if(e2.getSrcKey() == KeyEvent.VK_ESCAPE) {
                        changeSettingButton(noColor,noColor," ");
                        setting[button] = false;
                    }
                });

                controlButtons[button].addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (e3) -> {
                    String temp = String.valueOf((char) e3.getSrcKey());
                    if (setting[button] && !temp.equals(getSetting("left")) && !temp.equals(getSetting("right")) && !temp.equals(getSetting("interact")) && !temp.equals(getSetting("shoot")) && !temp.equals(getSetting("jump")) && e3.getSrcKey() != KeyEvent.VK_ESCAPE) {
                        settingsParser.overrideSetting(controlKeyLabels[button].toLowerCase(), temp);
                        controlButtons[button].setText(settingsParser.getSetting(controlKeyLabels[button].toLowerCase()));
                        properties.getSoundManager().startSound(2);
                        changeSettingButton(noColor,noColor," ");
                        setting[button] = false;
                    }
                });
            }
        });
    }

    /**
     * Prüft, ob gerade ein Button geändert wird.
     */
    private boolean isExpectingUserInput() {
        for (int i = 0; i < setting.length; i++) {
            if (setting[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ändert die Farbe der einzelnen Lautstärkebalken
     */
    public void updateVolButtons() {
        for (int i = 0; i < volumeButtons.length; i++) {
            if (properties.getSoundManager().getVolume() < i) {
                volumeButtons[i].setBackgroundColor(new Color(255, 255, 255));
            } else {
                volumeButtons[i].setBackgroundColor(new Color(0, 255, 0));
            }
        }
    }

    /**
     * Ändert die Parameter des 'settingButton', welcher auf dem Bildschirm erscheint, wenn gerade ein Button zum Steuern geändert wird
     */
    public void changeSettingButton(Color color1, Color color2, String text){
        settingButton.setForegroundColor(color1);
        settingButton.setBackgroundColor(color2);
        settingButton.setText(text);
    }

    /**
     * Beim Ändern eines Steuerung-Knopfes muss abgefragt werden, welche Funktion dieser jeweilige Knopf hat. Diese Methode liefert
     * es zurück.
     */
    public String getSetting(String key) {
        return settingsParser.getSetting(key);
    }
}
