package Model.Physics.Block;

import Model.Abstraction.ICanvas;
import Model.Abstraction.IDrawableObject;
import com.Physics2D.PhysicsObject;
import jdk.nashorn.internal.ir.Block;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Amasso on 26.12.2016.
 */
public class SolidTerrainBlock extends PhysicsObject implements IDrawableObject {


    private ICanvas canvas;
    private Color topColor, innerColor;
    private BufferedImage img;
    private BlockType blockType;

    public SolidTerrainBlock(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.topColor = color;
    }

    public SolidTerrainBlock(int x, int y, BlockType blockType){
        super(x,y,50,50);
        this.blockType = blockType;
        img = blockType.getImage();
    }

    public SolidTerrainBlock(int x, int y, int width, int height, Color topColor, Color innerColor) {
        super(x, y, width, height);
        this.topColor = topColor;
        this.innerColor = innerColor;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public double getFrictionConstant() {
        return 0.1;
    }

    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        if(img != null){
            g.drawImage(img,getX(),getY(),null);
        }else {
            g.setColor(topColor);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
            if (innerColor != null) {
                g.setColor(innerColor);
                g.fillRect(getX(), getY() + (int) (getHeight() * 0.05), getWidth(), (int) (getHeight() * 0.95));
            }
        }
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void provideCanvas(ICanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public Shape getBounds() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    public BlockType getBlockType(){
        return blockType;
    }
}
