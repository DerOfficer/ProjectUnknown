package Model.Physics.Block;

import Model.Managing.SpriteManager;

import java.awt.image.BufferedImage;

import static Model.Managing.SpriteManager.BLOCK;

/**
 * Created by Amasso on 02.01.2017.
 */
public enum BlockType {
    EARTH,
    GRASS,
    STONE_BRICK,
    CRYSTAL_STONE_BRICK,;

    private BufferedImage image;

    BlockType(){
        image = SpriteManager.SPRITES[BLOCK][ordinal()];
    }

    public BufferedImage getImage() {
        return image;
    }
}
