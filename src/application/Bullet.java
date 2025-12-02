package application;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import java.util.*;

public class Bullet {
	private double x;
	private double y;
	private double width = 4;
	private double height = 10;
	private double speed = -100;
		public Bullet(double startX, double startY) {
			//center the bullet
			x= startX-(width/2);
			y= startY;
			
		}
		
		public void update(double deltaTime) {
			y+= speed*deltaTime;
		}
		
		public void draw(GraphicsContext gc)
		{
			gc.setFill(Color.YELLOW);
			gc.rect(x, y, width, height);
		}
		
		public Rectangle2D getBoundary() {
			return new Rectangle2D(x,y,width,height);
		}
		//Checks if the bullet is off screen
		public boolean isOffScreen() {
			return y<0;
		}
		
		


	}

