package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Grass extends Entity {
private String name = "grass";
    public Grass(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update() {

    }
}
