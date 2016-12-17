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


    private int y = Toolkit.getDefaultToolkit().getScreenSize().height;

    private Image earth;
    private Image jupiter;
    private Image mars;
    private Image mercury;
    private Image neptune;
    private Image saturn;
    private Image uranus;
    private Image venus;
    private Image background;

    private LevelSelectBackground sunSystem;
    private Planet planetMercury;
    private Planet planetVenus;
    private Planet planetEarth;
    private Planet planetMars;
    private Planet planetJupiter;
    private Planet planetSaturn;
    private Planet planetUranus;
    private Planet planetNeptune;

    private Model.Button back = new Model.Button(200,974,100,50,"← Back");



    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);
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
            mercury = ImageIO.read(new File("Images/mercury.png"));
            planetMercury  = new Planet(mercury,400,(y/2)-(mercury.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            venus = ImageIO.read(new File("Images/venus.png"));
            planetVenus  = new Planet(venus,500,(y/2)-(venus.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            earth = ImageIO.read(new File("Images/earth.png"));
            planetEarth  = new Planet(earth,600,(y/2)-(earth.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mars = ImageIO.read(new File("Images/mars.png"));
            planetMars  = new Planet(mars,700,(y/2)-(mars.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jupiter = ImageIO.read(new File("Images/jupiter.png"));
            planetJupiter  = new Planet(jupiter,800,(y/2)-(jupiter.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            saturn = ImageIO.read(new File("Images/saturn.png"));
            planetSaturn  = new Planet(saturn,1000,(y/2)-(saturn.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            uranus = ImageIO.read(new File("Images/uranus.png"));
            planetUranus  = new Planet(uranus,1300,(y/2)-(uranus.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            neptune = ImageIO.read(new File("Images/neptune.png"));
            planetNeptune  = new Planet(neptune,1450,(y/2)-(neptune.getHeight(this)/2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            background = ImageIO.read(new File("Images/background.png"));
            sunSystem  = new LevelSelectBackground(background);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
