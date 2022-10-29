package uet.oop.bomberman.bom;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Explosion extends Entity {
     private  int time = 20 ;
    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (time > 0) {
            time--;
        } else {
            this.remove();
        }
    }
}
