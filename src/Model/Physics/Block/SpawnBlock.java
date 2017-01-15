package Model.Physics.Block;


import Model.Abstraction.IDrawableObject;
import Model.Physics.Entity.Mobs.Enemy;

/**
 * Created by Max on 14.01.2017.
 */
public class SpawnBlock extends Block implements IDrawableObject{

    private double timer = 20;
    private Enemy.Type enemy;


    public SpawnBlock(int x, int y, BlockType blockType, Enemy.Type enemyType){
        super(x,y,blockType,"");
        this.enemy = enemyType;
    }

    @Override
    public void update(double dt) {
        try{
            if ((Math.abs(getWorld().getPlayer().getX() - getX())) <= 500) {
                timer = timer + dt;
                if (timer >= 10) {
                    getWorld().addObject(new Enemy((int) getX(), (int) getY() - 100, enemy));
                    timer = 0;
                }
            }
        }catch(IllegalStateException e){}
    }

    public Enemy.Type getEnemyType(){
        return enemy;
    }

}
