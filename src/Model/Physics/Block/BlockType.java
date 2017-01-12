package Model.Physics.Block;

import Model.Managing.SpriteManager;

import java.awt.image.BufferedImage;

import static Model.Managing.SpriteManager.BLOCK;

/**
 * Created by Amasso on 02.01.2017.
 */
public enum BlockType {
    EARTH("earth.png"),
    GRASS("grass.png"),;
    //TODO Add Teleporter Image!!!
    //TELEPORTER("grass.png");

    private BufferedImage image;

    BlockType(String text){
        image = SpriteManager.SPRITES[BLOCK][ordinal()];
    }

    public BufferedImage getImage() {
        return image;
    }
}
