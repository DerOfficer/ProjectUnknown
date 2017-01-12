package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Managing.SpriteManager;
import View.StaticImageBackgroundPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Model.Managing.SpriteManager.MISC;
import static Model.Managing.SpriteManager.MISC_STAR;

/**
 * Created by jardu on 12/19/2016.
 */
public class DefaultBackground extends StaticImageBackgroundPanel {
    public DefaultBackground(ProjectUnknownProperties properties) {
        super(properties, null);
        image = constructImage();
    }

    private BufferedImage constructImage() {
        BufferedImage backgroundImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = backgroundImg.getGraphics();
        g.setColor(Color.BLACK);
        BufferedImage img = SpriteManager.SPRITES[MISC][MISC_STAR];
        g.fillRect(0, 0, screenWidth, screenHeight);
        int amount = screenWidth / 120;
        for (int i = 0; i < amount; i++) {
            int x = (screenWidth / amount) * i;
            int y = (int) (screenHeight * Math.random());
            g.drawImage(img, x, y, null);
        }
        return backgroundImg;
    }

}
