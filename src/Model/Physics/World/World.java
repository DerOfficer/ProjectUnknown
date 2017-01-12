package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.InconsitentStateBlock;
import Model.Physics.Block.Teleporter;
import Model.Physics.Entity.Mobs.Enemy;
import Model.Physics.Entity.Player;
import Model.Planet;
import Model.UI.Overlay.GraphicalUserInterface;
import View.DrawingPanel;
import View.StaticDrawingPanel;
import com.Physics2D.PhysicsObject;
import com.Physics2D.event.MovementEvent;
import com.SideScroller.SideScrollingPhysicsWorld;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class World extends SideScrollingPhysicsWorld{

    public static final int PIXEL_TO_METER = 50;

    private Player player;
    private Point spawnPoint;

    private GraphicalUserInterface gui;

    private LevelRenderer renderer;

    private ProjectUnknownProperties properties;

    public World(Path path, ProjectUnknownProperties properties, Planet p) {
        super(p.getGravity() * PIXEL_TO_METER);
        this.properties = properties;
        renderer = new LevelRenderer(properties);
        try {
            List<String> lines = Files.readAllLines(path);
            createWorld(lines);
            player = new Player(properties);
        } catch (IOException e) {
            ProjectUnknownProperties.raiseException(e);
        }
        //player.setPosition(spawnPoint.getX(), spawnPoint.getY());
        setFocusYOffset((int)(ProjectUnknownProperties.getScreenDimension().getHeight()/2)-50);
        setFocusXOffset((int)(ProjectUnknownProperties.getScreenDimension().getWidth()/2)-10);
        gui = new GraphicalUserInterface(player, properties);
        properties.getFrame().setForegroundPanel(gui);
        addObject(new Enemy(600,0, Enemy.Type.ZOMBIE,properties));
        focusWithoutScrolling(player);
        addObject(player);
    }

    @Override
    public void addObject(PhysicsObject o) {
        super.addObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.addObject(drawableObject);
        }
        o.addMovementListener(this);
    }

    @Override
    public void removeObject(PhysicsObject o){
        super.removeObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.scheduleRemoveObject(drawableObject);
        }
        o.removeMovementListener(this);
    }

    @Override
    public void onMovement(MovementEvent event){
        super.onMovement(event);
        renderer.forceRepaint();
    }

    public LevelRenderer getRenderer() {
        return renderer;
    }

    private void createWorld(List<String> lines) {
        Teleporter tempTeleporter = null;
        for(String line: lines){
            String[] values = line.split(" ");
            if(tempTeleporter == null){
                switch (values[0]) {
                    case "BLOCK":
                        BlockType blockType = BlockType.valueOf(values[1]);
                        int x = Integer.parseInt(values[2]);
                        int y = Integer.parseInt(values[3]);
                        addObject(new InconsitentStateBlock(x, y, blockType));
                        break;
                    case "PLAYER":
                        x = Integer.parseInt(values[1]);
                        y = Integer.parseInt(values[2]);
                        spawnPoint = new Point(x, y);
                    case "TP1":
                        blockType = BlockType.valueOf(values[1]);
                        x = Integer.parseInt(values[2]);
                        y = Integer.parseInt(values[3]);
                        Teleporter temp = new Teleporter(properties, x, y);
                        addObject(temp);
                        tempTeleporter = temp;
                        break;
                }
            }else{
                if(values[0].equals("TP2")){
                    BlockType blockType = BlockType.valueOf(values[1]);
                    int x = Integer.parseInt(values[2]);
                    int y = Integer.parseInt(values[3]);
                    Teleporter temp = new Teleporter(properties,x, y);
                    temp.link(tempTeleporter);
                    tempTeleporter.link(temp);
                    addObject(temp);
                    tempTeleporter = null;
                    break;
                }
            }
        }
    }

    public Player getPlayer(){
        return player;
    }
    private class LevelRenderer extends StaticDrawingPanel {

        @Override
        protected Point getRenderingOffset(){
            return new Point(-getRendererXOffset(), -getRendererYOffset());
        }

        public LevelRenderer(ProjectUnknownProperties properties) {
            super(properties);
        }

        @Override
        public void keyPressed(KeyEvent event){
            super.keyPressed(event);
            if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
                properties.getFrame().setContentPanel(properties.getFrame().getLevelSelect());
                properties.getFrame().setForegroundPanel(new DrawingPanel(properties));
            }
        }
    }
}
