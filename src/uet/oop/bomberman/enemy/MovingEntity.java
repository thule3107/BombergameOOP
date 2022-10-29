package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovingEntity extends Entity {
    protected int speed = Sprite.SCALED_SIZE;
    public MovingEntity( int x , int y , Image img ) {
        super(x , y , img);
    }

    @Override
    public void update() {

    }

    protected boolean collide(Entity e){
        return (e instanceof Grass || e instanceof MovingEntity);
    }
}
