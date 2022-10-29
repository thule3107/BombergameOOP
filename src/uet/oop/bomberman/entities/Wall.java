package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends Entity {
     private  String name = "wall";
    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    public String getName() {
        return name;
    }

    @Override
    public void update() {

    }
}
