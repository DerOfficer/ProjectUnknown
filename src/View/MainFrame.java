package View;

import Control.ProjectUnknownProperties;
import Model.KeyManager;
import Model.UI.Screen.DefaultBackground;
import Model.UI.Screen.LevelSelect;
import Model.UI.Screen.Settings;
import Model.UI.Screen.Start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

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

    public MainFrame(String name, int x, int y, int width, int height, ProjectUnknownProperties properties) throws IOException {
        this.properties = properties;

        setLocation(x,y);
        setSize(width,height);

        start = new Start(properties);
        settings = new Settings(properties);
        levelSelect = new LevelSelect(properties);
        defaultBackground = new DefaultBackground(properties);
        levelSelectBackground = new StaticImageBackgroundPanel(properties, ImageIO.read(new File("Images/background.png")));

        contentPane = new JLayeredPane();
        contentPane.setSize(getSize());
        contentPane.setLayout(null);

        setContentPane(contentPane);

        setBackgroundPanel(new DefaultBackground(properties));
        setContentPanel(start);

        addKeyListener(KeyManager.getInstance());
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public void setContentPanel(DrawingPanel p){
        p.addObject(properties.getNotificationArea());
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
        }
        foreground = p;
        if(p != null) {
            contentPane.add(p);
            contentPane.setLayer(p, 2);
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
}
