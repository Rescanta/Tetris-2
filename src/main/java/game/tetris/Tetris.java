package game.tetris;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Tetris extends Application {
    // variable
    static int speed = 5;
    static int widthInCubes = 12;
    static int heightInCubes = 20;
    static int cubeSize = 25;
    static int width = widthInCubes * cubeSize;
    static int height = heightInCubes * cubeSize;
    static int cubeX = width / 2 / cubeSize * cubeSize;
    static int cubeY = 0;
    static int cubeXMax = width / cubeSize;
    static int cubeYMax = height / cubeSize;
    static boolean stuck = false;

    static boolean gameOver = false;
    static Random rand = new Random();
    static Dir direction = Dir.NONE;
//    static OBlock initblock1 = null;
//    static IBlock initblock2 = null;
//    static JBlock initblock3 = null;
//    static LBlock initblock4 = null;
//    static SBlock initblock5 = null;
//    static TBlock initblock6 = null;
    //static ZBlock block = null;
    static InterfaceBlock block;
    static Terrain terrain = null;


    public enum Dir {
        LEFT, RIGHT, NONE
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            VBox root = new VBox();
            Canvas c = new Canvas(width, height);
            GraphicsContext gc = c.getGraphicsContext2D();
            root.getChildren().add(c);

            new AnimationTimer() {
                long lastTick = 0;

                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(gc);
                        return;
                    }

                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(gc);
                    }
                }

            }.start();

            Scene scene = new Scene(root, width, height);

            // control
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.A) {
                    direction = Dir.LEFT;
                }
                if (key.getCode() == KeyCode.D) {
                    direction = Dir.RIGHT;
                }

            });

            primaryStage.setScene(scene);
            primaryStage.setTitle("Tetris");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tick
    public static void tick(GraphicsContext gc) {

        // fill
        // background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);


        if (terrain == null) {
            terrain = new Terrain(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            //terrain.getData();
        }
        if (stuck) {
            var arr = block.getArr();
            for (var a : arr) {
                int x = a.get(0);
                int y = a.get(1);
                System.out.println(x + " " + y);
                terrain.setNewCube(y, x);
            }
            block = null;
        }
        if (block == null) {
            int randomInt = (int) ((Math.random() * (7 - 1)) + 1);
            switch (randomInt) {
                case 1 -> block = new OBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
                case 2 -> block = new IBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
                case 3 -> block = new JBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
                case 4 -> block = new LBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
                case 5 -> block = new SBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
                case 6 -> block = new TBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
                case 7 -> block = new ZBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            }
            //block = new OBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            //block = new IBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            //block = new JBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
//            block = new LBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            //block = new SBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            //block = new TBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
            //block = new ZBlock(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);


        }
        block.getData();
        stuck = block.moveBlock(direction.toString(), cubeSize, cubeXMax, cubeYMax, terrain);
        gc.setFill(Color.RED);
        var arr = block.getArr();
        //terrain.setNewCube(5, 0);
        terrain.checkIfThereAreFullLines(cubeX, cubeY, cubeSize, cubeXMax, cubeYMax);
        var terr = terrain.getArr();
        //gc.fillRect(x, y, cubeSize, cubeSize);
        for (java.util.ArrayList<Integer> integers : arr) {
            gc.fillRect(integers.get(0) * cubeSize, integers.get(1) * cubeSize, cubeSize, cubeSize);
        }
        for (int i = 0; i < cubeYMax; i++) {
            for (int j = 0; j < cubeXMax; j++) {
                if (terr.get(i).get(j)) {
                    gc.fillRect(j * cubeSize, i * cubeSize, cubeSize, cubeSize);
                }
            }
        }

        direction = Dir.NONE;

    }


    public static void main(String[] args) {
        launch(args);
    }


}