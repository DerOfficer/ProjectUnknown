package Model.Abstraction;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Interface für Objekte, die nur gezeichnet und programm-steuerbar sein m�ssen.
 */
public interface IDrawableObject {

    void draw();

    void update(double dt);

    void provideCanvas(ICanvas canvas);

    @NotNull Shape getBounds();
}