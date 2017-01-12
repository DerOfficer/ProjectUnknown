package View;

import Control.ProjectUnknownProperties;
import Model.Managing.KeyManager;
import Model.UI.Screen.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements MouseListener {

    private ProjectUnknownProperties properties;

    private JLayeredPane contentPane;

    private DrawingPanel background;
    private DrawingPanel content;
    private DrawingPanel foreground;

    private Start start;
    private Settings settings;
    private LevelSelect levelSelect;
    private DefaultBackground defaultBackground;
    private StaticImageBackgroundPanel levelSelectBackground;
    private WorldEditor worldEditor;

    public MainFrame(String name, int x, int y, int width, int height, ProjectUnknownProperties properties) throws IOException {
        this.properties = properties;

        setLocation(x,y);
        setSize(width,height);

        start = new Start(properties);
        settings = new Settings(properties);
        levelSelect = new LevelSelect(properties);
        defaultBackground = new DefaultBackground(properties);
        levelSelectBackground = new StaticImageBackgroundPanel(properties, ImageIO.read(new File("Images/background.png")));
        worldEditor = new WorldEditor(properties);

        contentPane = new JLayeredPane();
        contentPane.setSize(getSize());
        contentPane.setLayout(null);

        setContentPane(contentPane);

        setBackgroundPanel(new DefaultBackground(properties));
        setContentPanel(start);
        //a dummy panel to make the notification area work
        setForegroundPanel(new DrawingPanel(properties));

        addKeyListener(KeyManager.getInstance());
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public void setContentPanel(DrawingPanel p){
        if(p == null)
            throw new NullPointerException();
        p.setSize(getSize());
        if(content != null){
            contentPane.remove(content);
            removeKeyListener(content);
        }
        content = p;
        contentPane.add(p);
        contentPane.setLayer(p, 1);
        addKeyListener(p);
        repaint();
        revalidate();
    }

    public void setBackgroundPanel(DrawingPanel p){
        p.setSize(getSize());
        if(background != null){
            contentPane.remove(background);
        }
        background = p;
        if(p != null) {
            contentPane.add(p);
            contentPane.setLayer(p, 0);
        }
        repaint();
        revalidate();
    }

    public void setForegroundPanel(DrawingPanel p){
        p.setSize(getSize());
        if(foreground != null){
            contentPane.remove(foreground);
            removeKeyListener(foreground);
            foreground.removeObject(properties.getNotificationArea());
            foreground.removeMouseListener(this);
        }
        foreground = p;
        if(p != null) {
            contentPane.add(p);
            contentPane.setLayer(p, 2);
            p.addObject(properties.getNotificationArea());
            p.addMouseListener(this);
        }
        addKeyListener(p);
        repaint();
        revalidate();
    }

    public Start getStart() {
        return start;
    }

    public Settings getSettings() {
        return settings;
    }

    public LevelSelect getLevelSelect(){return levelSelect;}

    public DefaultBackground getDefaultBackground(){
        return defaultBackground;
    }

    public StaticImageBackgroundPanel getLevelSelectBackground(){
        return levelSelectBackground;
    }

    public WorldEditor getWorldEditor() {
        return worldEditor;
    }

    //Since we have a foreground panel, its impossible for the contentpanel to get click events. Thus we need to delegate them form the foreground panel
    @Override
    public void mouseClicked(MouseEvent e) {
        content.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        content.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        content.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        content.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        content.mouseExited(e);
    }
}
