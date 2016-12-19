package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import View.StaticImageBackgroundPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jardu on 12/19/2016.
 */
public class DefaultBackground extends StaticImageBackgroundPanel {
    public DefaultBackground(ProjectUnknownProperties properties) {
        super(properties, null);
        image = constructImage();
        revalidate();
    }

    private BufferedImage constructImage() {
        BufferedImage backgroundImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = backgroundImg.getGraphics();
        g.setColor(Color.BLACK);
        try {
            BufferedImage img = ImageIO.read(new File("Images/star-50px.png"));
            g.fillRect(0, 0, screenWidth, screenHeight);
            int amount = screenWidth / 120;
            for (int i = 0; i < amount; i++) {
                int x = (screenWidth / amount) * i;
                int y = (int) (screenHeight * Math.random());
                g.drawImage(img, x, y, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return backgroundImg;
    }

}
