package Model.Abstraction;

import java.awt.*;

/**
 * Interface für Objekte, die nur gezeichnet und programm-steuerbar sein m�ssen.
 */
public interface IDrawableObject {

    void draw();

    void update(double dt);

    void provideCanvas(ICanvas canvas);

    Shape getBounds();
}