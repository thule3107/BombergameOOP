package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.bom.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
//    private String name = "MovingEntity";




//    @Override
//    public String getName() {
//        return name;
//    }

    public Balloon(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (timeCountDown > 0) {
            timeCountDown--;
        } else {
            handleMove();
            timeCountDown = 30;
        }
    }

    private void handleMove() {
        direction = this.randomDirection();
        if (direction == 0) { // right
            if (isCanMove(x + speed, y)) {
                x += speed;
                img = Sprite.balloom_right1.getFxImage();
            }
        } else if (direction == 1) { // left
            if (isCanMove(x - speed, y)) {
                x -= speed;
                img = Sprite.balloom_left1.getFxImage();
            }
        } else if (direction == 2) { // up
            if (isCanMove(x, y - speed)) {
                y -= speed;
                img = Sprite.balloom_left1.getFxImage();

            }
        } else if (direction == 3) { // down
            if (isCanMove(x, y + speed)) {
                y += speed;
                img = Sprite.balloom_right1.getFxImage();
            }
        }
    }

    private boolean isCanMove(int x, int y) {
        Entity entity = BombermanGame.getEntity(x, y);
        if (entity instanceof Enemy) return false;
        if (entity == null) return true;
        return this.collide(entity);
    }
}
