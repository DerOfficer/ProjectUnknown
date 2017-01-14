package Model.Physics.Block;


import Model.Abstraction.IDrawableObject;
import Model.Managing.SpriteManager;
import Model.Physics.Entity.Mobs.Enemy;

import java.awt.*;

import static Model.Managing.SpriteManager.BLOCK;
import static Model.Managing.SpriteManager.BLOCK_STONE_BRICK;

/**
 * Created by Max on 14.01.2017.
 */
public class Spawner extends Block implements IDrawableObject{

    private long timer = 0;
    private Enemy.Type enemy;


    public Spawner(int x, int y, String id, Enemy.Type enemyType){
        super(x,y,BlockType.CRYSTAL_STONE_BRICK,id);
        this.enemy = enemyType;
    }

    @Override
    public void draw() {
        Graphics2D g = canvas.getPencil();
        g.drawImage(SpriteManager.SPRITES[BLOCK][BLOCK_STONE_BRICK], (int) getX(), (int) getY(), null);
    }

    @Override
    public void update(long dt) {
        timer = timer + dt;
        if(timer == 10){
           // getWorld().addObject();
            getWorld().addObject(new Enemy((int)getX(), (int)getY(), enemy));
            timer = 0;
        }
    }

}
