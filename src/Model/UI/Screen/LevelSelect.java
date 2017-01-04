package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.Entity.Player;
import Model.Physics.World.SimplePlanetWorld;
import Model.Physics.World.World;
import Model.Planet;
import Model.UI.Button;
import View.DrawingPanel;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

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
                //SimplePlanetWorld level = new SimplePlanetWorld(Planet.JUPITER, properties);
                World level = new World(Paths.get("Worlds/test.world"), new Player(ProjectUnknownProperties.getScreenDimension().width/2,(int)(ProjectUnknownProperties.getScreenDimension().height/2),properties),properties);
                properties.getFrame().setContentPanel(level.getRenderer());

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

   /* private void createLevel(Planet planet){
            SimplePlanetWorld level = new SimplePlanetWorld(planet, properties);
            properties.getFrame().setContentPanel(level.getRenderer());
    }*/

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
