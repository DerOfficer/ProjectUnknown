package Model.Managing;

import Control.ProjectUnknownProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public static final int BACKGROUND = 4;
    public static final int MISC = 5;

    public static final int BLOCK_EARTH = 0;
    public static final int BLOCK_GRASS = 1;
    public static final int BLOCK_STONE_BRICK = 2;
    public static final int BLOCK_CRYSTAL_STONE_BRICK = 3;
    public static final int BLOCK_LEVER_ON = 4;
    public static final int BLOCK_LEVER_OFF = 5;

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

    public static final int MANA_CAST_FIRE_BALL = 0;
    public static final int MANA_CAST_LIGHT_BALL = 1;
    public static final int MANA_CAST_METAL_BALL = 2;
    public static final int MANA_CAST_RAINBOW_BALL = 3;

    public static final int BACKGROUND_LEVEL_SELECT = 0;

    public static final int MISC_LOGO = 0;
    public static final int MISC_STAR = 1;

    @Nullable
    public static final BufferedImage[][] SPRITES = new BufferedImage[][]{
        {readImageSave(Paths.get("Images/Blocks/earth.png")), readImageSave(Paths.get("Images/Blocks/grass.png")), readImageSave(Paths.get("Images/Blocks/stone_brick.png")), readImageSave(Paths.get("Images/Blocks/crystal_stone_brick.png")), readImageSave(Paths.get("Images/Blocks/lever_on.png")), readImageSave(Paths.get("Images/Blocks/lever_off.png"))},
        {readImageSave(Paths.get("Images/Entity/player_spritesheet.png")), readImageSave(Paths.get("Images/Entity/zombie_spritesheet.png"))},
        {readImageSave(Paths.get("Images/Planets/mercury.png")), readImageSave(Paths.get("Images/Planets/venus.png")), readImageSave(Paths.get("Images/Planets/earth.png")), readImageSave(Paths.get("Images/Planets/mars.png")), readImageSave(Paths.get("Images/Planets/jupiter.png")), readImageSave(Paths.get("Images/Planets/saturn.png")), readImageSave(Paths.get("Images/Planets/uranus.png")), readImageSave(Paths.get("Images/Planets/neptune.png"))},
        {readImageSave(Paths.get("Images/ManaCast/fireball.png")), readImageSave(Paths.get("Images/ManaCast/lightball.png")),readImageSave(Paths.get("Images/ManaCast/metalball.png")),readImageSave(Paths.get("Images/ManaCast/rainbowball.png"))},
        {readImageSave(Paths.get("Images/background.png"))},
        {readImageSave(Paths.get("Images/logo.png")), readImageSave(Paths.get("Images/star.png"))}
    };

    private static BufferedImage readImageSave(@NotNull Path path){
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
