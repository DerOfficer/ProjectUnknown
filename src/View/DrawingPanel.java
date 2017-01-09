package View;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import Model.Abstraction.IInteractableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JComponent implements ActionListener, KeyListener, MouseListener, ICanvas {

    protected long lastLoop, elapsedTime;
    private boolean graphicsLock;
    protected ArrayList<IDrawableObject> drawableObjects;
    private Graphics2D graphics;

    protected ProjectUnknownProperties properties;

    protected static final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    protected static final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    protected Timer timer;

    public DrawingPanel(ProjectUnknownProperties properties){
        super();

        addMouseListener(this);
        setDoubleBuffered(true);

        this.properties = properties;

        drawableObjects = new ArrayList<>();
        lastLoop = System.nanoTime();
        timer = new Timer(30, this);
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
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.translate(getRenderingOffset().getX(), getRenderingOffset().getY());
        graphicsLock = true;
        for(IDrawableObject tempDO : drawableObjects){
            tempDO.draw();
            tempDO.update((double)dt/1000);
        }
        graphicsLock = false;
    }

    protected Point getRenderingOffset(){
        return new Point(0,0);
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
        try {
            drawableObjects.stream()
                    .filter(tempDO -> tempDO instanceof IInteractableObject)
                    .filter(tempDO -> tempDO.getBounds().contains(e.getPoint()))
                    .forEach(tempDO -> ((IInteractableObject) tempDO).mouseClicked(e));
        }catch(Throwable t){}
    }

    @Override
    public Graphics2D getPencil() {
        if(!canDraw()){
            throw new UnsupportedOperationException("Graphics Context currently not available");
        }
        return graphics;
    }
}
