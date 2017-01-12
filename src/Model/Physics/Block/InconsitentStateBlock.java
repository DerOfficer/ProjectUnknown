package Model.Physics.Block;

import java.awt.*;

/**
 * Created by jardu on 1/10/2017.
 */
public class InconsitentStateBlock extends SolidTerrainBlock {

    private boolean state = true;

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

    @Override
    public void draw(){
        super.draw();
        if(!isSolid()) {
            Graphics2D g = canvas.getPencil();
            g.setColor(new Color(0, 0, 0, 100));
            g.fill(getBounds());
        }
    }
}
