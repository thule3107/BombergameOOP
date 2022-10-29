package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends MovingEntity {
    protected Random random = new Random();
    protected int direction = -1;
    protected int timeCountDown;
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }
    protected int randomDirection(){
        return random.nextInt(4);
    }
}
