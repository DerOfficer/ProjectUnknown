package Model;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IEventInteractableObject;
import View.DrawingPanel;

import java.awt.*;
import java.awt.Button;
import java.awt.event.KeyEvent;

/**
 * Created by 204g03 on 16.12.2016.
 */
public class LevelSelect extends DrawingPanel {

    private LevelSelectBackground sunSystem = new LevelSelectBackground();
    private Planet mercury = new Planet("mercury",200,200);
    private Planet venus = new Planet("venus",300,200);
    private Planet earth = new Planet("earth",400,200);
    private Planet mars = new Planet("mars",500,200);
    private Planet jupiter = new Planet("jupiter",600,200);
    private Planet saturn = new Planet("saturn",700,200);
    private Planet uranus = new Planet("uranus",800,200);
    private Planet neptune = new Planet("neptune",900,200);

    private Model.Button back = new Model.Button(200,800,100,50,"← Back");



    public LevelSelect(ProjectUnknownProperties properties){
        super(properties);
        addObject(mercury);
        addObject(venus);
        addObject(earth);
        addObject(mars);
        addObject(jupiter);
        addObject(saturn);
        addObject(uranus);
        addObject(neptune);
        addObject(back);


        mercury.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("merury")
        );
        venus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("venus")
        );
        earth.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("earth")
        );
        mars.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("mars")
        );
        jupiter.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("jupiter")
        );
        saturn.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("saturn")
        );
        uranus.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
                System.out.println("uranus")
        );
        neptune.addEventHandler(IEventInteractableObject.EventType.MOUSE_RELEASED, (event) ->
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
}
