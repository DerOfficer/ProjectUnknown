package Model.Physics.Level;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.PlanetBlock;
import Model.Physics.Entity.Human;
import Model.Physics.Entity.Player;
import Model.Planet;

import java.io.IOException;

/**
 * Created by Amasso on 25.12.2016.
 */
public class SimplePlanetLevel extends AbstractLevel {
    public SimplePlanetLevel(Planet planet, ProjectUnknownProperties properties) {
        super(planet.getGravity(), properties);

        try {
            Human human = new Player(ProjectUnknownProperties.getScreenDimension().width/2,ProjectUnknownProperties.getScreenDimension().height/2,10, 10, properties);
            addObject(human);
            focusWithoutScrolling(human);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addObject(new PlanetBlock(0,2700,900,200,planet));
        addObject(new PlanetBlock(0, 2600, 100,100,planet));
        addObject(new PlanetBlock(0, 2590, 100, 10,planet));
        addObject(new PlanetBlock(0, 3590, 1000, 10,planet));
    }
}
