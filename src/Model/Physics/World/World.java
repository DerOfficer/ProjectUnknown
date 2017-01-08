package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.SolidTerrainBlock;
import Model.Physics.Entity.Player;
import View.DrawingPanel;
import View.MainFrame;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
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
        player.setX((int) spawnPoint.getX());
        player.setY((int) spawnPoint.getY());
        addObject(player);
        setFocusYOffset((int)(ProjectUnknownProperties.getScreenDimension().getHeight()/2)-50);
        setFocusXOffset((int)(ProjectUnknownProperties.getScreenDimension().getWidth()/2)-10);
        focusWithoutScrolling(player);
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

        private Player player;

        private RoundRectangle2D.Double mainBoard;

        //ATTRIBUTE
        private final double WIDTH = screenWidth*0.7;
        private final double HEIGHT = screenHeight*0.15;
        private final double X_POS = screenWidth/2 - WIDTH/2;
        private final double Y_POS = screenHeight*0.8;

        private final Color CIRCLE_COLOR = new Color(25, 12, 12);
        private final Color BACKGROUND_COLOR = new Color(63, 17, 33);
        private final Color MANA_COLOR = new Color(71, 102, 227);
        private final Color HEALTH_COLOR = new Color(68, 153, 30);

        public GraphicalUserInterface(Player player,ProjectUnknownProperties properties) {
            super(properties);
            this.player = player;
            mainBoard = new RoundRectangle2D.Double(X_POS,Y_POS,WIDTH,HEIGHT,HEIGHT/2,HEIGHT/2);
        }

        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            double halfWidth = screenWidth/2;
            double widthCircle = screenHeight*0.1;
            double xOval = halfWidth - widthCircle/2;
            double yOval = Y_POS - widthCircle*0.7;
            g.setColor(CIRCLE_COLOR);
            g2d.fillOval((int)xOval,(int)yOval,(int)widthCircle,(int)widthCircle);
            g.setColor(BACKGROUND_COLOR);
            g2d.fill(mainBoard);

            g.setColor(Color.WHITE);
            String level = String.valueOf(player.getLevel());
            g2d.setFont(properties.getGameFont().deriveFont(30f));
            FontMetrics metrics = g2d.getFontMetrics();
            int stringWidthLevel = metrics.stringWidth(level)/2;
            int stringHeightLevel = metrics.getHeight()/2;
            g2d.drawString(level,(int)(halfWidth-stringWidthLevel),(int)(yOval+widthCircle/2-stringHeightLevel));

            g.setColor(MANA_COLOR);
            double widthMana = WIDTH*0.8;
            double heightMana = HEIGHT*0.3;
            double xMana = halfWidth-widthMana/2;
            double yMana = Y_POS+HEIGHT*0.6;
            RoundRectangle2D rect = new RoundRectangle2D.Double(xMana,yMana,widthMana,heightMana,heightMana,heightMana);
            g2d.fill(rect);
            g.setColor(Color.WHITE);
            String mana = "MANA";
            g2d.setFont(properties.getGameFont().deriveFont(20f));
            metrics = g2d.getFontMetrics();
            int stringWidthMana = metrics.stringWidth(mana)/2;
            int stringHeightMana = metrics.getHeight()/2;
            g2d.drawString(mana,(int)(halfWidth-stringWidthMana),(int)(yMana+heightMana-stringHeightMana));

            g.setColor(HEALTH_COLOR);
            double widthHealth = WIDTH*0.8;
            double heightHealth = HEIGHT*0.4;
            double xHealth = halfWidth-widthHealth/2;
            double yHealth = Y_POS+HEIGHT*0.1;
            rect = new RoundRectangle2D.Double(xHealth,yHealth,widthHealth,heightHealth,heightHealth,heightHealth);
            g2d.fill(rect);
            g.setColor(Color.WHITE);
            String health = "HEALTH";
            g2d.setFont(properties.getGameFont().deriveFont(20f));
            metrics = g2d.getFontMetrics();
            int stringWidthHealth = metrics.stringWidth(health)/2;
            int stringHeightHealth = metrics.getHeight()/2;
            g2d.drawString(health,(int)(halfWidth-stringWidthHealth),(int)(yHealth+heightHealth/2+stringHeightHealth));
        }
    }
}
