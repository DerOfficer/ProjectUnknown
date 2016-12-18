package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.BackgroundRenderer;
import View.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Amasso on 18.12.2016.
 */
public abstract class ScreenPanel  extends DrawingPanel {

    private BackgroundRenderer bgRenderer;


    public ScreenPanel(ProjectUnknownProperties properties) {
        super(properties);
        drawGalaxy();
        addObject(bgRenderer);

    }

    private void drawGalaxy() {
        try {
            BufferedImage img = ImageIO.read(new File("Images/star-50px.png"));
            BufferedImage backgroundImg = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics g = backgroundImg.getGraphics();
            int amount = screenWidth/100;
            for (int i = 0; i < amount; i++) {
                int x = (int) (screenWidth/amount)*i;
                int y = (int) (screenHeight*Math.random());
                g.drawImage(img, x, y, this);
            }
            bgRenderer = new BackgroundRenderer(backgroundImg);
            setBackground(new Color(0, 0, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
