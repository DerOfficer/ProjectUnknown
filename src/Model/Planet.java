package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Amasso on 25.12.2016.
 */
public enum Planet {

    MERCURY("mercury",new Color(0,0,0), new Color(0,0,0), 20.0),
    VENUS("venus", new Color(0,0,0), new Color(0,0,0), 20),
    EARTH("earth", new Color(0,0,0), new Color(0,0,0), 20),
    MARS("mars", new Color(0,0,0), new Color(0,0,0), 20),
    JUPITER("jupiter",new Color(140,130,70), new Color(165,160,95), 24.79),
    SATURN("saturn", new Color(0,0,0), new Color(0,0,0), 20),
    URANUS("uranus", new Color(0,0,0), new Color(0,0,0),20),
    NEPTUNE("neptune", new Color(0,0,0), new Color(0,0,0), 20);

    private Color topColor, innerColor;
    private double gravity;
    private String name;

    Planet(String name, Color topColor, Color innerColor, double gravity) {
        this.topColor = topColor;
        this.innerColor = innerColor;
        this.gravity = gravity;
        this.name = name;
    }

    public Color getTopColor() {
        return topColor;
    }

    public Color getInnerColor() {
        return innerColor;
    }

    public double getGravity() {
        return gravity;
    }

    public String getName(){
        return name;
    }

    public BufferedImage getImage(){
        try {
             return ImageIO.read(new File("Images/Planets/"+this.getName()+".png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
