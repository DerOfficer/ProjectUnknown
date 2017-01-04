package Model.Physics.Block;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by Amasso on 02.01.2017.
 */
public enum BlockType {
    EARTH(new Color(118, 54, 33),"earth.png"),
    GRASS(new Color(10, 149, 34),"grass.png");

    private Color color;
    private BufferedImage image;


    BlockType(Color color,String text){
        this.color = color;
        try {
            this.image = ImageIO.read(new File("Images/Blocks/"+text));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Color getColor(){
        return color;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
