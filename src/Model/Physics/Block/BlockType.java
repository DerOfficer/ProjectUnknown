package Model.Physics.Block;

import java.awt.*;

/**
 * Created by Amasso on 02.01.2017.
 */
public enum BlockType {
    EARTH(),
    STONE(),
    STONE_BRICK();

    BlockType(){

    }

    public Color getColor(){
        switch (this){
            case EARTH: return new Color(118, 54, 33);
            case STONE: return new Color(149, 149, 149);
            case STONE_BRICK: return new Color(50, 50, 49);
            default: return new Color(0,0,0);
        }
    }
}
