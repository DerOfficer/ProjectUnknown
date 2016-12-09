package View;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Abstraction.IInteractableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class DrawingPanel extends JPanel implements ActionListener, KeyListener, MouseListener, ICanvas {

    //Attribute
    private long lastLoop, elapsedTime;

    private boolean graphicsLock;

    // Referenzen
    private ArrayList<IDrawableObject> drawableObjects;

    private Graphics2D graphics;
    /**
     * Konstruktor
     */
    public DrawingPanel(){
        super();
        addMouseListener(this);
        setDoubleBuffered(true);

        drawableObjects = new ArrayList<>();
        lastLoop = System.nanoTime();
        javax.swing.Timer timer = new javax.swing.Timer(17, this);
        timer.start();

    }

    /**
     * Zeichnen aller registrierten Objekte
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        elapsedTime = System.nanoTime() - lastLoop;
        lastLoop = System.nanoTime();
        int dt = (int) ((elapsedTime / 1000000L)+0.5);
        if ( dt == 0 ) dt = 1;
        graphics = (Graphics2D)g;
        graphicsLock = true;
        for(IDrawableObject tempDO : drawableObjects){
            tempDO.draw();
            tempDO.update((double)dt/1000);
        }
        graphicsLock = false;
    }

    /**
     * Diese Methode fügt ein neues Objekt zum Zeichnen hinzu. Die
     * Klasse des Objekts muss mindestens das Interface IDrawableObject implementieren.
     * @param d Das ab sofort zu zeichnende Objekt
     */
    @Override
    public void addObject(IDrawableObject d){
        drawableObjects.add(d);
        d.provideCanvas(this);
    }

    /**
     * Diese Methode entfernt ein Objekt aus der Menge der zu zeichnenden Objekte. Die
     * Klasse des Objekts muss mindestens das Interface IDrawableObject implementieren.
     * @param d Das ab sofort nicht mehr zu zeichnende Objekt
     */
    @Override
    public void removeObject(IDrawableObject d){
        drawableObjects.remove(d);
    }

    @Override
    public void scheduleRemoveObject(IDrawableObject object) {
        SwingUtilities.invokeLater(() -> removeObject(object));
    }

    @Override
    public boolean canDraw() {
        return graphicsLock;
    }

    /**
     * Timer-Repaint
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void keyTyped(KeyEvent e){

    }

    /**
     * Weitergabe an Zeichnungsobjekte.
     */
    public void keyPressed(KeyEvent e){
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .forEach(tempDO -> ((IInteractableObject) tempDO).keyPressed(e.getKeyCode()));
    }

    /**
     * Weitergabe an Zeichnungsobjekte.
     */
    public void keyReleased(KeyEvent e){

        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .forEach(tempDO -> ((IInteractableObject)tempDO).keyReleased(e.getKeyCode()));

    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mousePressed(MouseEvent e) {
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .filter(tempDO -> tempDO.getBounds().contains(e.getPoint()))
                .forEach(tempDO -> ((IInteractableObject) tempDO).mousePressed(e));
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseReleased(MouseEvent e) {
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .filter(tempDO -> tempDO.getBounds().contains(e.getPoint()))
                .forEach(tempDO -> ((IInteractableObject)tempDO).mouseReleased(e));
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Unbenutzt bis auf Weiteres
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Weitergabe an Zeichnungsobjekte.
     */
    public void mouseClicked(MouseEvent e) {
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .filter(tempDO -> tempDO.getBounds().contains(e.getPoint()))
                .forEach(tempDO -> ((IInteractableObject)tempDO).mouseClicked(e));
    }

    @Override
    public Graphics2D getPencil() {
        if(!canDraw()){
            throw new UnsupportedOperationException("Graphics Context currently not available");
        }
        return graphics;
    }
}
