package View;

import Control.ProjectUnknownProperties;
import Model.KeyManager;
import Model.UI.Screen.LevelSelect;
import Model.UI.Screen.Settings;
import Model.UI.Screen.Start;
import javax.swing.*;

public class MainFrame extends JFrame {

    private DrawingPanel currentPanel;

    //private DrawingPanel activePanel;
    //private ArrayList<DrawingPanel> panels;
    private Start start;
    private Settings settings;
    private LevelSelect levelSelect;

    public MainFrame(String name, int x, int y, int width, int height, ProjectUnknownProperties properties) {
        //panels = new ArrayList<>();
        //activePanel = new DrawingPanel(properties);
        start = new Start(properties);
        settings = new Settings(properties);
        levelSelect = new LevelSelect(properties);
        //panels.add(activePanel);

        addKeyListener(KeyManager.getInstance());

        //add(activePanel);
        //addKeyListener(activePanel);
        setLocation(x,y);
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    /*public DrawingPanel getActiveDrawingPanel(){
        return currentPanel;
    }*/

    public void setDrawingPanel(DrawingPanel p){
        if(p == null)
            throw new NullPointerException();
        registerDrawingPanel(p);
        if(currentPanel != null)
            unregisterDrawingPanel(currentPanel);
        currentPanel = p;
        revalidate();
        /*panels.clear();
        panels.add(p);
        setActiveDrawingPanel(panels.size()-1);*/
    }

    private void unregisterDrawingPanel(DrawingPanel p){
        remove(p);
        removeKeyListener(p);
        //removeMouseListener(p);
    }

    private void registerDrawingPanel(DrawingPanel p){
        add(p);
        addKeyListener(p);
        //addMouseListener(p);
    }

    /*public void setActiveDrawingPanel(int index){
        if (index < panels.size()){
            remove(activePanel);
            removeKeyListener(activePanel);
            activePanel = panels.get(index);
            add(activePanel);
            addKeyListener(activePanel);
            revalidate();
        }
    }*/

    public Start getStart() {
        return start;
    }

    public Settings getSettings() {
        return settings;
    }

    public LevelSelect getLevelSelect(){return levelSelect;}
}
