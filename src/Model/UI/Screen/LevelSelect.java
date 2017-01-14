package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.World.World;
import Model.Planet;
import Model.UI.Button;
import View.DrawingPanel;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

public class LevelSelect extends DrawingPanel {

    private BufferedImage background;

    private Button buttonBack;
    private Button[] btnPlanets;

    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);

        buttonBack = new Button(200, 974, "← Back", properties.getGameFont());

        initImages();

        for (int i = 0; i < btnPlanets.length; i++) {
            int finalI = i;
            btnPlanets[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                World level = new World(Paths.get("Worlds/"+Planet.values()[finalI].toString().toLowerCase()+".world"), properties, Planet.values()[finalI]);
                properties.getFrame().setContentPanel(level.getRenderer());
            });
        }

        buttonBack.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getSoundManager().startSound(4);
            properties.getFrame().setContentPanel(properties.getFrame().getStart());
        });

        buttonBack.addEventHandler(IEventInteractableObject.EventType.KEY_PRESSED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE) {
                properties.getSoundManager().startSound(4);
                properties.getFrame().setContentPanel(properties.getFrame().getStart());
                properties.getFrame().setBackgroundPanel(properties.getFrame().getDefaultBackground());
            }
        });

        addObject(buttonBack);
    }

    private void initImages(){
        Planet[] planets = Planet.values();
        this.btnPlanets = new Button[planets.length];

        int offset = planets[planets.length-1].getImage().getWidth();
        for(int i = Planet.values().length - 1; i >= 0; --i){
            BufferedImage tempImg = Planet.values()[i].getImage();
            btnPlanets[i] = new Button(screenWidth-offset-tempImg.getWidth()/2, (screenHeight/2)-tempImg.getHeight()/2, tempImg);
            offset += tempImg.getWidth() + 10;
            addObject(btnPlanets[i]);
        }
    }
}
