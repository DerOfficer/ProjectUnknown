package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import View.DrawingPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Amasso on 03.01.2017.
 */
public class WorldEditor extends DrawingPanel implements KeyListener {
    private int camX,camY;

    public WorldEditor(ProjectUnknownProperties properties) {
        super(properties);
        camX = 0;
        camY = 0;

    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.translate(camX,camY);
            makeGrid(g);
        g.translate(-camX,-camY);
    }

    private void makeGrid(Graphics g) {
        for (int i = 0; i < (int)(screenHeight/50); i++) {
            g.drawLine(camX , camY+i*50 , camX+screenWidth, camY+i*50);
        }
        for (int i = 0; i < (int)(screenWidth/50); i++) {
            g.drawLine(camX+i*50 , camY , camX+i*50 ,camY+screenHeight);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            camX = camX + 50;
        }

        if (key == KeyEvent.VK_RIGHT) {
            camX = camX - 50;
        }

        if (key == KeyEvent.VK_UP) {
            camY = camY - 50;
        }

        if (key == KeyEvent.VK_DOWN) {
            camY = camY + 50;
        }
    }


}
