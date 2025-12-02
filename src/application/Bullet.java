package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
    private double x;
    private double y;
    private final double speed = 10.0;
    private final double width = 3.0;
    private final double height = 10.0;
    
 
    public Bullet(double x, double y) {
    
        this.x = x - (width / 2); 
        
        this.y = y - height; 
    }
    
  
    public void move() {
        this.y -= speed; 
    }

   
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED); 
        gc.fillRect(x, y, width, height);
    }


    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }
    
    public double getX() { return x; }
    public double getY() { return y; }
}


