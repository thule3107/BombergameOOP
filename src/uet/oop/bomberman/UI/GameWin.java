package uet.oop.bomberman.UI;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class GameWin {
    private ImageView background, playAgain, btnBack ;
    private static Text scoreLabel ;
    private  static  int scoreNow ;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static int getScoreNow() {
        return scoreNow;
    }

    public static void setScoreNow(int scoreNow) {
        GameWin.scoreNow = scoreNow;
    }

    public static Text getScoreLabel() {
        return scoreLabel;
    }

    public static void setScoreLabel(Text scoreLabel) {
        GameWin.scoreLabel = scoreLabel;
    }



    public  void createGameWinMenu(Group root){
        Pane pane = new Pane();
        pane.setPrefSize(Sprite.SCALED_SIZE*WIDTH, Sprite.SCALED_SIZE*HEIGHT);
        // background
        background= new ImageView("images/victory.png");
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
        scoreLabel.setY(450);
        pane.getChildren().addAll(background, scoreLabel);
        root.getChildren().add(pane);

    }
}
