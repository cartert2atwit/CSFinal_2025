package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class Main extends Application {

    private Canvas canvas;
    private GraphicsContext gc;

    private Player player;
    private AlienGroup alienGroup;

    @Override
    public void start(Stage primaryStage) {
        try {
            //start menu scene
            Text sceneTitle = new Text("Space Invaders");
            sceneTitle.setFont(Font.font("SF Collegiate Solid Bold", FontWeight.BOLD, 100));
            sceneTitle.setFill(Color.WHITE);
            Button startButton = new Button("Start");

            VBox rootStart = new VBox(100, sceneTitle, startButton);
            rootStart.setAlignment(Pos.CENTER);
            rootStart.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            Scene startScene = new Scene(rootStart, 800, 800);

            // main scene
            Pane rootMain = new Pane();
            rootMain.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

            canvas = new Canvas(800, 800);
            gc = canvas.getGraphicsContext2D();

            player = new Player(350, 750);

            rootMain.getChildren().addAll(canvas, player);

            Scene mainScene = new Scene(rootMain, 800, 800);

            // makes an alien group
            alienGroup = new AlienGroup(4, 8, 60, 60, 70, 60);

            //Player movement
            mainScene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT) player.moveLeft();
                if (event.getCode() == KeyCode.RIGHT) player.moveRight();
            });

            /**
             * GAME LOOP
             */
            AnimationTimer gameLoop = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    gc.clearRect(0, 0, 800, 800);

                    // Update and draw alien group
                    alienGroup.update();
                    alienGroup.draw(gc);

                    // You can add bullets and collision handling here later
                }
            };
            gameLoop.start();

            //start button
            startButton.setOnAction(e -> primaryStage.setScene(mainScene));
            primaryStage.setScene(startScene);
            primaryStage.show();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
