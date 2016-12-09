package Model.Abstraction;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */

import java.awt.event.MouseEvent;

/**
 * Interface für Objekte, die gezeichnet, programm-steuerbar
 * UND nutzerinteragierbar sein müssen.
 */
public interface IInteractableObject extends IDrawableObject {

    void keyPressed(int key);

    void keyReleased(int key);

    void mouseClicked(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mousePressed(MouseEvent e);
}
