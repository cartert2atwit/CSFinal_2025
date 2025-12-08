package application;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class AlienGroup {

    private ArrayList<Alien> aliens = new ArrayList<>();
    private double speedX = 2.0;
    private boolean movingRight = true;
    private double dropAmount = 10;

    public AlienGroup(int rows, int cols, double startX, double startY, double spacingX, double spacingY) {
        // Spawn aliens
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                aliens.add(new Alien(startX + c * spacingX, startY + r * spacingY));
            }
        }
    }

    // UPDATE POSITION
    public void update() {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;

        // finds the edge of the group
        for (Alien alien : aliens) {
            if (alien.isAlive()) {
                if (alien.getX() < minX) minX = alien.getX();
                if (alien.getX() + 40 > maxX) maxX = alien.getX() + 40;
            }
        }

        // moves down and changes direction if edge is hit
        if (movingRight && maxX >= 800 - 10) {
            movingRight = false;
            dropDown();
        } else if (!movingRight && minX <= 10) {
            movingRight = true;
            dropDown();
        }

        // Move horizontally
        double dx = movingRight ? speedX : -speedX;
        for (Alien alien : aliens) {
            if (alien.isAlive()) alien.moveX(dx);
        }
    }

    //draw method
    public void draw(GraphicsContext gc) {
        for (Alien alien : aliens) {
            if (alien.isAlive()) alien.draw(gc);
        }
    }

    //moves the aliens down
    private void dropDown() {
        for (Alien alien : aliens) {
            if (alien.isAlive()) alien.moveDown(dropAmount);
        }
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }
}