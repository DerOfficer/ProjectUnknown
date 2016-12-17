package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import Model.LevelSelectBackground;
import Model.Physics.Entity.Human;
import Model.PlanetButton;
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

    private LevelSelectBackground sunSystem;
    private PlanetButton planetMercury;
    private PlanetButton planetVenus;
    private PlanetButton planetEarth;
    private PlanetButton planetMars;
    private PlanetButton planetJupiter;
    private PlanetButton planetSaturn;
    private PlanetButton planetUranus;
    private PlanetButton planetNeptune;

    private Button buttonBack;

    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);

        buttonBack = new Button(200, 974, 100, 50, "← Back");

        initImages();
        addObject(sunSystem);
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

            Human human = new Human(10,10,10,10, jupiter);
                    System.out.println("jupiter");
                }
        );
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
            properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
        });

        buttonBack.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE)
                properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
        });
    }

    private void initImages(){
        try {
            mercury = ImageIO.read(new File("Images/mercury.png"));
            planetMercury  = new PlanetButton(mercury,400,(screenHeight/2)-(mercury.getHeight(this)/2));
            venus = ImageIO.read(new File("Images/venus.png"));
            planetVenus  = new PlanetButton(venus,500,(screenHeight/2)-(venus.getHeight(this)/2));
            earth = ImageIO.read(new File("Images/earth.png"));
            planetEarth  = new PlanetButton(earth,600,(screenHeight/2)-(earth.getHeight(this)/2));
            mars = ImageIO.read(new File("Images/mars.png"));
            planetMars  = new PlanetButton(mars,700,(screenHeight/2)-(mars.getHeight(this)/2));
            jupiter = ImageIO.read(new File("Images/jupiter.png"));
            planetJupiter  = new PlanetButton(jupiter,800,(screenHeight/2)-(jupiter.getHeight(this)/2));
            saturn = ImageIO.read(new File("Images/saturn.png"));
            planetSaturn  = new PlanetButton(saturn,1000,(screenHeight/2)-(saturn.getHeight(this)/2));
            uranus = ImageIO.read(new File("Images/uranus.png"));
            planetUranus  = new PlanetButton(uranus,1300,(screenHeight/2)-(uranus.getHeight(this)/2));
            neptune = ImageIO.read(new File("Images/neptune.png"));
            planetNeptune  = new PlanetButton(neptune,1450,(screenHeight/2)-(neptune.getHeight(this)/2));
            background = ImageIO.read(new File("Images/background.png"));
            sunSystem  = new LevelSelectBackground(background);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
