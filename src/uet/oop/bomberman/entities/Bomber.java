package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.enemy.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sounds.Sounds;

public class Bomber extends MovingEntity {
    private  String name = "MovingEntity";
    private  int speed = 8;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public String getName() {
        return name;
    }

    private Direction direction = Direction.NONE;
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(isRight()== true ){
            if (this.getSwap() == 1) {
                this.setX(this.getX()+speed);
                this.setImg(Sprite.player_right.getFxImage());
                this.setSwap(2);

                setCount(getCount()+1);

            } else if (this.getSwap() == 2) {
                this.setX(this.getX()+speed);
                this.setImg(Sprite.player_right_1.getFxImage());
                this.setSwap(3);
                setCount(getCount()+1);
            } else if (this.getSwap() == 3) {
                this.setX(this.getX()+speed);
                this.setImg(Sprite.player_right_2.getFxImage());
                this.setSwap(4);
                setCount(getCount()+1);
            } else {
                this.setX(this.getX()+speed);
                this.setImg(Sprite.player_right.getFxImage());
                this.setSwap(1);
                setCount(getCount()+1);
            }
     if(getCount()==4){
         setCount(0);
         setRight(false);
     }

        }
        if(isDown()== true){
            if (this.getSwap() == 1) {
                setY(getY()+speed);
                this.setImg(Sprite.player_down.getFxImage());
                this.setSwap(2);

                setCount(getCount()+1);

            } else if (this.getSwap() == 2) {
                setY(getY()+speed);
                this.setImg(Sprite.player_down_1.getFxImage());
                this.setSwap(3);
                setCount(getCount()+1);
            } else if (this.getSwap() == 3) {
                setY(getY()+speed);
                this.setImg(Sprite.player_down_2.getFxImage());
                this.setSwap(4);
                setCount(getCount()+1);
            } else {
                setY(getY()+speed);
                this.setImg(Sprite.player_down.getFxImage());
                this.setSwap(1);
                setCount(getCount()+1);
            }
            if(getCount()==4){
                setCount(0);
                setDown(false);
            }
        }
        if(isUp()== true){
            if (this.getSwap() == 1) {
                setY(getY()-speed);
                this.setImg(Sprite.player_up.getFxImage());
                this.setSwap(2);

                setCount(getCount()+1);

            } else if (this.getSwap() == 2) {
                setY(getY()-speed);
                this.setImg(Sprite.player_up_1.getFxImage());
                this.setSwap(3);
                setCount(getCount()+1);
            } else if (this.getSwap() == 3) {
                setY(getY()-speed);
                this.setImg(Sprite.player_up_2.getFxImage());
                this.setSwap(4);
                setCount(getCount()+1);
            } else {
                setY(getY()-speed);
                this.setImg(Sprite.player_up.getFxImage());
                this.setSwap(1);
                setCount(getCount()+1);
            }
            if(getCount()==4){
                setCount(0);
                setUp(false);
            }
        }
        if(isLeft()== true){
            if (this.getSwap() == 1) {
                setX(getX()-speed);
                this.setImg(Sprite.player_left.getFxImage());
                this.setSwap(2);

                setCount(getCount()+1);

            } else if (this.getSwap() == 2) {
                setX(getX()-speed);
                this.setImg(Sprite.player_left_1.getFxImage());
                this.setSwap(3);
                setCount(getCount()+1);
            } else if (this.getSwap() == 3) {
                setX(getX()-speed);
                this.setImg(Sprite.player_left_2.getFxImage());
                this.setSwap(4);
                setCount(getCount()+1);
            } else {
                setX(getX()-speed);
                this.setImg(Sprite.player_left.getFxImage());
                this.setSwap(1);
                setCount(getCount()+1);
            }
            if(getCount()==4){
                setCount(0);
                setLeft(false);
            }
        }
    }




    public boolean isCanMove( int a , int b ) {
        Entity entity = BombermanGame.getEntity(a , b);
        if (entity == null) return true;
        return this.collide(entity);
    }



    @Override
    public void remove() {
        super.remove();
        Sounds.play("res/sounds/5.wav");

    }

    public void setDirection( Direction direction ) {

        this.direction = direction;
    }

}
