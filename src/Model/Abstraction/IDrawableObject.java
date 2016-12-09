package Model.Abstraction;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Interface für Objekte, die nur gezeichnet und programm-steuerbar sein m�ssen.
 */
public interface IDrawableObject {

    void draw();

    void update(double dt);

    void provideCanvas(ICanvas canvas);

    Shape getBounds();
}