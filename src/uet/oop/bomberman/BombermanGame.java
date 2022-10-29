package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.UI.GameOver;
import uet.oop.bomberman.UI.GameWin;
import uet.oop.bomberman.UI.Menu;
import uet.oop.bomberman.bom.Bom;
import uet.oop.bomberman.enemy.Balloon;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.Flame;
import uet.oop.bomberman.item.Speed;
import uet.oop.bomberman.sounds.Sounds;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    private static boolean back = false;
    private static boolean bomMod = false;
    private String url = "./res/levels/Level";
    private  int level = 1 ;
    private static int time = 20200;
    private  static int score = 0;
    private static boolean flameitem = false;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

     GameWin gameWin;
     GameOver gameOver;
      Menu menu;

    public static Text levelLabel, timeLabel, scoreLabel;
    public static Stage window;
    public static Scene menuScene;
    public static Scene gameScene;
    public static  Scene gameOverScene;
    public static  Scene gameWinScene;
    private static List<Entity> explosions = new ArrayList<>();

    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> fixedEntities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private static Bomber bomberman;
    private static Bom bomb;
    private Portal portal;


    public static boolean isBack() {
        return back;
    }

    public static void setBack(boolean back) {
        BombermanGame.back = back;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        BombermanGame.score = score;
    }

    public static Bomber getBomberman() {
        return bomberman;
    }

    public static void setBomberman(Bomber bomberman) {
        BombermanGame.bomberman = bomberman;
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static void setEntities(List<Entity> entities) {
        BombermanGame.entities = entities;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        BombermanGame.time = time;
    }

    public static Scene getMenuScene() {
        return menuScene;
    }

    public static Scene getGameScene() {
        return gameScene;
    }


    public static Stage getWindow() {
        return window;
    }


    public static boolean isFlameitem() {
        return flameitem;
    }

    public static void setFlameitem(boolean flameitem) {
        BombermanGame.flameitem = flameitem;
    }



    // Hàm tạo entity
    public static Entity getEntity(int x, int y) {
        for (Entity e : entities) {
            if (e.compareCoordinate(x, y)) {
                return e;
            }
        }
        if (bomb != null) {
            if (bomb.compareCoordinate(x, y)) {
                return bomb;
            }
        }
        for (Entity e : fixedEntities) {
            if (e.compareCoordinate(x, y)) {
                return e;
            }
        }

        return null;
    }

    public static Entity getEnemy(int x, int y) {
        for (Entity e : entities) {
            if (e.compareCoordinate(x, y) && !(e instanceof Bomber) && !(e instanceof Brick) && !(e instanceof Portal))
                return e;
        }
        return null;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        window = stage;
        //Game Scene
        Group root = new Group();
        // level
        levelLabel = new Text("LEVEL: " + level);
        levelLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        levelLabel.setFill(Color.WHITE);
        levelLabel.setX(Sprite.SCALED_SIZE * WIDTH / 2 - 50);
        levelLabel.setY(30);
        // level
        //  Time
        timeLabel = new Text("TIME: " + time/100);
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        timeLabel.setFill(Color.WHITE);
        timeLabel.setX(Sprite.SCALED_SIZE * WIDTH / 3 - 100);
        timeLabel.setY(30);
        // Time
        // Score
        scoreLabel = new Text("SCORE: " + score);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        scoreLabel.setFill(Color.WHITE);
        scoreLabel.setX(Sprite.SCALED_SIZE * WIDTH   - 350);
        scoreLabel.setY(30);
        // Score
        Pane pane = new Pane();
        pane.getChildren().addAll(levelLabel, timeLabel, scoreLabel);
        pane.setStyle("-fx-background-color: #353535");
        pane.setPrefSize(Sprite.SCALED_SIZE * WIDTH, 50);
        VBox v = new VBox();
        v.getChildren().add(pane);
        v.getChildren().add(canvas);
        root.getChildren().add(v);
        gameScene = new Scene(root);
        // GameScene ended

        // MenuScene start
        Group root2 = new Group();
        menu = new Menu();
        menu.createMenu(root2);
        menuScene = new Scene(root2);
        // MenuScene ended
        // gameover
        Group gameoverRoot = new Group();
        gameOver = new GameOver();
        gameOver.createGameOverMenu(gameoverRoot);
        gameOverScene = new Scene(gameoverRoot);



        // gameover
        // gameWin
        Group gamewinRoot = new Group();
        gameWin = new GameWin();
        gameWin.createGameWinMenu(gamewinRoot);
        gameWinScene = new Scene(gamewinRoot);
        // gameWin
        window.setScene(menuScene);
        window.setTitle("Bombergame design by Loc ole");
        window.getIcons().add(new Image("images/Logo.jpeg"));

        String path = "./res/sounds/music_background.wav";
        Sounds.loop(path);
        window.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        // create map1
        createMap(url+ level+".txt");

    }

    public void createMap(String url) throws IOException {

        fixedEntities.clear();
        entities.clear();
        canvas.setDisable(false);
        // read file from file.txt
        FileInputStream fileInputStream = new FileInputStream(url);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = bufferedReader.readLine();
        int rowCount = 0;
        while (line != null) {
            System.out.println(rowCount + " " + line);
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    fixedEntities.add(new Wall(i, rowCount, Sprite.wall.getFxImage()));
                } else {
                    fixedEntities.add(new Grass(i, rowCount, Sprite.grass.getFxImage()));
                }
            }
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case '*':
                        entities.add(new Brick(i, rowCount, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        portal = new Portal(i, rowCount, Sprite.portal.getFxImage());
                        entities.add(new Portal(i, rowCount, Sprite.portal.getFxImage()));
                        break;
                    case '1':
                        entities.add(new Balloon(i, rowCount, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2':
                        entities.add(new Oneal(i, rowCount, Sprite.oneal_left1.getFxImage()));
                        break;
                }
            }
            line = bufferedReader.readLine();

            rowCount++;

        }
        // read file from file.txt
        levelLabel.setText("LEVEL: " + level);
        createLevel();
    }
    // funtion update : update entity in list enitites , fixedEntities , bomb , explosion
    public void update() {
        if (bomb != null) {
            bomb.update();
        }

        if (!explosions.isEmpty()) {
            for (int i = 0; i < explosions.size(); i++) {
                Entity entity = explosions.get(i);
                entity.update();
                if (entity.isRemoved()) {
                    explosions.remove(i);
                }
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.update();
            if (entity.isRemoved()) {
                entities.remove(entity);
                if (entity instanceof Brick) {
                    if ((entity.getX() == 224 && entity.getY() == 32) || (entity.getX() == 32 && entity.getY() == 224)) {
                        entities.add(new Speed(entity.getX() / Sprite.SCALED_SIZE, entity.getY() / Sprite.SCALED_SIZE, Sprite.powerup_speed.getFxImage()));
                    } else if ((entity.getX() == 544 && entity.getY() == 256) || (entity.getX() == 560 && entity.getY() == 288)) {
                        entities.add(new Flame(entity.getX() / Sprite.SCALED_SIZE, entity.getY() / Sprite.SCALED_SIZE, Sprite.powerup_flames.getFxImage()));
                    }
                    score = score + 5;
                    scoreLabel.setText("SCORE: "+ score);
                    gameOver.getScoreLabel().setText("SCORE: "+ score);
                    gameWin.getScoreLabel().setText("SCORE: "+ score);
                }
                if(entity instanceof Balloon){
                    score = score+ 10;
                    scoreLabel.setText("SCORE: "+ score);
                    gameOver.getScoreLabel().setText("SCORE: "+ score);
                    gameWin.getScoreLabel().setText("SCORE: "+ score);
                }
                if (entity instanceof Oneal ){
                    score = score+ 20;
                    scoreLabel.setText("SCORE: "+ score);
                    gameOver.getScoreLabel().setText("SCORE: "+ score);
                    gameWin.getScoreLabel().setText("SCORE: "+ score);
                }

            }
        }
        if (bomberman.isRemoved()) {
            window.setScene(gameOverScene);
        }
        if(time==0){
            window.setScene(gameOverScene);
        }
        if(isWinGame() == true && level == 2 ){
           window.setScene(gameWinScene);
        }

        // update entities


    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        fixedEntities.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        if (bomb != null) {
            bomb.render(gc);
        }
        if (!explosions.isEmpty()) {
            explosions.forEach(g -> g.render(gc));
        }
    }

    private boolean isWinGame() {
        // extirpated full enemy && found portal
        for (Entity entity : entities) {
            if ((entity instanceof Oneal) || (entity instanceof Balloon)) {
                return false;
            }
        }
        return true;
    }
    public static void bombExplode(List<Entity> exs) {
        bomb = null;
        explosions = exs;
    }
    public void createLevel() throws IOException {
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        gameScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DOWN:
                    if (getEntity(bomberman.getX(), bomberman.getY() + 4 * bomberman.getSpeed()) instanceof Grass) {
                        bomberman.setDown(true);
                        bomberman.update();
                    }
                    if (getEntity(bomberman.getX(), bomberman.getY() + 4 * bomberman.getSpeed()) instanceof Speed) {
                        getEntity(bomberman.getX(), bomberman.getY() + 4 * bomberman.getSpeed()).remove();
                        bomberman.setDown(true);
                        bomberman.update();
                    }
                    if (getEntity(bomberman.getX(), bomberman.getY() + 4 * bomberman.getSpeed()) instanceof Flame) {
                        getEntity(bomberman.getX(), bomberman.getY() + 4 * bomberman.getSpeed()).remove();
                        bomberman.setDown(true);
                        setFlameitem(true);
                        bomberman.update();

                    }
                    if (getEnemy(bomberman.getX(), bomberman.getY() + 4 * bomberman.getSpeed()) != null) {
                        bomberman.setDown(true);
                        bomberman.update();
                        bomberman.remove();
                    }
                    if ((getEntity(bomberman.getX() , bomberman.getY() + 4 * bomberman.getSpeed()) instanceof Portal) && isWinGame() == true) {
                        bomberman.setDown(true);
                        bomberman.update();
                        level++;
                        try {
                            time = 20200;
                            createMap(url + level + ".txt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(level ==2){
                            System.out.println("Win");
                        }
                    }
                    break;
                case UP:
                    if (getEntity(bomberman.getX(), bomberman.getY() - 4 * bomberman.getSpeed()) instanceof Grass) {
                        bomberman.setUp(true);
                        bomberman.update();
                    }
                    if (getEntity(bomberman.getX() , bomberman.getY() - 4 * bomberman.getSpeed()) instanceof Speed) {
                        getEntity(bomberman.getX(), bomberman.getY() - 4 * bomberman.getSpeed()).remove();
                        bomberman.setUp(true);
                        bomberman.update();
                    }
                    if (getEntity(bomberman.getX(), bomberman.getY() - 4 * bomberman.getSpeed()) instanceof Flame) {
                        getEntity(bomberman.getX(), bomberman.getY() - 4 * bomberman.getSpeed()).remove();
                        bomberman.setUp(true);
                        setFlameitem(true);
                        bomberman.update();

                    }
                    if (getEnemy(bomberman.getX(), bomberman.getY() - 4 * bomberman.getSpeed()) != null) {
                        bomberman.setUp(true);
                        bomberman.update();
                        bomberman.remove();
                    }
                    if ((getEntity(bomberman.getX() , bomberman.getY() - 4 * bomberman.getSpeed()) instanceof Portal) && isWinGame() == true) {
                        bomberman.setUp(true);
                        bomberman.update();
                        level ++;
                        try {
                            time = 20200;
                            createMap(url+ level + ".txt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(level ==2){
                            System.out.println("Win");
                        }
                    }
                    break;
                case LEFT:
                    if (getEntity(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Grass) {
                        bomberman.setLeft(true);
                        bomberman.update();
                    }
                    if (getEntity(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Speed) {
                        getEntity(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()).remove();
                        bomberman.setLeft(true);
                        bomberman.update();
                    }
                    if (getEntity(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Flame) {
                        getEntity(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()).remove();
                        bomberman.setLeft(true);
                        setFlameitem(true);
                        bomberman.update();

                    }
                    if (getEnemy(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()) != null) {
                        bomberman.setLeft(true);
                        bomberman.update();
                        bomberman.remove();
                    }
                    if ((getEntity(bomberman.getX() - 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Portal) && isWinGame() == true) {
                        bomberman.setLeft(true);
                        bomberman.update();
                        level ++;
                        try {
                            time = 20200;
                            createMap(url+ level+ ".txt");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(level ==2){
                            System.out.println("Win");
                        }
                    }
                    break;
                case RIGHT:
                    if ((getEntity(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Grass)) {
                        bomberman.setRight(true);
                        bomberman.update();
                    }

                    if ((getEntity(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Speed)) {
                        getEntity(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()).remove();
                        bomberman.setRight(true);
                        bomberman.update();
                    }
                    if ((getEntity(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Flame)) {
                        getEntity(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()).remove();
                        bomberman.setRight(true);
                        setFlameitem(true);
                        bomberman.update();
                    }
                    if (getEnemy(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()) != null) {
                        bomberman.setRight(true);
                        bomberman.update();
                        bomberman.remove();

                    }
                    if ((getEntity(bomberman.getX() + 4 * bomberman.getSpeed(), bomberman.getY()) instanceof Portal) && isWinGame() == true) {
                            bomberman.setRight(true);
                            bomberman.update();
                            level++;
                            try {
                                time = 20200;
                                createMap(url+ level + ".txt");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                    }


                    break;
                case SPACE:
                    System.out.println("bom");
                    bomb = new Bom(bomberman.getX() / Sprite.SCALED_SIZE,
                            bomberman.getY() / Sprite.SCALED_SIZE,
                            Sprite.bomb.getFxImage());

                default:
                    break;
            }
        });

    }
}
