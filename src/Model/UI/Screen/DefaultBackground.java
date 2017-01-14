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
        BufferedImage starSprite = SpriteManager.SPRITES[MISC][MISC_STAR];

        Graphics g = backgroundImg.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);

        int starCount = screenWidth / 120;
        for (int i = 0; i < starCount; i++) {
            int x = (screenWidth / starCount) * i;
            int y = (int) (screenHeight * Math.random());
            g.drawImage(starSprite, x, y, null);
        }
        return backgroundImg;
    }

}
