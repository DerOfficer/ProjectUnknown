package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.SolidTerrainBlock;
import Model.Physics.Entity.Human;
import Model.Physics.Entity.Player;
import Model.Planet;

import java.io.IOException;

/**
 * Created by Amasso on 25.12.2016.
 */
public class SimplePlanetWorld extends AbstractWorld {
    public SimplePlanetWorld(Planet planet, ProjectUnknownProperties properties) {
        super(planet.getGravity(), properties);

        try {
            Human human = new Player((int)(ProjectUnknownProperties.getScreenDimension().width/2),(int)(ProjectUnknownProperties.getScreenDimension().height/2), properties);
            addObject(human);
            focusWithoutScrolling(human);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int counter=0;
        for (int i = 0; i < 10; i++) {
            addObject(new SolidTerrainBlock(counter,(i*50)+300,200,2000,planet.getTopColor(),planet.getInnerColor()));
            counter = counter+150;
        }

    }
}
