package View;

import Control.ProjectUnknownProperties;
import Model.Settings;
import Model.Start;
import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private DrawingPanel activePanel;
    private ArrayList<DrawingPanel> panels;
    private Start start;
    private Settings settings;

    public MainFrame(String name, int x, int y, int width, int height, ProjectUnknownProperties properties) {
        panels = new ArrayList<>();
        activePanel = new DrawingPanel(properties);
        start = new Start(properties);
        settings = new Settings(properties);
        panels.add(activePanel);

        add(activePanel);
        addKeyListener(activePanel);
        setLocation(x,y);
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public DrawingPanel getActiveDrawingPanel(){
        return activePanel;
    }

    public void addNewDrawingPanel(DrawingPanel p){
        panels.clear();
        panels.add(p);
        setActiveDrawingPanel(panels.size()-1);
    }

    public void setActiveDrawingPanel(int index){
        if (index < panels.size()){
            remove(activePanel);
            removeKeyListener(activePanel);
            activePanel = panels.get(index);
            add(activePanel);
            addKeyListener(activePanel);
            revalidate();
        }
    }

    public Start getStart() {
        return start;
    }

    public Settings getSettings() {
        return settings;
    }
}
