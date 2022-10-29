package uet.oop.bomberman.bom;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.enemy.MovingEntity;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.Speed;
import uet.oop.bomberman.sounds.Sounds;

import java.util.ArrayList;
import java.util.List;

public class Bom extends Entity {
    private  boolean bomMod = false;
    private  int timeToExplodes = 100;// 100 seconds
    private int xLeft;
    private  int xRight;
    private int yTop;
    private  int yBottom;
    Entity entityLeft;
    Entity entityRight;
    Entity entityUp;
    Entity entityBottom;
    Entity entityLeftPlus;
    Entity entityRightPlus;
    Entity entityUpPlus;
    Entity entityBottomPlus;
    Entity entity_center;
    List<Entity> explosions;


    public Bom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        explosions = new ArrayList<>();
    }

    public boolean isBomMod() {
        return bomMod;
    }

    public void setBomMod(boolean bomMod) {
        this.bomMod = bomMod;
    }

    @Override
    public void update() {
        xLeft = x - Sprite.SCALED_SIZE;// Khi bom nổ vị trí xleft = vị trí x - 2 lần cái chiều dài mặc định của pixel
        xRight = x + Sprite.SCALED_SIZE;
        yTop = y - Sprite.SCALED_SIZE;
        yBottom = y + Sprite.SCALED_SIZE;
        entityLeft = BombermanGame.getEntity(xLeft,y);
        entityLeftPlus = BombermanGame.getEntity(xLeft - Sprite.SCALED_SIZE,y);
        entityRight =  BombermanGame.getEntity(xRight , y);
        entityRightPlus =  BombermanGame.getEntity(xRight+Sprite.SCALED_SIZE , y);
        entityUp =  BombermanGame.getEntity(x , yTop);
        entityUpPlus = BombermanGame.getEntity(x,yTop- Sprite.SCALED_SIZE);
        entityBottom = BombermanGame.getEntity(x , yBottom);
        entityBottomPlus = BombermanGame.getEntity(x , yBottom+ Sprite.SCALED_SIZE);
        entity_center = BombermanGame.getEntity(x,y);
        if (timeToExplodes > 0) {
            timeToExplodes--;
        } else{
            addExplosion();
            BombermanGame.bombExplode(explosions);
            bomExplodes();
        }
        if (timeToExplodes < 20) {
            img = Sprite.bomb_2.getFxImage();
        } else if (timeToExplodes < 70) {
            img = Sprite.bomb_1.getFxImage();
        }

    }
    // function bomExplodes
    private  void bomExplodes(){
        Sounds.play("res/sounds/1.wav");
        if(BombermanGame.isFlameitem()== true){
            if (entityLeft instanceof Brick || entityLeft instanceof MovingEntity) {
                System.out.println("left brick");
                entityLeft.remove();
            }
            if (entityRight instanceof  Brick|| entityRight instanceof MovingEntity) {
                System.out.println("right brick");
                entityRight.remove();
            }
            if (entityUp instanceof  Brick || entityUp instanceof MovingEntity) {
                System.out.println("up brick");
                entityUp.remove();
            }
            if (entityBottom instanceof  Brick || entityBottom instanceof MovingEntity) {
                System.out.println("bottom brick");
                entityBottom.remove();
            }
            if (entityLeftPlus instanceof Brick || entityLeftPlus instanceof MovingEntity) {
                System.out.println("left brick");
                entityLeftPlus.remove();
            }
            if (entityRightPlus instanceof  Brick || entityRightPlus instanceof MovingEntity) {
                System.out.println("right brick");
                entityRightPlus.remove();
            }
            if (entityUpPlus instanceof  Brick || entityUpPlus instanceof MovingEntity) {
                System.out.println("up brick");
                entityUpPlus.remove();
            }
            if (entityBottomPlus instanceof  Brick || entityBottomPlus instanceof MovingEntity) {
                System.out.println("bottom brick");
                entityBottomPlus.remove();
            }
        }else {
            if (entityLeft instanceof Brick || entityLeft instanceof MovingEntity) {
                System.out.println("left brick");
                entityLeft.remove();
            }
            if (entityRight instanceof  Brick|| entityRight instanceof MovingEntity) {
                System.out.println("right brick");
                entityRight.remove();
            }
            if (entityUp instanceof  Brick || entityUp instanceof MovingEntity) {
                System.out.println("up brick");
                entityUp.remove();
            }
            if (entityBottom instanceof  Brick || entityBottom instanceof MovingEntity) {
                System.out.println("bottom brick");
                entityBottom.remove();
            }
        }



    }
    private void addExplosion() {
       if(BombermanGame.isFlameitem() == true){
           if (!(entityLeft instanceof Wall)) {
               System.out.println( " not left wall");
               explosions.add(new Explosion(xLeft / 32 , y / 32 , Sprite.explosion_horizontal2.getFxImage()));
           }
           if (!(entityRight instanceof Wall)) {
               System.out.println("not right wall");
               explosions.add(new Explosion(xRight / 32 , y / 32 , Sprite.explosion_horizontal2.getFxImage()));
           }
           if (!(entityUp instanceof Wall)) {
               System.out.println("not up wall");
               explosions.add(new Explosion(x / 32 , yTop / 32 , Sprite.explosion_vertical2.getFxImage()));
           }
           if (!(entityBottom instanceof Wall)) {
               System.out.println("not bottom wall");
               explosions.add(new Explosion(x / 32 , yBottom / 32 , Sprite.explosion_vertical2.getFxImage()));
           }
           if (!(entityLeftPlus instanceof Wall)) {
               System.out.println( " not left wall");
               explosions.add(new Explosion((xLeft - Sprite.SCALED_SIZE)/ 32 , y / 32 , Sprite.explosion_horizontal2.getFxImage()));
           }
           if (!(entityRightPlus instanceof Wall)) {
               System.out.println("not right wall");
               explosions.add(new Explosion((xRight+Sprite.SCALED_SIZE) / 32 , y / 32 , Sprite.explosion_horizontal2.getFxImage()));
           }
           if (!(entityUpPlus instanceof Wall)) {
               System.out.println("not up wall");
               explosions.add(new Explosion(x / 32 , (yTop-Sprite.SCALED_SIZE) / 32 , Sprite.explosion_vertical2.getFxImage()));
           }
           if (!(entityBottomPlus instanceof Wall)) {
               System.out.println("not bottom wall");
               explosions.add(new Explosion(x / 32 , (yBottom+ Sprite.SCALED_SIZE) / 32 , Sprite.explosion_vertical2.getFxImage()));
           }
           explosions.add(new Explosion(x / 32 , y / 32 , Sprite.bomb_exploded2.getFxImage()));
       }else {
           if (!(entityLeft instanceof Wall)) {
               System.out.println( " not left wall");
               explosions.add(new Explosion(xLeft / 32 , y / 32 , Sprite.explosion_horizontal2.getFxImage()));
           }
           if (!(entityRight instanceof Wall)) {
               System.out.println("not right wall");
               explosions.add(new Explosion(xRight / 32 , y / 32 , Sprite.explosion_horizontal2.getFxImage()));
           }
           if (!(entityUp instanceof Wall)) {
               System.out.println("not up wall");
               explosions.add(new Explosion(x / 32 , yTop / 32 , Sprite.explosion_vertical2.getFxImage()));
           }
           if (!(entityBottom instanceof Wall)) {
               System.out.println("not bottom wall");
               explosions.add(new Explosion(x / 32 , yBottom / 32 , Sprite.explosion_vertical2.getFxImage()));
           }
           explosions.add(new Explosion(x / 32 , y / 32 , Sprite.bomb_exploded2.getFxImage()));
       }
    }
}
