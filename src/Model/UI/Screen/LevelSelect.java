package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.Physics.Entity.Player;
import Model.UI.ImageButton;
import Model.Physics.Block.GrassBlock;
import Model.Physics.Entity.Human;
import Model.Physics.Level;
import Model.UI.Button;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class LevelSelect extends DrawingPanel {

    private BufferedImage earth;
    private BufferedImage jupiter;
    private BufferedImage mars;
    private BufferedImage mercury;
    private BufferedImage neptune;
    private BufferedImage saturn;
    private BufferedImage uranus;
    private BufferedImage venus;
    private BufferedImage background;

    private ImageButton planetMercury,planetVenus,planetEarth,
                        planetMars,planetJupiter,planetSaturn,
                        planetUranus,planetNeptune;

    private Button buttonBack;

    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);

        buttonBack = new Button(200, 974, "← Back", properties.getGameFont());

        initImages();
        //addObject(sunSystem);
        addObject(planetMercury);
        addObject(planetVenus);
        addObject(planetEarth);
        addObject(planetMars);
        addObject(planetJupiter);
        addObject(planetSaturn);
        addObject(planetUranus);
        addObject(planetNeptune);
        addObject(buttonBack);

        planetMercury.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("mercury")
        );
        planetVenus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("venus")
        );
        planetEarth.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("earth")
        );
        planetMars.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("mars")
        );
        planetJupiter.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {

            try {
                Human human = new Player(screenWidth/2,screenHeight/2,10, 10, ImageIO.read(new File("Images/character_sprite.png")), properties);
                Level level = new Level(24.79, properties);
                level.focusWithoutScrolling(human);
                level.addObject(new GrassBlock(0,700,900,200));
                level.addObject(new GrassBlock(0, 600, 100,100));
                level.addObject(new GrassBlock(0, 590, 100, 10));
                level.addObject(new GrassBlock(0, 3590, 1000, 10));
                properties.getFrame().setContentPanel(level.getRenderer());
                level.addObject(human);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("jupiter");
        });
        planetSaturn.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("saturn")
        );
        planetUranus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("uranus")
        );
        planetNeptune.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("neptune")
        );

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

    private void initImages(){
        try {
            mercury = ImageIO.read(new File("Images/mercury.png"));
            venus = ImageIO.read(new File("Images/venus.png"));
            earth = ImageIO.read(new File("Images/earth.png"));
            mars = ImageIO.read(new File("Images/mars.png"));
            jupiter = ImageIO.read(new File("Images/jupiter.png"));
            saturn = ImageIO.read(new File("Images/saturn.png"));
            uranus = ImageIO.read(new File("Images/uranus.png"));
            neptune = ImageIO.read(new File("Images/neptune.png"));
            background = ImageIO.read(new File("Images/background.png"));
            planetMercury  = new ImageButton(mercury, (int) (screenWidth*0.2),(screenHeight/2)-(mercury.getHeight(this)/2));
            planetVenus  = new ImageButton(venus, (int) (screenWidth*0.25),(screenHeight/2)-(venus.getHeight(this)/2));
            planetEarth  = new ImageButton(earth, (int) (screenWidth*0.3),(screenHeight/2)-(earth.getHeight(this)/2));
            planetMars  = new ImageButton(mars, (int) (screenWidth*0.35),(screenHeight/2)-(mars.getHeight(this)/2));
            planetJupiter  = new ImageButton(jupiter, (int) (screenWidth*0.4),(screenHeight/2)-(jupiter.getHeight(this)/2));
            planetSaturn  = new ImageButton(saturn, (int) (screenWidth*0.55),(screenHeight/2)-(saturn.getHeight(this)/2));
            planetUranus  = new ImageButton(uranus, (int) (screenWidth*0.75),(screenHeight/2)-(uranus.getHeight(this)/2));
            planetNeptune  = new ImageButton(neptune, (int) (screenWidth*0.85),(screenHeight/2)-(neptune.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
