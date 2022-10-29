package uet.oop.bomberman.UI;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.bom.Bom;
import uet.oop.bomberman.graphics.Sprite;

public class Help{
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private ImageView background = new ImageView();
    private  ImageView btnBack = new ImageView();
    public void createHelp(Group root){
        Pane pane = new Pane();
        pane.setPrefSize(Sprite.SCALED_SIZE*WIDTH, Sprite.SCALED_SIZE*HEIGHT);
        background = new ImageView(new Image("images/backgroundHelp.png"));
        background.setFitWidth(Sprite.SCALED_SIZE*WIDTH);
        background.setFitHeight(Sprite.SCALED_SIZE*HEIGHT);
        btnBack = new ImageView( new Image("images/button_go-back.png"));
        btnBack.setX(800);
        btnBack.setY(10);
        pane.getChildren().addAll(background,btnBack);
        btnBack.setOnMouseClicked(e-> {
            BombermanGame.getWindow().setScene(BombermanGame.getMenuScene());
        });
        pane.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case ESCAPE:
                    BombermanGame.getWindow().setScene(BombermanGame.getMenuScene());
            }
        });
       root.getChildren().add(pane);
    }

}
