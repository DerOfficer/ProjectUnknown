package Model.UI.Screen;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.SolidTerrainBlock;
import Model.Physics.Block.Teleporter;
import View.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amasso on 03.01.2017.
 */
public class WorldEditor extends DrawingPanel implements KeyListener,MouseListener {
    private int camX,camY,realX,realY;
    private Point spawnPoint;
    private Point pos1,pos2;
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

        this.addMouseListener(this);
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
            if(pos1 != null){
                g.setColor(Color.BLUE);
                g.drawRect((int)pos1.getX(),(int)pos1.getY(),50,50);
            }
            if(pos2 != null){
                g.setColor(Color.BLUE);
                g.drawRect((int)pos2.getX(),(int)pos2.getY(),50,50);
            }
            super.paintComponent(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int x = (((realX+MouseInfo.getPointerInfo().getLocation().x)/50))*50;
        int y = (((realY+MouseInfo.getPointerInfo().getLocation().y)/50))*50;
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
            createBlock(x,y);
        }
        if (e.getKeyChar() == 'l') {
            String file = JOptionPane.showInputDialog(properties.getFrame(), "Which world do you want to load?");
            if(file != null){
                loadWorld(file);
            }
        }
        if(e.getKeyChar() == 'p'){
            spawnPoint.move(x,y);
        }
        if(e.getKeyChar()=='1'){
            pos1 = new Point(x,y);
        }
        if(e.getKeyChar()=='2'){
            pos2 = new Point(x,y);
        }
        if(e.getKeyChar()=='f'){
            fillSpace();
        }
        if(e.getKeyChar() == 't'){
            createTeleporter(x,y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            int x = (((realX+MouseInfo.getPointerInfo().getLocation().x)/50))*50;
            int y = (((realY+MouseInfo.getPointerInfo().getLocation().y)/50))*50;
            createBlock(x,y);
        }
        if(e.getButton() == MouseEvent.BUTTON2){
            indexOfBlockType++;
            if(indexOfBlockType >= BlockType.values().length){
                indexOfBlockType = 0;
            }
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            removeBlock();
        }
    }

    private void loadWorld(String file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("Worlds/"+file+".world"));
            for (SolidTerrainBlock block:blocks) {
                super.removeObject(block);
            }
            blocks = new ArrayList<>();
            boolean tpBlock = false;
            for(String line: lines){
                String[] values = line.split(" ");
                if(!tpBlock) {
                    switch (values[0]) {
                        case "BLOCK":
                            BlockType blockType = BlockType.valueOf(values[1]);
                            int x = Integer.parseInt(values[2]);
                            int y = Integer.parseInt(values[3]);
                            SolidTerrainBlock temp = new SolidTerrainBlock(x, y, blockType);
                            blocks.add(temp);
                            super.addObject(temp);
                            break;
                        case "PLAYER":
                            int xS = Integer.parseInt(values[1]);
                            int yS = Integer.parseInt(values[2]);
                            spawnPoint.move(xS, yS);
                        case "TP1":
                            blockType = BlockType.valueOf(values[1]);
                            x = Integer.parseInt(values[2]);
                            y = Integer.parseInt(values[3]);
                            temp = new Teleporter(properties, x, y);
                            blocks.add(temp);
                            super.addObject(temp);
                            tpBlock = true;
                            break;
                    }
                }else{
                    if(values[0].equals("TP2")){
                        BlockType blockType = BlockType.valueOf(values[1]);
                        int x = Integer.parseInt(values[2]);
                        int y = Integer.parseInt(values[3]);
                        Teleporter temp = new Teleporter(properties,x, y);
                        temp.link((Teleporter)blocks.get(blocks.size()));
                        blocks.add(temp);
                        super.addObject(temp);
                        tpBlock = false;
                        //break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createTeleporter(int x, int y){
        boolean noBlock = true;
        for(SolidTerrainBlock block: blocks){
            if(block.getX() == x && block.getY() == y){
                noBlock = false;
            }
        }
        if(noBlock){
            Teleporter teleporter = new Teleporter(properties,x,y);
            if(blocks.get(blocks.size()-1) instanceof Teleporter){
                Teleporter temp = (Teleporter)blocks.get(blocks.size()-1);
                teleporter.link(temp);
                temp.link(teleporter);
            }
            blocks.add(teleporter);
            super.addObject(teleporter);
        }

    }

    private void createBlock(int x, int y) {
        boolean noBlock = true;
        for(SolidTerrainBlock block: blocks){
            if(block.getX() == x && block.getY() == y){
                noBlock = false;
            }
        }
        if (noBlock) {
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
            for (int i = 0; i < blocks.size(); i++) {
                SolidTerrainBlock block = blocks.get(i);
                int x = (int) block.getX();
                int y = (int) block.getY();
                if (block instanceof Teleporter) {
                    writer.println("TP1 " + block.getBlockType().toString() + " " + x + " " + y);
                    i++;
                    block = blocks.get(i);
                    x = (int) block.getX();
                    y = (int) block.getY();
                    writer.println("TP2 " + block.getBlockType().toString() + " " + x + " " + y);
                } else {
                    writer.println("BLOCK " + block.getBlockType().toString() + " " + x + " " + y);
                }
            }
            writer.println("PLAYER "+(int)spawnPoint.getX()+" "+(int)spawnPoint.getY());
            writer.close();
            properties.getFrame().setContentPanel(properties.getFrame().getStart());
        } catch (IOException e) {

        }
    }

    private void fillSpace(){
        if(pos1 != null && pos2 != null){
            int width = (int) (pos2.getX() - pos1.getX());
            int height = (int) (pos2.getY() - pos1.getY());

            for (int i = 0; i < 1+(height/50); i++) {
                for (int j= 0; j < 1+(width/50); j++) {
                    createBlock((int)pos1.getX()+j*50,(int)pos1.getY()+i*50);
                }
            }
            pos1 = null;
            pos2 = null;
        }
    }
}
