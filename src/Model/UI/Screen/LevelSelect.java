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
                /*if(finalI == 2){
                    InconsistentStateBlock b2 = new InconsistentStateBlock(1400, -300, BlockType.STONE_BRICK, "");
                    InconsistentStateBlock b3 = new InconsistentStateBlock(1400, -250, BlockType.STONE_BRICK, "");
                    InconsistentStateBlock b4 = new InconsistentStateBlock(1400, -200, BlockType.STONE_BRICK, "");
                    InconsistentStateBlock b1 = new InconsistentStateBlock(1400, -150, BlockType.STONE_BRICK, "");
                    level.addObject(b1);
                    level.addObject(b2);
                    level.addObject(b3);
                    level.addObject(b4);
                    Lever l1 = new Lever(1300, -150, "", false);
                    Consumer<Boolean> onToggle = (on) -> {
                        b1.toggleSolidity();
                        b2.toggleSolidity();
                        b3.toggleSolidity();
                        b4.toggleSolidity();
                    };

                    level.addObject(l1);
                }*/
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
