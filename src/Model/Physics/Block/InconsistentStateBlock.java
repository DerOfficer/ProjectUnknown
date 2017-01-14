package Model.Physics.Block;

import java.awt.*;

/**
 * Created by jardu on 1/10/2017.
 */
public class InconsistentStateBlock extends Block {

    private boolean state = true;

    public InconsistentStateBlock(int x, int y, BlockType blockType, String id) {
        super(x, y, blockType, id);
    }

    public void toggleSolidity() {
        state = !state;
    }

    @Override
    public boolean isSolid() {
        return state;
    }

    @Override
    public void draw() {
        super.draw();
        if (!isSolid()) {
            Graphics2D g = canvas.getPencil();
            g.setColor(new Color(0, 0, 0, 100));
            g.fill(getBounds());
        }
    }
}
