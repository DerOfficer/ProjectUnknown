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
    private int camX,camY,realX,realY;

    public WorldEditor(ProjectUnknownProperties properties) {
        super(properties);
        camX = 0;
        camY = 0;
        realX = 0;
        realY = 0;

    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.translate(camX,camY);
            for (int i = 0; i < (int)(screenHeight/50)+1; i++) {
                g.drawLine(realX , realY+i*50 , realX+screenWidth, realY+i*50);
            }
            for (int i = 0; i < (int)(screenWidth/50)+1; i++) {
                g.drawLine(realX+i*50 , realY , realX+i*50 ,realY+screenHeight);
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_LEFT) {
            camX = camX + 50;
            realX = realX -50;
        }
        if (k == KeyEvent.VK_RIGHT) {
            camX = camX - 50;
            realX = realX + 50;
        }
        if (k == KeyEvent.VK_UP) {
            camY = camY + 50;
            realY = realY - 50;
        }
        if (k == KeyEvent.VK_DOWN) {
            camY = camY - 50;
            realY = realY + 50;
        }

        if(e.getKeyChar() == 'a'){

        }
    }


}
