package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.Block.Teleporter;
import Model.Physics.Entity.Player;
import Model.Physics.World.SimplePlanetWorld;
import Model.Physics.World.World;
import Model.Planet;
import Model.UI.Button;
import View.DrawingPanel;

import java.awt.*;
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
                World level = new World(Paths.get("Worlds/test.world"), properties.getPlayer(),properties, Planet.values()[finalI]);
                Teleporter t1 = new Teleporter(properties, 0,400,100,100, Color.RED);
                Teleporter t2 = new Teleporter(properties, 1000,400,100,100, Color.RED);
                t1.link(t2);
                t2.link(t1);
                level.addObject(t1);
                level.addObject(t2);
                properties.getFrame().setContentPanel(level.getRenderer());
                properties.setCurrentWorld(level);

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

        addObject(buttonBack);
    }

   /* private void createLevel(Planet planet){
            SimplePlanetWorld level = new SimplePlanetWorld(planet, properties);
            properties.getFrame().setContentPanel(level.getRenderer());
    }*/

    private void initImages(){
        btnPlanets = new Button[Planet.values().length];
        /*int temp = 100 + (int) (1f / Planet.values().length);
        for (int i = 0; i < Planet.values().length; i++) {
            BufferedImage tempImg = Planet.values()[i].getImage();
            btnPlanets[i] = new Button(temp, (screenHeight/2)-tempImg.getHeight()/2, tempImg);
            temp = temp + tempImg.getWidth()+50;
            addObject(btnPlanets[i]);
        }*/
        Planet[] planets = Planet.values();
        int offset = planets[planets.length-1].getImage().getWidth();
        for(int i = Planet.values().length - 1; i >= 0; --i){
            BufferedImage tempImg = Planet.values()[i].getImage();
            btnPlanets[i] = new Button(screenWidth-offset-tempImg.getWidth()/2, (screenHeight/2)-tempImg.getHeight()/2, tempImg);
            offset += tempImg.getWidth() + 10;
            addObject(btnPlanets[i]);
        }
    }
}
