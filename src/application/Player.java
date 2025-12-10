package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {

	private double speed = 10;
	public int score = 0;

	public Player(double x, double y) {
		super(100, 30, Color.BLUE); // width, height, color

		// set initial position
		setLayoutX(x);
		setLayoutY(y);
	}

	public void moveLeft() {
		if (getLayoutX() >= 10) {
			setLayoutX(getLayoutX() - speed);
		}
	}

	public void moveRight() {
		if (getLayoutX() <= 690) {
			setLayoutX(getLayoutX() + speed);
		}
	}

	public void upScore() {
		score += 10;
	}

	public int getScore() {
		return score;
	}

}