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
import java.util.Iterator;
import java.util.ArrayList;

public class Main extends Application {

	private Canvas canvas;
	private GraphicsContext gc;
	// values for making bullet cooldown
	private long lastShot = 0;
	private final long cooldown = 500_000_000;
	private boolean shooting = false;

	private Player player;
	private AlienGroup alienGroup;
	private ArrayList<Bullet> bullets = new ArrayList<>();

	@Override
	public void start(Stage primaryStage) {
		try {
			// starting scene
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
			Text scoreText = new Text("Score: 0");
			scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			scoreText.setFill(Color.WHITE);


			scoreText.setLayoutX(650);
			scoreText.setLayoutY(30);

			rootMain.getChildren().add(scoreText);
			Scene mainScene = new Scene(rootMain, 800, 800);

			
			// Alien group
			alienGroup = new AlienGroup(4, 8, 60, 60, 70, 60);

			// Player controls
			mainScene.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.LEFT)
					player.moveLeft();
				if (event.getCode() == KeyCode.RIGHT)
					player.moveRight();
				if (event.getCode() == KeyCode.SPACE)
					shooting = true;
			});

			mainScene.setOnKeyReleased(event -> {
				if (event.getCode() == KeyCode.SPACE)
					shooting = false;
			});

			// game loop
			AnimationTimer gameLoop = new AnimationTimer() {
				@Override
				public void handle(long now) {
					gc.clearRect(0, 0, 800, 800);

					if (shooting) {
						shoot(now);
					}

					// Update and draw aliens
					alienGroup.update();
					alienGroup.draw(gc);

					// makes all the bullets move up
					Iterator<Bullet> bulletIter = bullets.iterator();
					while (bulletIter.hasNext()) {
						Bullet bullet = bulletIter.next();
						bullet.move();
						bullet.draw(gc);

						if (bullet.getY() < 0) {
							bulletIter.remove();
							continue;
						}

						// checks if aliens collide with bullet
						for (Alien alien : alienGroup.getAliens()) {
							if (alien.isAlive() && alien.isHit(bullet)) {
								bulletIter.remove();
								player.upScore();
								scoreText.setText("Score: " + player.getScore());
								break;
							}
						}
					}
				}
			};

			gameLoop.start();

			// start button
			startButton.setOnAction(e -> primaryStage.setScene(mainScene));

			primaryStage.setScene(startScene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method for shooting bullets
	private void shoot(long now) {
		if (now - lastShot < cooldown)
			return;

		bullets.add(Bullet.shootFrom(player));
		lastShot = now;
	}

	public static void main(String[] args) {
		launch(args);
	}
}