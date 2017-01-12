package Model.Managing;

import Control.ProjectUnknownProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jardu on 1/12/2017.
 */
public class SpriteManager {

    public static final int BLOCK = 0;
    public static final int ENTITY = 1;
    public static final int PLANET = 2;
    public static final int MANA_CAST = 3;

    public static final int BLOCK_EARTH = 0;
    public static final int BLOCK_GRASS = 1;
    public static final int BLOCK_STONE_BRICK = 2;
    public static final int BLOCK_CRYSTAL_STONE_BRICK = 3;
    public static final int BLOCK_TELEPORTER = 4;

    public static final int ENTITY_PLAYER = 0;
    public static final int ENTITY_ZOMBIE = 1;

    public static final int PLANET_MERCURY = 0;
    public static final int PLANET_VENUS = 1;
    public static final int PLANET_EARTH = 2;
    public static final int PLANET_MARS = 3;
    public static final int PLANET_JUPITER = 4;
    public static final int PLANET_SATURN = 5;
    public static final int PLANET_URANUS = 6;
    public static final int PLANET_NEPTUNE = 7;

    public static final int MANA_CAST_FIREBALL = 0;
    public static final int MANA_CAST_LIGHTBALL = 1;

    public static final BufferedImage[][] SPRITES = new BufferedImage[][]{
        {readImageSave(Paths.get("Images/Blocks/earth.png")), readImageSave(Paths.get("Images/Blocks/grass.png")), readImageSave(Paths.get("Images/Blocks/stone_brick.png")), readImageSave(Paths.get("Images/Blocks/crystal_stone_brick.png"))},
        {readImageSave(Paths.get("Images/character_sprite.png")), readImageSave(Paths.get("Images/zombie_sprite.png"))},
        {readImageSave(Paths.get("Images/Planets/mercury.png")), readImageSave(Paths.get("Images/Planets/venus.png")), readImageSave(Paths.get("Images/Planets/earth.png")), readImageSave(Paths.get("Images/Planets/mars.png")), readImageSave(Paths.get("Images/Planets/jupiter.png")), readImageSave(Paths.get("Images/Planets/saturn.png")), readImageSave(Paths.get("Images/Planets/uranus.png")), readImageSave(Paths.get("Images/Planets/neptune.png"))},
        {readImageSave(Paths.get("Images/ManaCast/fireball.png")), readImageSave(Paths.get("Images/ManaCast/lightball.png"))},
    };

    private static BufferedImage readImageSave(Path path){
        System.out.println("Reading: "+path);
        try {
            return ImageIO.read(path.toFile());
        } catch (IOException e) {
            ProjectUnknownProperties.raiseException(e);
        }
        //The Compiler is too stupid to notice that we either return something or exit the program, so we need return null here
        return null;
    }
}