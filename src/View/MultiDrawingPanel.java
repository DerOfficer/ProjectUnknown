package View;

import Control.ProjectUnknownProperties;
import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jardu on 12/12/2016.
 */
public class MultiDrawingPanel extends DrawingPanel {

    private List<DrawingPanelZLayerWrapper> panels;

    public MultiDrawingPanel(ProjectUnknownProperties properties){
        super(properties);
        panels = new ArrayList<>();
    }

    public void addDrawingPanel(DrawingPanel panel, int zlayer){
        panels.add(new DrawingPanelZLayerWrapper(zlayer, panel));
    }

    public void removeDrawingPanel(DrawingPanel panel){
        for (DrawingPanelZLayerWrapper drawingPanelZLayerWrapper : panels) {
            if(panel == drawingPanelZLayerWrapper.drawingPanel){
                panels.remove(panel);
                break; //maybe this prevents the ConcurrentModificationexception? Needs testing
            }
        }
    }

    private void sort(){
        for (DrawingPanelZLayerWrapper panel : panels) {
            remove(panel.drawingPanel);
        }
        Collections.sort(panels);
        for (DrawingPanelZLayerWrapper panel : panels) {
            add(panel.drawingPanel);
        }
    }

    private class DrawingPanelZLayerWrapper implements Comparable<DrawingPanelZLayerWrapper>{
        private int zLayer;
        private DrawingPanel drawingPanel;

        private DrawingPanelZLayerWrapper(int zLayer, DrawingPanel drawingPanel) {
            this.zLayer = zLayer;
            this.drawingPanel = drawingPanel;
        }

        @Override
        public int compareTo(DrawingPanelZLayerWrapper o) {
            if(o.zLayer > zLayer){
                return 1;
            }else if(o.zLayer < zLayer){
                return -1;
            }
            return 0;
        }
    }
}
