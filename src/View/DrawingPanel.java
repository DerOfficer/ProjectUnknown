package View;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Abstraction.IInteractableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements ActionListener, KeyListener, MouseListener, ICanvas {

    private long lastLoop, elapsedTime;
    private boolean graphicsLock;
    private ArrayList<IDrawableObject> drawableObjects;
    private Graphics2D graphics;
    protected ProjectUnknownProperties properties;

    public DrawingPanel(ProjectUnknownProperties properties){
        super();
        addMouseListener(this);
        setDoubleBuffered(true);

        this.properties = properties;

        drawableObjects = new ArrayList<>();
        lastLoop = System.nanoTime();
        javax.swing.Timer timer = new javax.swing.Timer(17, this);
        timer.start();
    }

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

    @Override
    public void addObject(IDrawableObject d){
        drawableObjects.add(d);
        d.provideCanvas(this);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyTyped(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .forEach(tempDO -> ((IInteractableObject) tempDO).keyPressed(e.getKeyCode()));
    }

    public void keyReleased(KeyEvent e){

        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .forEach(tempDO -> ((IInteractableObject)tempDO).keyReleased(e.getKeyCode()));

    }

    public void mousePressed(MouseEvent e) {
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .filter(tempDO -> tempDO.getBounds().contains(e.getPoint()))
                .forEach(tempDO -> ((IInteractableObject) tempDO).mousePressed(e));
    }

    public void mouseReleased(MouseEvent e) {
        drawableObjects.stream()
                .filter(tempDO -> tempDO instanceof IInteractableObject)
                .filter(tempDO -> tempDO.getBounds().contains(e.getPoint()))
                .forEach(tempDO -> ((IInteractableObject)tempDO).mouseReleased(e));
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

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
