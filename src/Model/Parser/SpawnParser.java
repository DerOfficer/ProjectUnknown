package Model.Parser;

import Control.ProjectUnknownProperties;
import Model.Physics.Entity.Mobs.Enemy;
import Model.Physics.World.World;

import java.io.IOException;
import java.util.Map;

/**
 * Created by jardu on 1/14/2017.
 */
final class SpawnParser {
    public static void spawnEntity(Map<String, String> parameters, World world){
        int x = ParserUtils.getX(parameters);
        int y = ParserUtils.getY(parameters);

        Enemy.Type enemyType = getEnemyType(parameters.get("type"));

        world.addObject(new Enemy(x, y, enemyType));
    }

    private static Enemy.Type getEnemyType(String type){
        for(Enemy.Type enemyType : Enemy.Type.values()){
            if(enemyType.toString().toLowerCase().equals(type.toLowerCase())){
                return enemyType;
            }
        }
        ProjectUnknownProperties.raiseException(new IOException("Invalid Mob Type"));
        return null; //yuy good compiler
    }
}
