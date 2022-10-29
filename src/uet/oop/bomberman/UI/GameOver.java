package uet.oop.bomberman.UI;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.bom.Bom;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;



public class GameOver {
     static ImageView background, playAgain, btnBack ;
    private static Text scoreLabel ;
    private  static  int scoreNow ;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static ImageView getPlayAgain() {
        return playAgain;
    }

    public static void setPlayAgain(ImageView playAgain) {
        GameOver.playAgain = playAgain;
    }

    public static int getScoreNow() {
        return scoreNow;
    }

    public static void setScoreNow(int scoreNow) {
        GameOver.scoreNow = scoreNow;
    }

    public static Text getScoreLabel() {
        return scoreLabel;
    }

    public static void setScoreLabel(Text scoreLabel) {
        GameOver.scoreLabel = scoreLabel;
    }

    public  void createGameOverMenu(Group root){
        Pane pane = new Pane();
        pane.setPrefSize(Sprite.SCALED_SIZE*WIDTH, Sprite.SCALED_SIZE*HEIGHT);
        // background
        background= new ImageView("images/gameover.png");
        background.setFitWidth(Sprite.SCALED_SIZE*WIDTH);
        background.setFitHeight(Sprite.SCALED_SIZE*HEIGHT);
        // background
        // btn

        // btn
        // Score label
           scoreLabel = new Text("SCORE : "+ scoreNow);
        scoreLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        scoreLabel.setFill(Color.BLACK);
           scoreLabel.setX(450);
           scoreLabel.setY(350);
        pane.getChildren().addAll(background, scoreLabel);
        root.getChildren().add(pane);

    }
}
