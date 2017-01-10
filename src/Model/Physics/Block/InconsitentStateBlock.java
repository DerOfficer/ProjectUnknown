package Model.Physics.Block;

import java.awt.*;

/**
 * Created by jardu on 1/10/2017.
 */
public abstract class InconsitentStateBlock extends SolidTerrainBlock {

    private boolean state;

    public InconsitentStateBlock(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public InconsitentStateBlock(int x, int y, BlockType blockType) {
        super(x, y, blockType);
    }

    public InconsitentStateBlock(int x, int y, int width, int height, Color topColor, Color innerColor) {
        super(x, y, width, height, topColor, innerColor);
    }

    public void toggleSolidity(){
        state = !state;
    }

    @Override
    public boolean isSolid(){
        return state;
    }
}
