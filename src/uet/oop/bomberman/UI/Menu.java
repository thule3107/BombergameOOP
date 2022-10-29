package uet.oop.bomberman.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;




public class Menu {
    private Group rootHelp ;
    private  Scene helpScene ;
    private Help helpMenu= new Help();
    private ImageView imgBackground;
    private ImageView btnStart , btnHelp, btnExit;
    private  Text levelLabel;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public void createMenu(Group root){
        //stackpane Background
        BombermanGame.setBack(false);
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(Sprite.SCALED_SIZE*WIDTH,Sprite.SCALED_SIZE*HEIGHT);
      Image img = new Image("images/background.jpg");
      imgBackground = new ImageView(img);
   imgBackground.setFitWidth(Sprite.SCALED_SIZE*WIDTH);
   imgBackground.setFitHeight(Sprite.SCALED_SIZE*HEIGHT);
   stackPane.getChildren().add(imgBackground);
   // stackpane Background
   Pane pane = new Pane();
     pane.setPrefSize(Sprite.SCALED_SIZE*WIDTH, Sprite.SCALED_SIZE*HEIGHT);
    btnStart = new ImageView(new Image("images/startButton1.png"));
    btnHelp= new ImageView(new Image("images/help1.png"));
    btnExit = new ImageView(new Image("images/exitButton1.png"));

   pane.getChildren().addAll(btnStart,btnHelp, btnExit);
   btnStart.setX(600);
   btnStart.setY(110);
        btnHelp.setX(600);
        btnHelp.setY(210);
        btnExit.setX(600);
        btnExit.setY(310);
//    vBox.setPrefSize(200,100);
   stackPane.getChildren().add(pane);
      rootHelp = new Group();
        helpMenu.createHelp(rootHelp);
         helpScene = new Scene(rootHelp);
   btnStart.setOnMouseClicked(e -> {
       System.out.println(" start clicked");
       AnimationTimer timer = new AnimationTimer() {
           @Override
           public void handle(long now) {
               BombermanGame.setTime(BombermanGame.getTime()-1); ;
               if(BombermanGame.getTime()%10==0) {
                   BombermanGame.timeLabel.setText(" TIME: " + (BombermanGame.getTime()/100-1));
               }
           }
       };
       timer.start();
       BombermanGame.getWindow().setScene(BombermanGame.getGameScene());
   });
        btnHelp.setOnMouseClicked(e -> {
            System.out.println("help clicked");
            BombermanGame.getWindow().setScene(helpScene);

        });
        btnExit.setOnMouseClicked(e -> {
            System.out.println("exit clicked");
            BombermanGame.getWindow().close();

        });

//   stackPane.setAlignment(vBox, Pos.CENTER);
   root.getChildren().add(stackPane);





    }
}
