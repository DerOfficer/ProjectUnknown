package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.InconsitentStateBlock;
import Model.Physics.Block.SolidTerrainBlock;
import Model.Physics.Block.Teleporter;
import Model.Physics.Entity.Mobs.Enemy;
import Model.Physics.Entity.Player;
import Model.Planet;
import Model.UI.Overlay.GraphicalUserInterface;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Amasso on 02.01.2017.
 */
public class World extends AbstractWorld{

    private Player player;
    private Point spawnPoint;
    private GraphicalUserInterface gui;
    private ProjectUnknownProperties pUP;

    public World(Path path, ProjectUnknownProperties projectUnknownProperties, Planet p){
        super(p.getGravity(),projectUnknownProperties);
        pUP = projectUnknownProperties;
        try {
            List<String> lines = Files.readAllLines(path);
            createWorld(lines);
            player = new Player(projectUnknownProperties);
        } catch (IOException e) {
            System.err.println("Error: World doesn't exist...");
        }
        player.setX((int) spawnPoint.getX());
        player.setY((int) spawnPoint.getY());
        setFocusYOffset((int)(ProjectUnknownProperties.getScreenDimension().getHeight()/2)-50);
        setFocusXOffset((int)(ProjectUnknownProperties.getScreenDimension().getWidth()/2)-10);
        focusWithoutScrolling(player);
        addObject(player);
        gui = new GraphicalUserInterface(player, projectUnknownProperties);
        projectUnknownProperties.getFrame().setForegroundPanel(gui);

        //addObject(new Enemy(600,0,Enemy.Type.ZOMBIE,projectUnknownProperties));

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
                        Teleporter temp = new Teleporter(pUP, x, y, blockType);
                        addObject(temp);
                        tempTeleporter = temp;
                        break;
                }
            }else{
                if(values[0].equals("TP2")){
                    BlockType blockType = BlockType.valueOf(values[1]);
                    int x = Integer.parseInt(values[2]);
                    int y = Integer.parseInt(values[3]);
                    Teleporter temp = new Teleporter(pUP,x, y, blockType);
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
}
