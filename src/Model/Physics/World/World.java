package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Abstraction.IDrawableObject;
import Model.Parser.WorldExtensionParser;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.Teleporter;
import Model.Physics.Block.InconsistentStateBlock;
import Model.Physics.Entity.Mobs.Enemy;
import Model.Physics.Entity.Player;
import Model.Planet;
import Model.UI.Overlay.GraphicalUserInterface;
import View.DrawingPanel;
import View.StaticDrawingPanel;
import com.Physics2D.PhysicsObject;
import com.Physics2D.event.MovementEvent;
import com.SideScroller.SideScrollingPhysicsWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created and edited by Oussama and Patrick on 03.01.2017.
 */
public class World extends SideScrollingPhysicsWorld{

    public static final int PIXEL_TO_METER = 50;

    private Player player;
    private Point spawnPoint;

    private GraphicalUserInterface gui;

    private LevelRenderer renderer;

    private ProjectUnknownProperties properties;

    /**
     * constructs a new world object dependent on planet and the world file
     * @param path world file
     * @param properties
     * @param p selected Planet
     */
    public World(Path path, ProjectUnknownProperties properties, Planet p) {
        super(p.getGravity() * PIXEL_TO_METER);

        this.properties = properties;
        this.renderer = new LevelRenderer(properties);
        this.player = new Player(properties, properties.getLevel());
        this.gui = new GraphicalUserInterface(player, properties);

        try {
            createWorld(Files.readAllLines(path));
            createEnemies(p);
        } catch (IOException e) {
            ProjectUnknownProperties.raiseException(e);
        }

        player.setPosition(spawnPoint.getX(), spawnPoint.getY());

        focusWithoutScrolling(player);
        addObject(player);

        properties.getFrame().setForegroundPanel(gui);

    }

    @Override
    public void addObject(PhysicsObject o) {
        super.addObject(o);
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.scheduleAddObject(drawableObject);
        }
        o.addMovementListener(this);
    }

    @Override
    public void removeObject(PhysicsObject o){
        if(o instanceof IDrawableObject){
            IDrawableObject drawableObject = (IDrawableObject)o;
            renderer.scheduleRemoveObject(drawableObject);
        }
        o.removeMovementListener(this);
        SwingUtilities.invokeLater(() -> super.removeObject(o));
    }

    @Override
    public void onMovement(MovementEvent event){
        super.onMovement(event);
        renderer.forceRepaint();
    }

    /**
     * interpret world file and creates the world
     * @param lines
     */
    private void createWorld(List<String> lines) {
        Teleporter tempTeleporter = null;
        for(String line : lines){
            if(line.equals("stardust .world extension")){
                WorldExtensionParser.parse(lines.subList(lines.indexOf(line), lines.size()), this);
                break;
            }else {
                String[] values = line.split(" ");
                if (tempTeleporter == null) {
                    switch (values[0]) {
                        case "BLOCK":
                            BlockType blockType = BlockType.valueOf(values[1]);
                            int x = Integer.parseInt(values[2]);
                            int y = Integer.parseInt(values[3]);
                            addObject(new InconsistentStateBlock(x, y, blockType));
                            break;
                        case "PLAYER":
                            x = Integer.parseInt(values[1]);
                            y = Integer.parseInt(values[2]);
                            spawnPoint = new Point(x, y);
                            break;
                        case "TP1":
                            x = Integer.parseInt(values[2]);
                            y = Integer.parseInt(values[3]);
                            tempTeleporter = new Teleporter(properties, x, y);
                            addObject(tempTeleporter);
                            break;
                    }
                } else {
                    if (values[0].equals("TP2")) {
                        int x = Integer.parseInt(values[2]);
                        int y = Integer.parseInt(values[3]);
                        Teleporter temp = new Teleporter(properties, x, y);
                        temp.link(tempTeleporter);
                        tempTeleporter.link(temp);
                        addObject(temp);
                        tempTeleporter = null;
                    }
                }
            }
        }
    }

    private void createEnemies(Planet planet){
        switch (planet){
            case MERCURY:
                break;
            case VENUS:
                break;
            case EARTH:
                addObject(new Enemy(6700,-6450, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(6700,-6450, Enemy.Type.MEGA_ZOMBIE,properties));
                addObject(new Enemy(4800,1050, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(4300,-1650, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(5000,-1850, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(4250,-2750, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(-2450,-1000, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(550,-200, Enemy.Type.ZOMBIE,properties));
                addObject(new Enemy(1900,0, Enemy.Type.ZOMBIE,properties));
                break;
            case MARS:
                break;
            case JUPITER:
                break;
            case SATURN:
                break;
            case URANUS:
                break;
            case NEPTUNE:
                break;
        }
    }

    public Player getPlayer(){
        return player;
    }

    public LevelRenderer getRenderer() {
        return renderer;
    }

    private class LevelRenderer extends StaticDrawingPanel {

        @Override
        protected Point getRenderingOffset(){
            return new Point(-getRendererXOffset(), -getRendererYOffset());
        }

        public LevelRenderer(ProjectUnknownProperties properties) {
            super(properties);

            setFocusYOffset(screenHeight / 2 - 50);
            setFocusXOffset(screenWidth / 2 - 10);
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
