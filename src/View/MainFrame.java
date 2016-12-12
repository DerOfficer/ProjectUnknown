package View;

import Control.ProjectUnknownProperties;
import Model.Settings;
import Model.Start;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Jean-Pierre on 15.11.2016.
 */
public class MainFrame extends JFrame {

    // Attribute

    // Referenzen
    private DrawingPanel activePanel;
    private ArrayList<DrawingPanel> panels;



    private Start start;
    private Settings settings;

    /**
     * Konstruktor
     * @param name Der Titel des Fensters
     * @param x Die obere linke x-Koordinate des Fensters bzgl. des Bildschirms
     * @param y Die obere linke y-Koordinaite des Fensters bzgl. des Bildschirms
     * @param width Die Breite des Fensters
     * @param height Die Höhe des Fensters
     */
    public MainFrame(String name, int x, int y, int width, int height, ProjectUnknownProperties pup) {
        panels = new ArrayList<>();
        activePanel = new DrawingPanel(pup);
        panels.add(activePanel);

        start = new Start(pup);
        settings = new Settings(pup);

        add(activePanel);
        addKeyListener(activePanel);
        setLocation(x,y);
        setSize(width,height);
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    /**
     * Liefert das aktuell vom DrawWindow angezeigte DrawingBoard zur�ck
     * @return Das aktuelle DrawingBoard
     */
    public DrawingPanel getActiveDrawingPanel(){
        return activePanel;
    }

    /**
     * F�gt dem DrawWindow ein neues DrawingBoard hinzu. Dieses wird nicht zum
     * aktuellen DrawingBoard!
     */
    public void addNewDrawingPanel(DrawingPanel p){
        panels.clear();
        panels.add(p);
        setActiveDrawingPanel(panels.size()-1);
    }

    /**
     * �ndert das aktuell vom DrawWindow gezeigte DrawingBoard.
     * @param index Der Index des neuen zu zeigenden DrawingBoards (angefangen bei 0).
     */
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
