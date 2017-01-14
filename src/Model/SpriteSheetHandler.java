package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Amasso on 17.12.2016.
 */

public class SpriteSheetHandler {
    private int[] gaps;

    private BufferedImage image;
    private BufferedImage[] sprites;

    private Dimension[] dimensions;

    public SpriteSheetHandler(int[] gaps, BufferedImage image) {
        this.gaps = gaps;
        this.image = image;

        subSpriteImage();
    }

    /*public SpriteSheetHandler(Dimension[] dimensions, BufferedImage image) {
        this.image = image;
        this.dimensions = dimensions;

        subExactSpriteImage();
    }*/

    /*private void subExactSpriteImage() {
        if (image != null) {
            sprites = new BufferedImage[dimensions.length];
            int temp = 0;
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = image.getSubimage(temp, 0, dimensions[i].width, dimensions[i].height);
                temp = temp + dimensions[i].width;
            }
        }
    }*/

    private void subSpriteImage() {
        if (image != null) {
            sprites = new BufferedImage[gaps.length];
            int temp = 0;
            for (int i = 1; i < sprites.length; i++) {
                temp = temp + gaps[i - 1];
                sprites[i] = image.getSubimage(temp, 0, gaps[i], image.getHeight());
            }
        }
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }
}
