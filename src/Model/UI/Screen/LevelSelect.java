package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.Entity.Player;
import Model.Physics.Level.SimplePlanetLevel;
import Model.Planet;
import Model.UI.ImageButton;
import Model.Physics.Entity.Human;
import Model.Physics.Level.AbstractLevel;
import Model.UI.Button;
import View.DrawingPanel;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class LevelSelect extends DrawingPanel {

    private BufferedImage background;

    private Button buttonBack;
    private Button[]btnPlanets;

    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);

        buttonBack = new Button(200, 974, "← Back", properties.getGameFont());

        initImages();

        for (int i = 0; i < btnPlanets.length; i++) {
            int finalI = i;
            btnPlanets[i].addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
                createLevel(Planet.values()[finalI]);
            });
        }


        buttonBack.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().setContentPanel(properties.getFrame().getStart());
        });

        buttonBack.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE) {
                properties.getFrame().setContentPanel(properties.getFrame().getStart());
                properties.getFrame().setBackgroundPanel(properties.getFrame().getDefaultBackground());
            }
        });
    }

    private void createLevel(Planet planet){
            SimplePlanetLevel level = new SimplePlanetLevel(planet, properties);
            properties.getFrame().setContentPanel(level.getRenderer());
    }

    private void initImages(){
        btnPlanets = new Button[Planet.values().length];
        int temp = (int) (0.2*screenWidth);
        for (int i = 0; i < Planet.values().length; i++) {
            BufferedImage tempImg = Planet.values()[i].getImage();
            btnPlanets[i] = new Button((int) (temp), (screenHeight/2)-tempImg.getHeight()/2, tempImg);
            temp = temp + tempImg.getWidth()+50;
            addObject(btnPlanets[i]);
        }
    }
}
