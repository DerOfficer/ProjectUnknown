package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.SolidTerrainBlock;
import View.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

/**
 * Created by Amasso on 03.01.2017.
 */
public class WorldEditor extends DrawingPanel implements KeyListener {
    private int camX,camY,realX,realY;
    private Point spawnPoint;
    private int indexOfBlockType;
    private ArrayList<SolidTerrainBlock> blocks;

    public WorldEditor(ProjectUnknownProperties properties) {
        super(properties);
        camX = 0;
        camY = 0;
        realX = 0;
        realY = 0;
        indexOfBlockType = 0;
        spawnPoint = new Point(0,0);
        blocks = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.translate(camX,camY);
            for (int i = 0; i < (int)(screenHeight/50)+1; i++) {
                g.drawLine(realX , realY+i*50 , realX+screenWidth, realY+i*50);
            }
            for (int i = 0; i < (int)(screenWidth/50)+1; i++) {
                g.drawLine(realX+i*50 , realY , realX+i*50 ,realY+screenHeight);
            }
            g.drawString("P",(int) spawnPoint.getX()+25,(int) spawnPoint.getY()+25);
            super.paintComponent(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_LEFT) {
            camX = camX + 50;
            realX = realX -50;
        }
        if (k == KeyEvent.VK_RIGHT) {
            camX = camX - 50;
            realX = realX + 50;
        }
        if (k == KeyEvent.VK_UP) {
            camY = camY + 50;
            realY = realY - 50;
        }
        if (k == KeyEvent.VK_DOWN) {
            camY = camY - 50;
            realY = realY + 50;
        }

        if(k == KeyEvent.VK_SPACE){
            indexOfBlockType++;
            if(indexOfBlockType >= BlockType.values().length){
                indexOfBlockType = 0;
            }
        }
        if (e.getKeyChar() == 'd') {
            removeBlock();
        }
        if (e.getKeyChar() == 's'){
            String file = JOptionPane.showInputDialog(properties.getFrame(), "How do you want to name your world?");
            if(file != null){
                saveWorld(file);
            }
        }
        if (e.getKeyChar() == 'a') {
            createBlock();
        }
        if (e.getKeyChar() == 'l') {
            String file = JOptionPane.showInputDialog(properties.getFrame(), "Which world do you want to load?");
            if(file != null){
                loadWorld(file);
            }
        }
        if(e.getKeyChar() == 'p'){
            int x = ((int)((realX+MouseInfo.getPointerInfo().getLocation().x)/50))*50;
            int y = ((int)((realY+MouseInfo.getPointerInfo().getLocation().y)/50))*50;
            spawnPoint.move(x,y);
        }
    }

    private void loadWorld(String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("Worlds/"+file+".world"));
            for (SolidTerrainBlock block:blocks) {
                super.removeObject(block);
            }

            ArrayList<SolidTerrainBlock>blocks = new ArrayList<>();
            for(String line: lines){
                String[] values = line.split(" ");
                switch (values[0]){
                    case "BLOCK":
                        BlockType blockType = BlockType.valueOf(values[1]);
                        int x = Integer.parseInt(values[2]);
                        int y = Integer.parseInt(values[3]);
                        SolidTerrainBlock temp = new SolidTerrainBlock(x,y,blockType);
                        blocks.add(temp);
                        super.addObject(temp);
                        break;
                    case "PLAYER":
                        int xS = Integer.parseInt(values[1]);
                        int yS = Integer.parseInt(values[2]);
                        spawnPoint.move(xS,yS);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createBlock() {
        int x = ((int)((realX+MouseInfo.getPointerInfo().getLocation().x)/50))*50;
        int y = ((int)((realY+MouseInfo.getPointerInfo().getLocation().y)/50))*50;
        boolean isBlockThere = true;
        for(SolidTerrainBlock block: blocks){
            if(block.getX() == x && block.getY() == y){
                isBlockThere = false;
            }
        }
        if (isBlockThere) {
            SolidTerrainBlock temp = new SolidTerrainBlock(x, y, BlockType.values()[indexOfBlockType]);
            blocks.add(temp);
            super.addObject(temp);
        }
    }

    private void removeBlock(){
        int x = ((int)((realX+MouseInfo.getPointerInfo().getLocation().x)/50))*50;
        int y = ((int)((realY+MouseInfo.getPointerInfo().getLocation().y)/50))*50;
        SolidTerrainBlock temp;
        for(SolidTerrainBlock block: blocks){
            if(block.getX() == x && block.getY() == y){
                temp = block;
                blocks.remove(temp);
                super.removeObject(temp);
                break;
            }
        }
    }

    private void saveWorld(String text){
        try{
            PrintWriter writer = new PrintWriter(new File("Worlds/"+text+".world"));
            for(SolidTerrainBlock block: blocks){
                int x = block.getX();
                int y = block.getY();
                writer.println("BLOCK "+block.getBlockType().toString()+" "+x+" "+y);
            }
            writer.println("PLAYER "+(int)spawnPoint.getX()+" "+(int)spawnPoint.getY());
            writer.close();
            properties.getFrame().setContentPanel(properties.getFrame().getStart());
        } catch (IOException e) {

        }
    }
}
