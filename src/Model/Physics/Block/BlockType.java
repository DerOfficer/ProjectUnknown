package Model.Physics.Block;

import Model.Managing.SpriteManager;

import java.awt.image.BufferedImage;

import static Model.Managing.SpriteManager.BLOCK;

/**
 * BlockType contains every block type with his texture
 * Created by Oussama on 02.01.2017.
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
