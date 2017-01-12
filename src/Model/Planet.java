package Model;

import Model.Managing.SpriteManager;

import java.awt.image.BufferedImage;

import static Model.Managing.SpriteManager.PLANET;

/**
 * Created by Amasso on 25.12.2016.
 */
public enum Planet {

    MERCURY(3.7),
    VENUS(8.87),
    EARTH(9.81),
    MARS(3.69),
    JUPITER(24.79),
    SATURN(10.44),
    URANUS(8.87),
    NEPTUNE(11.15);

    private double gravity;
    private BufferedImage image;

    Planet(double gravity) {
        this.gravity = gravity;
        this.image = SpriteManager.SPRITES[PLANET][ordinal()];
    }

    public double getGravity() {
        return gravity;
    }

    public BufferedImage getImage(){
        return image;
    }

    public static Planet getPlanetByName(String name){
        for(Planet p : Planet.values()){
            if(p.toString().toLowerCase().equals(name)){
                return p;
            }
        }
        return null;
    }
}
