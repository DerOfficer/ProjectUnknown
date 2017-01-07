package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.SolidTerrainBlock;
import Model.Physics.Entity.Player;
import View.DrawingPanel;
import View.MainFrame;

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

    public World(Path path, Player player, ProjectUnknownProperties projectUnknownProperties){
        super(20.00,projectUnknownProperties);
        try {
            List<String> lines = Files.readAllLines(path);
            createWorld(lines);
        } catch (IOException e) {
            System.out.println("Error: World doesn't exist...");
        }
        this.player = player;
        addObject(player);
        focusWithoutScrolling(player);
        player.setX((int) spawnPoint.getX());
        player.setY((int) spawnPoint.getY());
        gui = new GraphicalUserInterface(player, projectUnknownProperties);
        projectUnknownProperties.getFrame().setForegroundPanel(gui);

    }

    private void createWorld(List<String> lines) {
        for(String line: lines){
            String[] values = line.split(" ");
            switch (values[0]){
                case "BLOCK":
                    BlockType blockType = BlockType.valueOf(values[1]);
                    int x = Integer.parseInt(values[2]);
                    int y = Integer.parseInt(values[3]);
                    addObject(new SolidTerrainBlock(x,y,blockType));
                    break;
                case "PLAYER":
                    x = Integer.parseInt(values[1]);
                    y = Integer.parseInt(values[2]);
                    spawnPoint = new Point(x,y);
            }

        }
    }

    private class GraphicalUserInterface extends DrawingPanel{

        private Dimension healthbar_dimension;
        private Player player;
        private Rectangle rectangle;

        public GraphicalUserInterface(Player player,ProjectUnknownProperties properties) {
            super(properties);
            healthbar_dimension = new Dimension((int) (screenWidth*0.7), (int)(screenHeight*0.05));
            rectangle = new Rectangle((int)((screenWidth/2)-(healthbar_dimension.getWidth()/2)),(int)(screenHeight*0.9),(int)(healthbar_dimension.getWidth()*player.getHealthInPercent()),(int)(healthbar_dimension.getHeight()));
        }

        @Override
        public void paintComponent(Graphics g){
            g.setColor(Color.green);
            Graphics2D g2d = (Graphics2D) g;
            g2d.fill(rectangle);
        }
    }
}
