package Model.Physics.World;

import Control.ProjectUnknownProperties;
import Model.Physics.Block.BlockType;
import Model.Physics.Block.SolidTerrainBlock;
import Model.Physics.Entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Amasso on 02.01.2017.
 */
public class World extends AbstractWorld{

    private Player player;

    public World(Path path, Player player, ProjectUnknownProperties projectUnknownProperties){
        super(-20.00,projectUnknownProperties);
        try {
            List<String> lines = Files.readAllLines(path);
            this.player = player;
            addObject(player);
            createWorld(lines);
            focusWithoutScrolling(player);
        } catch (IOException e) {
            System.out.println("Error: World doesn't exist...");
        }
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
                    player.setX(x);
                    player.setY(y);
            }

        }
    }
}
