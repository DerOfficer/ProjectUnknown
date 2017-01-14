package Model.Physics.Block;

import Model.Abstraction.IDrawableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Oussama on 26.12.2016.
 */
public class Block extends AbstractBlock implements IDrawableObject {

    private BufferedImage img;
    private BlockType blockType;

    /**
     * constructs a solid block which can be added to a world. block type defines the texture.
     *
     * @param x
     * @param y
     * @param blockType
     */
    public Block(int x, int y, BlockType blockType, String id) {
        super(x, y, id);
        this.blockType = blockType;
        img = blockType.getImage();
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public double getFrictionConstant() {
        return 0.3;
    }

    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        g.drawImage(img, (int) getX(), (int) getY(), null);
    }

    @Override
    public void update(double dt) {
    }

    public BlockType getBlockType() {
        return blockType;
    }
}
