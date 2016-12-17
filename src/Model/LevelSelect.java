package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Button;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class LevelSelect extends DrawingPanel {


    private Image earth = Toolkit.getDefaultToolkit().getImage("earth.png");
    private Image jupiter = Toolkit.getDefaultToolkit().getImage("jupiter.png");
    private Image mars;
    private Image mercury = Toolkit.getDefaultToolkit().getImage("mercury.png");
    private Image neptune = Toolkit.getDefaultToolkit().getImage("neptune.png");
    private Image saturn = Toolkit.getDefaultToolkit().getImage("saturn.png");
    private Image uranus = Toolkit.getDefaultToolkit().getImage("uranus.png");
    private Image venus = Toolkit.getDefaultToolkit().getImage("venus.png");

    private LevelSelectBackground sunSystem = new LevelSelectBackground();
    private Planet planetMercury = new Planet(mercury,200,200);
    private Planet planetVenus = new Planet(venus,300,200);
    private Planet planetEarth = new Planet(earth,400,200);
    private Planet planetMars;
    private Planet planetJupiter = new Planet(jupiter,600,200);
    private Planet planetSaturn = new Planet(saturn,700,200);
    private Planet planetUranus = new Planet(uranus,800,200);
    private Planet planetNeptune = new Planet(neptune,900,200);

    private Model.Button back = new Model.Button(200,800,100,50,"← Back");



    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);
        initImages();
        addObject(planetMercury);
        addObject(planetVenus);
        addObject(planetEarth);
        addObject(planetMars);
        addObject(planetJupiter);
        addObject(planetSaturn);
        addObject(planetUranus);
        addObject(planetNeptune);
        addObject(back);


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
        planetJupiter.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("jupiter")
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

        back.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) -> {
            properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
        });

        back.addEventHandler(IEventInteractableObject.EventType.KEY_RELEASED, (event) -> {
            if(event.getSrcKey() == KeyEvent.VK_ESCAPE)
                properties.getFrame().setDrawingPanel(properties.getFrame().getStart());
        });
    }

    private void initImages(){
        try {
            mars = ImageIO.read(new File("Images/mars.png"));
            planetMars  = new Planet(mars,500,200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
